import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Frame extends JFrame{
	public static int nOfFrames = 0;
	public static int x = 0;
	public static int y = 0;
	public static boolean isOpen = true;
	public static Button add2b;
	public static Button addb;
	public static Button subb;
	public static Button sub2b;
	public static Button add2bw;
	public static Button addbw;
	public static Button subbw;
	public static Button sub2bw;
	public static JPanel buttonContainer;
	public static GridLayout buttonLayout =  new GridLayout(3, 5);
	public static int aColor = 0;
	public static int aColorW = 10;
	public static JLabel aColorDis;
	public static JLabel aColorDisW;
	public static JLabel ballPosx;
	public static JLabel ballPosy;
	public Frame (Panel panel, String name, int sizex, int sizey) {
		super(name);
		addWindowListener(new java.awt.event.WindowAdapter() {
		    public void windowClosing(WindowEvent winEvt) {
		    	isOpen = false;
		        System.exit(0);
		    }
		});
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setContentPane(panel);
		nOfFrames++;
		
		if(nOfFrames == 4){
			buttonContainer = new JPanel(buttonLayout);
			sub2b = new Button("--", Button.FunctionType.SS);
			buttonContainer.add(sub2b);
			subb = new Button("-", Button.FunctionType.S);
			buttonContainer.add(subb);
			addb = new Button("+", Button.FunctionType.A);
			buttonContainer.add(addb);
			add2b = new Button("++", Button.FunctionType.AA);
			buttonContainer.add(add2b);
			aColorDis = new JLabel("Color Start: " + aColor);
			buttonContainer.add(aColorDis);
			sub2bw = new Button("--", Button.FunctionType.SSW);
			buttonContainer.add(sub2bw);
			subbw = new Button("-", Button.FunctionType.SW);
			buttonContainer.add(subbw);
			addbw = new Button("+", Button.FunctionType.AW);
			buttonContainer.add(addbw);
			add2bw = new Button("++", Button.FunctionType.AAW);
			buttonContainer.add(add2bw);
			aColorDisW = new JLabel("Color End: " + aColorW);
			buttonContainer.add(aColorDisW);
			ballPosx = new JLabel("x=" + DetectBallDemo.bi[0]);
			ballPosy = new JLabel("y=" + DetectBallDemo.bi[1]);
			buttonContainer.add(ballPosx);
			buttonContainer.add(ballPosy);
			buttonContainer.add(new JLabel("Hue Range: "));
			buttonContainer.add(new JLabel("0 - 180"));
			setLayout(new BorderLayout());
			add(buttonContainer, BorderLayout.SOUTH);

			setSize(400, 300);
		}
		else{
			setSize(sizex+50, sizey+50);
		}  
		setBounds(0, 0, getWidth(), getHeight());  
				
	    setVisible(true);
	    setLocation(x, y);
	    x += 700;
	    if(x >= 1400) {
	    	x = 0;
	    	y += 500;
	    }
	}
	
}
