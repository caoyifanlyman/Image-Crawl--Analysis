package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class FlickrURLGenerator {


	public static ArrayList<String> generateURL(Document doc)
			throws ParserConfigurationException, SAXException, IOException {

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
