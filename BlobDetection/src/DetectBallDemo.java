import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.highgui.Highgui;
import org.opencv.highgui.VideoCapture;
import org.opencv.imgproc.Imgproc;


public class DetectBallDemo {
	public int sizex = 700;
	public int sizey = 700;

	public void run() {  
		Panel panel1 = new Panel();
		Frame frame1 = new Frame(panel1, "Picture", sizex, sizey);
	    

		Panel panel2 = new Panel();
		Frame frame2 = new Frame(panel2, "Picture", sizex, sizey);
	    
		Panel panel3 = new Panel();
		Frame frame3 = new Frame(panel3, "Picture", sizex, sizey);
		
		Panel panel4 = new Panel();
		Frame frame4 = new Frame(panel4, "Picture", sizex, sizey);

		VideoCapture capture = new VideoCapture(0);
		capture.set(3, sizex - 100);
		capture.set(4, sizey - 100);
	    
		Mat image = Highgui.imread("res/rb.png");
		Mat image2 = new Mat();
		Mat image3 = new Mat();
		Mat image4 = new Mat();

		int tn = 10;
		/*
		H: 0 - 180
			Hue is color in degrees
			Starts at red and cycles through all colors until it returns to red (180 and 0)
		S: 0 - 255
		V: 0 - 255
		*/
		Scalar hsv_min = new Scalar(0, 100, 100, 0);
		Scalar hsv_max = new Scalar(10, 255, 255, 0);

        Mat erode = Imgproc.getStructuringElement(Imgproc.MORPH_ELLIPSE, new Size(3,3));
        Mat dilate = Imgproc.getStructuringElement(Imgproc.MORPH_ELLIPSE, new Size(5,5));
		while(Frame.isOpen) {
			
			capture.read(image);
			Imgproc.cvtColor(image, image2, Imgproc.COLOR_BGR2HSV);

			Core.inRange(image2, hsv_min, hsv_max, image3);
			image3.copyTo(image4);
			Imgproc.erode(image3, image3, erode);
			Imgproc.erode(image3, image3, erode);
			
			Imgproc.dilate(erode, erode, dilate);
			Imgproc.dilate(erode, erode, dilate);
			
		
			List<MatOfPoint> contours = new ArrayList<>();

	        Imgproc.findContours(image3, contours, new Mat(), Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);
	        Imgproc.drawContours(image3, contours, -1, new Scalar(255,255,0));
	        
		    panel1.setimagewithMat(image);
		    frame1.repaint();
		    panel2.setimagewithMat(image2);
		    frame2.repaint();
		    panel3.setimagewithMat(image3);
		    frame3.repaint();
		    panel4.setimagewithMat(image4);
		    frame4.repaint();
		}
	    //Successful update from desktop!
		capture.release();
	    System.out.println("Success");
	    System.exit(0);
	}

}
