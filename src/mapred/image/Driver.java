package mapred.image;

import java.io.IOException;
import mapred.job.Optimizedjob;
import mapred.util.SimpleParser;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;

public class Driver {

	public static void main(String args[]) throws Exception {
		SimpleParser parser = new SimpleParser(args);
		//System.loadLibrary("/home/jacky/image_crawl_analysis/lib/opencv-246");

		String input = parser.get("input");
		String output = parser.get("output");
		
		// input is the txt file containing urls
		// output file contains the image feature statistics
		getJobFeatureVector(input, output);

	}

	private static void getJobFeatureVector(String input, String output) 
		throws IOException, ClassNotFoundException, InterruptedException {
		Configuration conf = new Configuration();
		conf.set("output", output);
		Optimizedjob job = new Optimizedjob(conf, input, output,
				"image downloading and analysis");

		job.setClasses(ImageMapper.class, null, null);
		job.setMapOutputClasses(Text.class, NullWritable.class);

		job.run();
	}	
}
