package run.halo.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

/**
 * Genhub main class.
 *
 * @author ssatwa
 * @date 2017-11-14
 */
@Slf4j
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		// Customize the spring config location
		System.setProperty("spring.config.additional-location",
				"optional:file:${user.home}/.halo/,optional:file:${user.home}/halo-dev/");

		log.info("user.home={}", System.getProperty("user.home"));
		SpringApplication.run(Application.class, args);
	}

}
