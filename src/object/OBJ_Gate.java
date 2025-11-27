package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.Gamepannel;

public class OBJ_Gate extends SuperObject {
	
	Gamepannel gp;
	
	public OBJ_Gate(Gamepannel gp) {
		
		this.gp =gp;
		
		//constructor{
		name = "Gate";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/logfinal.png"));
			utool.scaledImage(image, gp.tilesize, gp.tilesize);
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		collision = true;
		
	}
}
