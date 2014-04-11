package util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class XMLDownloader {

	public static String excuteDownload(String targetURL, String urlParameters) {
		URL url;
		HttpURLConnection connection = null;
		DataOutputStream wr = null;
		BufferedReader rd = null;
		try {
			// Create connection
			url = new URL(targetURL+urlParameters);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setUseCaches(false);
			connection.setDoInput(true);
			connection.setDoOutput(true);

			// Send request
			wr = new DataOutputStream(connection.getOutputStream());
			wr.flush();
			wr.close();

			// Get Response
			InputStream is = connection.getInputStream();
			rd = new BufferedReader(new InputStreamReader(is));
			String line;
			StringBuffer response = new StringBuffer();
			while ((line = rd.readLine()) != null) {
				response.append(line);
			}
			rd.close();
			return response.toString();

		} catch (Exception e) {

			e.printStackTrace();
			return null;

		} finally {

			if (connection != null) {
				connection.disconnect();
			}
			try {

				if (wr != null) {
					wr.close();

				}
				if (rd != null) {
					rd.close();
				}
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		}
	}
}
