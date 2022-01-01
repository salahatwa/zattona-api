package run.halo.app.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import run.halo.app.model.enums.MFAType;
import run.halo.app.utils.DateUtils;

import javax.persistence.*;
import java.util.Date;

/**
 * User entity
 *
 * @author ssatwa
 * @date 2019-03-12
 */
@Data
@Entity
@Table(name = "subscribers")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Subscribers extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	/**
	 * User name.
	 */
	@Column(name = "username", length = 50)
	private String username;

	/**
	 * User email.
	 */
	@Column(name = "email", length = 127,nullable = false)
	private String email;

}
