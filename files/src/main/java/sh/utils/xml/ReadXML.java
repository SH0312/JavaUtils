package sh.utils.xml;

import java.io.FileReader;
import java.util.HashMap;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;

public class ReadXML {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * @Author SH
	 * @param XMLPath
	 * @return XML 데이터 - HashMap
	 */
	public HashMap<String, String> readXML(String XMLPath) {
		HashMap<String, String> hm = new HashMap<String, String>();

		try {
			SAXBuilder builder = new SAXBuilder();
			Document jdomdoc = builder.build(new InputSource(new FileReader(XMLPath)));
			Element root = jdomdoc.getRootElement();

			hm.put("TITLE", root.getChild("TITLE").getValue());
			hm.put("CONTENT", root.getChild("CONTENT").getValue());
			hm.put("CREATE_DATE", root.getChild("CREATE_DATE").getValue());
			hm.put("CREATE_DATE", root.getChild("CREATE_DATE").getValue());

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		logger.info("====END====");
		return hm;
	}
}
