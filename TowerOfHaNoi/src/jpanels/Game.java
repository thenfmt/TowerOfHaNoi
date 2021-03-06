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
import images.Texture;
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
	public static int step = 0;
	protected JLabel background;
	CustomLabel lblNoti;
	
	/*
	 * phương thức constructor khởi tạo những buttons và những label quan trọng
	 * constructor gọi đến phương thức initObject() khởi tạo những rod and disk 
	 */
	public Game() {
		setLayout(null);
		setBounds(0, 0, 1080, 720);
		
		//components
		CircleButton btnLauncher = new CircleButton(35, 5);
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
		// khởi tạo rod nhưng chưa thêm vào panel
		initRods();
				
		// khởi tạo các đĩa rồi thêm vào panel
		// ban đầu tạo mội đĩa base ảo (có shape và position nhưng không thể nhìn thấy và tương tác) để làm đĩa gốc
		// các đĩa sau base sẽ giảm đi chiều width-38 và height-5 để tạo sự cân đối cho ảnh.
		// lí do chọn số 38 và 5: qua các phép thử thấy cân đối nên giữ nguyên
		Disk baseDisk = rod[1].peekStack();
		for(int i = 0; i < height; i++) {
			ImageIcon diskIconNormal = new ImageIcon(Frame.texture.diskImg[0].getScaledInstance(baseDisk.getWidth()-38, baseDisk.getHeight()-5, Image.SCALE_DEFAULT));
			ImageIcon diskIconHover = new ImageIcon(Frame.texture.diskImg[1].getScaledInstance(baseDisk.getWidth()-38, baseDisk.getHeight()-5, Image.SCALE_DEFAULT));
			ImageIcon diskIconClicked = new ImageIcon(Frame.texture.diskImg[2].getScaledInstance(baseDisk.getWidth()-38, baseDisk.getHeight()-5, Image.SCALE_DEFAULT));
			
			disk = new Disk(diskIconNormal, diskIconHover, diskIconClicked);
			
			disk.setLocation(baseDisk.getX()+19, baseDisk.getY()-baseDisk.getHeight()+10);
			disk.setShape(baseDisk.getWidth()-38, baseDisk.getHeight()-5);
			add(disk);
			rod[1].pushStack(disk);
			
			baseDisk = disk;
		}
		
		// thêm các rod vào panel: nếu thêm trước lúc khởi tạo đĩa thì sẽ đè lên đĩa làm mất thẩm mỹ
		add(rod[1]);
		add(rod[2]);
		add(rod[3]);
	}
	
	
	public void initRods() {
		/*
		 * khởi tạo cột và thêm một base disk không thể tương tác.
		 * thêm những mouseListener vào những
		 */
		rod[1] = new Rod(new ImageIcon(Frame.texture.rodImg[0].getScaledInstance(32, 300, Image.SCALE_DEFAULT)),
						 new ImageIcon(Frame.texture.rodImg[1].getScaledInstance(32, 300, Image.SCALE_DEFAULT)));

		rod[1].setShape(32, 300);
		rod[1].setLocation(270, 200);
		disk = new Disk(null, null, null);
		disk.setEnabled(false);
		disk.setBasekDisk(true);
		disk.setBounds(151, 500, 270, 63);
		rod[1].pushStack(disk);
		rod[1].addMouseListener(new MouseAdapter() {
	        public void mouseClicked(MouseEvent e) {
	            if(e.getClickCount() > 0 && isAllowPick()){
	            	if(isRodPick()==false) {
	            		setRodPick(1); // pick rod with id = 1
	            	} else if(rod[1].isPick()==true){
	            		unPickRod(1); // unpick rod with id = 1
	            	} else {
	            		moveDisk(getRodPick(), 1);
	            	}
	            }
	        }
	        public void mouseEntered(MouseEvent e) {
	        	 rod[1].setIconHover();
	        	 if(!rod[1].isPick())
	        		 rod[1].peekStack().setIconHover();
	        }
	        public void mouseExited(MouseEvent e) {
	        	 rod[1].setIconNormal();
	        	 if(!rod[1].isPick())
	        		 rod[1].peekStack().setIconNormal();
	        }
	    });
		
		
		
		rod[2] = new Rod(new ImageIcon(Frame.texture.rodImg[0].getScaledInstance(32, 300, Image.SCALE_DEFAULT)),
				 		 new ImageIcon(Frame.texture.rodImg[1].getScaledInstance(32, 300, Image.SCALE_DEFAULT)));
		
		rod[2].setShape(32, 300);
		rod[2].setLocation(540, 200);
		disk = new Disk(null, null, null);
		disk.setEnabled(false);
		disk.setBasekDisk(true);
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
	        public void mouseEntered(MouseEvent e) {
	        	 rod[2].setIconHover();
	        	 if(!rod[2].isPick())
	        	 	rod[2].peekStack().setIconHover();
	        }
	        public void mouseExited(MouseEvent e) {
	        	 rod[2].setIconNormal();
	        	 if(!rod[2].isPick())
		        	 rod[2].peekStack().setIconNormal();
	        }
	    });
		
		
		
		rod[3] = new Rod(new ImageIcon(Frame.texture.rodImg[0].getScaledInstance(32, 300, Image.SCALE_DEFAULT)),
				 		 new ImageIcon(Frame.texture.rodImg[1].getScaledInstance(32, 300, Image.SCALE_DEFAULT)));

		rod[3].setShape(32, 300);
		rod[3].setLocation(810, 200);
		disk = new Disk(null, null, null);
		disk.setEnabled(false);
		disk.setBasekDisk(true);
		disk.setBounds(691, 500, 270, 63);
		rod[3].pushStack(disk);
		rod[3].addMouseListener(new MouseAdapter() {
	        public void mouseClicked(MouseEvent e) {
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
	        public void mouseEntered(MouseEvent e) {
	        	 rod[3].setIconHover();
	        	 if(!rod[3].isPick())
		        	 rod[3].peekStack().setIconHover();
	        }
	        public void mouseExited(MouseEvent e) {
	        	 rod[3].setIconNormal();
	        	 if(!rod[3].isPick())
		        	 rod[3].peekStack().setIconNormal();
	        }
	    });
		
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
	
	public boolean isWin() {
		if(rod[1].peekStack().isBasekDisk() && rod[2].peekStack().isBasekDisk())
			return true;
		
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
			
			if(isWin())
        		Frame.winOn();
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


