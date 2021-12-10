package run.halo.app.model.params;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
import run.halo.app.model.enums.MFAType;

/**
 * Multi-Factor Auth Param.
 *
 * @author ssatwa
 * @date 2020-3-26
 */
@Data
public class MultiFactorAuthParam {

	private MFAType mfaType = MFAType.NONE;

	private String mfaKey;

	@NotBlank(message = "MFA Code cannot be empty")
	@Size(min = 6, max = 6, message = "MFA Code should be {max} bits")
	private String authcode;

}
