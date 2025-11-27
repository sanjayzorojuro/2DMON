package entity;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.plaf.basic.BasicComboBoxUI.KeyHandler;

import main.Gamepannel;
import main.UtilityTool;

public class Entity {
	
	protected Gamepannel gp;
	
	
	public int worldx,worldy;
	public int speed;
	public BufferedImage up1,up2,down1,down2,left1,left2,right1,right2;
	public String direction;
	public int walking = 0;
	public int walkingnum = 1;
	
	//collision creating
	public Rectangle solidarea = new Rectangle(0,0,48,48);    //with the rectangle imported we can make a invisible box where we can store the data such as x,y,width,height.
	public int solidareadefaultx,solidareadefaulty;
	public boolean collisionon = false;
	public int actionLockCounter = 0;
	public String dialouges[] = new String[50];
	int dialougeIndex = 0;
	
	//Character Status
	public int maxLife;
	public int life;
	
	//monster variables
	public BufferedImage attackUp, attackDown, attackLeft, attackRight;   // single attack sprite (optional)
	public boolean attacking = false;
	public int attackCooldown = 0;
	public int hurtCounter = 0;
	
	//constructor
	public Entity(Gamepannel gp) {
		this.gp = gp;
	}
	
	public void setAction() {
		//this method is also created in the sub class so this is used as the dummy method because the priority is given to the sub class 
		//so when the code is written in the sub class that code will execute instead of this 
		//so we dont need to write the code here its enough in NPC_oldMan()
	}
	
	public void speak() {
		
		//we can call this  method in all the sub class where we need this message to display 
		// first its called in NPC_OldMan
		
		
		if(dialouges[dialougeIndex]== null) {
			dialougeIndex = 0;
		}
		gp.ui.currentDialouge = dialouges[dialougeIndex];
		dialougeIndex++;
		
		
		switch(gp.player.direction) {
		case"up":
			direction = "down";
			break;
			
		case"down":
			direction = "up";
			break;
			
		case "left":
			direction = "right";
			break;
			
		case "right":
			direction = "left";
			break;
			
			
		}
		
		
	}
	
	public void update() {     
		
		setAction();
		
		collisionon = false;
		gp.cChecker.checkTile(this);
		gp.cChecker.CheckObject(this, false);
		gp.cChecker.checkPlayer(this);
		
		
		
		//if collision is false player can,t move
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
		if (walking > 15) {
			if (walkingnum == 1) {
				walkingnum = 2;
			}
			else if(walkingnum == 2) {
				walkingnum = 1;
			}
			walking = 0;
		}
		
		
	}
	
	public void draw(Graphics2D g2) {
	    BufferedImage image = null;

	    int screenx = worldx - gp.player.worldx + gp.player.screenx;
	    int screeny = worldy - gp.player.worldy + gp.player.screeny;

	    // Only draw if inside screen bounds (same test as before)
	    if (worldx + gp.tilesize > gp.player.worldx - gp.player.screenx &&
	        worldx - gp.tilesize < gp.player.worldx + gp.player.screenx &&
	        worldy + gp.tilesize > gp.player.worldy - gp.player.screeny &&
	        worldy - gp.tilesize < gp.player.worldy + gp.player.screeny) {

	        // choose walk sprite
	        switch (direction) {
	            case "up":
	                image = (walkingnum == 1) ? up1 : up2;
	                break;
	            case "down":
	                image = (walkingnum == 1) ? down1 : down2;
	                break;
	            case "left":
	                image = (walkingnum == 1) ? left1 : left2;
	                break;
	            case "right":
	                image = (walkingnum == 1) ? right1 : right2;
	                break;
	        }

	        // Player flashing when invincible (don't draw every other frame)
	        if (this instanceof entity.Player) {
	            entity.Player p = (entity.Player) this;
	            if (p.invincible && p.invincibleCounter % 10 < 5) {
	                // Skip drawing to produce flashing effect
	                return;
	            }
	        }

	        // If drawing normally - handle alpha for invincible player
	        if (this instanceof entity.Player) {
	            entity.Player p = (entity.Player) this;
	            if (p.invincible) {
	                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
	            }
	        }

	        // Draw base image
	        g2.drawImage(image, screenx, screeny, gp.tilesize, gp.tilesize, null);

	        // Draw attack overlay if attacking (draw in front of entity based on direction)
	        if (attacking) {
	            BufferedImage attackSprite = null;
	            int ax = screenx;
	            int ay = screeny;

	            switch(direction) {
	                case "up":
	                    attackSprite = attackUp;
	                    ay -= gp.tilesize;
	                    break;
	                case "down":
	                    attackSprite = attackDown;
	                    ay += gp.tilesize;
	                    break;
	                case "left":
	                    attackSprite = attackLeft;
	                    ax -= gp.tilesize;
	                    break;
	                case "right":
	                    attackSprite = attackRight;
	                    ax += gp.tilesize;
	                    break;
	            }

	            if (attackSprite != null) {
	                g2.drawImage(attackSprite, ax, ay, gp.tilesize, gp.tilesize, null);
	            }
	        }

	        // Reset composite back to full opacity
	        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

	        // If hurt recently, draw HP above entity
	        if (hurtCounter > 0) {
	            String hp = String.valueOf(life);
	            g2.setColor(java.awt.Color.WHITE);
	            int textX = screenx + gp.tilesize/4;
	            int textY = screeny - 6;
	            g2.drawString(hp, textX, textY);
	        }
	    }
	}

	
	
    public BufferedImage setup(String imagepath){
      	 
      	 UtilityTool utool = new UtilityTool();
      	 BufferedImage image = null;
      	 
      	 
      	 try {
      		 
      		 image = ImageIO.read(getClass().getResourceAsStream( imagepath + ".png"));
      		 image = utool.scaledImage(image,gp.tilesize,gp.tilesize);
    
      		 
      	 }catch(IOException e){
      		 e.printStackTrace();
      	 }
      	 return image;
      	 
       }
	
}
