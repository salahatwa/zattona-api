package run.halo.app.controller.content.api;

import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import run.halo.app.model.dto.MenuDTO;
import run.halo.app.model.vo.MenuTeamVO;
import run.halo.app.model.vo.MenuVO;
import run.halo.app.service.MenuService;

import java.util.List;

import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.data.domain.Sort.Direction.DESC;

/**
 * Content menu controller.
 *
 * @author ssatwa
 * @date 2019-04-03
 */
@RestController("ApiContentMenuController")
@RequestMapping("/api/content/menus")
public class MenuController {

	private final MenuService menuService;

	public MenuController(MenuService menuService) {
		this.menuService = menuService;
	}

	@GetMapping
	@ApiOperation("Lists all menus")
	public List<MenuDTO> listAll(@SortDefault(sort = "priority", direction = DESC) Sort sort) {
		return menuService.listDtos(sort);
	}

	@GetMapping(value = "tree_view")
	@ApiOperation("Lists menus with tree view")
	public List<MenuVO> listMenusTree(@SortDefault(sort = "createTime", direction = DESC) Sort sort) {
		return menuService.listAsTree(sort.and(Sort.by(ASC, "priority")));
	}

	@GetMapping(value = "team/tree_view")
	@ApiOperation("Lists all menus grouped by team")
	public List<MenuTeamVO> listMenuTeam(@SortDefault(sort = "priority", direction = DESC) Sort sort) {
		return menuService.listTeamVos(sort);
	}
}
