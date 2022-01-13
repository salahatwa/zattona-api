package run.halo.app.model.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Post category entity.
 *
 * @author ssatwa
 */
@Getter
@Setter
@ToString(callSuper = true)
@RequiredArgsConstructor
@Entity
@Table(name = "post_categories", indexes = { @Index(name = "post_categories_post_id", columnList = "post_id"),
		@Index(name = "post_categories_category_id", columnList = "category_id") })
public class PostCategory extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "custom-id")
	@GenericGenerator(name = "custom-id", strategy = "run.halo.app.model.entity.support.CustomIdGenerator")
	private Integer id;

	/**
	 * Category id.
	 */
	@Column(name = "category_id", nullable = false)
	private Integer categoryId;

	/**
	 * Post id.
	 */
	@Column(name = "post_id", nullable = false)
	private Integer postId;

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		PostCategory that = (PostCategory) o;

		return Objects.equals(categoryId, that.categoryId) && Objects.equals(postId, that.postId);
	}

	@Override
	public int hashCode() {
		System.out.println(Objects.hash(postId, categoryId)+":))))))))))))))))))))))))))))))))))))))))))))))))))))");
		return super.hashCode();
	}

}
