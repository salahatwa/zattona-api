package run.halo.app.service;

import java.io.IOException;
import java.util.List;

import freemarker.template.TemplateException;
import run.halo.app.generator.entity.GTemplateGroup;
import run.halo.app.generator.entity.GeneratedCode;
import run.halo.app.generator.entity.ParamInfo;

/**
 * GeneratorService
 *
 * @author ssatwa
 */
public interface GeneratorService {

	List<GTemplateGroup> getTemplateConfig() throws IOException;

	public List<GeneratedCode> getResultByParams(ParamInfo paramInfo) throws IOException, TemplateException;

	public void readQR() throws IOException;
}
