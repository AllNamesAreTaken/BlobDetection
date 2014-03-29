import org.opencv.core.Core;



public class Start {

	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
//		Mat mat = Mat.zeros(5, 5, CvType.CV_8UC1);
//		System.out.println("mat = " + mat.dump());
		new DetectBallDemo().run();
	}

}

