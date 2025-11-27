package object;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import main.Gamepannel;
import main.UtilityTool;

public class SuperObject {

	public BufferedImage image,image2,image3;
	public String name;
	public boolean collision = false;
	public int worldx, worldy;     
	public Rectangle solidarea  = new Rectangle(0,0,48,48);   //used for keys and chest collision.
	public int solidareadefaultx = 0;
	public int solidareadefaulty = 0;
	
	UtilityTool utool = new UtilityTool();
	
	
	//if we know the objects worldx and worldy then we can know the screenx on screeny based on the players position.
	
	public void draw(Graphics2D g2,Gamepannel gp) {
		
		 int screenx = worldx - gp.player.worldx + gp.player.screenx;    //player.world-x means the position of the player in the world.
		 int screeny = worldy - gp.player.worldy + gp.player.screeny;    //player.screenx=0 and screen-y=0 means the 1st block which is visible in the screen or panel.
		 
		 if(worldx + gp.tilesize > gp.player.worldx - gp.player.screenx && 
			worldx - gp.tilesize < gp.player.worldx + gp.player.screenx &&
			worldy + gp.tilesize > gp.player.worldy - gp.player.screeny &&
			worldy - gp.tilesize < gp.player.worldy + gp.player.screeny) {
			 
			 g2.drawImage(image , screenx,screeny ,gp.tilesize,gp.tilesize,null);
		 }
		
		 //to draw the key in the game we need to call this draw method in the game panel class.
		
	}
}
