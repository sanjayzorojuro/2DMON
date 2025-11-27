package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.Gamepannel;
import main.keyHandler;
import java.util.ArrayList;
import java.util.Iterator;

public class Player extends Entity {


	keyHandler KeyH;
	
	public final int screenx;    //player position middle
	public final int screeny;    //player position middle
	
	public int haskey = 0;   
	public int hashammer = 0;
	int standCounter = 0;
	public int hastorch = 0;
	public boolean hasStrength = false;
	public boolean hasSword = false;
	public int attackCooldown = 0;
	public boolean invincible = false;
	public int invincibleCounter = 0;
	public boolean hasBoots = false;
	public boolean bootsActive = false;
	public int bootsSpeedBonus = 0;
	
	public BufferedImage fireballUp, fireballDown, fireballLeft, fireballRight;
	public ArrayList<Projectile> projectiles = new ArrayList<>();
	public int fireballCooldown = 0;
	public boolean hasFireball = false;
	
	
	
	public Player(Gamepannel gp,keyHandler KeyH) {
		
		super(gp);
		this.KeyH = KeyH;
		
		
		setDefaultValues();
		getPlayerImage();
		
		screenx = gp.screenwidth/2 - (gp.tilesize/2) ;    //player position middle center of the screen
		screeny = gp.screenheight/2 - (gp.tilesize/2);   //player position middle center of the screen
		
		//collision instantiate.
		solidarea = new Rectangle();   //we pass 4 parameter  to the constructor.
		solidarea.x = 11 ;      //tiles collision
		solidarea.y = 17;
		solidarea.width = 25;
		solidarea.height = 25;
		solidareadefaultx = solidarea.x;     //these two are used for key and chest collision
		solidareadefaulty = solidarea.y;    //these two are used for key and chest collision
		
		
		
	}
	public void setDefaultValues() {
		
		if(gp.currentMap == 0) {
			worldx = gp.tilesize * 62;      //default player position
			worldy = gp.tilesize * 63;
			direction = "down";
		}
		else if(gp.currentMap == 1) {
			worldx = gp.tilesize * 52;      //default player position
			worldy = gp.tilesize * 69;
			direction = "up";
		}
	
		speed = 5;
	
		
		
		//Player Status Life values
		maxLife = 6;
		life = maxLife;
		
	}
	
	public void SetDefaultPosition() {
		if(gp.currentMap == 0) {
			worldx = gp.tilesize * 62;      //default player position
			worldy = gp.tilesize * 63;
			direction = "down";
		}
		else if(gp.currentMap == 1) {
			worldx = gp.tilesize * 52;      //default player position
			worldy = gp.tilesize * 69;
			direction = "up";
		}
		else if(gp.currentMap == 2) {
			worldx = gp.tilesize * 54;      //default player position
			worldy = gp.tilesize * 56;
			direction = "up";
			
		}
		else if(gp.currentMap == 3) {
			worldx = gp.tilesize * 30;      //default player position
			worldy = gp.tilesize * 37;
			direction = "up";
			
		}
		speed = 5;
	
		
		
		//Player Status Life values
	
	}
	
	public void restoreLife() {
		maxLife = 6;
		life = maxLife;
		invincible = false;
		
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
			try {
			    attackUp = ImageIO.read(getClass().getResourceAsStream("/player/attack-up.png"));
			    attackDown = ImageIO.read(getClass().getResourceAsStream("/player/attack-front.png"));
			    attackLeft = ImageIO.read(getClass().getResourceAsStream("/player/attack-left.png"));
			    attackRight = ImageIO.read(getClass().getResourceAsStream("/player/attack-right.png"));
			} catch (IOException e) {
			    e.printStackTrace();
			}
			
			
			//fireball
			
			try {
	            fireballUp = ImageIO.read(getClass().getResourceAsStream("/objects/fireballup.png"));
	            fireballDown = ImageIO.read(getClass().getResourceAsStream("/objects/fireballdown.png"));
	            fireballLeft = ImageIO.read(getClass().getResourceAsStream("/objects/fireballleft.png"));
	            fireballRight = ImageIO.read(getClass().getResourceAsStream("/objects/fireballright.png"));
	            } catch (IOException e) {
	                       System.out.println("Fireball sprites not found, using default fireball image");
	                       try {
	                    	   // Fallback to single fireball image for all directions if directional ones don't exist
	                    	   BufferedImage defaultFireball = ImageIO.read(getClass().getResourceAsStream("/objects/fireball.png"));
	                    	   fireballUp = defaultFireball;
	                    	   fireballDown = defaultFireball;
	                    	   fireballLeft = defaultFireball;
	                    	   fireballRight = defaultFireball;
	                       } catch (IOException e2) {
	                    	   System.out.println("No fireball images found!");
	                    	   e2.printStackTrace();
	                       }
	            }


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
	        collisionon = false;
	        gp.cChecker.checkTile(this);
	        
	        int objIndex = gp.cChecker.CheckObject(this,true);
	        pickupobject(objIndex);
	        
	        //Check NPC collision
	        int npcIndex = gp.cChecker.checkEntity(this,gp.npc);
	        interactNPC(npcIndex);
	        
	        //check monster collision
	        int monsterIndex = gp.cChecker.checkMonster(this, true);
	        contactMonster(monsterIndex);
	        
	        //Check Event
	        gp.eHandler.checkEvent();
	        
	        //if collision is false player can move
	        if(collisionon == false) {
	            switch(direction) {
	            case "up":
	                worldy -= speed;
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
	    else {
	        standCounter++;
	        if(standCounter == 20) {
	            walkingnum = 2;
	            standCounter = 0;
	        }
	    }

	    // Attack cooldown
	    if(attackCooldown > 0) {
	        attackCooldown--;
	        if (attackCooldown < 20) {
	            attacking = false;
	        }
	    }
	    
	    // Fireball cooldown
	    if(fireballCooldown > 0) {
	        fireballCooldown--;
	    }
	    
	    // Invincibility
	    if(invincible) {
	        invincibleCounter++;
	        if(invincibleCounter > 60) {
	            invincible = false;
	            invincibleCounter = 0;
	        }
	    }
	    
	    if(life <= 0) {
	    	gp.gameState = gp.gameOver;
	    }
	    
	    // Update all projectiles
	    updateProjectiles();
	}
	
	
	
	//pickup object in player class
	public void pickupobject(int i) {
		//here if the player has key then  the chest will be opened or else not 
		if(i != 999) {
			 String objectName = gp.obj[gp.currentMap][i].name;    //here the object name gets the name from the OBJ_Key class name where we have mentioned the name of the objects .
			
			
			if(gp.currentMap == 0) {
				switch(objectName){
				case "Key":
					haskey++;
					gp.obj[gp.currentMap][i] = null;
					gp.ui.ShowMessage("You got a Axe!!");
					
		
					break;
				case "Chest":   //rock
					if(hashammer > 0) {
						gp.obj[gp.currentMap][i] = null;
						hashammer --;
						gp.ui.ShowMessage("You Destroyed The Rock!!");
					}else {
						gp.ui.ShowMessage("You Need A Hammer To Destroy!!");	
					}
					
					break;
					
				case "Boots":
					 	bootsSpeedBonus = 2; 
					    hasBoots = true;
					    bootsActive = true;  // Activate by default when picked up
					    speed += bootsSpeedBonus;
					    gp.obj[gp.currentMap][i] = null;
					    gp.ui.ShowMessage("Speed UP! Press B to toggle boots.");
					    break;
					    
				case "Hammer":
					hashammer++;
				
					gp.obj[gp.currentMap][i] = null;
					gp.ui.ShowMessage("You got a Hammer!!");
					break;
				case "Gate":
					if(haskey > 0) {
						gp.obj[gp.currentMap][i] = null;
						haskey--;
						gp.ui.ShowMessage("Path Cleared !!");
					}
					else {
					gp.ui.ShowMessage("You Need Axe To Cut The Log!!");
					}
					break;
				case "Final":
					gp.obj[gp.currentMap][i] = null;
					gp.ui.gameFinished = true;
					break;

				
				}
			}
			else if(gp.currentMap == 1) {
				switch(objectName) {
					case "Torch":
						if(gp.currentMap == 1) {
							hastorch++;
							gp.obj[gp.currentMap][i] = null;
							gp.ui.ShowMessage("You got a Torch!!!");
							gp.eManager.updateLightning();
							
						
						}
				
						break;
					case "Strength":
						hasStrength = true;
						gp.obj[gp.currentMap][i] = null;
						break;
					case "Sword":
					    hasSword = true;
					    gp.obj[gp.currentMap][i] = null;
					    gp.ui.ShowMessage("You got a Sword! Press SPACE to attack!");
					    break;
			
						
						
						
						
				}
			}
			else if(gp.currentMap == 2) {
			    switch(objectName) {
			        case "Heal":
			            gp.obj[gp.currentMap][i] = null;
			            break;
			        case "Fireball":
			            hasFireball = true;
			            gp.obj[gp.currentMap][i] = null;
			            gp.ui.ShowMessage("You got Fireball! Press F to shoot!");
			            break;
			    }
			}

			
		}
	}
	
	
	public void interactNPC(int i) {
		
		if( i != 999) {
			
			gp.gameState =  gp.dialougeState;
			gp.npc[gp.currentMap][i].speak();
			
		}
		
		
	}
	
	
	public void toggleBoots() {
	    if(!hasBoots) {
	        gp.ui.ShowMessage("You don't have boots!");
	        return;
	    }
	    
	    if(bootsActive) {
	        // Deactivate boots
	        speed -= bootsSpeedBonus;
	        bootsActive = false;
	        gp.ui.ShowMessage("Boots deactivated!");
	    } else {
	        // Activate boots
	        speed += bootsSpeedBonus;
	        bootsActive = true;
	        gp.ui.ShowMessage("Boots activated!");
	    }
	
	}
	
	public void pushRock() {
		if(!hasStrength) {
			gp.ui.ShowMessage("You need Strength to push rocks!");
			return;
		}
		
		// Calculate player's CENTER position for more accurate tile detection
		int playerCenterX = worldx + gp.tilesize / 2;
		int playerCenterY = worldy + gp.tilesize / 2;
		int playerCol = playerCenterX / gp.tilesize;
		int playerRow = playerCenterY / gp.tilesize;
		int targetCol = playerCol;
		int targetRow = playerRow;
		
		// Calculate which tile is in front of player
		switch(direction) {
		case "up":
			targetRow = playerRow - 1;
			break;
		case "down":
			targetRow = playerRow + 1;
			break;
		case "left":
			targetCol = playerCol - 1;
			break;
		case "right":
			targetCol = playerCol + 1;
			break;
		}
		
		// DEBUG: Print positions
		System.out.println("Player at tile: (" + playerCol + "," + playerRow + ") facing " + direction);
		System.out.println("Looking for rock at tile: (" + targetCol + "," + targetRow + ")");
		
		// Find if there's a CaveRock in front of the player
		int rockIndex = -1;
		
		for(int i = 0; i < gp.obj[gp.currentMap].length; i++) {
			if(gp.obj[gp.currentMap][i] != null && 
			   gp.obj[gp.currentMap][i].name.equals("CaveRock")) {
				
				int objCol = gp.obj[gp.currentMap][i].worldx / gp.tilesize;
				int objRow = gp.obj[gp.currentMap][i].worldy / gp.tilesize;
				
				System.out.println("Found CaveRock at tile: (" + objCol + "," + objRow + ")");
				
				if(objCol == targetCol && objRow == targetRow) {
					rockIndex = i;
					System.out.println("MATCH! Rock found at index: " + i);
					break;
				}
			}
		}
		
		if(rockIndex == -1) {
			gp.ui.ShowMessage("No rock in front of you!");
			System.out.println("No rock found at target position");
			return;
		}
		
		// Calculate new position for the rock (one tile forward)
		int newRockCol = targetCol;
		int newRockRow = targetRow;
		
		switch(direction) {
		case "up":
			newRockRow = targetRow - 1;
			break;
		case "down":
			newRockRow = targetRow + 1;
			break;
		case "left":
			newRockCol = targetCol - 1;
			break;
		case "right":
			newRockCol = targetCol + 1;
			break;
		}
		
		// Check if the new position is valid
		if(canPushRockTo(newRockCol, newRockRow)) {
			// Move the rock
			gp.obj[gp.currentMap][rockIndex].worldx = newRockCol * gp.tilesize;
			gp.obj[gp.currentMap][rockIndex].worldy = newRockRow * gp.tilesize;
			gp.ui.ShowMessage("Rock pushed!");
		} else {
			gp.ui.ShowMessage("Can't push rock there!");
		}
	}
	
	
	
	
	// Helper method to check if rock can be pushed to a position
	private boolean canPushRockTo(int col, int row) {
		// Check map boundaries
		if(col < 0 || col >= gp.maxworldcol || row < 0 || row >= gp.maxworldrow) {
			return false;
		}
		
		// Check if tile is solid (wall/obstacle)
		int tileNum = gp.tileM.maptileno[gp.currentMap][col][row];
		if(gp.tileM.tile[tileNum].collision) {
			return false;
		}
		
		// Check if another object is already there
		for(int i = 0; i < gp.obj[gp.currentMap].length; i++) {
			if(gp.obj[gp.currentMap][i] != null) {
				int objCol = gp.obj[gp.currentMap][i].worldx / gp.tilesize;
				int objRow = gp.obj[gp.currentMap][i].worldy / gp.tilesize;
				
				if(objCol == col && objRow == row) {
					return false; // Another object is blocking
				}
			}
		}
		
		return true; // Position is clear
	}
	
	
	
	
	
	
	//monster
	//monster - ONLY for mini monsters now
	public void contactMonster(int i) {
	    if(i != 999) {
	        if(invincible == false) {
	            // Only mini monsters use this collision damage
	            if(!(gp.monster[gp.currentMap][i] instanceof monster.MON_Main)) {
	                life -= 1; // Mini monsters deal 1 heart
	                gp.ui.ShowMessage("Monster hit you!");
	                invincible = true;
	            }
	            // Boss handles its own damage in MON_Main.java
	        }
	    }
	}
	public void attackMonster() {
	    if(!hasSword) {
	        gp.ui.ShowMessage("You need a sword to attack!");
	        return;
	    }

	    if (attackCooldown > 0) return;

	    // start attack animation
	    attacking = true;
	    attackCooldown = 30;
	    int playerCol = worldx / gp.tilesize;
	    int playerRow = worldy / gp.tilesize;
	    int attackCol = playerCol;
	    int attackRow = playerRow;

	    switch(direction) {
	        case "up": attackRow = playerRow - 1; break;
	        case "down": attackRow = playerRow + 1; break;
	        case "left": attackCol = playerCol - 1; break;
	        case "right": attackCol = playerCol + 1; break;
	    }

	    boolean hitMonster = false;
	    for(int i = 0; i < gp.monster[gp.currentMap].length; i++) {
	        if(gp.monster[gp.currentMap][i] != null && gp.monster[gp.currentMap][i].life > 0) {
	            int monsterCol = gp.monster[gp.currentMap][i].worldx / gp.tilesize;
	            int monsterRow = gp.monster[gp.currentMap][i].worldy / gp.tilesize;

	            if(monsterCol == attackCol && monsterRow == attackRow) {
	                // damage monster
	                gp.monster[gp.currentMap][i].life -= 1;
	                gp.ui.ShowMessage("Hit! Monster HP: " + gp.monster[gp.currentMap][i].life);
	                gp.monster[gp.currentMap][i].hurtCounter = 30;
	                hitMonster = true;
	                
	                if(gp.monster[gp.currentMap][i].life <= 0) {
	                    // Check if it's the MAIN BOSS on MAP 3
	                    if(gp.monster[gp.currentMap][i] instanceof monster.MON_Main && gp.currentMap == 3) {
	                        // Final Boss defeated - SAVE AND STOP TIMER
	                        gp.finalCompletionTime = gp.totalPlayTime; // SAVE THE FINAL TIME
	                        gp.timerRunning = false;
	                        gp.ui.ShowMessage("FINAL BOSS DEFEATED!");
	                        gp.ui.gameFinished = true;
	                        gp.gameState = gp.finishState;
	                    } else {
	                        // Mini monster defeated - NO VICTORY SCREEN
	                        gp.ui.ShowMessage("Monster defeated!");
	                    }
	                }
	                
	                break;
	            }
	        }
	    }

	    if(!hitMonster) {
	        gp.ui.ShowMessage("Swing!");
	    }
	}
	
	public void shootFireball() {
		
		  System.out.println("shootFireball called!");
	    if (!hasFireball) {
	    	 System.out.println("You need firebll to attack");
	        gp.ui.ShowMessage("You need fireball ability!");
	        return;
	    }
	    
	    if (fireballCooldown > 0) {
	    	 System.out.println("Cooldown active: " + fireballCooldown);
	        return; // Still on cooldown
	    }
	    
	    // Set cooldown (60 frames = 1 second at 60 FPS)
	    fireballCooldown = 40;
	    
	    // Determine spawn position (in front of player)
	    int spawnX = worldx;
	    int spawnY = worldy;
	    BufferedImage fireballImage = null;
	    
	    switch(direction) {
	    
	        case "up":
	            spawnY -= gp.tilesize;
	            fireballImage = fireballUp;
	            break;
	        case "down":
	            spawnY += gp.tilesize;
	            fireballImage = fireballDown;
	            break;
	        case "left":
	            spawnX -= gp.tilesize;
	            fireballImage = fireballLeft;
	            break;
	        case "right":
	            spawnX += gp.tilesize;
	            fireballImage = fireballRight;
	            break;
	    }
	    
	    // Create new projectile
	    Projectile fireball = new Projectile(gp, spawnX, spawnY, direction, fireballImage);
	    projectiles.add(fireball);
	    
	    gp.ui.ShowMessage("Fireball launched!");
	}

	public void updateProjectiles() {
	    Iterator<Projectile> iterator = projectiles.iterator();
	    while (iterator.hasNext()) {
	        Projectile proj = iterator.next();
	        proj.update();
	        
	        // Remove inactive projectiles
	        if (!proj.active) {
	            iterator.remove();
	        }
	    }
	}
	
	
	
	public void draw(Graphics2D g2) {
	    BufferedImage image = null;
	    
	    if (attacking) {
	        switch(direction) {
	        case "up": image = attackUp; break;
	        case "down": image = attackDown; break;
	        case "left": image = attackLeft; break;
	        case "right": image = attackRight; break;
	        }
	    } else {
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
	    }
	    
	    g2.drawImage(image,screenx,screeny,gp.tilesize,gp.tilesize,null);
	    
	    // Draw all active projectiles
	    for(Projectile proj : projectiles) {
	        proj.draw(g2);
	    }
	}

}





