package button;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import frame.Frame;
import myInterface.MyShape;

public class CircleButton extends JButton implements MyShape{
	ImageIcon normalIcon;
	ImageIcon hoverIcon;
	
	public CircleButton(int radius, int iconIndex) {
		super();
		setBackground(new Color(106, 139, 158));
		setFocusable(false);
		normalIcon = new ImageIcon(Frame.texture.iconImg[iconIndex].getScaledInstance(radius, radius, Image.SCALE_DEFAULT));
		hoverIcon = new ImageIcon(Frame.texture.iconImg[iconIndex+8].getScaledInstance(radius, radius, Image.SCALE_DEFAULT));
		setIcon(normalIcon);
		
		// enlarge the button so that is becomes a circle rather than an oval
		Dimension size = getPreferredSize();
		size.width = size.height = Math.max(size.width, size.height);
		
		//this call cause the JButton not to paint the background. Allows us to paint the background
		setContentAreaFilled(false);
		setRolloverEnabled(false);
		setFocusPainted(false);
		
		addMouseListener(new MouseAdapter() {
	         public void mouseEntered(MouseEvent e) {
	        	 setIcon(hoverIcon);
	         }
	         public void mouseExited(MouseEvent e) {
	        	 setIcon(normalIcon);
	         }
	      });
	}
	
	
	protected void paintComponent(Graphics g) {
	    if (getModel().isArmed()) {
	      g.setColor(Color.lightGray);
	    } else {
	      g.setColor(getBackground());
	    }
	    g.fillOval(0, 0, getSize().width, getSize().height);

	    super.paintComponent(g);
	}


	 Shape shape;
	 public boolean contains(int x, int y) {
		 if (shape == null || !shape.getBounds().equals(getBounds())) {
//	      shape = new Ellipse2D.Float(0, 0, getWidth(), getHeight());
			 shape = setShape(getWidth(), getHeight());
	    }
	    return shape.contains(x, y);
	 }


	public Shape setShape(int width, int height) {
		Shape s = new Ellipse2D.Float(0, 0, width, height);
		return s;
	}
}
