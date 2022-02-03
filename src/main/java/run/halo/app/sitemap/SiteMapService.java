package run.halo.app.sitemap;

import java.util.List;

import org.springframework.stereotype.Service;

import run.halo.app.sitemap.Sitemap.URL;

@Service
public class SiteMapService {
	String createSitemaps(List<URL> urls) throws Exception {

		Sitemap sitemap = new Sitemap();
		sitemap.setUrls(urls);

		return new SitemapIndexMarshaller().marshall(sitemap);
	}

	Sitemap createSitemapsO(List<URL> urls) throws Exception {

		Sitemap sitemap = new Sitemap();
		sitemap.setUrls(urls);

		return sitemap;
	}
}
