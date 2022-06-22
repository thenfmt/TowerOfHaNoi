package label;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;

import javax.swing.JLabel;

public class CustomLabel extends JLabel{
	public CustomLabel(String title, int fontSize) {
		super(title);
		Font font = new Font("Arial", Font.BOLD, fontSize);
		
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResource("/font/SP3-Traveling-Typewriter.otf").openStream());
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   

		GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();
		genv.registerFont(font);
		font = font.deriveFont(Font.BOLD, (float)fontSize);
		
		
		setFont(font);
		setForeground(new Color(206, 202, 191));
	}

}
