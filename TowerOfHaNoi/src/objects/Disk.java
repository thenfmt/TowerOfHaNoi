package objects;

import java.awt.Shape;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import myInterface.MyShape;

public class Disk extends JLabel implements MyShape{
	private ImageIcon iconNormal;
	private ImageIcon iconHover;
	private ImageIcon iconClicked;
	public boolean basekDisk = false;
	
	
	public Disk(ImageIcon iconNormal, ImageIcon iconHover, ImageIcon iconClicked) {
		super(iconNormal);
		this.iconNormal = iconNormal;
		this.iconHover = iconHover;
		this.iconClicked = iconClicked;
	}
	
	public void setIconNormal() {
		setIcon(iconNormal);
	}
	
	public void setIconHover() {
		setIcon(iconHover);
	}
	
	public void setIconClicked() {
		setIcon(iconClicked);
	}

	@Override
	public Shape setShape(int width, int height) {
		this.setSize(width, height);
		return null;
	}

	public boolean isBasekDisk() {
		return basekDisk;
	}

	public void setBasekDisk(boolean baskDisk) {
		this.basekDisk = baskDisk;
	}
}
