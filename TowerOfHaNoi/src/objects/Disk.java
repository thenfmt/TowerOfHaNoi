package objects;

import java.awt.Shape;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import myInterface.MyShape;

public class Disk extends JLabel implements MyShape{
	public Disk(ImageIcon img) {
		super(img);
	}

	@Override
	public Shape setShape(int width, int height) {
		this.setSize(width, height);
		return null;
	}
}
