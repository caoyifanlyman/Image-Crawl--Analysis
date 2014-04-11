package util;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

public class XMLDownloader {

	public static Document excuteDownload(String targetURL, String urlParameters) {
		URL url;
		HttpURLConnection connection = null;
		InputStream xmlStream = null;

		Document doc = null;

		try {
			// Create connection
			System.out.println(targetURL + urlParameters);
			url = new URL(targetURL + urlParameters);
			connection = (HttpURLConnection) url.openConnection();
			xmlStream = connection.getInputStream();

			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();

			doc = builder.parse(xmlStream);

		} catch (Exception e) {

			e.printStackTrace();
			return null;

		} finally {

			if (connection != null) {
				connection.disconnect();
			}
			if (xmlStream != null) {
				try {
					xmlStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return doc;
	}
}
