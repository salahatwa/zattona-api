package run.halo.app.service.core;

import org.springframework.http.HttpMethod;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import lombok.Data;

@Data
public class RestCoreModel {
	private String url;
	private Object body;
	private MultiValueMap<String, String> headers =new LinkedMultiValueMap<String, String>();
	private HttpMethod method;
}
