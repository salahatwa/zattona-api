package run.halo.app.model.params;

import lombok.Data;
import lombok.ToString;
import run.halo.app.model.dto.base.InputConverter;
import run.halo.app.model.entity.Menu;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Menu param.
 *
 * @author ssatwa
 * @date 4/3/19
 */
@Data
@ToString
public class MenuParam implements InputConverter<Menu> {

	private Integer id;

	@NotBlank(message = "The menu name cannot be empty")
	@Size(max = 50, message = "The character length of the menu name cannot exceed {max}")
	private String name;

	@NotBlank(message = "Menu address cannot be empty")
	@Size(max = 1023, message = "The character length of the menu address cannot exceed {max}")
	private String url;

	@Min(value = 0, message = "The sort number cannot be lower than {value}")
	private Integer priority;

	@Size(max = 50, message = "Length of menu target must not be more than {max}")
	private String target;

	@Size(max = 50, message = "The character length of the menu icon cannot exceed {max}")
	private String icon;

	private Integer parentId;

	@Size(max = 255, message = "The character length of the menu group cannot exceed {max}")
	private String team;
}
