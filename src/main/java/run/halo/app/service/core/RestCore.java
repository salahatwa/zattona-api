package run.halo.app.service.core;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestCore {

	@Autowired
	private RestTemplate restTemplate;

	public <T> T call(RestCoreModel model, Class<T> clazz) {
		HttpHeaders headers = new HttpHeaders(model.getHeaders());

		HttpEntity<Object> entity = new HttpEntity<>(model.getBody(), headers);

		ResponseEntity<T> response = restTemplate.exchange(model.getUrl(), model.getMethod(), entity, clazz);

		if (response.getStatusCode() == HttpStatus.OK || response.getStatusCode() == HttpStatus.ACCEPTED
				|| response.getStatusCode() == HttpStatus.CREATED
				|| response.getStatusCode() == HttpStatus.NO_CONTENT) {
			return response.getBody();
		} else {
			System.out.println("Request Failed:" + response.getBody());
			System.out.println("Request Failed:" + response.getStatusCode());
			return response.getBody();
		}
	}

	public <T> List<T> callList(RestCoreModel model, Class<T> clazz) {
		HttpHeaders headers = new HttpHeaders(model.getHeaders());

		HttpEntity<Object> entity = new HttpEntity<>(model.getBody(), headers);

		ResponseEntity<List<T>> response = restTemplate.exchange(model.getUrl(), model.getMethod(), entity,
				new ParameterizedTypeReference<List<T>>() {
				});

		if (response.getStatusCode() == HttpStatus.OK) {
			return response.getBody();
		} else {
			System.out.println("Request Failed:" + response.getBody());
			System.out.println("Request Failed:" + response.getStatusCode());
			return response.getBody();
		}
	}

}
