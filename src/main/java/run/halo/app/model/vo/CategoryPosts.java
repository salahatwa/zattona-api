package run.halo.app.model.vo;

import org.springframework.data.domain.Page;

import lombok.Data;
import lombok.ToString;
import run.halo.app.model.entity.Category;

@Data
@ToString(callSuper = true)
public class CategoryPosts {
	Category category;
	Page<PostListVO> posts;
}
