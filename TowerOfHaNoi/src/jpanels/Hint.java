package jpanels;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.Timer;

import button.CircleButton;
import frame.Frame;

public class Hint extends Game {

	/**
	 * Create the panel.
	 */
	private boolean pause = false;
	private int index = 0;
	private List<int[]> step = new ArrayList<int[]>();
	private Timer timer;
	
	public Hint(){
		super();
		setAllowMove(true);
		setAllowPick(false);
		solution();
		
		CircleButton btnNext = new CircleButton(50, 7);
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				moveNext();
			}
		});
		btnNext.setBounds(675, 550, 50, 50);
		add(btnNext);
		
		CircleButton btnBack = new CircleButton(50, 1);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				moveBack();
			}
		});
		btnBack.setBounds(400, 550, 50, 50);
		add(btnBack);
		
		CircleButton btnPlay = new CircleButton(50, 3);
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Frame.gameOn();
			}
		});
		btnPlay.setBounds(110, 11, 50, 50);
		add(btnPlay);
		
		ImageIcon playNormal = new ImageIcon(Frame.texture.iconNormal[8].getScaledInstance(50, 50, Image.SCALE_DEFAULT));
		ImageIcon playHover = new ImageIcon(Frame.texture.iconHover[8].getScaledInstance(50, 50, Image.SCALE_DEFAULT));
		ImageIcon pauseNormal = new ImageIcon(Frame.texture.iconNormal[3].getScaledInstance(50, 50, Image.SCALE_DEFAULT));
		ImageIcon pauseHover = new ImageIcon(Frame.texture.iconHover[3].getScaledInstance(50, 50, Image.SCALE_DEFAULT));
		
		CircleButton btnPause = new CircleButton(playNormal, playHover);
		btnPause.setBounds(538, 550, 50, 50);
		btnPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(isPause()==false) {
					setPause(true);
					btnPause.setIconNormal(pauseNormal);
					btnPause.setIconHover(pauseHover);
				}else {
					setPause(false);
					btnPause.setIconNormal(playNormal);
					btnPause.setIconHover(playHover);
					timer.start();
				}
					
			}
		});
		add(btnPause);
		autoRun();
		
		add(background);
	}
	
	
	public void moveNext() {
		index = Math.min(step.size(), index);
		if(index >= step.size()) {
			notiNotLegal();
			return;
		}
					
		int from = step.get(index)[0];
		int to = step.get(index)[1];
		moveDisk(from, to);
		index++;
	}
	
	public void moveBack() {
		index--;
		index = Math.max(0, index);
		
		int from = step.get(index)[1];
		int to = step.get(index)[0];
		moveDisk(from, to);
	}
	
	
	public void autoRun() {
		timer = new Timer(1000, new ActionListener() {
			  @Override
			  public void actionPerformed(ActionEvent arg0) {
			    	if(isPause()==false)
			    		moveNext();
			    	else 
			    		timer.stop();
			  }
			});
		timer.setRepeats(true); 
		timer.start(); 
	}
	 
	
	public void solution() {
		recusive(GamePlay.height, 1, 3, 2);
	}
	
	public void recusive(int height, int A, int C, int B) {
		if(height==0)
			return;
		recusive(height-1, A, B, C);
		int arr[] = {A, C};
		step.add(arr);
		recusive(height-1, B, C, A);
	}


	public boolean isPause() {
		return pause;
	}


	public void setPause(boolean pause) {
		this.pause = pause;
	}
}
