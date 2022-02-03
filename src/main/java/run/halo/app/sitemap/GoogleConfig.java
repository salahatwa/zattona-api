package run.halo.app.sitemap;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties("google")
public class GoogleConfig {

	private String siteUrl;
	private String sitemapUrl;
	private String sitemapUrlExtra;
	private String postUrl;
	private String categoryUrl;
	private String tagUrl;

	private String ads;

}
