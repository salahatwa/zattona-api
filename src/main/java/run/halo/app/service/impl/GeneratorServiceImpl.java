package run.halo.app.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.script.Bindings;
import javax.script.Invocable;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.apache.commons.io.IOUtils;
import org.json.XML;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import run.halo.app.generator.entity.ClassInfo;
import run.halo.app.generator.entity.GTemplate;
import run.halo.app.generator.entity.GTemplateGroup;
import run.halo.app.generator.entity.GeneratedCode;
import run.halo.app.generator.entity.GeneratedCode.TmpData;
import run.halo.app.generator.entity.ParamInfo;
import run.halo.app.generator.utils.FreemarkerUtil;
import run.halo.app.generator.utils.MapUtil;
import run.halo.app.generator.utils.TableParseUtil;
import run.halo.app.service.GeneratorService;
import run.halo.app.utils.QrOptions;

/**
 * GeneratorService
 *
 * @author ssatwa
 */
@Slf4j
@Service
public class GeneratorServiceImpl implements GeneratorService {

	private ObjectMapper mapper = new ObjectMapper();

	private Invocable callScript;

	GeneratorServiceImpl() throws IOException, ScriptException {

		InputStream qrScript = this.getClass().getClassLoader().getResourceAsStream("tools/kjua.min.js");
		String result = IOUtils.toString(qrScript, StandardCharsets.UTF_8);
//			System.out.println(result);

		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("JavaScript");

		engine.eval(result);
		// create the engine and have it load your javascript
		Bindings bind = engine.getBindings(ScriptContext.ENGINE_SCOPE);
		Set<String> allAttributes = bind.keySet();
		Set<String> allFunctions = new HashSet<String>();
		for (String attr : allAttributes) {
			if ("function".equals(engine.eval("typeof " + attr))) {
				allFunctions.add(attr);
			}
		}
		System.out.println(allFunctions);

		callScript = (Invocable) engine;
	}

	/**
	 * Read String from the JSON file in the project
	 *
	 */
	@Override
	public List<GTemplateGroup> getTemplateConfig() throws IOException {
		String templateCpnfig = null;
		if (templateCpnfig != null) {
		} else {
			InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("template.json");
			templateCpnfig = new BufferedReader(new InputStreamReader(inputStream)).lines()
					.collect(Collectors.joining(System.lineSeparator()));
			inputStream.close();
		}
		List<GTemplateGroup> templatesGroups = mapper.readValue(templateCpnfig,
				new TypeReference<List<GTemplateGroup>>() {
				});
		return templatesGroups;
	}

	/**
	 * Perform traversal analysis according to the configured Template template to
	 * get the generated String
	 *
	 */
	@Override
	public List<GeneratedCode> getResultByParams(ParamInfo paramInfo) throws IOException, TemplateException {

		List<GeneratedCode> codes = new ArrayList<GeneratedCode>();

		// 1.Parse Table Structure
		String dataType = MapUtil.getString(paramInfo.getOptions(), "dataType");

		if (!dataType.equals("xml")) {
			paramInfo.setInput(XML.toJSONObject(paramInfo.getInput()).toString());
		}

		List<ClassInfo> clazzes = TableParseUtil.processJsonToClassInfo(paramInfo);

		for (ClassInfo classInfo : clazzes) {
			GeneratedCode generatedCode = new GeneratedCode();
			BeanUtils.copyProperties(classInfo, generatedCode);
			paramInfo.getOptions().put("classInfo", classInfo);
			paramInfo.getOptions().put("tableName",
					classInfo == null ? System.currentTimeMillis() : classInfo.getTableName());

			List<GTemplateGroup> parentTemplates = getTemplateConfig();

			List<GTemplate> allTemps = parentTemplates.stream().flatMap(p -> p.getTemplates().stream())
					.filter(i -> paramInfo.getSelectedTmpIds().stream().anyMatch(id -> id.equals(i.getId())))
					.collect(Collectors.toList());

			for (GTemplate template : allTemps) {
				TmpData d = new TmpData();

				d.setTmpName(template.getName());
				d.setTmpCode(FreemarkerUtil.processString(template.getGroup() + "/" + template.getName() + ".ftl",
						paramInfo.getOptions()));
				generatedCode.getResults().add(d);
			}
			codes.add(generatedCode);
		}

		return codes;
	}

	/**
	 * Read String from the JSON file in the project
	 *
	 */
	@Override
	public void readQR() throws IOException {

		// read script file
		try {

			QrOptions options = new QrOptions();
			options.setEcLevel("L");
			options.setMinVersion(4);

			options.setTitle("Amazing idea");
			options.setText("kkkkkkkkkk");
			options.setFill("#c15757");
			options.setBack("#fff");

			options.setSize(200);

			options.setMode("label");
			options.setFontname("Ubuntu");
			options.setFontcolor("rgb(255, 152, 24)");
			options.setMsize(35);
			options.setLabel("atwa");
			options.setMposx(50);
			options.setMposy(50);

			options.setRounded(80);
			options.setQuiet(8);
//			// call function from script file
			// makeImg,makeSvg
			Object obj = (Object) this.callScript.invokeFunction("createDataURL", options);
			System.out.println(obj);
		} catch (ScriptException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}

	}

}
