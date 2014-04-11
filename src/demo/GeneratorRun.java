package demo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import util.FlickrURLGenerator;
import util.XMLDownloader;

public class GeneratorRun {
	private final String targetURL = "https://api.flickr.com/services/rest/";
	private String xmlString = null;

	public GeneratorRun(String params) {
		xmlString = XMLDownloader.excuteDownload(targetURL, params);
	}

	public String getXmlString() {
		return xmlString;
	}

	public void setXmlString(String xmlString) {
		this.xmlString = xmlString;
	}

	public static String recentPhotoParameter(String method, String api_key,
			String per_page, String page) {

		return "?method=" + method + "&api_key=" + api_key + "&per_page="
				+ per_page + "&page=" + page;
	}

	public static void main(String[] args) {
		BufferedWriter bw = null;
		try {

			int per_page = 500;// Maximum per page
			int pages = 5;
			String export_filename =  "/tmp/photo_url.txt";
		
			File file = new File(export_filename);
			bw = new BufferedWriter(new FileWriter(file, true)); // true mean append
			
			for (int i = 0; i < pages; i++) {
				String params = GeneratorRun.recentPhotoParameter(
						"flickr.photos.getRecent",
						"314859c708417e548a161f1385dd9990", String.valueOf(per_page), String.valueOf(i));
				GeneratorRun generator = new GeneratorRun(params);
				FlickrURLGenerator fug = new FlickrURLGenerator(
						generator.getXmlString());

				ArrayList<String> urls = fug.generateURL();

				for (String str : urls) {
					bw.write(str);
					bw.newLine();
				}
				System.out.println(i +" completed!");
			}

			bw.close();
			System.out.println("Succeed!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null) {
					bw.close();
				}
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		}
	}
}
