package environment;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.image.BufferedImage;


import main.Gamepannel;

public class Lighting {

	Gamepannel gp;
	BufferedImage darknessFilter;
	int circleSize;
	
	 
	public Lighting(Gamepannel gp,int circleSize) {
		
		this.gp = gp;
		this.circleSize = circleSize;
		
		
		//Create buffered image
		darknessFilter = new BufferedImage(gp.screenwidth,gp.screenheight,BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = (Graphics2D)darknessFilter.getGraphics();
	
		
		//Get the center of the screen
		int centerX = gp.player.screenx + (gp.tilesize)/2;
		int centerY = gp.player.screeny + (gp.tilesize)/2;
	
		
		
		  if (circleSize >= Math.max(gp.screenwidth, gp.screenheight) * 2) {
	            // Don't draw any darkness - leave the image transparent
	            g2.dispose();
	            return;
	        }
		  
		  
		//create a gradient effect which is light near the player and gets darker in ends
		Color color[] = new Color[5];
		float fraction[] = new float[5];
		
		color[0] = new Color(0,0,0,0f);      //transparent and the below one keep getting darker
		color[1] = new Color(0,0,0,0.25f);
		color[2] = new Color(0,0,0,0.5f);
		color[3] = new Color(0,0,0,0.75f);
		color[4] = new Color(0,0,0,0.98f);
		
		
		//these are the distance between the player and the black circle start
		fraction[0] = 0f;
		fraction[1] = 0.25f;
		fraction[2] = 0.5f;
		fraction[3] = 0.75f;
		fraction[4] = 1f;
		
		
		//Create a paint setting using these arrays
		RadialGradientPaint gPaint = new RadialGradientPaint(centerX,centerY,(circleSize/2),fraction,color);
		g2.setPaint(gPaint);
		
		g2.fillRect(0, 0, gp.screenwidth, gp.screenheight);
		
		
		g2.dispose();
		
		
	}
	
	
	public void draw(Graphics2D g2) {
		
		g2.drawImage(darknessFilter, 0, 0, null);
		
		
	}
	
}
