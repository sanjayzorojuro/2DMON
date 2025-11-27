package object;

import java.io.IOException;
import javax.imageio.ImageIO;
import main.Gamepannel;

public class OBJ_Strength extends SuperObject{
	

    Gamepannel gp;

    public OBJ_Strength(Gamepannel gp) {
        this.gp = gp;
        name = "Strength";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/strengthobj.png"));
            image = utool.scaledImage(image, gp.tilesize, gp.tilesize); // must assign!
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
