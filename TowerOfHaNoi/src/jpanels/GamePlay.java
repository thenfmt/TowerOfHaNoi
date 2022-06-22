package jpanels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import button.CircleButton;
import frame.Frame;

public class GamePlay extends Game {
	
	public GamePlay() {
		super();
		setAllowMove(true);
		setAllowPick(true);
		
		CircleButton btnHint = new CircleButton(30, 4);
		btnHint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Frame.HintOn();
			}
		});
		btnHint.setBounds(110, 25, 30, 30);
		add(btnHint);
		
		add(background);
	}
}
