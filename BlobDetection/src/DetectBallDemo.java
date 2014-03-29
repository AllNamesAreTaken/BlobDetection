import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.highgui.Highgui;
import org.opencv.highgui.VideoCapture;
import org.opencv.imgproc.Imgproc;


public class DetectBallDemo {
	public int sizex = 800;
	public int sizey = 800;

	public void run() {  
		Panel panel1 = new Panel();
		Frame frame1 = new Frame(panel1, "Picture", sizex, sizey);
	    

		Panel panel2 = new Panel();
		Frame frame2 = new Frame(panel2, "Picture", sizex, sizey);
	    
		Panel panel3 = new Panel();
		Frame frame3 = new Frame(panel3, "Picture", sizex, sizey);

		VideoCapture capture = new VideoCapture(1);
		capture.set(3, sizex - 100);
		capture.set(4, sizey - 100);
	    
		Mat image = Highgui.imread("res/rb.png");
		Mat image2 = new Mat();
		Mat image3 = new Mat();

		int tn = 10;
		// H: 0 - 180
		// S: 0 - 255
		// V: 0 - 255
		Scalar hsv_min = new Scalar(tn - 10, 50, 200, 0);
		Scalar hsv_max = new Scalar(tn, 255, 255, 0);
		while(capture.isOpened()) {
			capture.read(image);
			Imgproc.cvtColor(image, image2, Imgproc.COLOR_BGR2HSV);
			
			Core.inRange(image2, hsv_min, hsv_max, image3);
		    
		    panel1.setimagewithMat(image);
		    frame1.repaint();
		    panel2.setimagewithMat(image2);
		    frame2.repaint();
		    panel3.setimagewithMat(image3);
		    frame3.repaint();
		}
	    //Successful update from desktop!
		capture.release();
	    System.out.println("Success");
	}

}
