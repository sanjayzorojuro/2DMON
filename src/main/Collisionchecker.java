package main;

import entity.Entity;
import entity.Player;

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
		 tilenum1 = gp.tileM.maptileno[gp.currentMap][entityleftcol][entitytoprow];   //checking the one side of the character ie left top.
		 tilenum2 = gp.tileM.maptileno[gp.currentMap][entityrightcol][entitytoprow];   //checking the other side of the character ie right top.
		 if (gp.tileM.tile[tilenum1].collision == true || gp.tileM.tile[tilenum2].collision == true) {       //checking if the collision is true or not.
			 entity.collisionon = true;
			 
		 }
		 
		 break;
	 case "down":
		 entitybottomrow = (entityBottomWorldy + entity.speed ) / gp.tilesize;    //substracting players speed from player current solid area world wide.
		 tilenum1 = gp.tileM.maptileno[gp.currentMap][entityleftcol][entitybottomrow];   //checking the one side of the character ie left top.
		 tilenum2 = gp.tileM.maptileno[gp.currentMap][entityrightcol][entitybottomrow];   //checking the other side of the character ie right top.
		 if (gp.tileM.tile[tilenum1].collision == true || gp.tileM.tile[tilenum2].collision == true) {       //checking if the collision is true or not.
			 entity.collisionon = true;
			 
		 }
		 
		 break;
	 case "left":
		 entityleftcol = (entityLeftWorldx - entity.speed ) / gp.tilesize;    //substracting players speed from player current solid area world wide.
		 tilenum1 = gp.tileM.maptileno[gp.currentMap][entityleftcol][entitytoprow];   //checking the one side of the character ie left top.
		 tilenum2 = gp.tileM.maptileno[gp.currentMap][entityleftcol][entitybottomrow];   //checking the other side of the character ie right top.
		 if (gp.tileM.tile[tilenum1].collision == true || gp.tileM.tile[tilenum2].collision == true) {       //checking if the collision is true or not.
			 entity.collisionon = true;
			 
		 }
		 
		 break;
	 case "right":           //here in the right and down should be + and up and left should be - or else when the player gets hit with the collision object he gets stuck.
		 entityrightcol = (entityRightWorldx + entity.speed ) / gp.tilesize;    //substracting players speed from player current solid area world wide.
		 tilenum1 = gp.tileM.maptileno[gp.currentMap][entityrightcol][entitytoprow];   //checking the one side of the character ie left top.
		 tilenum2 = gp.tileM.maptileno[gp.currentMap][entityrightcol][entitybottomrow];   //checking the other side of the character ie right top.
		 if (gp.tileM.tile[tilenum1].collision == true || gp.tileM.tile[tilenum2].collision == true) {       //checking if the collision is true or not.
			 entity.collisionon = true;
			 
		 }
		 
		 break;
		 
		
	 }
		
	}
	
	
	public int CheckObject(Entity entity,boolean player) {
		//in this method we check if the player is hitting the object if yes we return the index of the object.
		int index = 999;
		
		for(int i = 0 ; i < gp.obj[1].length ; i++) {     //scanning object array and obj of game panel line 43.
			
			if(gp.obj[gp.currentMap][i] != null) {
				
				//get entities solid area position
				entity.solidarea.x = entity.worldx + entity.solidarea.x;    //fining position of the entity
				entity.solidarea.y = entity.worldy + entity.solidarea.y;
				
				//get objects solid area position
				gp.obj[gp.currentMap][i].solidarea.x = gp.obj[gp.currentMap][i].worldx + gp.obj[gp.currentMap][i].solidarea.x;   //finding position of the object
				gp.obj[gp.currentMap][i].solidarea.y = gp.obj[gp.currentMap][i].worldy + gp.obj[gp.currentMap][i].solidarea.y;
				
				
				switch(entity.direction) {
				case "up":
					entity.solidarea.y -= entity.speed;
					if(entity.solidarea.intersects(gp.obj[gp.currentMap][i].solidarea)) {   //here intersects function checks if the two entity collide or touching or not.
						if (gp.obj[gp.currentMap][i].collision == true) {
							entity.collisionon = true;
						}
						if(player == true) {
							index = i;       //here if the player touches the object then it will return true for collision npc and monster it will  return false.
						}
						
					}
					break;
				case "down" :
					entity.solidarea.y += entity.speed;
					if(entity.solidarea.intersects(gp.obj[gp.currentMap][i].solidarea)) {   //here intersects function checks if the two entity collide or touching or not.
						if (gp.obj[gp.currentMap][i].collision == true) {
							entity.collisionon = true;
						}
						if(player == true) {
							index = i;       //here if the player touches the object then it will return true for collision npc and monster it will  return false.
						}
					}
					break;
				case "left":
					entity.solidarea.x -= entity.speed;
					if(entity.solidarea.intersects(gp.obj[gp.currentMap][i].solidarea)) {   //here intersects function checks if the two entity collide or touching or not.
						if (gp.obj[gp.currentMap][i].collision == true) {
							entity.collisionon = true;
						}
						if(player == true) {
							index = i;       //here if the player touches the object then it will return true for collision npc and monster it will  return false.
						}
					}
					break;
				case "right":
					entity.solidarea.x += entity.speed;
					if(entity.solidarea.intersects(gp.obj[gp.currentMap][i].solidarea)) {   //here intersects function checks if the two entity collide or touching or not.
						if (gp.obj[gp.currentMap][i].collision == true) {
							entity.collisionon = true;
						}
						if(player == true) {
							index = i;       //here if the player touches the object then it will return true for collision npc and monster it will  return false.
						}
					}
				
					break;
				}
				//we need to reset the x and y each time or else it keep on increasing 
				
				entity.solidarea.x = entity.solidareadefaultx;
				entity.solidarea.y = entity.solidareadefaulty;
				gp.obj[gp.currentMap][i].solidarea.x =  gp.obj[gp.currentMap][i].solidareadefaultx;
				gp.obj[gp.currentMap][i].solidarea.y =  gp.obj[gp.currentMap][i].solidareadefaulty;	
			}
		
			
		}
		
		return index;
		
	}
	
	
	
	
	//method for NPC  or monster
	public int checkEntity(Entity entity,Entity[][] target) {
		
		
int index = 999;
		
		for(int i = 0 ; i < target[1].length ; i++) {     //scanning object array and obj of game panel line 43.
			
			if(target[gp.currentMap][i] != null) {
				
				//get entities solid area position
				entity.solidarea.x = entity.worldx + entity.solidarea.x;    //fining position of the entity
				entity.solidarea.y = entity.worldy + entity.solidarea.y;
				
				//get objects solid area position
				target[gp.currentMap][i].solidarea.x = target[gp.currentMap][i].worldx + target[gp.currentMap][i].solidarea.x;   //finding position of the object
				target[gp.currentMap][i].solidarea.y = target[gp.currentMap][i].worldy + target[gp.currentMap][i].solidarea.y;
				
				
				switch(entity.direction) {
				case "up":
					entity.solidarea.y -= entity.speed;
					if(entity.solidarea.intersects(target[gp.currentMap][i].solidarea)) {   //here intersects function checks if the two entity collide or touching or not.
					
							entity.collisionon = true;
							index = i;

					}
					break;
				case "down" :
					entity.solidarea.y += entity.speed;
					if(entity.solidarea.intersects(target[gp.currentMap][i].solidarea)) {   //here intersects function checks if the two entity collide or touching or not.
						
							entity.collisionon = true;
							index = i;

					}
					break;
				case "left":
					entity.solidarea.x -= entity.speed;
					if(entity.solidarea.intersects(target[gp.currentMap][i].solidarea)) {   //here intersects function checks if the two entity collide or touching or not.
					
							entity.collisionon = true;
							index = i;

					}
					break;
				case "right":
					entity.solidarea.x += entity.speed;
					if(entity.solidarea.intersects(target[gp.currentMap][i].solidarea)) {   //here intersects function checks if the two entity collide or touching or not.
					
							entity.collisionon = true;
							index = i;

					}
					break;
				}
				//we need to reset the x and y each time or else it keep on increasing 
				
				entity.solidarea.x = entity.solidareadefaultx;
				entity.solidarea.y = entity.solidareadefaulty;
				target[gp.currentMap][i].solidarea.x =  target[gp.currentMap][i].solidareadefaultx;
				target[gp.currentMap][i].solidarea.y =  target[gp.currentMap][i].solidareadefaulty;	
			}
		
			
		}
		
		return index;
		
		
	}
	
	
	
	//used to check the collision of player for NPCS
	public void checkPlayer(Entity entity) {  //since the method is void we dont need to return something.
		
		//get entities solid area position
		entity.solidarea.x = entity.worldx + entity.solidarea.x;    //fining position of the entity
		entity.solidarea.y = entity.worldy + entity.solidarea.y;
		
		//get objects solid area position
		gp.player.solidarea.x = gp.player.worldx + gp.player.solidarea.x;   //finding position of the object
		gp.player.solidarea.y = gp.player.worldy + gp.player.solidarea.y;
		
		
		switch(entity.direction) {
		case "up":
			entity.solidarea.y -= entity.speed;
			if(entity.solidarea.intersects(gp.player.solidarea)) {   //here intersects function checks if the two entity collide or touching or not.
			
				if(entity instanceof Player == false){
				    entity.collisionon = true;
				}

				

			}
			break;
		case "down" :
			entity.solidarea.y += entity.speed;
			if(entity.solidarea.intersects(gp.player.solidarea)) {   //here intersects function checks if the two entity collide or touching or not.
				
				if(entity instanceof Player == false){
				    entity.collisionon = true;
				}

			
			}
			break;
		case "left":
			entity.solidarea.x -= entity.speed;
			if(entity.solidarea.intersects(gp.player.solidarea)) {   //here intersects function checks if the two entity collide or touching or not.
			
				if(entity instanceof Player == false){
				    entity.collisionon = true;
				}

				

			}
			break;
		case "right":
			entity.solidarea.x += entity.speed;
			if(entity.solidarea.intersects(gp.player.solidarea)) {   //here intersects function checks if the two entity collide or touching or not.
			
				if(entity instanceof Player == false){
				    entity.collisionon = true;
				}

			
			}
			break;
		}
		//we need to reset the x and y each time or else it keep on increasing 
		
		entity.solidarea.x = entity.solidareadefaultx;
		entity.solidarea.y = entity.solidareadefaulty;
		gp.player.solidarea.x =  gp.player.solidareadefaultx;
		gp.player.solidarea.y = gp.player.solidareadefaulty;
		 
		
		
	}
	
	//monster collision
	public int checkMonster(Entity entity, boolean player) {
	    int index = 999;
	    
	    for(int i = 0; i < gp.monster[gp.currentMap].length; i++) {
	        if(gp.monster[gp.currentMap][i] != null) {
	            
	            // Get entity solid area position
	            entity.solidarea.x = entity.worldx + entity.solidarea.x;
	            entity.solidarea.y = entity.worldy + entity.solidarea.y;
	            
	            // Get monster solid area position
	            gp.monster[gp.currentMap][i].solidarea.x = 
	                gp.monster[gp.currentMap][i].worldx + gp.monster[gp.currentMap][i].solidarea.x;
	            gp.monster[gp.currentMap][i].solidarea.y = 
	                gp.monster[gp.currentMap][i].worldy + gp.monster[gp.currentMap][i].solidarea.y;
	            
	            switch(entity.direction) {
	                case "up":
	                    entity.solidarea.y -= entity.speed;
	                    break;
	                case "down":
	                    entity.solidarea.y += entity.speed;
	                    break;
	                case "left":
	                    entity.solidarea.x -= entity.speed;
	                    break;
	                case "right":
	                    entity.solidarea.x += entity.speed;
	                    break;
	            }
	            
	            if(entity.solidarea.intersects(gp.monster[gp.currentMap][i].solidarea)) {
	                // If the entity asking is the player, we might want to still allow movement but register contact:
	                if (player) {
	                    index = i; // return monster index so player contact logic runs
	                } else {
	                    entity.collisionon = true; // NPCs/monsters will collide with monsters
	                }
	            }
	            
	            // Reset positions
	            entity.solidarea.x = entity.solidareadefaultx;
	            entity.solidarea.y = entity.solidareadefaulty;
	            gp.monster[gp.currentMap][i].solidarea.x = 
	                gp.monster[gp.currentMap][i].solidareadefaultx;
	            gp.monster[gp.currentMap][i].solidarea.y = 
	                gp.monster[gp.currentMap][i].solidareadefaulty;
	        }
	    }
	    
	    return index;
	}

	
	
	
	
	
}
