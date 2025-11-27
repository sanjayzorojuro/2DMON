package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.Gamepannel;

public class OBJ_Steps extends  SuperObject {
	
	Gamepannel gp;
	
	public OBJ_Steps(Gamepannel gp) {
		this.gp =gp;
		
		name = "Final";
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/tunnel11.png"));
			utool.scaledImage(image, gp.tilesize, gp.tilesize);
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		collision = false;
		
		
	}
	

}
