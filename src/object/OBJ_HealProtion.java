package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.Gamepannel;

public class OBJ_HealProtion extends SuperObject{
	
	
    Gamepannel gp;

    public OBJ_HealProtion(Gamepannel gp) {
        this.gp = gp;
        name = "Heal";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/heal.png"));
            image = utool.scaledImage(image, gp.tilesize, gp.tilesize); // must assign!
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
