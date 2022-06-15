package jpanels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import button.CircleButton;
import frame.Frame;
import label.CustomLabel;
import java.awt.Rectangle;

public class Win extends JPanel {

	/**
	 * Create the panel.
	 */
	public Win() {
		setLayout(null);
		
		CircleButton btnLauncher = new CircleButton(50, 4);
		btnLauncher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Frame.launcherOn();
			}
		});
		btnLauncher.setBounds(419, 426, 50, 50);
		add(btnLauncher);
		
		CircleButton btnPlay = new CircleButton(50, 5);
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Frame.gameOn();
			}
		});
		btnPlay.setBounds(616, 426, 50, 50);
		add(btnPlay);
		
		CustomLabel lblWin = new CustomLabel("Win", 100);
		lblWin.setHorizontalAlignment(SwingConstants.CENTER);
		lblWin.setBounds(348, 138, 360, 191);
		add(lblWin);
		
		JLabel background = new JLabel(new ImageIcon(Frame.texture.background));
		background.setBounds(new Rectangle(0, 0, 1080, 720));
		add(background);
	}
}