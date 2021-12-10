package run.halo.app.model.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Sheet comment.
 *
 * @author ssatwa
 * @date 19-4-24
 */
@Entity(name = "SheetComment")
@DiscriminatorValue("1")
public class SheetComment extends BaseComment {

}
