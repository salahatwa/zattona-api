package run.halo.app.controller.content;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.alibaba.fastjson.JSON;

import freemarker.template.TemplateException;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import run.halo.app.exception.ServiceException;
import run.halo.app.tools.APIResult;
import run.halo.app.tools.IpInfoDTO;
import run.halo.app.tools.StateCode;
import run.halo.app.tools.services.IpService;
import run.halo.app.tools.utils.IpUtils;

/**
 * <p>
 * Title: KitboxController
 * </p>
 * <p>
 * Description: Toolbox
 * </p>
 *
 * @author ssatwa
 */
@Slf4j
@RestController("ApiContentToolBokKitController")
@RequestMapping("/api")
public class KitBoxApiController {
	
	@Autowired
	protected HttpServletRequest request;
	
	@Autowired
	private IpService ipService;

	@PostMapping("freemarker/test")
	@ApiOperation(value = "FreeMarker(FTL) Online testing tool", notes = "FreeMarker(FTL) Online testing tool", tags = "Open interface")
	public APIResult<String> getContentByFreeMarkerAndBean(String ftl, String beanJson)
			throws IOException, TemplateException {
		FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
		freemarker.template.Configuration configuration = configurer.createConfiguration();
		freemarker.cache.StringTemplateLoader templateLoader = new freemarker.cache.StringTemplateLoader();
		configuration.setTemplateLoader(templateLoader);
		configuration.setDefaultEncoding("UTF-8");
		APIResult<String> apiResult;
		try {
			freemarker.template.Template template = new freemarker.template.Template("freemarkerTest", ftl,
					configuration);
			StringWriter stringWriter = new StringWriter();
			Object object = JSON.parseObject(beanJson, Object.class);
			template.process(object, stringWriter);
			apiResult = APIResult.builder().code(StateCode.OK).message("").data(stringWriter.toString()).build();
		} catch (Exception ex) {
			log.info(ex.getMessage());
			apiResult = APIResult.builder().code(StateCode.Error).message(ex.getMessage()).data(ex.getMessage())
					.build();
		}
		return apiResult;
	}

	@GetMapping("ip/{ip}")
	@ApiOperation(value = "Query IP address information", notes = "Query IP address information", tags = "Open interface")
	public APIResult<IpInfoDTO> queryIpInfo(@PathVariable("ip") String ip) {
		try {
			return new APIResult<>(ipService.query(ip));
		} catch (ServiceException businessException) {
			return APIResult.builder().code(StateCode.Failure).message(businessException.getMessage()).build();
		}
	}

	@GetMapping("ip")
	@ApiOperation(value = "Check my IP address information", notes = "Check my IP address information", tags = "Open interface")
	public APIResult<IpInfoDTO> queryIpInfo() {
		try {
			return new APIResult<>(ipService.query(IpUtils.getIpAddress(request)));
		} catch (ServiceException businessException) {
			return APIResult.builder().code(StateCode.Failure).message(businessException.getMessage()).build();
		}
	}

}
