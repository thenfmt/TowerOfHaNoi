package objects;

import java.awt.Shape;
import java.util.Stack;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import myInterface.MyShape;

public class Rod extends JLabel implements MyShape {
	
	private boolean pick = false;
	public Stack<Disk> stack = new Stack<Disk>();
	
	private ImageIcon iconNormal;
	private ImageIcon iconHover;
	
	public Rod(ImageIcon iconNormal, ImageIcon iconHover) {
		super(iconNormal);
		this.iconNormal = iconNormal;
		this.iconHover = iconHover;
	}
	
	public void setIconNormal() {
		setIcon(iconNormal);
	}
	
	public void setIconHover() {
		setIcon(iconHover);
	}
	
	public void pushStack(Disk disk) {
		stack.push(disk);
	}
	
	public Disk peekStack() {
		return stack.peek();
	}
	
	public Disk popStack() {
		return stack.pop();
	}

	public boolean isPick() {
		return pick;
	}

	public void setPick(boolean pick) {
		this.pick = pick;
	}

	@Override
	public Shape setShape(int width, int height) {
		this.setSize(width, height);
		return null;
	}

}
