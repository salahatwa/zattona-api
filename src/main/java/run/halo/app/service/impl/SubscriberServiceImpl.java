package run.halo.app.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import run.halo.app.model.entity.Subscribers;
import run.halo.app.model.entity.User;
import run.halo.app.repository.SubscriberRepository;
import run.halo.app.service.SubscriberService;
import run.halo.app.service.base.AbstractCrudService;

/**
 * UserService implementation class.
 *
 * @author ssatwa
 * @date 2019-03-14
 */
@Service
public class SubscriberServiceImpl extends AbstractCrudService<Subscribers, Integer> implements SubscriberService {

	private final SubscriberRepository subscriberRepository;

	public SubscriberServiceImpl(SubscriberRepository userRepository) {
		super(userRepository);
		this.subscriberRepository = userRepository;
	}

	@Override
	public Optional<Subscribers> getByEmail(String email) {
		return subscriberRepository.findByEmail(email);
	}

	@Override
	public Subscribers createSubscriber(String email) {
		Subscribers sub=new Subscribers();
		sub.setEmail(email);
		return subscriberRepository.save(sub);
	}

}
