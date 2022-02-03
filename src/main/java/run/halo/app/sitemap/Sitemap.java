package run.halo.app.sitemap;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "urlset", namespace = "http://www.sitemaps.org/schemas/sitemap/0.9")
public class Sitemap {

	@XmlElement(name = "url")
	List<URL> urls = null;

	public List<URL> getUrls() {
		return urls;
	}

	public void setUrls(List<URL> urls) {
		this.urls = urls;
	}

	public void addURL(String url) {
		if (urls == null)
			urls = new ArrayList<URL>();

	}

	@Data
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlRootElement(name = "url")
	public static class URL {

		private String loc = null;
		private String lastmod = null;

		public URL() {
		}

		public URL(String s, Date created) {
			loc = s;
			lastmod = (new SimpleDateFormat("yyyy-MM-dd")).format(created);
		}

		@Override
		public String toString() {
			return loc;
		}

	}

}
