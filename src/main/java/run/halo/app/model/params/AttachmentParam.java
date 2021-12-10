package run.halo.app.model.params;

import lombok.Data;
import run.halo.app.model.dto.base.InputConverter;
import run.halo.app.model.entity.Attachment;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Attachment params.
 *
 * @author ssatwa
 * @date 2019/04/20
 */
@Data
public class AttachmentParam implements InputConverter<Attachment> {

	@NotBlank(message = "The attachment name cannot be empty")
	@Size(max = 255, message = "The character length of the attachment name cannot exceed {max}")
	private String name;

}
