package sh.utils.xml;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import org.jdom2.CDATA;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateXML {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * @Author SH
	 * @param xml 경로
	 * @return boolean
	 */
	public boolean createXML(String path) {
		Element rootEle = new Element("DOCUMENT");
		rootEle.addContent(new Element("TITLE").addContent(new CDATA("title")));
		rootEle.addContent(new Element("CONTENT").addContent(new CDATA("content")));
		rootEle.addContent(new Element("AUTHOR").addContent("author"));
		rootEle.addContent(new Element("CREATE_DATE").addContent("create_date"));

		
		XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat().setEncoding("utf-8"));
		Document doc = new Document(rootEle);
		try {
			FileOutputStream fos = null;
			fos = new FileOutputStream(path);
			OutputStreamWriter os = new OutputStreamWriter(fos, "utf-8");

			outputter.output(doc, os);
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("=========END=========");
		return true;
	}
}
