package object;

import java.io.IOException;
import javax.imageio.ImageIO;
import main.Gamepannel;

public class OBJ_Pit extends SuperObject {

    Gamepannel gp;

    public OBJ_Pit(Gamepannel gp) {
        this.gp = gp;
        name = "Pit";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/pit.png"));
            image = utool.scaledImage(image, gp.tilesize, gp.tilesize); // must assign!
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
