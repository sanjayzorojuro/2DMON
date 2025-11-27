package environment;

import java.awt.Graphics2D;

import entity.Player;
import main.Gamepannel;

public class EnvironmentManager {

	Gamepannel gp;
	Lighting lighting;
	Player player;
	
	
	
	public EnvironmentManager(Gamepannel gp) {
		this.gp = gp;
		
		
	}
	
	
	
	public void setup() {
		
		//drawing lighting
		lighting =  new Lighting(gp,350);     //this the code where we can change the size of the circle
		
		
		
	}
	
	public void updateLightning() {
		
		if(gp.currentMap != 1) {
			return;
		}
		
		int newCircleSize = calculateLightRadius();
		
		
		lighting = new Lighting(gp , newCircleSize);
		
		
	}
	
	private int calculateLightRadius() {
		int baseLightRadius = 350;
		int torchCount = gp.player.hastorch;
		
		
		if(torchCount == 0 ) {
			return baseLightRadius;
			
		}else if(torchCount == 1) {
			return 450;
			
		}else if(torchCount == 2) {
			return 700;
			
		}else if(torchCount == 3) {
			return Math.max(gp.screenwidth, gp.screenheight)*2;
			
			
		}
		return baseLightRadius;
		
		
		
	}
	
	public void draw(Graphics2D g2) {
		
		
		if(gp.currentMap == 1) {
			lighting.draw(g2);
			
			
		}

	}
}
