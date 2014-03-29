import javax.swing.JFrame;


public class Frame extends JFrame{
	public static int x = 0;
	public static int y = 0;
	public Frame (Panel panel, String name, int sizex, int sizey) {
		super(name);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(sizex, sizey - 100);  
		setBounds(0, 0, getWidth(), getHeight());  
		setContentPane(panel);  
	    setVisible(true);
	    setLocation(x, y);
	    x += 400;
	}
	
}
