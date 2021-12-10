package run.halo.app.model.params;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import run.halo.app.model.dto.base.InputConverter;
import run.halo.app.model.entity.Photo;

/**
 * Post param.
 *
 * @author ssatwa
 * @date 2019/04/25
 */
@Data
public class PhotoParam implements InputConverter<Photo> {

	@NotBlank(message = "Photo name cannot be empty")
	private String name;

	private String description;

	private Date takeTime;

	private String location;

	@NotBlank(message = "Photo thumbnail link address cannot be empty")
	private String thumbnail;

	@NotBlank(message = "The photo link address cannot be empty")
	private String url;

	private String team;
}
