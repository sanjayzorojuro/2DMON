package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {
	public int worldx,worldy;
	public int speed;
	
	public BufferedImage up1,up2,down1,down2,left1,left2,right1,right2;
	public String direction;
	
	public int walking = 0;
	public int walkingnum = 1;
	
	//collision creating
	public Rectangle solidarea;    //with the rectangle imported we can make a invisible box where we can store the data such as x,y,width,height.
	public int solidareadefaultx,solidareadefaulty;
	public boolean collisionon = false;
}
