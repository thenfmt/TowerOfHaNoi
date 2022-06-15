package frame;

import java.awt.CardLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import images.Texture;
import jpanels.GamePlay;
import jpanels.Hint;
import jpanels.Launcher;
import jpanels.Win;

public class Frame extends JFrame {

	private static JPanel contentPane;
	static CardLayout cardLayout;
	public static Texture texture;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame frame = new Frame();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public Frame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		texture = new Texture();
		setBounds(0, 0, 1105, 770);
		contentPane = new JPanel();
		contentPane.setVisible(true);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		cardLayout = new CardLayout();
		contentPane = new JPanel(cardLayout);
		
		Launcher launcher = new Launcher();
		contentPane.add("Launcher", launcher);
		
		getContentPane().add(contentPane, "mainPanel");
		
	}
	
	public static void gameOn() {
		GamePlay gamePlay = new GamePlay();
		contentPane.add("Game", gamePlay);
		
	    cardLayout.show(contentPane, "Game");
	}
	
	public static void launcherOn() {
		cardLayout.show(contentPane, "Launcher");
	}
	
	public static void winOn() {
		Win win = new Win();
		contentPane.add("Win", win);
		
		cardLayout.show(contentPane, "Win");
	}
	
	public static void HintOn() {
		Hint hint = new Hint();
		contentPane.add("Hint", hint);
		
		cardLayout.show(contentPane, "Hint");
	}
	
}
