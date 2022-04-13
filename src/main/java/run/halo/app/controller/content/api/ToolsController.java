package run.halo.app.controller.content.api;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.json.XML;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import run.halo.app.generator.entity.GTemplateGroup;
import run.halo.app.generator.entity.GeneratedCode;
import run.halo.app.generator.entity.ParamInfo;
import run.halo.app.generator.utils.MapUtil;
import run.halo.app.service.GeneratorService;

/**
 * Content user controller.
 *
 * @author ssatwa
 * @date 2019-04-03
 */
@RestController("ApiContentToolsController")
@RequestMapping("/api/content/tools")
public class ToolsController {

	private final GeneratorService generatorService;

	public ToolsController(GeneratorService generatorService) {
		this.generatorService = generatorService;
	}
	
	@GetMapping("/qr")
	public void readQR() throws Exception {
		 generatorService.readQR();
	}

	@GetMapping("/generator/template/list")
	public List<GTemplateGroup> getAllTemplates() throws Exception {
		List<GTemplateGroup> templates = generatorService.getTemplateConfig();
		return templates;
	}

	@PostMapping("/generator/generate")
	public List<GeneratedCode> generateCode(@RequestBody ParamInfo paramInfo) throws Exception {
		if (StringUtils.isEmpty(paramInfo.getInput())) {
			return null;
		}

		

		System.out.println(paramInfo.getInput());
		System.out.println(paramInfo.getSelectedTmpIds());
		// 3.generate the code by freemarker templates with parameters .
		List<GeneratedCode> result = generatorService.getResultByParams(paramInfo);
		return result;
	}
}
