package run.halo.app.controller.content.model;

import lombok.Data;

@Data
public class RepoModel {
	private String full_name;
	private String status;
	private String html_url;
}
