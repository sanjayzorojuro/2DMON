package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.Gamepannel;

public class OBJ_CaveRock extends SuperObject{
	
	Gamepannel gp;
	
	public OBJ_CaveRock(Gamepannel gp) {
		this.gp = gp;
		 name = "CaveRock";
		 collision = true;
		 
	        try {
	            image = ImageIO.read(getClass().getResourceAsStream("/objects/zero.png"));
	            image = utool.scaledImage(image, gp.tilesize, gp.tilesize); // must assign!
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	}

}
