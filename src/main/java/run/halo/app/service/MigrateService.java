package run.halo.app.service;

import java.io.InputStream;

import org.springframework.lang.NonNull;
import org.springframework.web.multipart.MultipartFile;

import run.halo.app.model.enums.MigrateType;

/**
 * Migrate service interface.
 *
 * @author ssatwa
 * @date 2019-10-29
 */
public interface MigrateService {

	/**
	 * Migrate.
	 *
	 * @param file        multipart file must not be null
	 * @param migrateType migrate type
	 */
	void migrate(@NonNull MultipartFile file, @NonNull MigrateType migrateType);

	void migrate(@NonNull InputStream inputStream, @NonNull MigrateType halo);
}
