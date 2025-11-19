package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.Gamepannel;
import main.keyHandler;

public class Player extends Entity {

	Gamepannel gp;
	keyHandler KeyH;
	
	public final int screenx;    //player position middle
	public final int screeny;    //player position middle
	
	int haskey = 0;   
	
	public Player(Gamepannel gp,keyHandler KeyH) {
		this.gp = gp;
		this.KeyH = KeyH;
		
		
		setDefaultValues();
		getPlayerImage();
		
		screenx = gp.screenwidth/2 - (gp.tilesize/2) ;    //player position middle center of the screen
		screeny = gp.screenheight/2 - (gp.tilesize/2);   //player position middle center of the screen
		
		//collision instanciate.
		solidarea = new Rectangle();   //we pass 4 parameter  to the constructor.
		solidarea.x = 8 ;      //tiles collision
		solidarea.y = 12;
		solidarea.width = 28;
		solidarea.height = 28;
		solidareadefaultx = solidarea.x;     //these two are used for key and chest collision
		solidareadefaulty = solidarea.y;    //these two are used for key and chest collision
		
		
		
	}
	public void setDefaultValues() {
		worldx = gp.tilesize * 62;      //default player position
		worldy = gp.tilesize * 63;
		speed = 5;
		direction = "down";
		
	}
	
	public void getPlayerImage(){
		try {
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/back.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/back2.png"));
			down1= ImageIO.read(getClass().getResourceAsStream("/player/Front1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/front2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/movingL.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/standingL.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/movingR.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/standingR.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
			
		}
	}
	
	
	
	
	public void update() {
		
		if(KeyH.uppress == true || KeyH.downpress == true || KeyH.leftpress == true ||KeyH.rightpress == true) {
			if (KeyH.uppress == true) {
				direction ="up";
				
				
			}
			else if(KeyH.downpress == true) {
				direction ="down";
			
				
			}
			else if(KeyH.rightpress == true) {
				direction ="right";
				
				
			}
			else if(KeyH.leftpress == true) {
				direction ="left";
				
				
			}
			
			// check tile collision 
			
			collisionon = false;                //collision checker can take player class as entity.
			gp.cChecker.checkTile(this);      //here this means passing this player class to this method.
			  
			
			
			int objIndex = gp.cChecker.CheckObject(this,true);   //here we are calling the method of object collision since it return a int value index so we use int objindex and store it in a variable.
			pickupobject(objIndex);
			//now we create a new method for what happens if the player collide with the object
			
			
			
			//if collision is false player cant move 
			if(collisionon == false) {
				switch(direction) {
				case "up":
					worldy -= speed;    //this player speed or movement is moved from the update ifelse statement to this switch case because if the collision is false then only the player would be able to move
					break;
				case "down":
					worldy += speed;
					break;
				case "left":
					worldx -= speed;
					break;
				case "right":
					worldx += speed;
					break;
					
				}
			}
			
			
			walking++;
			if (walking >15) {
				if (walkingnum == 1) {
					walkingnum = 2;
				}
				else if(walkingnum == 2) {
					walkingnum = 1;
				}
				walking = 0;
			}
		}
		

		
	}
	
	
	public void pickupobject(int i) {
		//here if the player has key then  the chest will be opened or else not 
		if(i != 999) {
			String objectName = gp.obj[i].name;    //here the object name gets the name from the OBJ_Key class name where we have mentioned the name of the objects .
			
			switch(objectName){
				case "Key":
					haskey++;
					gp.obj[i] = null;
					System.out.println("Key:"+haskey);
					break;
				case "Chest":
					if(haskey > 0) {
						gp.obj[i] = null;
						haskey--;
					}
					System.out.println("Key:"+haskey);
					break;
				case "Boots":
					speed += 7;
					gp.obj[i] = null;
					break;
			}
			
		}
	}
	
	
	public void draw(Graphics2D g2) {
//		g2.setColor(Color.white);
//		g2.fillRect(x, y, gp.tilesize,gp.tilesize);
		
		BufferedImage image = null;
		
		switch(direction) {
		case "up":
			if(walkingnum == 1) {
				image = up1;
			}
			if(walkingnum == 2) {
				image = up2;
			}
			break;
			
		case "down":
			if(walkingnum == 1) {
				image = down1;
			}
			if(walkingnum == 2) {
				image = down2;
			}
			break;
			
		case "left":
			if(walkingnum == 1) {
				image = left1;
			}
			if(walkingnum == 2) {
				image = left2;
			}
			break;
			
		case "right":
			if(walkingnum == 1) {
				image = right1;
			}
			if(walkingnum == 2) {
				image = right2;
			}
			break;
			
			
		}
		g2.drawImage(image,screenx,screeny,gp.tilesize,gp.tilesize,null);
		
	}
	
}
