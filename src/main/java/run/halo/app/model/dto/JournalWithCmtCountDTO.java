package run.halo.app.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Journal with comment count dto.
 *
 * @author ssatwa
 * @date 19-4-25
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class JournalWithCmtCountDTO extends JournalDTO {

    private Long commentCount;

}
