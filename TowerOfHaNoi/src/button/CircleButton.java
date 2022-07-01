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


/*
 * class CircleButton chuyển shape mặc định của JButton từ hình chữ nhật sang hình tròn
 * CircleButton kế thừa thừ JButton và implements MyShape
 */
public class CircleButton extends JButton implements MyShape{
	
	/*
	 * button có hai ảnh icon tương ứng với hai trạng thái(normal hoặc hover)
	 */
	ImageIcon iconNormal;
	ImageIcon iconHover;
	
	/*
	 * constructor using radius and iconIndex
	 */
	public CircleButton(int radius, int iconIndex) {
		super();
		setBackground(new Color(106, 139, 158));
		setFocusable(false);
		iconNormal = new ImageIcon(Frame.texture.iconNormal[iconIndex].getScaledInstance(radius, radius, Image.SCALE_DEFAULT));
		iconHover = new ImageIcon(Frame.texture.iconHover[iconIndex].getScaledInstance(radius, radius, Image.SCALE_DEFAULT));
		setIcon(iconNormal);
		
		// enlarge the button so that is becomes a circle rather than an oval
		Dimension size = getPreferredSize();
		size.width = size.height = Math.max(size.width, size.height);
		
		//this call cause the JButton not to paint the background. Allows us to paint the background
		setContentAreaFilled(false);
		setRolloverEnabled(false);
		setFocusPainted(false);
		
		
		/*
		 * Chuyển đổi giữa hai loại icons normal và hover khi di chuyển chuột vào button và di chuyển ra khỏi button 
		 */
		addMouseListener(new MouseAdapter() {
	         public void mouseEntered(MouseEvent e) {
	        	 setIconHover();
	         }
	         public void mouseExited(MouseEvent e) {
	        	 setIconNormal();
	         }
	    });
	}
	
	
	/*
	 * @overriding the constructor using 2 state of icons(normal and hover)
	 */
	public CircleButton(ImageIcon iconNormal,  ImageIcon iconHover) {
		super();
		this.iconNormal = iconNormal;
		this.iconHover = iconHover;
		
		setBackground(new Color(106, 139, 158));
		setFocusable(false);
		setIcon(iconNormal);
		
		// enlarge the button so that is becomes a circle rather than an oval
		Dimension size = getPreferredSize();
		size.width = size.height = Math.max(size.width, size.height);
		
		//this call cause the JButton not to paint the background. Allows us to paint the background
		setContentAreaFilled(false);
		setRolloverEnabled(false);
		setFocusPainted(false);
		
		addMouseListener(new MouseAdapter() {
	         public void mouseEntered(MouseEvent e) {
	        	 setIconHover();
	         }
	         public void mouseExited(MouseEvent e) {
	        	 setIconNormal();
	         }
	    });
	}
	
	public void setIconNormal() {
		setIcon(iconNormal);
	}
	
	public void setIconHover() {
		setIcon(iconHover);
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
			 shape = setShape(getWidth(), getHeight());
	    }
	    return shape.contains(x, y);
	 }


	public Shape setShape(int width, int height) {
		Shape s = new Ellipse2D.Float(0, 0, width, height);
		return s;
	}

	public ImageIcon getIconNormal() {
		return iconNormal;
	}

	public void setIconNormal(ImageIcon iconNormal) {
		this.iconNormal = iconNormal;
	}

	public ImageIcon getIconHover() {
		return iconHover;
	}

	public void setIconHover(ImageIcon iconHover) {
		this.iconHover = iconHover;
	}
}
