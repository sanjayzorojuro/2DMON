package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class keyHandler implements KeyListener {

	public boolean uppress,downpress,leftpress,rightpress;
	
	

	public void keyTyped(KeyEvent e) {
		
	}

	public void keyPressed(KeyEvent e) {

		int code = e.getKeyCode();
		
		if (code == KeyEvent.VK_W) {
			uppress = true;
					
		}
		if (code == KeyEvent.VK_S) {
			downpress = true;
			
		}
		if (code == KeyEvent.VK_A) {
			leftpress = true;
			
		}
		if (code == KeyEvent.VK_D) {
			rightpress= true ;
			
		}
	}


	public void keyReleased(KeyEvent e) {
	
		int code = e.getKeyCode();
		
		
		if (code == KeyEvent.VK_W) {
			uppress = false;
					
		}
		if (code == KeyEvent.VK_S) {
			downpress = false;
			
		}
		if (code == KeyEvent.VK_A) {
			leftpress = false;
			
		}
		if (code == KeyEvent.VK_D) {
			rightpress= false ;
			
		}
	}
 
}
