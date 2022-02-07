package run.halo.app.model.vo;

import java.util.List;
import java.util.Map;

import lombok.Data;

/**
 * Post list vo.
 *
 * @author ssatwa
 * @date 2019-03-19
 */
@Data
public class BlogConfigVO {

	private List<PostListVO> topViewPosts;
	private List<PostListVO> latestPosts;
	private Map<String, List<PostListVO>> groupedPosts;
	private List<MenuTeamVO> menuTeams;

}
