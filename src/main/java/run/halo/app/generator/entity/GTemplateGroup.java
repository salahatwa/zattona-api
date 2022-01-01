package run.halo.app.generator.entity;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class GTemplateGroup implements Serializable {

	private String group;
	List<GTemplate> templates;
}
