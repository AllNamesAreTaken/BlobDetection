import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;


public class Frame extends JFrame{
	public static int x = 0;
	public static int y = 0;
	public static boolean isOpen = true;
	public static PButton addb;
	public static MButton subb;
	public static int aColor = 0;
	public Frame (Panel panel, String name, int sizex, int sizey) {
		super(name);
		addWindowListener(new java.awt.event.WindowAdapter() {
		    public void windowClosing(WindowEvent winEvt) {
		    	isOpen = false;
		        System.exit(0);
		    }
		});
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(sizex+50, sizey+50);  
		setBounds(0, 0, getWidth(), getHeight());  
		setContentPane(panel);
		subb = new MButton("-");
		add(subb);
		addb = new PButton("+");
		add(addb);
	    setVisible(true);
	    setLocation(x, y);
	    x += 400;
	    y += 100;
	}
	
	public class PButton extends JButton {
		public PButton(String name){
			super(name);
	        addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					aColor += 10;
					System.out.println("aColor = "+aColor);
				}
	        });
		}
	}
	public class MButton extends JButton {
		public MButton(String name){
			super(name);
	        addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					aColor -= 10;
					System.out.println("aColor = "+aColor);
				}
	        });
		}
	}
}
