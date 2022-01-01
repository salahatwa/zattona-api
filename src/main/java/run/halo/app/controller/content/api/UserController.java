package run.halo.app.controller.content.api;

import javax.validation.Valid;
import javax.validation.constraints.Email;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import run.halo.app.mail.MailService;
import run.halo.app.model.dto.UserDTO;
import run.halo.app.model.entity.Subscribers;
import run.halo.app.service.SubscriberService;
import run.halo.app.service.UserService;

/**
 * Content user controller.
 *
 * @author ssatwa
 * @date 2019-04-03
 */
@RestController("ApiContentUserController")
@RequestMapping("/api/content/users")
public class UserController {

	private final UserService userService;
	private final SubscriberService subscriberService;
	private final MailService mailService;

	public UserController(MailService mailService, UserService userService, SubscriberService subscriberService) {
		this.userService = userService;
		this.subscriberService = subscriberService;
		this.mailService = mailService;
	}

	@GetMapping("profile")
	@ApiOperation("Gets blogger profile")
	public UserDTO getProfile() {
		return userService.getCurrentUser().map(user -> (UserDTO) new UserDTO().convertFrom(user)).get();
	}

	@GetMapping("subscribe/{email}")
	@ApiOperation("Gets blogger profile")
	public Subscribers createUser(@PathVariable @Valid @Email String email) {
		System.out.println("adding subscribers");
		// Get subscriber
		return subscriberService.getByEmail(email).map(user -> {
			return user;
		}).orElseGet(() -> {

			return subscriberService.createSubscriber(email);
		});
	}
}
