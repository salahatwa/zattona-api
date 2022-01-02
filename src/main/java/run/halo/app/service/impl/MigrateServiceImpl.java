package run.halo.app.service.impl;

import java.io.InputStream;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;
import run.halo.app.handler.migrate.MigrateHandlers;
import run.halo.app.model.enums.MigrateType;
import run.halo.app.service.MigrateService;

/**
 * Migrate service implementation.
 *
 * @author ssatwa
 * @date 2019-10-29
 */
@Service
public class MigrateServiceImpl implements MigrateService {

	private final MigrateHandlers migrateHandlers;

	public MigrateServiceImpl(MigrateHandlers migrateHandlers) {
		this.migrateHandlers = migrateHandlers;
	}

	@Override
	public void migrate(MultipartFile file, MigrateType migrateType) {
		Assert.notNull(file, "Multipart file must not be null");
		Assert.notNull(migrateType, "Migrate type must not be null");

		migrateHandlers.upload(file, migrateType);
	}

	@Override
	public void migrate(InputStream inputStream, MigrateType migrateType) {
		Assert.notNull(inputStream, "inputStream file must not be null");
		Assert.notNull(migrateType, "Migrate type must not be null");

		migrateHandlers.upload(inputStream, migrateType);

	}
}
