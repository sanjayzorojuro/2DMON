package main;

import entity.Entity;

public class Collisionchecker {
	
	Gamepannel gp;
	
	public Collisionchecker(Gamepannel gp) {
		this.gp = gp;
		
	}
	
	public void checkTile(Entity entity) {
		
	//based on these cordinates we will find the col and row of the invisible rectangle which will be used for collision.
	 int entityLeftWorldx = entity.worldx + entity.solidarea.x;
	 int entityRightWorldx = entity.worldx + entity.solidarea.x + entity.solidarea.width;
	 int entityTopWorldy = entity.worldy + entity.solidarea.y;
	 int entityBottomWorldy = entity.worldy + entity.solidarea.y + entity.solidarea.height;
	 
	 int entityleftcol = entityLeftWorldx / gp.tilesize;
	 int entityrightcol = entityRightWorldx / gp.tilesize;
	 int entitytoprow =  entityTopWorldy / gp.tilesize;
	 int entitybottomrow = entityBottomWorldy / gp.tilesize;
	 
	 
	 int tilenum1,tilenum2;
	 
		
	 switch(entity.direction) {
	 case "up":
		 entitytoprow = (entityTopWorldy - entity.speed ) / gp.tilesize;    //substracting players speed from player current solid area world wide.
		 tilenum1 = gp.tileM.maptileno[entityleftcol][entitytoprow];   //checking the one side of the character ie left top.
		 tilenum2 = gp.tileM.maptileno[entityrightcol][entitytoprow];   //checking the other side of the character ie right top.
		 if (gp.tileM.tile[tilenum1].collision == true || gp.tileM.tile[tilenum2].collision == true) {       //checking if the collision is true or not.
			 entity.collisionon = true;
			 
		 }
		 
		 break;
	 case "down":
		 entitybottomrow = (entityBottomWorldy + entity.speed ) / gp.tilesize;    //substracting players speed from player current solid area world wide.
		 tilenum1 = gp.tileM.maptileno[entityleftcol][entitybottomrow];   //checking the one side of the character ie left top.
		 tilenum2 = gp.tileM.maptileno[entityrightcol][entitybottomrow];   //checking the other side of the character ie right top.
		 if (gp.tileM.tile[tilenum1].collision == true || gp.tileM.tile[tilenum2].collision == true) {       //checking if the collision is true or not.
			 entity.collisionon = true;
			 
		 }
		 
		 break;
	 case "left":
		 entityleftcol = (entityLeftWorldx - entity.speed ) / gp.tilesize;    //substracting players speed from player current solid area world wide.
		 tilenum1 = gp.tileM.maptileno[entityleftcol][entitytoprow];   //checking the one side of the character ie left top.
		 tilenum2 = gp.tileM.maptileno[entityleftcol][entitybottomrow];   //checking the other side of the character ie right top.
		 if (gp.tileM.tile[tilenum1].collision == true || gp.tileM.tile[tilenum2].collision == true) {       //checking if the collision is true or not.
			 entity.collisionon = true;
			 
		 }
		 
		 break;
	 case "right":           //here in the right and down should be + and up and left should be - or else when the player gets hit with the collision object he gets stuck.
		 entityrightcol = (entityRightWorldx + entity.speed ) / gp.tilesize;    //substracting players speed from player current solid area world wide.
		 tilenum1 = gp.tileM.maptileno[entityrightcol][entitytoprow];   //checking the one side of the character ie left top.
		 tilenum2 = gp.tileM.maptileno[entityrightcol][entitybottomrow];   //checking the other side of the character ie right top.
		 if (gp.tileM.tile[tilenum1].collision == true || gp.tileM.tile[tilenum2].collision == true) {       //checking if the collision is true or not.
			 entity.collisionon = true;
			 
		 }
		 
		 break;
		 
		
	 }
		
	}
	
	
	public int CheckObject(Entity entity,boolean player) {
		//in this method we check if the player is hitting the object if yes we return the index of the object.
		int index = 999;
		
		for(int i = 0 ; i < gp.obj.length ; i++) {     //scanning object array and obj of game panel line 43.
			
			if(gp.obj[i] != null) {
				
				//get entities solid area position
				entity.solidarea.x = entity.worldx + entity.solidarea.x;    //fining position of the entity
				entity.solidarea.y = entity.worldy + entity.solidarea.y;
				
				//get objects solid area position
				gp.obj[i].solidarea.x = gp.obj[i].worldx + gp.obj[i].solidarea.x;   //finding position of the object
				gp.obj[i].solidarea.y = gp.obj[i].worldy + gp.obj[i].solidarea.y;
				
				
				switch(entity.direction) {
				case "up":
					entity.solidarea.y -= entity.speed;
					if(entity.solidarea.intersects(gp.obj[i].solidarea)) {   //here intersects function checks if the two entity collide or touching or not.
						if (gp.obj[i].collision == true) {
							entity.collisionon = true;
						}
						if(player == true) {
							index = i;       //here if the player touches the object then it will return true for collision npc and monster it will  return false.
						}
						
					}
					break;
				case "down" :
					entity.solidarea.y += entity.speed;
					if(entity.solidarea.intersects(gp.obj[i].solidarea)) {   //here intersects function checks if the two entity collide or touching or not.
						if (gp.obj[i].collision == true) {
							entity.collisionon = true;
						}
						if(player == true) {
							index = i;       //here if the player touches the object then it will return true for collision npc and monster it will  return false.
						}
					}
					break;
				case "left":
					entity.solidarea.x -= entity.speed;
					if(entity.solidarea.intersects(gp.obj[i].solidarea)) {   //here intersects function checks if the two entity collide or touching or not.
						if (gp.obj[i].collision == true) {
							entity.collisionon = true;
						}
						if(player == true) {
							index = i;       //here if the player touches the object then it will return true for collision npc and monster it will  return false.
						}
					}
					break;
				case "right":
					if(entity.solidarea.intersects(gp.obj[i].solidarea)) {   //here intersects function checks if the two entity collide or touching or not.
						if (gp.obj[i].collision == true) {
							entity.collisionon = true;
						}
						if(player == true) {
							index = i;       //here if the player touches the object then it will return true for collision npc and monster it will  return false.
						}
					}
					entity.solidarea.x += entity.speed;
					break;
				}
				//we need to reset the x and y each time or else it keep on increasing 
				
				entity.solidarea.x = entity.solidareadefaultx;
				entity.solidarea.y = entity.solidareadefaulty;
				gp.obj[i].solidarea.x =  gp.obj[i].solidareadefaultx;
				gp.obj[i].solidarea.y =  gp.obj[i].solidareadefaulty;	
			}
		
			
		}
		
		return index;
		
	}
	
	
}
