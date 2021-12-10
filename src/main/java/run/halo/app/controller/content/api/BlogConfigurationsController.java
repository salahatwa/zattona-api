package run.halo.app.controller.content.api;

import static org.springframework.data.domain.Sort.Direction.DESC;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import run.halo.app.model.entity.Post;
import run.halo.app.model.enums.PostStatus;
import run.halo.app.model.vo.BlogConfigVO;
import run.halo.app.model.vo.PostListVO;
import run.halo.app.service.OptionService;
import run.halo.app.service.PostCommentService;
import run.halo.app.service.PostService;

/**
 * Content post controller.
 *
 * @author ssatwa
 * @date 2019-04-02
 */
@RestController("ApiBlogConfigurationsController")
@RequestMapping("/api/content/configure")
public class BlogConfigurationsController {

	private final PostService postService;

	public BlogConfigurationsController(PostService postService) {
		this.postService = postService;
	}

	@GetMapping
	@ApiOperation("Lists posts")
	public Page<PostListVO> pageBy(@PageableDefault(sort = "createTime", direction = DESC) Pageable pageable) {
		Page<Post> postPage = postService.pageBy(PostStatus.PUBLISHED, pageable);
		return postService.convertToListVo(postPage);
	}

	@GetMapping("/init")
	@ApiOperation("Lists top 5 view posts and latest  5 articles")
	public BlogConfigVO init() {
		List<Post> posts = postService.topView();

		BlogConfigVO configs = new BlogConfigVO();
		configs.setTopViewPosts(postService.convertToListVo(posts));
		configs.setLatestPosts(this.pageBy(PageRequest.of(0, 5, Sort.by(Direction.DESC, "createTime"))).getContent());

		return configs;
	}

}
