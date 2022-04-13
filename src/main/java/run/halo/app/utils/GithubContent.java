package run.halo.app.utils;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GithubContent implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String sha;
	private String path;
	private String base64Content;
	private String originalContent;
	private long size;
	private String branch;
	private String repo;
	private String type;
	private String url;

	public GithubContent(String path, String base64Content) {
		this.path = path;
		this.base64Content = base64Content;
	}

}
