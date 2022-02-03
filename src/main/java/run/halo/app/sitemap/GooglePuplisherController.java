//package run.halo.app.sitemap;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.time.Instant;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Date;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import run.halo.app.service.CategoryService;
//import run.halo.app.service.PostService;
//import run.halo.app.service.TagService;
//import run.halo.app.sitemap.Sitemap.URL;
//
//@Controller
//public class GooglePuplisherController {
//
//	@Autowired
//	private PostService postService;
//
//	@Autowired
//	private TagService tagService;
//
//	@Autowired
//	private CategoryService categoryService;
//
//	@Autowired
//	private GoogleConfig googleConfig;
//
//	@Autowired
//	private SiteMapService siteMapService;
//
//	/**
//	 * @return
//	 * @throws IOException
//	 */
//	@GetMapping("ads.txt")
//	@ResponseBody
//	public void getAdsTxt(HttpServletResponse response) throws IOException {
//		PrintWriter out = response.getWriter();
//		out.println(googleConfig.getAds());
//	}
//
//	@RequestMapping(value = "generate/sitemap.xml", method = RequestMethod.GET, produces = { "application/xml",
//			"text/xml" })
//	@ResponseBody
//	public void getSitemap(HttpServletResponse response) throws Exception {
//
//		List<URL> maps = new ArrayList<Sitemap.URL>();
//
//		List<URL> posts = postService.listAll().stream()
//				.map(item -> new URL(googleConfig.getPostUrl() + item.getSlug(), item.getCreateTime()))
//				.collect(Collectors.toList());
//
//		List<URL> tags = tagService.listAll().stream()
//				.map(item -> new URL(googleConfig.getTagUrl() + item.getSlug(), item.getCreateTime()))
//				.collect(Collectors.toList());
//
//		List<URL> categories = categoryService.listAll().stream()
//				.map(item -> new URL(googleConfig.getCategoryUrl() + item.getSlug(), item.getCreateTime()))
//				.collect(Collectors.toList());
//
//		List<URL> extraUrls = Arrays.asList(googleConfig.getSitemapUrlExtra().split(",")).stream()
//				.map(url -> new URL(url, Date.from(Instant.parse("2022-02-01T00:00:00.000Z"))))
//				.collect(Collectors.toList());
//
//		maps.addAll(categories);
//		maps.addAll(posts);
//		maps.addAll(tags);
//		maps.addAll(extraUrls);
//
//		String xml = siteMapService.createSitemaps(maps);
//		System.out.println(xml);
//
//		response.addHeader("content-type", "application/xml; charset=utf-8");
//		response.setStatus(200);
//
//		PrintWriter out = response.getWriter();
//		out.println(xml);
//
//	}
//
//	@RequestMapping(value = { "/robots.txt", "/robot.txt" })
//	@ResponseBody
//	public void getRobotsTxt(HttpServletResponse response) {
//		String robot = "User-agent: *\n" + "Disallow: /admin\n" + "Sitemap: " + googleConfig.getSitemapUrl() + "\n";
//		response.setStatus(200);
//		try {
//			response.getWriter().write(robot);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//}
