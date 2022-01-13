package run.halo.app.service.impl;

import static run.halo.app.model.support.HaloConst.URL_SEPARATOR;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import run.halo.app.exception.NotFoundException;
import run.halo.app.model.dto.CategoryWithPostCountDTO;
import run.halo.app.model.entity.Category;
import run.halo.app.model.entity.Post;
import run.halo.app.model.entity.PostCategory;
import run.halo.app.model.enums.PostStatus;
import run.halo.app.model.projection.CategoryPostCountProjection;
import run.halo.app.repository.CategoryRepository;
import run.halo.app.repository.PostCategory2Repositoy;
import run.halo.app.repository.PostCategoryRepository;
import run.halo.app.repository.PostRepository;
import run.halo.app.service.OptionService;
import run.halo.app.service.PostCategoryService;
import run.halo.app.service.base.AbstractCrudService;
import run.halo.app.utils.ServiceUtils;

/**
 * Post category service implementation.
 *
 * @author ssatwa
 * @date 2019-03-19
 */
@Service
public class PostCategoryServiceImpl extends AbstractCrudService<PostCategory, Integer> implements PostCategoryService {

	private final PostCategoryRepository postCategoryRepository;

	private final PostRepository postRepository;

	private final CategoryRepository categoryRepository;

	private final OptionService optionService;

	private final PostCategory2Repositoy postCategory2Repositoy;

	@PersistenceContext
	private EntityManager entityManager;

	public PostCategoryServiceImpl(PostCategoryRepository postCategoryRepository, PostRepository postRepository,
			CategoryRepository categoryRepository, OptionService optionService,
			PostCategory2Repositoy postCategory2Repositoy) {
		super(postCategoryRepository);
		this.postCategoryRepository = postCategoryRepository;
		this.postRepository = postRepository;
		this.categoryRepository = categoryRepository;
		this.optionService = optionService;
		this.postCategory2Repositoy = postCategory2Repositoy;
	}

	@Override
	public List<Category> listCategoriesBy(Integer postId) {
		Assert.notNull(postId, "Post id must not be null");

		// Find all category ids
		Set<Integer> categoryIds = postCategoryRepository.findAllCategoryIdsByPostId(postId);

		return categoryRepository.findAllById(categoryIds);
	}

	@Override
	public Map<Integer, List<Category>> listCategoryListMap(Collection<Integer> postIds) {
		if (CollectionUtils.isEmpty(postIds)) {
			return Collections.emptyMap();
		}

		// Find all post categories
		List<PostCategory> postCategories = postCategoryRepository.findAllByPostIdIn(postIds);

		// Fetch category ids
		Set<Integer> categoryIds = ServiceUtils.fetchProperty(postCategories, PostCategory::getCategoryId);

		// Find all categories
		List<Category> categories = categoryRepository.findAllById(categoryIds);

		// Convert to category map
		Map<Integer, Category> categoryMap = ServiceUtils.convertToMap(categories, Category::getId);

		// Create category list map
		Map<Integer, List<Category>> categoryListMap = new HashMap<>();

		// Foreach and collect
		postCategories.forEach(
				postCategory -> categoryListMap.computeIfAbsent(postCategory.getPostId(), postId -> new LinkedList<>())
						.add(categoryMap.get(postCategory.getCategoryId())));

		return categoryListMap;
	}

	@Override
	public List<Post> listPostBy(Integer categoryId) {
		Assert.notNull(categoryId, "Category id must not be null");

		// Find all post ids
		Set<Integer> postIds = postCategoryRepository.findAllPostIdsByCategoryId(categoryId);

		return postRepository.findAllById(postIds);
	}

	@Override
	public List<Post> listPostBy(Integer categoryId, PostStatus status) {
		Assert.notNull(categoryId, "Category id must not be null");
		Assert.notNull(status, "Post status must not be null");

		// Find all post ids
		Set<Integer> postIds = postCategoryRepository.findAllPostIdsByCategoryId(categoryId, status);

		return postRepository.findAllById(postIds);
	}

	@Override
	public List<Post> listPostBy(String slug, PostStatus status) {
		Assert.notNull(slug, "Category slug must not be null");
		Assert.notNull(status, "Post status must not be null");

		Category category = categoryRepository.getBySlug(slug).orElseThrow(
				() -> new NotFoundException("No information in this category can be found").setErrorData(slug));

		Set<Integer> postsIds = postCategoryRepository.findAllPostIdsByCategoryId(category.getId(), status);

		return postRepository.findAllById(postsIds);
	}

	@Override
	public Page<Post> pagePostBy(Integer categoryId, Pageable pageable) {
		Assert.notNull(categoryId, "Category id must not be null");
		Assert.notNull(pageable, "Page info must not be null");

		// Find all post ids
		Set<Integer> postIds = postCategoryRepository.findAllPostIdsByCategoryId(categoryId);

		return postRepository.findAllByIdIn(postIds, pageable);
	}

	@Override
	public Page<Post> pagePostBy(Integer categoryId, PostStatus status, Pageable pageable) {
		Assert.notNull(categoryId, "Category id must not be null");
		Assert.notNull(status, "Post status must not be null");
		Assert.notNull(pageable, "Page info must not be null");

		// Find all post ids
		Set<Integer> postIds = postCategoryRepository.findAllPostIdsByCategoryId(categoryId, status);

		return postRepository.findAllByIdIn(postIds, pageable);
	}

	@Override
	public List<PostCategory> mergeOrCreateByIfAbsent(Integer postId, Set<Integer> categoryIds) {
		Assert.notNull(postId, "Post id must not be null");

		if (CollectionUtils.isEmpty(categoryIds)) {
			return Collections.emptyList();
		}

		// Build post categories
		List<PostCategory> postCategoriesStaging = categoryIds.stream().map(categoryId -> {
			PostCategory postCategory = new PostCategory();
			postCategory.setPostId(postId);
			postCategory.setCategoryId(categoryId);
			return postCategory;
		}).collect(Collectors.toList());

		List<PostCategory> postCategoriesToCreate = new LinkedList<>();
		List<PostCategory> postCategoriesToRemove = new LinkedList<>();

		// Find all exist post categories
		System.out.println("Categories:" + categoryIds + ":POST ID:" + postId);
		List<PostCategory> postCategories = postCategoryRepository.findAllByPostId(postId);

		System.out.println("==============PostCategories==================");
		System.out.println(postCategories.size());

		postCategories.forEach(postCategory -> {
			if (!postCategoriesStaging.contains(postCategory)) {
				postCategoriesToRemove.add(postCategory);
			}
		});

		System.out.println("==============PostCategoriesStaging==================");
		System.out.println(postCategoriesStaging);

		postCategoriesStaging.forEach(postCategoryStaging -> {
			if (!postCategories.contains(postCategoryStaging)) {
				postCategoriesToCreate.add(postCategoryStaging);
			}
		});

		System.out.println("REMOVE===========:" + postCategoriesToRemove);
		System.out.println("CREATE===========:" + postCategoriesToCreate);
		// Remove post categories
		removeAll(postCategoriesToRemove);

		// Remove all post categories need to remove
		postCategories.removeAll(postCategoriesToRemove);

		
		System.out.println(postCategoriesToCreate.size() + "=====" + (postCategoriesToCreate.size() > 0));
		postCategories.addAll(createInBatch(postCategoriesToCreate));

		// Create them
		return postCategories;
	}

	@Override
	public List<PostCategory> listByPostId(Integer postId) {
		Assert.notNull(postId, "Post id must not be null");

		return postCategoryRepository.findAllByPostId(postId);
	}

	@Override
	public List<PostCategory> listByCategoryId(Integer categoryId) {
		Assert.notNull(categoryId, "Category id must not be null");

		return postCategoryRepository.findAllByCategoryId(categoryId);
	}

	@Override
	public Set<Integer> listCategoryIdsByPostId(Integer postId) {
		Assert.notNull(postId, "Post id must not be null");

		return postCategoryRepository.findAllCategoryIdsByPostId(postId);
	}

	@Override
	public List<PostCategory> removeByPostId(Integer postId) {
		Assert.notNull(postId, "Post id must not be null");

		return postCategoryRepository.deleteByPostId(postId);
	}

	@Override
	public List<PostCategory> removeByCategoryId(Integer categoryId) {
		Assert.notNull(categoryId, "Category id must not be null");

		return postCategoryRepository.deleteByCategoryId(categoryId);
	}

	@Override
	public List<CategoryWithPostCountDTO> listCategoryWithPostCountDto(Sort sort) {
		Assert.notNull(sort, "Sort info must not be null");

		List<Category> categories = categoryRepository.findAll(sort);

		// Query category post count
		Map<Integer, Long> categoryPostCountMap = ServiceUtils.convertToMap(postCategoryRepository.findPostCount(),
				CategoryPostCountProjection::getCategoryId, CategoryPostCountProjection::getPostCount);

		// Convert and return
		return categories.stream().map(category -> {
			// Create category post count dto
			CategoryWithPostCountDTO categoryWithPostCountDTO = new CategoryWithPostCountDTO().convertFrom(category);
			// Set post count
			categoryWithPostCountDTO.setPostCount(categoryPostCountMap.getOrDefault(category.getId(), 0L));

			StringBuilder fullPath = new StringBuilder();

			if (optionService.isEnabledAbsolutePath()) {
				fullPath.append(optionService.getBlogBaseUrl());
			}

			fullPath.append(URL_SEPARATOR).append(optionService.getCategoriesPrefix()).append(URL_SEPARATOR)
					.append(category.getSlug()).append(optionService.getPathSuffix());

			categoryWithPostCountDTO.setFullPath(fullPath.toString());

			return categoryWithPostCountDTO;
		}).collect(Collectors.toList());
	}

	@Override
	public List<PostCategory> getPostCat(Integer postId) {
		Assert.notNull(postId, "Post id must not be null");

		System.out.println("POST ID:" + postId);
		List<PostCategory> postCategories = postCategory2Repositoy.findAllByPostId(postId.intValue());

		List<PostCategory> postCategories2 = postCategory2Repositoy.findAllByPostIdNative(postId.intValue());

		List<PostCategory> postCategories3 = postCategoryRepository.findAllByPostId(postId.intValue());

		List<PostCategory> postCategories4 = postCategoryRepository.findAllByPostIdJPA(postId.intValue());

		List<PostCategory> postCategories5 = postCategoryRepository.findAllByPostIdJPA(postId);

		System.out.println("==============postCategories5==================");
		System.out.println(postCategories5.size());

		System.out.println("==============postCategories4==================");
		System.out.println(postCategories4.size());

		System.out.println("==============postCategories3==================");
		System.out.println(postCategories3.size());
		System.out.println("==============postCategories2==================");
		System.out.println(postCategories2.size());
		System.out.println("==============postCategories==================");
		System.out.println(postCategories.size());

		javax.persistence.Query query = entityManager
				.createNativeQuery("SELECT setval(pg_get_serial_sequence('post_categories', 'id'), coalesce(max(id)+1, 1), false) FROM post_categories;");
//		query.setParameter(1, postId);

		int rows=query.executeUpdate();
		System.out.println("================================::" + rows);
		
		List<PostCategory> list = query.getResultList();
		

		System.out.println(list);

		// Create them
		return postCategories;
	}

}
