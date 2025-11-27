package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.Gamepannel;

public class OBJ_Chest extends SuperObject {

	Gamepannel gp;
	
	
	public OBJ_Chest(Gamepannel gp) {
		
		this.gp =gp;
		
		//constructor{
		name = "Chest";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/rock.png"));
			utool.scaledImage(image, gp.tilesize, gp.tilesize);
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		collision = true;
		
	}
}
