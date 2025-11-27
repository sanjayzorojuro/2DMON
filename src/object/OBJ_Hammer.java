package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.Gamepannel;

public class OBJ_Hammer extends SuperObject {

	Gamepannel gp;
	
	public OBJ_Hammer(Gamepannel gp) {
		this.gp = gp;
		
		//constructor{
		name = "Hammer";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/final-hammer.png"));
			utool.scaledImage(image, gp.tilesize, gp.tilesize);
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		collision = true;
		
	}
}
