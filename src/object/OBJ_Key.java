package object;

import java.io.IOException;
import javax.imageio.ImageIO;

import main.Gamepannel;

public class OBJ_Key extends SuperObject{

	Gamepannel gp;
	public OBJ_Key(Gamepannel gp)    //constructor
	{
		this.gp = gp;
		name = "Key";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/axe.png"));
			utool.scaledImage(image, gp.tilesize, gp.tilesize);
			
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}
