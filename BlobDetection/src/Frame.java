import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;


public class Frame extends JFrame{
	public static int x = 0;
	public static int y = 0;
	public static boolean isOpen = true;
	public static Button close;
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
//		close = new Button("Close");
//		add(close);
	    setVisible(true);
	    setLocation(x, y);
	    x += 400;
	    y += 100;
	}
	
	public class Button extends JButton {
		public Button(String name){
			super(name);
	        addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					isOpen = false;
					System.exit(0);
				}
	        });
		}
	}
	
}
