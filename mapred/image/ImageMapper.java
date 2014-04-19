package mapred.image;

import java.io.IOException;
import mapred.util.ImageDownload;
import java.util.regex.Pattern;
import java.net.MalformedURLException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class ImageMapper extends Mapper<LongWritable, Text, Text, NullWritable> {
	Pattern pattern = Pattern.compile("/");
	ImageDownload ImgDnLd = new ImageDownload();
	DetectFaceDemo face = new DetectFaceDemo();

	@Override
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		String url = value.toString();		

		try {
			String[] imageName = pattern.split(url);
			String iName = imageName[imageName.length-1];
			ImgDnLd.saveUrl(iName, url);
			face.run(iName);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
