package run.halo.app.controller.content.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import run.halo.app.model.vo.BlogConfigVO;
import run.halo.app.service.impl.ZattonaConfigService;

/**
 * Content post controller.
 *
 * @author ssatwa
 * @date 2019-04-02
 */
@RestController("ApiBlogConfigurationsController")
@RequestMapping("/api/content/configure")
public class BlogConfigurationsController {
	String CONFIG_KEY = "configs";

	@Autowired
	private ZattonaConfigService configService;

//	@GetMapping
//	@ApiOperation("Lists posts")
//	public Page<PostListVO> pageBy(@PageableDefault(sort = "createTime", direction = DESC) Pageable pageable) {
//		Page<Post> postPage = postService.pageBy(PostStatus.PUBLISHED, pageable);
//		return postService.convertToListVo(postPage);
//	}

	@GetMapping("/init")
	@ApiOperation("Lists top 5 view posts and latest  5 articles")
	public BlogConfigVO init() {
		return configService.init();
	}

}
