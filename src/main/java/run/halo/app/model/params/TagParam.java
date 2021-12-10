package run.halo.app.model.params;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import run.halo.app.model.dto.base.InputConverter;
import run.halo.app.model.entity.Tag;
import run.halo.app.utils.SlugUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Tag param.
 *
 * @author ssatwa
 * @author ssatwa
 * @date 2019-03-20
 */
@Data
public class TagParam implements InputConverter<Tag> {

	@NotBlank(message = "The label name cannot be empty")
	@Size(max = 255, message = "The character length of the label name cannot exceed {max}")
	private String name;

	@Size(max = 255, message = "The character length of the label alias cannot exceed {max}")
	private String slug;

	@Size(max = 1023, message = "The character length of the cover image link cannot exceed {max}")
	private String thumbnail;

	@Override
	public Tag convertTo() {

		slug = StringUtils.isBlank(slug) ? SlugUtils.slug(name) : SlugUtils.slug(slug);

		if (null == thumbnail) {
			thumbnail = "";
		}

		return InputConverter.super.convertTo();
	}

	@Override
	public void update(Tag tag) {

		slug = StringUtils.isBlank(slug) ? SlugUtils.slug(name) : SlugUtils.slug(slug);

		if (null == thumbnail) {
			thumbnail = "";
		}

		InputConverter.super.update(tag);
	}
}
