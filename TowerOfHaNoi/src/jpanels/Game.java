package jpanels;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import button.CircleButton;
import frame.Frame;
import label.CustomLabel;
import objects.Disk;
import objects.Rod;

public class Game extends JPanel {

	/**
	 * Create the panel.
	 */
	
	public static int height = 3;
	private boolean allowMove = false;
	private boolean allowPick = false;
	private Rod rod[] = new Rod[4];
	private Disk disk;
	protected CustomLabel lblStepCounter;
	private int step = 0;
	protected JLabel background;
	CustomLabel lblNoti;
	
	public Game() {
		setLayout(null);
		setBounds(0, 0, 1080, 720);
		
		//components
		CircleButton btnLauncher = new CircleButton(50, 4);
		btnLauncher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Frame.launcherOn();
			}
		});
		btnLauncher.setBounds(912, 11, 50, 50);
		add(btnLauncher);
		
		lblStepCounter = new CustomLabel("Step: 0", 20);
		lblStepCounter.setBounds(339, 31, 133, 20);
		add(lblStepCounter);
		
		CustomLabel lblStepExpect = new CustomLabel("Expect Step: " + (int)(Math.pow(2, height)-1), 20);
		lblStepExpect.setBounds(535, 25, 214, 26);
		add(lblStepExpect);
		
		
		lblNoti = new CustomLabel("Not legal!", 20);
		lblNoti.setBounds(926, 103, 133, 35);
		lblNoti.setVisible(false);
		add(lblNoti);
		
		background = new JLabel(new ImageIcon(Frame.texture.background));
		background.setBounds(getBounds());
		
		initObject(height);
	}
	
	public void initObject(int height) {
		// rods
		initRods();
				
		// disk
		Disk baseDisk = rod[1].peekStack();
		for(int i = 0; i < height; i++) {
			disk = new Disk(new ImageIcon(Frame.texture.diskImg[0].getScaledInstance(baseDisk.getWidth()-38, baseDisk.getHeight()-5, Image.SCALE_DEFAULT)));
//			disk.setBounds(baseDisk.getX()+19, baseDisk.getY()-baseDisk.getHeight()+10, baseDisk.getWidth()-38, baseDisk.getHeight()-5);
			disk.setLocation(baseDisk.getX()+19, baseDisk.getY()-baseDisk.getHeight()+10);
			disk.setShape(baseDisk.getWidth()-38, baseDisk.getHeight()-5);
			add(disk);
			rod[1].pushStack(disk);
			baseDisk = disk;
		}
		
		// add to panel
		add(rod[1]);
		add(rod[2]);
		add(rod[3]);
	}
	
	public void deleteObject() {
		for(int i = 0; i < rod.length; i++) {
			rod[i] = null;
		}
	}
	
	public void initRods() {
		
		rod[1] = new Rod(new ImageIcon(Frame.texture.rodImg[0].getScaledInstance(32, 300, Image.SCALE_DEFAULT)));
//		rod[1].setBounds(270, 200, 32, 300);
		rod[1].setShape(32, 300);
		rod[1].setLocation(270, 200);
		disk = new Disk(null);
		disk.setBounds(151, 500, 270, 63);
		rod[1].pushStack(disk);
		rod[1].addMouseListener(new MouseAdapter() {
	        public void mouseClicked(MouseEvent e) {
	            if(e.getClickCount() > 0 && isAllowPick()){
	            	if(isRodPick()==false) {
	            		setRodPick(1);
	            	} else if(rod[1].isPick()==true){
	            		unPickRod(1);
	            	} else {
	            		moveDisk(getRodPick(), 1);
	            	}
	            }
	        }
	    });
		
		
		
		rod[2] = new Rod(new ImageIcon(Frame.texture.rodImg[0].getScaledInstance(32, 300, Image.SCALE_DEFAULT)));
//		rod[2].setBounds(540, 200, 32, 300);
		rod[2].setShape(32, 300);
		rod[2].setLocation(540, 200);
		disk = new Disk(null);
		disk.setBounds(421, 500, 270, 63);
		rod[2].pushStack(disk);
		rod[2].addMouseListener(new MouseAdapter() {
	        public void mouseClicked(MouseEvent e) {
	            if(e.getClickCount() > 0 && isAllowPick())   {
	            	if(isRodPick()==false) {
	            		setRodPick(2);
	            	} else if(rod[2].isPick()==true){
	            		unPickRod(2);
	            	} else {
	            		moveDisk(getRodPick(), 2);
	            	}
	            }
	        }
	    });
		
		
		
		rod[3] = new Rod(new ImageIcon(Frame.texture.rodImg[0].getScaledInstance(32, 300, Image.SCALE_DEFAULT)));
//		rod[3].setBounds(810, 200, 32, 300);
		rod[3].setShape(32, 300);
		rod[3].setLocation(810, 200);
		disk = new Disk(null);
		disk.setBounds(691, 500, 270, 63);
		rod[3].pushStack(disk);
		rod[3].addMouseListener(new MouseAdapter() {
	        public void mouseClicked(MouseEvent e) {
	        	if(isWin())
	        		Frame.winOn();
	        	
	            if(e.getClickCount() > 0 && isAllowPick())   {
	            	if(isRodPick()==false) {
	            		setRodPick(3);
	            	} else if(rod[3].isPick()==true){
	            		unPickRod(3);
	            	} else {
	            		moveDisk(getRodPick(), 3);
	            	}
	            }
	        }
	    });
		
	}
	
	public boolean isWin() {
		if(rod[3].stack.size()==GamePlay.height)
			return true;
		
		return false;
	}
	
	public void setRodPick(int id) {
		rod[id].setPick(true);
		Disk disk = rod[id].peekStack();
		disk.setIcon(new ImageIcon(Frame.texture.diskImg[2].getScaledInstance(disk.getWidth(), disk.getHeight(), Image.SCALE_DEFAULT)));
	}
	
	public void unPickRod(int id) {
		rod[id].setPick(false);
		Disk disk = rod[id].peekStack();
		disk.setIcon(new ImageIcon(Frame.texture.diskImg[0].getScaledInstance(disk.getWidth(), disk.getHeight(), Image.SCALE_DEFAULT)));
	}
	
	
	public boolean isRodPick() {
		if(rod[1].isPick() || rod[2].isPick() || rod[3].isPick())
			return true;
		
		return false;
	}
	
	public int getRodPick() {
		for(int i = 1; i <= 3; i++) {
			if(rod[i].isPick()==true)
				return i;
		}
		return 0;
	}
	
	public boolean isMoveLegal(int from, int to) {
		Disk upper = rod[from].peekStack();
		Disk below = rod[to].peekStack();
		if(upper.getWidth()<below.getWidth() && upper.getHeight()<below.getHeight())
			return true;
		else
			notiNotLegal();
		
		return false;
	}
	
	public void moveDisk(int from, int to) {
		if(isMoveLegal(from, to) && isAllowMove()) {
			step++;
			lblStepCounter.setText("Step: " + step);
			rod[from].setPick(false);
			Disk disk = rod[from].popStack();
			Disk baseDisk = rod[to].stack.peek();
			disk.setBounds(baseDisk.getX()+(baseDisk.getWidth()-disk.getWidth())/2, baseDisk.getY()-disk.getHeight()+5, disk.getWidth(), disk.getHeight());
			disk.setIcon(new ImageIcon(Frame.texture.diskImg[0].getScaledInstance(disk.getWidth(), disk.getHeight(), Image.SCALE_DEFAULT)));
			rod[to].pushStack(disk);
		}
	}


	public boolean isAllowMove() {
		return allowMove;
	}


	public void setAllowMove(boolean allowMove) {
		this.allowMove = allowMove;
	}

	public boolean isAllowPick() {
		return allowPick;
	}

	public void setAllowPick(boolean allowPick) {
		this.allowPick = allowPick;
	}

	
	public void notiNotLegal() {
		lblNoti.setVisible(true);
		
		int delay = 1300; //milliseconds
		ActionListener taskPerformer = new ActionListener() {
          public void actionPerformed(ActionEvent evt) {
                lblNoti.setVisible(false);
            }
        };
        Timer tick = new Timer(delay,taskPerformer);
        tick.setRepeats(false);
        tick.start();
		
	}
	
}


