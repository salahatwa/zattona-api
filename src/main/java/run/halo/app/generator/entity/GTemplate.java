package run.halo.app.generator.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class GTemplate implements Serializable {

	private String id;
	private String name;
	private String description;
	private String group;
	
}
