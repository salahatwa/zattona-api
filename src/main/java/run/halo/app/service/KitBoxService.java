package run.halo.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import run.halo.app.model.dto.KitBoxMenus;
import run.halo.app.model.dto.LinkVO;
import run.halo.app.utils.Builder;

/**
 * <p>
 * Title: KitBoxService
 * </p>
 * <p>
 * Description: Toolbox service
 * </p>
 *
 * @author ssatwa
 */
@Service
@CacheConfig(cacheNames = "KitBoxService")
public class KitBoxService {
	public static final String NETWORK_TOOL = "networkTool";
	public static final String DEVELOPMENT_TOOL = "developmentTool";
	public static final String ENCRYPTION_TOOL = "encryptionTool";
	public static final String OTHER_TOOL = "otherTool";

	@Cacheable
	public List<KitBoxMenus> getKitBoxMenus(String key) {
		List<KitBoxMenus> kitBoxMenus = getKitBoxMenus();
		for (KitBoxMenus kitBoxMenu : kitBoxMenus) {
			if (kitBoxMenu.getElementId().equals(key)) {
				kitBoxMenu.setIsOpen(true);
				break;
			}
		}
		return kitBoxMenus;
	}

	@Cacheable
	public List<KitBoxMenus> getKitBoxMenus() {
		List<KitBoxMenus> kitBoxMenus = new ArrayList<>();
		kitBoxMenus.add(Builder.of(KitBoxMenus::new).with(KitBoxMenus::setTitle, "Network tools")
				.with(KitBoxMenus::setElementId, NETWORK_TOOL).with(KitBoxMenus::setLinks, new ArrayList<LinkVO>() {
					{
						this.add(Builder.of(LinkVO::new).with(LinkVO::setHref, "/kitbox/tools/ip").with(LinkVO::setRel,
								"IP address information query tool, open service interface to realize IP information query")
								.with(LinkVO::setText, "IP address information query tool").build());
//						this.add(Builder.of(LinkVO::new).with(LinkVO::setHref, "/kitbox/digtrace").with(LinkVO::setRel,
//								"Domain name resolution query tool, open service interface to realize dig+trace query")
//								.with(LinkVO::setText, "Domain name resolution Dig query tool").build());
//						this.add(Builder.of(LinkVO::new).with(LinkVO::setHref, "/kitbox/dnsqps").with(LinkVO::setRel,
//								"Domain name resolution QPS stress test tool, only for testing and do not use it to launch attacks")
//								.with(LinkVO::setText, "Domain name QPS stress test tool").build());
//						this.add(Builder.of(LinkVO::new).with(LinkVO::setHref, "/kitbox/whois").with(LinkVO::setRel,
//								"Check whether the domain name has been registered, and the detailed information of the registered domain name")
//								.with(LinkVO::setText, "Domain Name Whois Query Tool").build());
//						this.add(Builder.of(LinkVO::new).with(LinkVO::setHref, "/kitbox/getmyip")
//								.with(LinkVO::setRel, "Public IP acquisition tool supports Linux, Windows, API")
//								.with(LinkVO::setText, "Public IP acquisition tool").build());
					}
				}).build());
		kitBoxMenus.add(Builder.of(KitBoxMenus::new).with(KitBoxMenus::setTitle, "Development tools")
				.with(KitBoxMenus::setElementId, DEVELOPMENT_TOOL).with(KitBoxMenus::setLinks, new ArrayList<LinkVO>() {
					{
						this.add(Builder.of(LinkVO::new).with(LinkVO::setHref, "/kitbox/tools/freemarkerTest")
								.with(LinkVO::setRel, "FreeMarker online test tool, online test Ftl file")
								.with(LinkVO::setText, "FreeMarker Online Test Tool").build());
//						this.add(Builder.of(LinkVO::new).with(LinkVO::setHref, "/kitbox/uuid").with(LinkVO::setRel,
//								"Online batch generation UUID/GUID tool, support uppercase and lowercase conjunctions")
//								.with(LinkVO::setText, "UUID/GUID online generation tool").build());
//						
//						this.add(Builder.of(LinkVO::new).with(LinkVO::setHref, "/kitbox/strHumpLineConvert")
//								.with(LinkVO::setRel, "Underline and CamelCase Naming Style Mutual Conversion Tool")
//								.with(LinkVO::setText, "Underline and CamelCase Naming Conversion Tool").build());
//						this.add(Builder.of(LinkVO::new).with(LinkVO::setHref, "/kitbox/byteUnitConversion")
//								.with(LinkVO::setRel, "Computer Byte (Byte) Unit Conversion Tool")
//								.with(LinkVO::setText, "Byte (Byte) Unit Conversion Tool").build());
//						this.add(Builder.of(LinkVO::new).with(LinkVO::setHref, "/kitbox/ueditor")
//								.with(LinkVO::setRel, "Baidu team's UEditor online text editor demo and experience")
//								.with(LinkVO::setText, "UEditor Rich Text Online Editor").build());
//						this.add(Builder.of(LinkVO::new).with(LinkVO::setHref, "/kitbox/wordIkAnalyze")
//								.with(LinkVO::setRel,
//										"IK Analyzer online word segmentation tool and API open interface service")
//								.with(LinkVO::setText, "IK Online Word Segmentation Tool and API").build());
//						this.add(Builder.of(LinkVO::new).with(LinkVO::setHref, "/kitbox/portNumberList")
//								.with(LinkVO::setRel, "Computer TCP/UDP Port Number Registration List")
//								.with(LinkVO::setText, "Port Number Registration List").build());
					}
				}).build());
//		kitBoxMenus.add(Builder.of(KitBoxMenus::new).with(KitBoxMenus::setTitle, "Encryption and decryption tools")
//				.with(KitBoxMenus::setElementId, ENCRYPTION_TOOL).with(KitBoxMenus::setLinks, new ArrayList<LinkVO>() {
//					{
//						this.add(Builder.of(LinkVO::new).with(LinkVO::setHref, "/kitbox/randomPassword").with(
//								LinkVO::setRel,
//								"According to the selected characters and password length, a random password is generated with one click")
//								.with(LinkVO::setText, "Random Password Generator").build());
//						this.add(Builder.of(LinkVO::new).with(LinkVO::setHref, "/kitbox/md5")
//								.with(LinkVO::setRel,
//										"MD5 encryption, MD5 calculation of string to get MD5 encrypted string")
//								.with(LinkVO::setText, "MD5 encryption tool").build());
//						this.add(Builder.of(LinkVO::new).with(LinkVO::setHref, "/kitbox/sha1").with(LinkVO::setRel,
//								"SHA-1 encryption, perform SHA-1 calculation on the string to encrypt the string")
//								.with(LinkVO::setText, "SHA-1 encryption tool").build());
//						this.add(Builder.of(LinkVO::new).with(LinkVO::setHref, "/kitbox/sha256").with(LinkVO::setRel,
//								"SHA-256 encryption, perform SHA-256 calculation on the string to encrypt the string")
//								.with(LinkVO::setText, "SHA-256 encryption tool").build());
//						this.add(Builder.of(LinkVO::new).with(LinkVO::setHref, "/kitbox/sha512").with(LinkVO::setRel,
//								"SHA-512 encryption, perform SHA-512 calculation on the string to encrypt the string")
//								.with(LinkVO::setText, "SHA-512 encryption tool").build());
//						this.add(Builder.of(LinkVO::new).with(LinkVO::setHref, "/kitbox/url16")
//								.with(LinkVO::setRel,
//										"URL URL hexadecimal encryption tool, hexadecimal encoding the URL")
//								.with(LinkVO::setText, "URL hexadecimal encryption").build());
//					}
//				}).build());
//		kitBoxMenus.add(Builder.of(KitBoxMenus::new).with(KitBoxMenus::setTitle, "Other tools")
//				.with(KitBoxMenus::setElementId, OTHER_TOOL).with(KitBoxMenus::setLinks, new ArrayList<LinkVO>() {
//					{
//						this.add(Builder.of(LinkVO::new).with(LinkVO::setHref, "/kitbox/qrcode")
//								.with(LinkVO::setRel, "Free and practical online tool for generating QR code images")
//								.with(LinkVO::setText, "Tool for generating QR code").build());
//						this.add(Builder.of(LinkVO::new).with(LinkVO::setHref, "/kitbox/ShortURL")
//								.with(LinkVO::setRel, "Free Online Short URL Generation Service, Shorten URL")
//								.with(LinkVO::setText, "Short URL Generation Tool").build());
//					}
//				}).build());
		return kitBoxMenus;
	}

}
