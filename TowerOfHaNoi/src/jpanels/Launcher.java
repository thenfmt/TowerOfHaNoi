package jpanels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import button.CircleButton;
import frame.Frame;
import label.CustomLabel;

public class Launcher extends JPanel {
	
	public Launcher() {
		
		setLayout(null);
		setBounds(0, 0, 1080, 720);
		
		CircleButton btnPlay = new CircleButton(50, 3);
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Frame.gameOn();
			}
		});
		btnPlay.setBounds(337, 443, 50, 50);
		add(btnPlay);
		
		CustomLabel lblHeight = new CustomLabel("Height: 3", 20);
		lblHeight.setBounds(497, 443, 110, 50);
		add(lblHeight);
		
		CircleButton btnAdd = new CircleButton(25, 0);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Game.height = Math.min(Game.height+1, 5);
				lblHeight.setText("Height: " + Game.height);
			}
		});
		btnAdd.setBounds(597, 443, 50, 50);
		add(btnAdd);
		
		CircleButton btnMinus = new CircleButton(25, 6);
		btnMinus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Game.height = Math.max(Game.height-1, 3);
				lblHeight.setText("Height: " + Game.height);
			}
		});
		btnMinus.setBounds(437, 443, 50, 50);
		add(btnMinus);
		
		CircleButton btnExit = new CircleButton(50, 2);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(691, 443, 50, 50);
		add(btnExit);
		
		
		
		CustomLabel lblTitle1 = new CustomLabel("TOWER", 80);
		lblTitle1.setBounds(411, 93, 265, 73);
		add(lblTitle1);
		
		CustomLabel lblTitle2 = new CustomLabel("of", 70);
		lblTitle2.setBounds(498, 187, 97, 73);
		add(lblTitle2);
		
		CustomLabel lblTitle3 = new CustomLabel("HA NOI", 80);
		lblTitle3.setBounds(399, 271, 284, 73);
		add(lblTitle3);
		
		
		JLabel background = new JLabel(new ImageIcon(Frame.texture.background));
		background.setBounds(getBounds());
		add(background);
	}
}
