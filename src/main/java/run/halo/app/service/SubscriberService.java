package run.halo.app.service;

import java.util.Optional;

import org.springframework.lang.NonNull;

import run.halo.app.model.entity.Subscribers;
import run.halo.app.service.base.CrudService;

/**
 * SubscriberService service interface.
 *
 * @author ssatwa
 * @author ssatwa
 * @date 2019-03-14
 */
public interface SubscriberService extends CrudService<Subscribers, Integer> {

	/**
	 * Gets user by email.
	 *
	 * @param email email must not be blank
	 * @return an optional user
	 */
	@NonNull
	Optional<Subscribers> getByEmail(@NonNull String email);
	
	@NonNull
	Subscribers createSubscriber(@NonNull String email);

}
