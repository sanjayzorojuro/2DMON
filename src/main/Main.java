package main;

import javax.swing.JFrame;


public class Main {
	
	
	public static JFrame window;
	
	public static void main(String[] args) {
		
		JFrame window = new JFrame();
	    Main.window = window;  
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("2d MON");
		
		Gamepannel gamepannel = new Gamepannel();
		window.add(gamepannel);
		
		window.pack();
		
		 
		window.setLocationRelativeTo(null);
		window.setVisible(true);
			
		gamepannel.setupGame();  //we need to call the objects method before the gamethread so that the keys and the chest are present when the game is started.
		gamepannel.startGameThread();
		
		
	}
}
