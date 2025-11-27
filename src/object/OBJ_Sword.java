package object;

import java.io.IOException;
import javax.imageio.ImageIO;
import main.Gamepannel;

public class OBJ_Sword extends SuperObject {
    
    Gamepannel gp;
    
    public OBJ_Sword(Gamepannel gp) {
        this.gp = gp;
        name = "Sword";
        
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/sowrd.png"));
            image = utool.scaledImage(image, gp.tilesize, gp.tilesize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}