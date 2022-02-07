package run.halo.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import run.halo.app.cache.AbstractStringCacheStore;
import run.halo.app.model.entity.Post;
import run.halo.app.model.vo.BlogConfigVO;
import run.halo.app.service.MenuService;
import run.halo.app.service.PostService;

@Service
public class ZattonaConfigService {

	String CONFIG_KEY = "configs";

	@Autowired
	private AbstractStringCacheStore cacheStore;
	@Autowired
	private PostService postService;
	@Autowired
	private MenuService menuService;

	public BlogConfigVO init() {
		return cacheStore.getAny(CONFIG_KEY, BlogConfigVO.class).orElseGet(() -> {
			System.out.println("GETTING CONFIG FROM DB>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

			List<Post> posts = postService.topView();
			BlogConfigVO configs = new BlogConfigVO();
			configs.setTopViewPosts(postService.convertToListVo(posts));
			configs.setLatestPosts(postService.topPosts(PageRequest.of(0, 100, Sort.by(Direction.DESC, "createTime"))));
			configs.setMenuTeams(menuService.listTeamVos(Sort.by(Direction.DESC, "team")));

			// cache configs
			cacheStore.putAny(CONFIG_KEY, configs);
			return configs;
		});
	}

}
