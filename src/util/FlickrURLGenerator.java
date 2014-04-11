package util;

import java.io.StringReader;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class FlickrURLGenerator {
	private Document doc;

	public FlickrURLGenerator(String xml) throws Exception {
		this.setDoc(loadXMLFromString(xml));
	}

	public Document getDoc() {
		return doc;
	}

	public void setDoc(Document doc) {
		this.doc = doc;
	}

	public static Document loadXMLFromString(String xml) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		InputSource is = new InputSource(new StringReader(xml));
		return builder.parse(is);
	}

	public ArrayList<String> generateURL() {
		ArrayList<String> res = new ArrayList<>();
		NodeList nList = doc.getElementsByTagName("photo");
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < nList.getLength(); i++) {
			Element node = (Element) nList.item(i);
			sb.setLength(0);
			sb.append("http://farm");
			sb.append(node.getAttribute("farm"));
			sb.append(".staticflickr.com/");
			sb.append(node.getAttribute("server"));
			sb.append("/");
			sb.append(node.getAttribute("id") + "_"
					+ node.getAttribute("secret"));
			sb.append(".jpg");
			res.add(sb.toString());
		}
		return res;
	}
}
