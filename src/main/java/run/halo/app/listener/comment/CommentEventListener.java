package run.halo.app.listener.comment;

import cn.hutool.core.lang.Validator;
import cn.hutool.core.text.StrBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import run.halo.app.event.comment.CommentNewEvent;
import run.halo.app.event.comment.CommentReplyEvent;
import run.halo.app.exception.ServiceException;
import run.halo.app.mail.MailService;
import run.halo.app.model.dto.post.BasePostMinimalDTO;
import run.halo.app.model.entity.*;
import run.halo.app.model.properties.CommentProperties;
import run.halo.app.service.*;

import java.util.HashMap;
import java.util.Map;

/**
 * PostComment event listener.
 *
 * @author ssatwa
 * @author ssatwa
 * @date 2019-04-23
 */
@Slf4j
@Component
public class CommentEventListener {

	private final MailService mailService;

	private final OptionService optionService;

	private final PostCommentService postCommentService;

	private final SheetCommentService sheetCommentService;

	private final JournalCommentService journalCommentService;

	private final PostService postService;

	private final SheetService sheetService;

	private final JournalService journalService;

	private final UserService userService;

	private final ThemeService themeService;

	public CommentEventListener(MailService mailService, OptionService optionService,
			PostCommentService postCommentService, SheetCommentService sheetCommentService,
			JournalCommentService journalCommentService, PostService postService, SheetService sheetService,
			JournalService journalService, UserService userService, ThemeService themeService) {
		this.mailService = mailService;
		this.optionService = optionService;
		this.postCommentService = postCommentService;
		this.sheetCommentService = sheetCommentService;
		this.journalCommentService = journalCommentService;
		this.postService = postService;
		this.sheetService = sheetService;
		this.journalService = journalService;
		this.userService = userService;
		this.themeService = themeService;
	}

	/**
	 * Received a new new comment event.
	 *
	 * @param newEvent new comment event.
	 */
	@Async
	@EventListener
	public void handleCommentNewEvent(CommentNewEvent newEvent) {
		Boolean newCommentNotice = optionService.getByPropertyOrDefault(CommentProperties.NEW_NOTICE, Boolean.class,
				false);

		if (!newCommentNotice) {
			// Skip mailing
			return;
		}

		User user = userService.getCurrentUser().orElseThrow(() -> new ServiceException("未查询到博主信息"));

		Map<String, Object> data = new HashMap<>();

		StringBuilder subject = new StringBuilder();

		Boolean enabledAbsolutePath = optionService.isEnabledAbsolutePath();

		if (newEvent.getSource() instanceof PostCommentService) {
			// Get postComment id
			PostComment postComment = postCommentService.getById(newEvent.getCommentId());

			log.debug("Got post comment: [{}]", postComment);

			BasePostMinimalDTO post = postService.convertToMinimal(postService.getById(postComment.getPostId()));

			data.put("pageFullPath",
					enabledAbsolutePath ? post.getFullPath() : optionService.getBlogBaseUrl() + post.getFullPath());
			data.put("pageTitle", post.getTitle());
			data.put("author", postComment.getAuthor());
			data.put("content", postComment.getContent());

			subject.append("Your blog post").append(post.getTitle()).append("》With new comments。");

		} else if (newEvent.getSource() instanceof SheetCommentService) {
			SheetComment sheetComment = sheetCommentService.getById(newEvent.getCommentId());

			log.debug("Got sheet comment: [{}]", sheetComment);

			BasePostMinimalDTO sheet = sheetService.convertToMinimal(sheetService.getById(sheetComment.getPostId()));

			data.put("pageFullPath",
					enabledAbsolutePath ? sheet.getFullPath() : optionService.getBlogBaseUrl() + sheet.getFullPath());
			data.put("pageTitle", sheet.getTitle());
			data.put("author", sheetComment.getAuthor());
			data.put("content", sheetComment.getContent());

			subject.append("Your blog page《").append(sheet.getTitle()).append("》With new comments。");
		} else if (newEvent.getSource() instanceof JournalCommentService) {
			JournalComment journalComment = journalCommentService.getById(newEvent.getCommentId());

			log.debug("Got journal comment: [{}]", journalComment);

			Journal journal = journalService.getById(journalComment.getPostId());

			StrBuilder url = new StrBuilder(optionService.getBlogBaseUrl()).append("/")
					.append(optionService.getJournalsPrefix());
			data.put("pageFullPath", url.toString());
			data.put("pageTitle", journal.getCreateTime());
			data.put("author", journalComment.getAuthor());
			data.put("content", journalComment.getContent());

			subject.append("Your blog post has a new comment");
		}

		String template = "common/mail_template/mail_notice.ftl";

		if (themeService.templateExists("mail_template/mail_notice.ftl")) {
			template = themeService.renderWithSuffix("mail_template/mail_notice");
		}

		mailService.sendTemplateMail(user.getEmail(), subject.toString(), data, template);
	}

	/**
	 * Received a new reply comment event.
	 *
	 * @param replyEvent reply comment event.
	 */
	@Async
	@EventListener
	public void handleCommentReplyEvent(CommentReplyEvent replyEvent) {
		Boolean replyCommentNotice = optionService.getByPropertyOrDefault(CommentProperties.REPLY_NOTICE, Boolean.class,
				false);

		if (!replyCommentNotice) {
			// Skip mailing
			return;
		}

		String baseAuthorEmail = "";

		String blogTitle = optionService.getBlogTitle();

		Map<String, Object> data = new HashMap<>();

		StringBuilder subject = new StringBuilder();

		Boolean enabledAbsolutePath = optionService.isEnabledAbsolutePath();

		log.debug("replyEvent.getSource():" + replyEvent.getSource().toString());

		if (replyEvent.getSource() instanceof PostCommentService) {

			PostComment postComment = postCommentService.getById(replyEvent.getCommentId());

			PostComment baseComment = postCommentService.getById(postComment.getParentId());

			if (StringUtils.isEmpty(baseComment.getEmail()) && !Validator.isEmail(baseComment.getEmail())) {
				return;
			}

			if (!baseComment.getAllowNotification()) {
				return;
			}

			baseAuthorEmail = baseComment.getEmail();

			BasePostMinimalDTO post = postService.convertToMinimal(postService.getById(postComment.getPostId()));

			data.put("pageFullPath",
					enabledAbsolutePath ? post.getFullPath() : optionService.getBlogBaseUrl() + post.getFullPath());
			data.put("pageTitle", post.getTitle());
			data.put("baseAuthor", baseComment.getAuthor());
			data.put("baseContent", baseComment.getContent());
			data.put("replyAuthor", postComment.getAuthor());
			data.put("replyContent", postComment.getContent());

			subject.append("You are in【").append(blogTitle).append("】Commented article ").append(post.getTitle())
					.append("has a new comment.");
		} else if (replyEvent.getSource() instanceof SheetCommentService) {

			SheetComment sheetComment = sheetCommentService.getById(replyEvent.getCommentId());

			SheetComment baseComment = sheetCommentService.getById(sheetComment.getParentId());

			if (StringUtils.isEmpty(baseComment.getEmail()) && !Validator.isEmail(baseComment.getEmail())) {
				return;
			}

			if (!baseComment.getAllowNotification()) {
				return;
			}

			baseAuthorEmail = baseComment.getEmail();

			BasePostMinimalDTO sheet = sheetService.convertToMinimal(sheetService.getById(sheetComment.getPostId()));

			data.put("pageFullPath",
					enabledAbsolutePath ? sheet.getFullPath() : optionService.getBlogBaseUrl() + sheet.getFullPath());
			data.put("pageTitle", sheet.getTitle());
			data.put("baseAuthor", baseComment.getAuthor());
			data.put("baseContent", baseComment.getContent());
			data.put("replyAuthor", sheetComment.getAuthor());
			data.put("replyContent", sheetComment.getContent());

			subject.append("You are【").append(blogTitle).append("】Comment page《").append(sheet.getTitle()).append("》With new comments。");
		} else if (replyEvent.getSource() instanceof JournalCommentService) {
			JournalComment journalComment = journalCommentService.getById(replyEvent.getCommentId());

			JournalComment baseComment = journalCommentService.getById(journalComment.getParentId());

			if (StringUtils.isEmpty(baseComment.getEmail()) && !Validator.isEmail(baseComment.getEmail())) {
				return;
			}

			if (!baseComment.getAllowNotification()) {
				return;
			}

			baseAuthorEmail = baseComment.getEmail();

			Journal journal = journalService.getById(journalComment.getPostId());

			StrBuilder url = new StrBuilder(optionService.getBlogBaseUrl()).append("/")
					.append(optionService.getJournalsPrefix());
			data.put("pageFullPath", url);
			data.put("pageTitle", journal.getContent());
			data.put("baseAuthor", baseComment.getAuthor());
			data.put("baseContent", baseComment.getContent());
			data.put("replyAuthor", journalComment.getAuthor());
			data.put("replyContent", journalComment.getContent());

			subject.append("You are in【").append(blogTitle).append("】Comment log").append("There are new comments.");
		}

		String template = "common/mail_template/mail_reply.ftl";

		if (themeService.templateExists("mail_template/mail_reply.ftl")) {
			template = themeService.renderWithSuffix("mail_template/mail_reply");
		}

		mailService.sendTemplateMail(baseAuthorEmail, subject.toString(), data, template);
	}
}
