package run.halo.app.model.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import run.halo.app.model.dto.BaseCommentDTO;

import java.util.List;

/**
 * Base comment vo.
 *
 * @author ssatwa
 * @date 19-4-24
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class BaseCommentVO extends BaseCommentDTO {

    List<BaseCommentVO> children;
}
