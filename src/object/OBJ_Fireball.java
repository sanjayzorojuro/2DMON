package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.Gamepannel;

public class OBJ_Fireball extends SuperObject {
	
    Gamepannel gp;
    
    public OBJ_Fireball(Gamepannel gp) {
        this.gp = gp;
        name = "Fireball";
        
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/fireball.png"));
            image = utool.scaledImage(image, gp.tilesize, gp.tilesize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
