package run.halo.app.model.params;

import lombok.Data;
import run.halo.app.model.dto.base.InputConverter;
import run.halo.app.utils.ReflectionUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.lang.reflect.ParameterizedType;

/**
 * Base meta param.
 *
 * @author ssatwa
 * @date 2019-08-04
 */
@Data
public abstract class BaseMetaParam<META> implements InputConverter<META> {

	@NotBlank(message = "Article id cannot be empty")
	private Integer postId;

	@NotBlank(message = "Meta key cannot be empty")
	@Size(max = 1023, message = "The character length of the Meta key cannot exceed {max}")
	private String key;

	@NotBlank(message = "Meta value cannot be empty")
	@Size(max = 1023, message = "The character length of Meta value cannot exceed {max}")
	private String value;

	@Override
	public ParameterizedType parameterizedType() {
		return ReflectionUtils.getParameterizedTypeBySuperClass(BaseMetaParam.class, this.getClass());
	}
}
