import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;


public class DetectBallDemo {
	

	public void run() {  
		Panel panel1 = new Panel();
		Frame frame1 = new Frame(panel1, "Picture");
	    

		Panel panel2 = new Panel();
		Frame frame2 = new Frame(panel2, "Picture");
	    
		Panel panel3 = new Panel();
		Frame frame3 = new Frame(panel3, "Picture");
		
//		Panel panel4 = new Panel();
//		Frame frame4 = new Frame(panel4, "Picture");
	    
		Mat image = Highgui.imread("res/rb.png");
		Mat image2 = new Mat();
		Mat image3 = new Mat();
//		Mat image4 = Highgui.imread("res/TestPic.png");

//		Imgproc.cvtColor(image4, image4, Imgproc.COLOR_BGR2HSV);
		Imgproc.cvtColor(image, image2, Imgproc.COLOR_BGR2HSV);
		int tn = 10;
		// Red 0 - 20
		// Yellow 20 - 35
		//		Nothing 35 - 55
		// Green 55 - 70
		Scalar hsv_min = new Scalar(tn - 10, 100, 100, 0);
		Scalar hsv_max = new Scalar(tn, 255, 255, 0);
		
		Core.inRange(image2, hsv_min, hsv_max, image3);
	    
	    panel1.setimagewithMat(image);
	    frame1.repaint();
	    panel2.setimagewithMat(image2);
	    frame2.repaint();
	    panel3.setimagewithMat(image3);
	    frame3.repaint();
//	    panel4.setimagewithMat(image4);
//	    frame4.repaint();
	    
	    //Successful push from laptop!
	    System.out.println("Success");
	}

}
