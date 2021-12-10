package run.halo.app.model.params;

import lombok.Data;
import run.halo.app.model.enums.AttachmentType;

/**
 * Attachment query params.
 *
 * @author ssatwa
 * @date 2019/04/18
 */
@Data
public class AttachmentQuery {

    /**
     * Keyword.
     */
    private String keyword;

    private String mediaType;

    private AttachmentType attachmentType;
}
