package run.halo.app.controller.content.api;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import run.halo.app.controller.content.model.GithubUser;
import run.halo.app.controller.content.model.OuathModel;
import run.halo.app.controller.content.model.RepoModel;
import run.halo.app.service.GitHubService;
import run.halo.app.utils.GithubContent;

/**
 * Content user controller.
 *
 * @author ssatwa
 * @date 2019-04-03
 */
@RestController("ApiContenthOauthController")
@RequestMapping("/api/content/oauth")
public class OauthController {

	@Autowired
	private GitHubService githubService;

	@GetMapping("/github/{code}")
	public GithubUser githubOuath(@PathVariable(name = "code") String code) throws Exception {
		OuathModel ouath = githubService.getOuath(code);

		return githubService.getUserDetails(ouath);
	}

	@PostMapping("/github/repos")
	public List<RepoModel> githubRepos(@RequestBody GithubUser user) throws Exception {

		return githubService.getRepos(user.getAccess_token());
	}

	@PostMapping("/github/repo/deleteRepo/{repo}")
	public void deleteRepo(@RequestBody GithubUser user, @PathVariable(name = "repo") String repo) throws Exception {

		System.out.println(repo);
		System.out.println(user.getLogin());
		githubService.delete(user.getAccess_token(), user.getLogin(), repo);
	}

	@PostMapping("/github/create/site")
	public GithubUser createSite(@RequestBody GithubUser user) throws Exception {

		String repoName = "salahatwa.github.io";
		boolean isRepoExist = githubService.createRepo(user.getAccess_token(), repoName, "CV Portfolio by Zattona");

		if (isRepoExist) {
			List<GithubContent> files = githubService.getRepoTree(user, repoName);

			githubService.updateFile(user, "https://github.com/Zattona/cv/blob/main/portfolio-02.zip?raw=true",
					"master", repoName,files);
		}
		else
			githubService.uploadFile(user, "https://github.com/Zattona/cv/blob/main/portfolio-02.zip?raw=true",
					"master", repoName);

		RepoModel page = githubService.activeGithupPage(user, repoName, "master", user.getAccess_token());

		if(Objects.nonNull(page))
		System.err.println(page.getHtml_url());

		return user;
	}

}
