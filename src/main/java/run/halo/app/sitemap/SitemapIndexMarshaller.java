package run.halo.app.sitemap;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SitemapIndexMarshaller {

	static Logger _logger = LoggerFactory.getLogger(SitemapIndexMarshaller.class);

	public String marshall(SitemapIndex index) throws Exception {
		_logger.info("Generating index");

		JAXBContext jaxbContext = JAXBContext.newInstance(SitemapIndex.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		StringWriter sw = new StringWriter();
		jaxbMarshaller.marshal(index, sw);

		_logger.info("Finished");

		return sw.toString();
	}

	public String marshall(Sitemap sitemap) throws Exception {
		_logger.info("Generating ");
		JAXBContext jaxbContext = JAXBContext.newInstance(Sitemap.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		StringWriter sw = new StringWriter();
		jaxbMarshaller.marshal(sitemap, sw);

		_logger.info("Finished");
		return sw.toString();
	}

}