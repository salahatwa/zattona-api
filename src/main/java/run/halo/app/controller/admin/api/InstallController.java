package run.halo.app.controller.admin.api;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hutool.crypto.SecureUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import run.halo.app.cache.lock.CacheLock;
import run.halo.app.event.logger.LogEvent;
import run.halo.app.exception.BadRequestException;
import run.halo.app.model.entity.Category;
import run.halo.app.model.entity.PostComment;
import run.halo.app.model.entity.User;
import run.halo.app.model.enums.LogType;
import run.halo.app.model.enums.PostStatus;
import run.halo.app.model.params.CategoryParam;
import run.halo.app.model.params.InstallParam;
import run.halo.app.model.params.MenuParam;
import run.halo.app.model.params.PostParam;
import run.halo.app.model.params.SheetParam;
import run.halo.app.model.properties.BlogProperties;
import run.halo.app.model.properties.OtherProperties;
import run.halo.app.model.properties.PrimaryProperties;
import run.halo.app.model.properties.PropertyEnum;
import run.halo.app.model.support.BaseResponse;
import run.halo.app.model.support.CreateCheck;
import run.halo.app.model.support.HaloConst;
import run.halo.app.model.vo.PostDetailVO;
import run.halo.app.service.CategoryService;
import run.halo.app.service.MenuService;
import run.halo.app.service.OptionService;
import run.halo.app.service.PostCommentService;
import run.halo.app.service.PostService;
import run.halo.app.service.SheetService;
import run.halo.app.service.UserService;
import run.halo.app.utils.ValidationUtils;

/**
 * Installation controller.
 *
 * @author ssatwa
 * @date 2019-03-17
 */
@Slf4j
@Controller
@RequestMapping("/api/admin/installations")
public class InstallController {

	private final UserService userService;

	private final CategoryService categoryService;

	private final PostService postService;

	private final SheetService sheetService;

	private final PostCommentService postCommentService;

	private final OptionService optionService;

	private final MenuService menuService;

	private final ApplicationEventPublisher eventPublisher;

	public InstallController(UserService userService, CategoryService categoryService, PostService postService,
			SheetService sheetService, PostCommentService postCommentService, OptionService optionService,
			MenuService menuService, ApplicationEventPublisher eventPublisher) {
		this.userService = userService;
		this.categoryService = categoryService;
		this.postService = postService;
		this.sheetService = sheetService;
		this.postCommentService = postCommentService;
		this.optionService = optionService;
		this.menuService = menuService;
		this.eventPublisher = eventPublisher;
	}

	@PostMapping
	@ResponseBody
	@CacheLock
	@ApiOperation("Initializes the blog")
	public BaseResponse<String> installBlog(@RequestBody InstallParam installParam) {
		// Validate manually
		ValidationUtils.validate(installParam, CreateCheck.class);

		// Check is installed
		boolean isInstalled = optionService.getByPropertyOrDefault(PrimaryProperties.IS_INSTALLED, Boolean.class,
				false);

		
		if (isInstalled) {
			throw new BadRequestException("This blog has been initialized and cannot be installed again！");
		}

		// Initialize settings
		initSettings(installParam);

		// Create default user
		User user = createUser(installParam);

		// Create default category
		Category category = createDefaultCategoryIfAbsent();

		// Create default post
		PostDetailVO post = createDefaultPostIfAbsent(category);

		// Create default sheet
		createDefaultSheet();

		// Create default postComment
		createDefaultComment(post);

		// Create default menu
		createDefaultMenu();

		eventPublisher.publishEvent(new LogEvent(this, user.getId().toString(), LogType.BLOG_INITIALIZED,
				"Blog has been successfully initialized"));

		return BaseResponse.ok("The installation is complete！");
	}

	private void createDefaultMenu() {
		long menuCount = menuService.count();

		if (menuCount > 0) {
			return;
		}

		MenuParam menuIndex = new MenuParam();

		menuIndex.setName("Home");
		menuIndex.setUrl("/");
		menuIndex.setPriority(1);

		menuService.create(menuIndex.convertTo());

		MenuParam menuArchive = new MenuParam();

		menuArchive.setName("Archive");
		menuArchive.setUrl("/archives");
		menuArchive.setPriority(2);
		menuService.create(menuArchive.convertTo());

		MenuParam menuCategory = new MenuParam();
		menuCategory.setName("Category");
		menuCategory.setUrl("/categories/default");
		menuCategory.setPriority(3);
		menuService.create(menuCategory.convertTo());

		MenuParam menuSheet = new MenuParam();
		menuSheet.setName("About");
		menuSheet.setUrl("/s/about");
		menuSheet.setPriority(4);
		menuService.create(menuSheet.convertTo());
	}

	@Nullable
	private void createDefaultComment(@Nullable PostDetailVO post) {
		if (post == null) {
			return;
		}

		long commentCount = postCommentService.count();

		if (commentCount > 0) {
			return;
		}

		PostComment comment = new PostComment();
		comment.setAuthor("Genhub");
		comment.setAuthorUrl("http://genhub.online");
		comment.setContent(
				"Welcome to Genhub. This is your first comment. The profile picture is from [Gravatar](https://cn.gravatar.com), you can also register by [Gravatar](https://cn.gravatar.com) Show your own avatar.");
		comment.setEmail("hi@genhub.com");
		comment.setPostId(post.getId());
		postCommentService.create(comment);
	}

	@Nullable
	private PostDetailVO createDefaultPostIfAbsent(@Nullable Category category) {

		long publishedCount = postService.countByStatus(PostStatus.PUBLISHED);

		if (publishedCount > 0) {
			return null;
		}

		PostParam postParam = new PostParam();
		postParam.setSlug("hello-genhub");
		postParam.setTitle("Hello genhub");
		postParam.setStatus(PostStatus.PUBLISHED);
		postParam.setOriginalContent("## Hello genhub\n" + "\n"
				+ "If you read this article, it proves that you have successfully installed it. Thank you for creating with [Genhub](https://halo.run). I hope you can enjoy it. \\n"
				+ "\n" + "## Related links" + "\n");

		Set<Integer> categoryIds = new HashSet<>();
		if (category != null) {
			categoryIds.add(category.getId());
			postParam.setCategoryIds(categoryIds);
		}
		return postService.createBy(postParam.convertTo(), Collections.emptySet(), categoryIds, false);
	}

	@Nullable
	private void createDefaultSheet() {
		long publishedCount = sheetService.countByStatus(PostStatus.PUBLISHED);
		if (publishedCount > 0) {
			return;
		}

		SheetParam sheetParam = new SheetParam();
		sheetParam.setSlug("about");
		sheetParam.setTitle("About page");
		sheetParam.setStatus(PostStatus.PUBLISHED);
		sheetParam.setOriginalContent(
				"## About the page\\n\" + \"\\n\" + \"This is a custom page, you can find it in the backstage `page` -> `all pages` -> `custom page`, you can use it to create a new page About pages, message board pages, etc. Use your own imagination! \\n"
						+ "\n" + "> This is an automatically generated page, you can delete it in the background.");
		sheetService.createBy(sheetParam.convertTo(), false);
	}

	@Nullable
	private Category createDefaultCategoryIfAbsent() {
		long categoryCount = categoryService.count();
		if (categoryCount > 0) {
			return null;
		}

		CategoryParam category = new CategoryParam();
		category.setName("Category");
		category.setSlug("default");
		category.setDescription("This is your default category, if you don’t need it, just delete it.");
		ValidationUtils.validate(category);
		return categoryService.create(category.convertTo());
	}

	private User createUser(InstallParam installParam) {
		// Get user
		return userService.getCurrentUser().map(user -> {
			// Update this user
			installParam.update(user);
			// Set password manually
			userService.setPassword(user, installParam.getPassword());
			// Update user
			return userService.update(user);
		}).orElseGet(() -> {
			String gravatar = "//cn.gravatar.com/avatar/" + SecureUtil.md5(installParam.getEmail()) + "?s=256&d=mm";
			installParam.setAvatar(gravatar);
			return userService.createBy(installParam);
		});
	}

	private void initSettings(InstallParam installParam) {
		// Init default properties
		Map<PropertyEnum, String> properties = new HashMap<>(11);
		properties.put(PrimaryProperties.IS_INSTALLED, Boolean.TRUE.toString());
		properties.put(BlogProperties.BLOG_LOCALE, installParam.getLocale());
		properties.put(BlogProperties.BLOG_TITLE, installParam.getTitle());
		properties.put(BlogProperties.BLOG_URL,
				StringUtils.isBlank(installParam.getUrl()) ? optionService.getBlogBaseUrl() : installParam.getUrl());
		properties.put(PrimaryProperties.THEME, HaloConst.DEFAULT_THEME_ID);

		Long birthday = optionService.getByPropertyOrDefault(PrimaryProperties.BIRTHDAY, Long.class, 0L);

		if (birthday.equals(0L)) {
			properties.put(PrimaryProperties.BIRTHDAY, String.valueOf(System.currentTimeMillis()));
		}

		Boolean globalAbsolutePathEnabled = optionService
				.getByPropertyOrDefault(OtherProperties.GLOBAL_ABSOLUTE_PATH_ENABLED, Boolean.class, null);

		if (globalAbsolutePathEnabled == null) {
			properties.put(OtherProperties.GLOBAL_ABSOLUTE_PATH_ENABLED, Boolean.FALSE.toString());
		}

		// Create properties
		optionService.saveProperties(properties);
	}

}
