import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.highgui.Highgui;
import org.opencv.highgui.VideoCapture;
import org.opencv.imgproc.Imgproc;


public class DetectBallDemo {
	public int sizex = 700;
	public int sizey = 700;
	public static double[] bi = new double[3];

	public void run() {  

//		Panel panel5 = new Panel();
//		Frame frame5 = new Frame(panel5, "Picture", sizex, sizey);

//		VideoCapture capture = new VideoCapture(1);
//		capture.set(3, sizex - 100);
//		capture.set(4, sizey - 100);
	    
		Mat image = Highgui.imread("res/TestPic.png");
		Mat image2 = new Mat();
		Mat image3 = new Mat();
		Mat image4 = new Mat();
//		Mat image5 = new Mat();

		Panel panel1 = new Panel();
		Frame frame1 = new Frame(panel1, "Image", image.width(), image.height());
	    

		Panel panel2 = new Panel();
		Frame frame2 = new Frame(panel2, "HSV", image.width(), image.height());
	    
		Panel panel3 = new Panel();
		Frame frame3 = new Frame(panel3, "Grayscale", image.width(), image.height());
		
		Panel panel4 = new Panel();
		Frame frame4 = new Frame(panel4, "CroppedBall", image.width(), image.height());

		int tn = 10;
		/*
		H: 0 - 180
			Hue is color in degrees
			Starts at red and cycles through all colors until it returns to red (180 and 0)
		S: 0 - 255
		V: 0 - 255
		*/
		Scalar hsv_min = new Scalar(0, 60, 60, 0);
		Scalar hsv_max = new Scalar(10, 255, 255, 0);

        Mat erode = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(3,3));
        Mat dilate = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(5,5));
        

		
//		Imgproc.pyrDown(image, image);
		//This may be important later for camera detection
		Imgproc.GaussianBlur(image, image, new Size(3,3), 2.0);
		Imgproc.cvtColor(image, image2, Imgproc.COLOR_BGR2HSV);

		Core.inRange(image2, hsv_min, hsv_max, image3);
		Imgproc.erode(image3, image3, erode);
		Imgproc.erode(image3, image3, erode);
		
		Imgproc.dilate(erode, erode, dilate);
		Imgproc.dilate(erode, erode, dilate);
	
//		List<MatOfPoint> contours = new ArrayList<>();
//
//        Imgproc.findContours(image3, contours, new Mat(), Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);
//        Imgproc.drawContours(image3, contours, -1, new Scalar(255,255,0));

        //Image5 contains a column with circle info, not an identified image apperantly
        Mat circles = new Mat();
        //HoughCircles:
        	//Image as Mat
        	//One long row of data as Mat (Stores x, y, and radius of each circle)
        		//Sample of two circles: [100, 200, 12.5, 20, 40, 2.2] is (x,y,r,x,y,r)
        	//Method of detection (I assume Hough is the technique and Gradient is the mat type)
        	//Resolution decay? (if x is parameter, new_res = cur_res/x)
        	//Distance from other circles (if spot is closer than this, ignore somehow)
        		//Since only one ball is detected, large number can go here
    		//Something to do with canny edge detection, not sure
        	//Also something to do with canny, smaller numbers find more circles
        	//Minimum Radius of circle (20)
        	//Maximum Radius of circle (100)
		Imgproc.HoughCircles(image3, circles, Imgproc.CV_HOUGH_GRADIENT, 2, 500, 50, 10, 20, 100);
		if(circles.cols() > 0) 
		{
			bi[0] = circles.get(0, 0)[0];
			bi[1] = circles.get(0, 0)[1];
			bi[2] = circles.get(0, 0)[2];
			int pad = 10;
			int rx = (int)(bi[0]-bi[2]-pad);
			int ry = (int)(bi[1]-bi[2]-pad);
			int rw = (int)((bi[2]+pad) * 2);
			int rh = (int)((bi[2]+pad) * 2);
			
			if(rx < 0) rx = 0;
			else if(rx > image3.width()) rx = image3.width();
			if(ry < 0) ry = 0;
			else if(ry > image3.height()) ry = image3.height();
			if((rw + rx) > image3.width()) rw = image3.width() - rx;
			if((rh + ry) > image3.width()) rh = image3.width() - ry;
			
			Rect rec = new Rect(rx, ry, rw, rh);
			image4 = new Mat(image, rec);
		}
		for(int i = 0; i < circles.cols(); i++) {
			double vCircle[] = circles.get(0, i);
			int rCircle = (int)Math.round(vCircle[2]);
			Point pt = new Point();
			pt.x = Math.round(vCircle[0]);
			pt.y = Math.round(vCircle[1]);
			
			
			Core.circle(image, pt, rCircle, new Scalar(0,255,0), 5);
		}
		
		System.out.println(circles.dump());
	    panel1.setimagewithMat(image);
	    frame1.repaint();
	    panel2.setimagewithMat(image2);
	    frame2.repaint();
	    panel3.setimagewithMat(image3);
	    frame3.repaint();
	    panel4.setimagewithMat(image4);
	    frame4.repaint();
        
//		while(Frame.isOpen) {
//			
//			capture.read(image);
////			Imgproc.pyrDown(image, image);
//			Imgproc.dilate(image, image, new Mat());
//			Imgproc.dilate(image, image, new Mat());
////			Imgproc.GaussianBlur(src, dst, ksize, sigmaX);
//			Imgproc.cvtColor(image, image2, Imgproc.COLOR_BGR2HSV);
//
//			Core.inRange(image2, hsv_min, hsv_max, image3);
//			image3.copyTo(image4);
//			Imgproc.erode(image3, image3, erode);
//			Imgproc.erode(image3, image3, erode);
//			
//			Imgproc.dilate(erode, erode, dilate);
//			Imgproc.dilate(erode, erode, dilate);
//			
//		
//			List<MatOfPoint> contours = new ArrayList<>();
//
//	        Imgproc.findContours(image3, contours, new Mat(), Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);
//	        Imgproc.drawContours(image3, contours, -1, new Scalar(255,255,0));
//	        
//		    panel1.setimagewithMat(image);
//		    frame1.repaint();
//		    panel2.setimagewithMat(image2);
//		    frame2.repaint();
//		    panel3.setimagewithMat(image3);
//		    frame3.repaint();
//		    panel4.setimagewithMat(image4);
//		    frame4.repaint();
//		}
        
	    //Successful update from desktop!
//		capture.release();
	    System.out.println(bi[0] + "/" + bi[1] + "/" + bi[2]);
	    System.out.println("Success");
//	    System.exit(0);
	}

}
