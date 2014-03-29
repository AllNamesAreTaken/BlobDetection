import javax.swing.JFrame;


public class Frame extends JFrame{
	public static int x = 0;
	public static int y = 0;
	public Frame (Panel panel, String name) {
		super(name);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 400);  
		setBounds(0, 0, getWidth(), getHeight());  
		setContentPane(panel);  
	    setVisible(true);
	    setLocation(x, y);
	    x += 400;
	}
	
}
