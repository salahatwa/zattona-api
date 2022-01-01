package run.halo.app.controller.content;

import static run.halo.app.service.KitBoxService.DEVELOPMENT_TOOL;
import static run.halo.app.service.KitBoxService.NETWORK_TOOL;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;
import run.halo.app.generator.utils.IpUtils;
import run.halo.app.service.KitBoxService;
import run.halo.app.tools.IpInfoDTO;
import run.halo.app.tools.services.IpService;

@Slf4j
@Controller
@RequestMapping("/kitbox/tools")
public class KitBoxController {
	@Autowired
	private KitBoxService kitBoxService;

	@Autowired
	protected HttpServletRequest request;

	@Autowired
	private IpService ipService;

	@RequestMapping("ip")
	public ModelAndView ip(ModelAndView mv) {
		String ip = IpUtils.getIpAddress(request);
		IpInfoDTO ipInfoDTO = ipService.query(ip);
		mv.addObject("myip", ip);
		mv.addObject("ipdto", ipInfoDTO);
		mv.addObject("KitBoxMenus", kitBoxService.getKitBoxMenus());
//        mv.addObject("title", "IP地址信息查询工具 - 开发者工具箱 - " + renFeiConfig.getSiteName());
//        setHead(mv, "IP地址信息查询工具，开放服务接口实现IP信息查询，查询IP地址所属地理位置",
//                "IP,地址,信息,查询,工具,地理,位置");
		mv.setViewName("themes/bao/module/tools/ipinfo");
//        setKitBoxMenus(mv, NETWORK_TOOL);
		return mv;
	}

	@GetMapping
	public ModelAndView kitbox(ModelAndView mv) {
		mv.addObject("title", "Developer Toolbox- Genhub");
//		setHead(mv, "Free developer and webmaster toolbox gadgets, including network tools, encryption and decryption tools, testing tools, etc. If you want to do your job well, you must first sharpen your tools.", "Development, Developer, Tools, Toolbox");
		mv.addObject("KitBoxMenus", kitBoxService.getKitBoxMenus());
		mv.setViewName("themes/bao/module/tools/index");
		return mv;
	}

	@RequestMapping({ "freemarkerTest", "FtlTest" })
	public ModelAndView freemarkerTest(ModelAndView mv) {
//		setHead(mv, "FreeMarker(FTL)在线测试工具", "FreeMarker,ftl,在线,测试,工具");
		mv.addObject("title", "FreeMarker(FTL)Online testing tool-developer toolbox- Genhub");
		setKitBoxMenus(mv, DEVELOPMENT_TOOL);
		mv.setViewName("themes/bao/module/tools/freemaker");
		return mv;
	}

	private void setKitBoxMenus(ModelAndView mv, String key) {
		mv.addObject("KitBoxMenus", kitBoxService.getKitBoxMenus(key));
	}

}
