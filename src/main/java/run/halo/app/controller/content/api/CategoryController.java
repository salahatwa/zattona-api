package run.halo.app.controller.content.api;

import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.data.domain.Sort.Direction.DESC;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import run.halo.app.model.dto.CategoryDTO;
import run.halo.app.model.entity.Category;
import run.halo.app.model.entity.Post;
import run.halo.app.model.enums.PostStatus;
import run.halo.app.model.vo.CategoryPosts;
import run.halo.app.model.vo.CategoryVO;
import run.halo.app.service.CategoryService;
import run.halo.app.service.PostCategoryService;
import run.halo.app.service.PostService;

/**
 * Content category controller.
 *
 * @author ssatwa
 * @date 2019-06-09
 */
@RestController("ApiContentCategoryController")
@RequestMapping("/api/content/categories")
public class CategoryController {

	private final CategoryService categoryService;

	private final PostCategoryService postCategoryService;

	private final PostService postService;

	public CategoryController(CategoryService categoryService, PostCategoryService postCategoryService,
			PostService postService) {
		this.categoryService = categoryService;
		this.postCategoryService = postCategoryService;
		this.postService = postService;
	}

	@GetMapping
	@ApiOperation("Lists categories")
	public List<? extends CategoryDTO> listCategories(@SortDefault(sort = "updateTime", direction = DESC) Sort sort,
			@RequestParam(name = "more", required = false, defaultValue = "false") Boolean more) {
		if (more) {
			return postCategoryService.listCategoryWithPostCountDto(sort);
		}
		return categoryService.convertTo(categoryService.listAll(sort));
	}

	@GetMapping("tree_view")
	@ApiOperation("List all categories as tree")
	public List<CategoryVO> listAsTree(@SortDefault(sort = "name", direction = ASC) Sort sort) {
		return categoryService.listAsTree(sort);
	}

	@GetMapping("{slug}/posts")
	@ApiOperation("Lists posts by category slug")
	public CategoryPosts listPostsBy(@PathVariable("slug") String slug,
			@PageableDefault(sort = { "topPriority", "updateTime" }, direction = DESC) Pageable pageable) {
		// Get category by slug
		Category category = categoryService.getBySlugOfNonNull(slug);
		Page<Post> postPage = postCategoryService.pagePostBy(category.getId(), PostStatus.PUBLISHED, pageable);

		CategoryPosts catPosts = new CategoryPosts();
		catPosts.setCategory(category);
		catPosts.setPosts(postService.convertToListVo(postPage));

		return catPosts;
	}
}
