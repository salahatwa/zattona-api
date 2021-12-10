package run.halo.app.model.params;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.URL;

import lombok.Data;
import run.halo.app.model.dto.base.InputConverter;
import run.halo.app.model.entity.Link;

/**
 * Link param.
 *
 * @author ssatwa
 * @date 4/3/19
 */
@Data
public class LinkParam implements InputConverter<Link> {

	@NotBlank(message = "Friendly link name cannot be empty")
	@Size(max = 255, message = "The character length of the friendship link name cannot exceed {max}")
	private String name;

	@NotBlank(message = "Friendly link address cannot be empty")
	@Size(max = 1023, message = "The character length of the friendship link address cannot exceed {max}")
	@URL(message = "Friendly link address format is incorrect")
	private String url;

	@Size(max = 1023, message = "The character length of the friendly link logo cannot exceed {max}")
	private String logo;

	@Size(max = 255, message = "The character length of the friendship link description cannot exceed {max}")
	private String description;

	@Size(max = 255, message = "The character length of the friendship link group {max}")
	private String team;

	@Min(value = 0, message = "The sort number cannot be lower than {value}")
	private Integer priority;
}
