package main;

import javax.swing.JFrame;


public class Main {
	public static void main(String[] args) {
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("2d MGM");
		
		Gamepannel gamepannel = new Gamepannel();
		window.add(gamepannel);
		
		window.pack();
		
		 
		window.setLocationRelativeTo(null);
		window.setVisible(true);
			
		gamepannel.setupGame();  //we need to call the objects method before the gamethread so that the keys and the chest are present when the game is started.
		gamepannel.startGameThread();
		
		
	}
}
