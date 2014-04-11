package demo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import org.w3c.dom.Document;

import util.FlickrURLGenerator;
import util.ImageDownload;
import util.XMLDownloader;

public class GeneratorRun {
	private static final String targetURL = "https://api.flickr.com/services/rest/";


	public static String generateParameter(String method, String api_key,
			String per_page, String page) {

		return "?method=" + method + "&api_key=" + api_key + "&per_page="
				+ per_page + "&page=" + page;
	}

	public static void main(String[] args) {
		Pattern pattern = Pattern.compile("/");
		try {			
			int per_page = 100;// Maximum per page
			int pages = 5;
			for (int i = 1; i <= pages; i++) {
				String params = GeneratorRun.generateParameter(
						"flickr.photos.getRecent",
						"314859c708417e548a161f1385dd9990", String.valueOf(per_page), String.valueOf(i));
			
				// here use the two utils 
				Document xmlDoc = XMLDownloader.excuteDownload(targetURL, params);
				ArrayList<String> urls = FlickrURLGenerator.generateURL(xmlDoc);
				ImageDownload ImgDnLd = new ImageDownload();
				for (String str : urls) {
					try {
						String[] imageName = pattern.split(str);
						ImgDnLd.saveUrl(imageName[imageName.length-1], str);
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.println(i +" completed!");
			}

			System.out.println("Succeed!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
	}
}
