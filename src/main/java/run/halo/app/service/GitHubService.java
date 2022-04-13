package run.halo.app.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import run.halo.app.controller.content.model.GithubUser;
import run.halo.app.controller.content.model.OuathModel;
import run.halo.app.controller.content.model.RepoModel;
import run.halo.app.service.core.RestCore;
import run.halo.app.service.core.RestCoreModel;
import run.halo.app.utils.GithubContent;
import run.halo.app.utils.ZipUtils;

@Service
public class GitHubService {
	@Autowired
	private RestCore restCore;

	public OuathModel getOuath(String code) {
		RestCoreModel model = new RestCoreModel();
		model.setUrl("https://github.com/login/oauth/access_token");
		Map<String, String> map = new HashMap<>();
		map.put("client_id", "a29af9ba6244de637ef1");
		map.put("client_secret", "05b8dcc325ae3e45bb8c533f3f8cbb05bb335cf7");
		map.put("scope", "public_repo,user,delete_repo");
		map.put("redirect_uri", "http://localhost:4200/git-tools");
		map.put("code", code);
		model.setBody(map);
		model.setMethod(HttpMethod.POST);
		OuathModel result = restCore.call(model, OuathModel.class);
		return result;
	}

	public GithubUser getUserDetails(OuathModel ouath) {
		RestCoreModel model = new RestCoreModel();
		model.setUrl("https://api.github.com/user");
		model.getHeaders().add("Accept", "application/vnd.github.v3+json");
		model.getHeaders().add("Authorization", "token " + ouath.getAccess_token());
		model.setMethod(HttpMethod.GET);
		GithubUser user = restCore.call(model, GithubUser.class);
		user.setAccess_token(ouath.getAccess_token());
		user.setRefresh_token(ouath.getRefresh_token());
		user.setExpires_in(ouath.getExpires_in());

		return user;
	}

	public boolean createRepo(String token, String repo, String desc) {
		try {
			RestCoreModel model = new RestCoreModel();
			model.setUrl("https://api.github.com/user/repos");
			model.getHeaders().add("Accept", "application/vnd.github.v3+json");
			model.getHeaders().add("Authorization", "token " + token);
			model.setMethod(HttpMethod.POST);

			Map<String, String> repoM = new HashMap<>();
			repoM.put("name", repo);
			repoM.put("description", desc);
			model.setBody(repoM);
			restCore.call(model, RepoModel.class);

			return false;
		} catch (Exception ex) {
			System.err.println("Repository already exist");
			return true;
		}
	}

	public List<RepoModel> getRepos(String token) {
		RestCoreModel model = new RestCoreModel();
		model.setUrl("https://api.github.com/user/repos?type=public&sort=created&per_page=100&page=1");
		model.getHeaders().add("Accept", "application/vnd.github.v3+json");
		model.getHeaders().add("Authorization", "token " + token);
		model.setMethod(HttpMethod.GET);

		return restCore.callList(model, RepoModel.class);
	}

	public void delete(String token, String owner, String repo) {
		RestCoreModel model = new RestCoreModel();
		model.setUrl("https://api.github.com/repos/" + owner + "/" + repo);
		model.getHeaders().add("Accept", "application/vnd.github.v3+json");
		model.getHeaders().add("Authorization", "token " + token);
		model.setMethod(HttpMethod.DELETE);

		String result = restCore.call(model, String.class);
		System.out.println(result);
	}

	public void updateFile(GithubUser user, String zipPath, String branch, String repo, List<GithubContent> files)
			throws Exception {

		List<GithubContent> tEntries = ZipUtils.getZipTemplate(zipPath, repo);

		for (GithubContent content : tEntries) {
			content.setBranch(branch);
			content.setRepo(repo);

			Optional<GithubContent> matchingObject = files.stream().filter(p -> p.getPath().equals(content.getPath()))
					.findFirst();

			if (matchingObject.isPresent())
				content.setSha(matchingObject.get().getSha());

			System.out.println(content.getSha());
			this.uploadGitFile(user, content);
		}
	}

	public void uploadFile(GithubUser user, String zipPath, String branch, String repo) throws Exception {

		List<GithubContent> tEntries = ZipUtils.getZipTemplate(zipPath, repo);

		for (GithubContent content : tEntries) {
			content.setBranch(branch);
			content.setRepo(repo);
			this.uploadGitFile(user, content);
		}
	}

	public static void main(String[] args) {
		LocalDateTime sylvester = LocalDateTime.of(2022, Month.DECEMBER, 31, 23, 59, 59);

		DayOfWeek dayOfWeek = sylvester.getDayOfWeek();
		System.out.println(dayOfWeek);      // WEDNESDAY

		Month month = sylvester.getMonth();
		System.out.println(month);          // DECEMBER

		long minuteOfDay = sylvester.getLong(ChronoField.MINUTE_OF_DAY);
		System.out.println(minuteOfDay);
	}

	public void uploadGitFile(GithubUser user, GithubContent content) throws MalformedURLException, IOException {
		RestCoreModel model = new RestCoreModel();

		model.setUrl("https://api.github.com/repos/" + user.getLogin() + "/" + content.getRepo() + "/contents/"
				+ content.getPath());
		model.getHeaders().add("Accept", "application/vnd.github.v3+json");
		model.getHeaders().add("Authorization", "token " + user.getAccess_token());
		model.setMethod(HttpMethod.PUT);

		Map<String, String> io = new HashMap<>();
		io.put("message", "Upload pictures via Zattona(https://github.com/XPoet/picx)");
		io.put("branch", content.getBranch());
		io.put("content", content.getBase64Content());
		io.put("sha", content.getSha());
		model.setBody(io);
		String rs = restCore.call(model, String.class);
		System.out.println(rs);
	}

	public List<GithubContent> getRepoTree(GithubUser user, String repo) throws MalformedURLException, IOException {
		RestCoreModel model = new RestCoreModel();

		model.setUrl("https://api.github.com/repos/" + user.getLogin() + "/" + repo + "/contents");
		model.getHeaders().add("Accept", "application/vnd.github.v3+json");
		model.getHeaders().add("Authorization", "token " + user.getAccess_token());
		model.setMethod(HttpMethod.GET);

		List<GithubContent> rs = restCore.callList(model, GithubContent.class);

		ObjectMapper mapper = new ObjectMapper();
		List<GithubContent> items = mapper.convertValue(rs, new TypeReference<List<GithubContent>>() {
		});

		System.out.println(items.size());

		List<GithubContent> all = new ArrayList<GithubContent>();
		all.addAll(items);

		for (GithubContent content : items) {
			if (content.getType().equals("dir")) {
				model.setUrl(content.getUrl());
				List<GithubContent> subContents = restCore.callList(model, GithubContent.class);

				List<GithubContent> subItems = mapper.convertValue(subContents,
						new TypeReference<List<GithubContent>>() {
						});
				all.addAll(subItems);
			}
		}

		System.out.println(all.size());
		return all;
	}

	public void getFile(GithubUser user, GithubContent content) throws MalformedURLException, IOException {
		RestCoreModel model = new RestCoreModel();

		model.setUrl("https://api.github.com/repos/" + user.getLogin() + "/" + content.getRepo() + "/contents/"
				+ content.getPath());
		model.getHeaders().add("Accept", "application/vnd.github.v3+json");
		model.getHeaders().add("Authorization", "token " + user.getAccess_token());
		model.setMethod(HttpMethod.GET);

		String rs = restCore.call(model, String.class);

		System.out.println(rs);
	}

	public RepoModel activeGithupPage(GithubUser user, String repoName, String branch, String token) {
		RestCoreModel model = new RestCoreModel();
		model.setUrl("https://api.github.com/repos/" + user.getLogin() + "/" + repoName + "/pages");
		model.getHeaders().add("Accept", "application/vnd.github.v3+json");
		model.getHeaders().add("Authorization", "token " + token);
		model.setMethod(HttpMethod.POST);

		Repo repo = new Repo(new Source(branch, "/"));

		model.setBody(repo);
		try {
			return restCore.call(model, RepoModel.class);
		} catch (Exception ex) {
			return null;
		}
	}

	@Data
	@AllArgsConstructor
	public static class Repo {
		private Source source;
	}

	@Data
	@AllArgsConstructor
	public static class Source {
		private String branch;
		private String path;
	}

}
