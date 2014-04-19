package mapred.image;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.highgui.Highgui;
import org.opencv.objdetect.CascadeClassifier;

//
// Detects faces in an image, draws boxes around them, and writes the results
// to "faceDetection.png".
//
public class DetectFaceDemo {
  public void run(String filename) {
    System.out.println("\nRunning DetectFaceDemo");
    //System.out.println(getClass().getResource("lbpcascade_frontalface.xml").getPath());
    // Create a face detector from the cascade file in the resources
    // directory.
    //CascadeClassifier faceDetector = new CascadeClassifier(getClass().getResource("lbpcascade_frontalface.xml").getPath());
    //Mat image = Highgui.imread(getClass().getResource("lena.png").getPath());
		/*
		 * Detected 0 faces Writing faceDetection.png libpng warning: Image
		 * width is zero in IHDR libpng warning: Image height is zero in IHDR
		 * libpng error: Invalid IHDR data
		 */
    String xmlfilePath=getClass().getResource("lbpcascade_frontalface.xml").getPath().substring(1);
    CascadeClassifier faceDetector = new CascadeClassifier(xmlfilePath);
    Mat image = Highgui.imread(getClass().getResource("test2.jpg").getPath().substring(1));
    // Detect faces in the image.
    // MatOfRect is a special container class for Rect.
    MatOfRect faceDetections = new MatOfRect();
    faceDetector.detectMultiScale(image, faceDetections);

    System.out.println(String.format("Detected %s faces", faceDetections.toArray().length));

    // Draw a bounding box around each face.
    for (Rect rect : faceDetections.toArray()) {
        Core.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 255, 0));
    }

    // Save the visualized detection.
    System.out.println(String.format("Writing %s", filename));
    Highgui.imwrite(filename, image);
  }
}

