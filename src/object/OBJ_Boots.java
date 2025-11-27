package object;

import java.io.IOException;
import javax.imageio.ImageIO;

import main.Gamepannel;

public class OBJ_Boots extends SuperObject{

	Gamepannel gp;
	public OBJ_Boots(Gamepannel gp)    //constructor
	{
		this.gp = gp;
		name = "Boots";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/Runningshoe.png"));
			utool.scaledImage(image, gp.tilesize, gp.tilesize);
			
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}
