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
		BufferedWriter bw = null;
		Pattern pattern = Pattern.compile("/");
		try {			
			int per_page = 500;// Maximum per page
			int pages = 4;
			String filename =  "C:\\Users\\Jacky\\projects\\Image-Crawl-Analysis\\photo_url_";

			for (int num = 0; num < 2000; num++) {
				String filename_tmp =  filename + num + ".txt";
				File file = new File(filename_tmp);
				bw = new BufferedWriter(new FileWriter(file, true)); // true mean append
				for (int i = 1; i <= pages; i++) {
					String params = GeneratorRun.generateParameter(
							"flickr.photos.getRecent",
							"314859c708417e548a161f1385dd9990", String.valueOf(per_page), String.valueOf(num*pages+i));

					// here use the two utils 
					Document xmlDoc = XMLDownloader.excuteDownload(targetURL, params);
					ArrayList<String> urls = FlickrURLGenerator.generateURL(xmlDoc);
					//ImageDownload ImgDnLd = new ImageDownload();
					for (String str : urls) {
						/*
						try {
							String[] imageName = pattern.split(str);
							ImgDnLd.saveUrl(imageName[imageName.length-1], str);
						} catch (MalformedURLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}*/
						bw.write(str);
						bw.newLine();
					}
					//System.out.println(i +" completed!");
				}
				System.out.println((num + 1) +" completed!");
				bw.close();
			}

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