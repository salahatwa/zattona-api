package run.halo.app.model.params;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import run.halo.app.model.dto.base.InputConverter;
import run.halo.app.model.entity.Post;
import run.halo.app.model.entity.PostMeta;
import run.halo.app.model.enums.PostEditorType;
import run.halo.app.model.enums.PostStatus;
import run.halo.app.utils.SlugUtils;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Post param.
 *
 * @author ssatwa
 * @author ssatwa
 * @author guqing
 * @date 2019-03-21
 */
@Data
public class PostParam implements InputConverter<Post> {

	@NotBlank(message = "The title of the article cannot be empty")
	@Size(max = 200, message = "The character length of the article title cannot exceed {max}")
	private String title;

	private PostStatus status = PostStatus.DRAFT;

	@Size(max = 255, message = "The character length of the article alias cannot exceed {max}")
	private String slug;

	private PostEditorType editorType;

	private String originalContent;

	private String summary;

	@Size(max = 1023, message = "The character length of the cover image link cannot exceed {max}")
	private String thumbnail;

	private Boolean disallowComment = false;

	@Size(max = 255, message = "The character length of the article password cannot exceed {max}")
	private String password;

	@Size(max = 255, message = "Length of template must not be more than {max}")
	private String template;

	@Min(value = 0, message = "Post top priority must not be less than {value}")
	private Integer topPriority = 0;

	private Date createTime;

	private String metaKeywords;

	private String metaDescription;

	private Set<Integer> tagIds;

	private Set<Integer> categoryIds;

	private Set<PostMetaParam> metas;

	@Override
	public Post convertTo() {
		slug = StringUtils.isBlank(slug) ? SlugUtils.slug(title) : SlugUtils.slug(slug);

		if (null == thumbnail) {
			thumbnail = "";
		}

		if (null == editorType) {
			editorType = PostEditorType.MARKDOWN;
		}

		return InputConverter.super.convertTo();
	}

	@Override
	public void update(Post post) {
		slug = StringUtils.isBlank(slug) ? SlugUtils.slug(title) : SlugUtils.slug(slug);

		if (null == thumbnail) {
			thumbnail = "";
		}

		if (null == editorType) {
			editorType = PostEditorType.MARKDOWN;
		}

		InputConverter.super.update(post);
	}

	public Set<PostMeta> getPostMetas() {
		Set<PostMeta> postMetaSet = new HashSet<>();
		if (CollectionUtils.isEmpty(metas)) {
			return postMetaSet;
		}

		for (PostMetaParam postMetaParam : metas) {
			PostMeta postMeta = postMetaParam.convertTo();
			postMetaSet.add(postMeta);
		}
		return postMetaSet;
	}
}
