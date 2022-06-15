package jpanels;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import button.CircleButton;
import frame.Frame;

public class Hint extends Game {

	/**
	 * Create the panel.
	 */
	private int index = 0;
	private List<int[]> step = new ArrayList<int[]>();
	
	public Hint(){
		super();
		setAllowMove(true);
		setAllowPick(false);
		solution();
		
		CircleButton btnNext = new CircleButton(50, 6);
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		});
		btnNext.setBounds(675, 550, 50, 50);
		add(btnNext);
		
		CircleButton btnBack = new CircleButton(50, 0);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				index--;
				index = Math.max(0, index);
				
				int from = step.get(index)[1];
				int to = step.get(index)[0];
				moveDisk(from, to);
			}
		});
		btnBack.setBounds(400, 550, 50, 50);
		add(btnBack);
		
		CircleButton btnPlay = new CircleButton(50, 5);
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Frame.gameOn();
			}
		});
		btnPlay.setBounds(110, 11, 50, 50);
		add(btnPlay);
		
		
		add(background);
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

}
