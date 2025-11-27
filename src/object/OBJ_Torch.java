package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.Gamepannel;

public class OBJ_Torch extends SuperObject{

	Gamepannel gp;
	
	
       public OBJ_Torch(Gamepannel gp) {
    	   this.gp = gp;
    	 
    	 
    	   
    	      name = "Torch";
    	      collision = true;
    	      
    	      
    	        try {
    	        	
    	            image = ImageIO.read(getClass().getResourceAsStream("/objects/torch.png"));
    	            image = utool.scaledImage(image, gp.tilesize, gp.tilesize); // must assign!
    	            
    	            
    	        } catch (IOException e) {
    	        	
    	            e.printStackTrace();
    	            
    	        }
    	        
    	   
       }
}
