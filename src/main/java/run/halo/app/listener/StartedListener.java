package run.halo.app.listener;

import java.io.IOException;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.Collections;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.internal.jdbc.JdbcUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ansi.AnsiColor;
import org.springframework.boot.ansi.AnsiOutput;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;
import org.springframework.util.ResourceUtils;

import lombok.extern.slf4j.Slf4j;
import run.halo.app.config.properties.HaloProperties;
import run.halo.app.model.properties.PrimaryProperties;
import run.halo.app.model.support.HaloConst;
import run.halo.app.service.OptionService;
import run.halo.app.service.ThemeService;
import run.halo.app.service.impl.ZattonaConfigService;
import run.halo.app.utils.FileUtils;

/**
 * The method executed after the application is started.
 *
 * @author ssatwa
 * @date 2018-12-05
 */
@Slf4j
@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
public class StartedListener implements ApplicationListener<ApplicationStartedEvent> {

	@Autowired
	private HaloProperties haloProperties;

	@Autowired
	private OptionService optionService;

	@Autowired
	private ThemeService themeService;

	@Autowired
	private ZattonaConfigService configService;

	@Value("${spring.datasource.url}")
	private String url;

	@Value("${spring.datasource.username}")
	private String username;

	@Value("${spring.datasource.password}")
	private String password;

	@Override
	public void onApplicationEvent(ApplicationStartedEvent event) {
		try {
			this.migrate();
		} catch (SQLException e) {
			log.error("Failed to migrate database!", e);
		}

		this.initThemes();
		this.initDirectory();
		this.initConfig();
		this.printStartInfo();
	}

	private void initConfig() {
		configService.init();
		log.info(AnsiOutput.toString(AnsiColor.BRIGHT_BLUE, "Intialized Cached Configs Successfully :)"));
	}

	private void printStartInfo() {
		String blogUrl = optionService.getBlogBaseUrl();
		log.info(AnsiOutput.toString(AnsiColor.BRIGHT_BLUE, "Genhub started at         ", blogUrl));
		log.info(AnsiOutput.toString(AnsiColor.BRIGHT_BLUE, "Genhub admin started at   ", blogUrl, "/",
				haloProperties.getAdminPath()));
		if (!haloProperties.isDocDisabled()) {
			log.info(AnsiOutput.toString(AnsiColor.BRIGHT_BLUE, "Genhub api doc was enabled at  ", blogUrl,
					"/swagger-ui.html"));
		}
		log.info(AnsiOutput.toString(AnsiColor.BRIGHT_YELLOW, "Genhub has started successfully!"));
	}

	/**
	 * Migrate database.
	 */
	private void migrate() throws SQLException {
		log.info("Starting migrate database...");

		Flyway flyway = Flyway.configure().locations("classpath:/migration").baselineVersion("1")
				.baselineOnMigrate(true).dataSource(url, username, password).load();
		flyway.repair();
		flyway.migrate();

		// Gets database connection
		Connection connection = flyway.getConfiguration().getDataSource().getConnection();

		// Gets database metadata
		DatabaseMetaData databaseMetaData = JdbcUtils.getDatabaseMetaData(connection);

		// Gets database product name
		HaloConst.DATABASE_PRODUCT_NAME = databaseMetaData.getDatabaseProductName() + " "
				+ databaseMetaData.getDatabaseProductVersion();

		// Close connection.
		connection.close();

		log.info("Migrate database succeed.");
	}

	/**
	 * Init internal themes
	 */
	private void initThemes() {
		// Whether the blog has initialized
		Boolean isInstalled = optionService.getByPropertyOrDefault(PrimaryProperties.IS_INSTALLED, Boolean.class,
				false);
		try {
			String themeClassPath = ResourceUtils.CLASSPATH_URL_PREFIX + ThemeService.THEME_FOLDER;

			URI themeUri = ResourceUtils.getURL(themeClassPath).toURI();

			log.info("Theme uri: [{}] , schema: [{}]", themeUri, themeUri.getScheme());

			Path source;
			boolean isJar = false;
			if ("jar".equalsIgnoreCase(themeUri.getScheme())) {

				// Create new file system for jar
				FileSystem fileSystem = getFileSystem(themeUri);
				source = fileSystem.getPath("/BOOT-INF/classes/" + ThemeService.THEME_FOLDER);
				isJar = true;
			} else {
				source = Paths.get(themeUri);
				isJar = false;
			}

			// Create theme folder
			Path themePath = themeService.getBasePath();

			// Fix the problem that the project cannot start after moving to a new server
			if (!haloProperties.isProductionEnv() || Files.notExists(themePath) || !isInstalled) {
				log.info("Copy theme folder from [{}] to [{}]", source, themePath);
				FileUtils.copyFolder(source, themePath, isJar);
				log.info("Done copied success!!");
			} else {
				log.info("Skipped copying theme folder due to existence of theme folder");
			}
		} catch (Exception e) {
			log.error("Initialize internal theme to user path error!", e);
		}
	}

	@NonNull
	private FileSystem getFileSystem(@NonNull URI uri) throws IOException {
		Assert.notNull(uri, "Uri must not be null");

		FileSystem fileSystem;

		try {
			fileSystem = FileSystems.getFileSystem(uri);
		} catch (FileSystemNotFoundException e) {
			fileSystem = FileSystems.newFileSystem(uri, Collections.emptyMap());
		}

		return fileSystem;
	}

	private void initDirectory() {
		Path workPath = Paths.get(haloProperties.getWorkDir());
		Path backupPath = Paths.get(haloProperties.getBackupDir());
		Path dataExportPath = Paths.get(haloProperties.getDataExportDir());

		try {
			if (Files.notExists(workPath)) {
				Files.createDirectories(workPath);
				log.info("Created work directory: [{}]", workPath);
			}

			if (Files.notExists(backupPath)) {
				Files.createDirectories(backupPath);
				log.info("Created backup directory: [{}]", backupPath);
			}

			if (Files.notExists(dataExportPath)) {
				Files.createDirectories(dataExportPath);
				log.info("Created data export directory: [{}]", dataExportPath);
			}
		} catch (IOException ie) {
			throw new RuntimeException("Failed to initialize directories", ie);
		}
	}
}
