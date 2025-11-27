package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.Gamepannel;

public class OBJ_Heart extends SuperObject{
	
	Gamepannel gp;
	public OBJ_Heart(Gamepannel gp)    //constructor
	{
		this.gp = gp;
		name = "Heart";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/heartfull.png"));
			image2 = ImageIO.read(getClass().getResourceAsStream("/objects/hearthalf.png"));
			image3 = ImageIO.read(getClass().getResourceAsStream("/objects/heartblank.png"));
			image = utool.scaledImage(image, gp.tilesize, gp.tilesize);
			image2 = utool.scaledImage(image2, gp.tilesize, gp.tilesize);
			image3 =utool.scaledImage(image3, gp.tilesize, gp.tilesize);
			
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}
