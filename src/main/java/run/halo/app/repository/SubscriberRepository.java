package run.halo.app.repository;

import java.util.Optional;

import org.springframework.lang.NonNull;

import run.halo.app.model.entity.Subscribers;
import run.halo.app.repository.base.BaseRepository;

/**
 * Subscriber repository.
 *
 * @author ssatwa
 */
public interface SubscriberRepository extends BaseRepository<Subscribers, Integer> {

	/**
	 * Gets user by username.
	 *
	 * @param username username must not be blank
	 * @return an optional user
	 */
	@NonNull
	Optional<Subscribers> findByUsername(@NonNull String username);

	/**
	 * Gets user by email.
	 *
	 * @param email email must not be blank
	 * @return an optional user
	 */
	@NonNull
	Optional<Subscribers> findByEmail(@NonNull String email);
}
