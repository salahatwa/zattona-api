package run.halo.app.controller.content.model;

import lombok.Data;

@Data
public class OuathModel {

	private String access_token;
	private String refresh_token;
	private String expires_in;

}
