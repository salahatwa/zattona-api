package run.halo.app.model.params;

import lombok.Data;
import lombok.EqualsAndHashCode;
import run.halo.app.model.support.CreateCheck;

import javax.validation.constraints.NotBlank;

/**
 * Install parameters.
 *
 * @author ssatwa
 * @date 3/19/19
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class InstallParam extends UserParam {

    /**
     * Blog locale.
     */
    private String locale = "en";

    /**
     * Blog title.
     */
    @NotBlank(message = "Blog name cannot be empty", groups = CreateCheck.class)
    private String title;

    /**
     * Blog url.
     */
    private String url;

}
