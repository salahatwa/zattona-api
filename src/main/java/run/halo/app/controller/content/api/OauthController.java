package run.halo.app.controller.content.api;

import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.SSLContext;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.TrustStrategy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.AllArgsConstructor;
import lombok.Data;
import run.halo.app.controller.content.model.OuathModel;

/**
 * Content user controller.
 *
 * @author ssatwa
 * @date 2019-04-03
 */
@RestController("ApiContenthOauthController")
@RequestMapping("/api/content/oauth")
public class OauthController {

	public OauthController() {
	}

	@GetMapping("/github/{code}")
	public OuathModel githubOuath(@PathVariable(name = "code") String code) throws Exception {
		TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;

		SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy)
				.build();

		SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);

		CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf).build();

		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();

		requestFactory.setHttpClient(httpClient);

		RestTemplate restTemplate = new RestTemplate(requestFactory);
		String url = "https://github.com/login/oauth/access_token";

		// request body parameters
		Map<String, String> map = new HashMap<>();
		map.put("client_id", "Iv1.2eafe96a5fb514f6");
		map.put("client_secret", "ce2011c8b7b2ed1020c948d610cfb2e923f2d514");
		map.put("scope", "public_repo");
		map.put("redirect_uri", "http://localhost:4200/verify");
		map.put("code", code);

//		// create headers
//		HttpHeaders headers = new HttpHeaders();
//		// set `content-type` header
//		headers.setContentType(MediaType.APPLICATION_JSON);
//		// set `accept` header
//		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//		headers.add("", "");
//		
//		// build the request
//		HttpEntity<Map<String, Object>> entity = new HttpEntity(map, headers);

		// send POST request
		ResponseEntity<OuathModel> response = restTemplate.postForEntity(url, map, OuathModel.class);

		// check response
		if (response.getStatusCode() == HttpStatus.OK) {
			System.out.println("Request Successful");
			System.out.println(response.getBody());
			
//			HttpHeaders headers = new HttpHeaders();
//			// set `content-type` header
//			headers.setContentType(MediaType.APPLICATION_JSON);
//			// set `accept` header
//			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//			headers.add("Authorization", "token "+response.getBody().getAccess_token());
//			HttpEntity<Map<String, Object>> entity = new HttpEntity(new Repo("sample_repo2030"), headers);
//			
//			ResponseEntity<String> response2 = restTemplate.postForEntity("https://api.github.com/user/repos", entity, String.class);
//			System.out.println(response2.getBody());
			return response.getBody();
		} else {
			System.out.println("Request Failed");
			return null;
		}

	}

	@Data
	@AllArgsConstructor
	public static class Repo{
		private String name;
	}
}

