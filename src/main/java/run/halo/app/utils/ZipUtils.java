package run.halo.app.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;

public class ZipUtils {



	private static List<GithubContent> extractZipEntries(byte[] content, String repo) throws Exception {
		List<GithubContent> entries = new ArrayList<>();

		ZipInputStream zi = null;
		try {
			zi = new ZipInputStream(new ByteArrayInputStream(content));

			ZipEntry zipEntry = null;
			GithubContent data = null;
			while ((zipEntry = zi.getNextEntry()) != null) {

				if (zipEntry.getName().endsWith("index.html")&&!repo.contains("github.io")) {
					String contents = IOUtils.toString(zi, StandardCharsets.UTF_8);

					String newHref = "<base href=\"/" + repo + "/\">";
					contents = contents.replaceAll("<base href=\"/\">", newHref);
					contents = contents.replaceAll("<base href=\"/\" >", newHref);

//					System.out.println(contents);
					data = new GithubContent(zipEntry.getName(),
							Base64.getEncoder().encodeToString(contents.getBytes()));

				} else
					data = new GithubContent(zipEntry.getName(), convertToB64(zi));

				

				String contents = IOUtils.toString(zi, StandardCharsets.UTF_8);
				data.setOriginalContent(contents);
				data.setSize(zipEntry.getSize());
				entries.add(data);
			}
		} finally {
			if (zi != null) {
				zi.close();
			}
		}
		return entries;
	}

	private static String convertToB64(final ZipInputStream inputStreamIn) throws Exception {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		IOUtils.copy(inputStreamIn, out);
		return new String(Base64.getEncoder().encodeToString(out.toByteArray()));
	}

	public static List<GithubContent> getZipTemplate(String url, String repo) throws Exception {
		byte[] content = IOUtils.toByteArray(new URL(url));

		List<GithubContent> entries = extractZipEntries(content, repo);

		return entries;
	}

}
