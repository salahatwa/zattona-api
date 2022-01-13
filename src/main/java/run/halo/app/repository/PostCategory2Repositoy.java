package run.halo.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import run.halo.app.model.entity.PostCategory;

@Repository
public interface PostCategory2Repositoy extends JpaRepository<PostCategory, Integer> {

	List<PostCategory> findAllByPostId(Integer postId);

	@Query(nativeQuery = true, value = "select * from post_categories where post_id=:postId")
	List<PostCategory> findAllByPostIdNative(@Param("postId") Integer postId);
}
