import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;


public class Button extends JButton{
	FunctionType functionType;
	public enum FunctionType {
		AA, A, S, SS, AAW, AW, SW, SSW
	}
	
	public Button(String name, final FunctionType ft) {
		super(name);
        addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			switch(ft) {
			case AA:
						Frame.aColor += 10;
						updateText();
				break;
			case A:
						Frame.aColor += 1;
						updateText();
				break;
			case S:
						Frame.aColor -= 1;
						updateText();
				break;
			case SS:
						Frame.aColor -= 10;
						updateText();
				break;
			case AAW:
						Frame.aColorW += 10;
						updateText();
				break;
			case AW:
						Frame.aColorW += 1;
						updateText();
				break;
			case SW:
						Frame.aColorW -= 1;
						updateText();
				break;
			case SSW:
						Frame.aColorW -= 10;
						updateText();
				break;
			}

			}
        });
	}
	
	public static void updateText() {
		Frame.aColorDis.setText("Color Start: " + Frame.aColor);
		Frame.aColorDisW.setText("Color End: " + Frame.aColorW);
	}
}
