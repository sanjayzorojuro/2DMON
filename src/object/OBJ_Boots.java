package object;

import java.io.IOException;
import javax.imageio.ImageIO;

public class OBJ_Boots extends SuperObject{

	
	public OBJ_Boots()    //constructor
	{
		name = "Boots";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/Runningshoe.png"));
			
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}
