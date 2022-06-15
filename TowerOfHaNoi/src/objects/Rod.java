package objects;

import java.util.Stack;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Rod extends JLabel {
	
	private boolean pick = false;
	public Stack<Disk> stack = new Stack<Disk>();
	
	public Rod(ImageIcon img) {
		super(img);
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

}
