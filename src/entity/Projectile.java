package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import main.Gamepannel;

public class Projectile {
    
    public Gamepannel gp;
    public int worldx, worldy;
    public String direction;
    public int speed = 8; // Fast movement
    public BufferedImage image;
    public boolean active = true;
    public int damage = 2; // Fireball does 2 damage
    
    // Travel distance tracking
    public int startX, startY;
    public int maxDistance; // in pixels
    
    // Collision box
    public Rectangle solidarea;
    
    public Projectile(Gamepannel gp, int worldx, int worldy, String direction, BufferedImage image) {
    	 System.out.println("Projectile created at: " + worldx + ", " + worldy + " dir: " + direction);
        this.gp = gp;
        this.worldx = worldx;
        this.worldy = worldy;
        this.startX = worldx;
        this.startY = worldy;
        this.direction = direction;
        this.image = image;
        this.maxDistance = gp.tilesize * 4; // 4 tiles range
        
        // Small collision box for the fireball
        solidarea = new Rectangle(8, 8, 32, 32);
    }
    
    public void update() {
        if (!active) return;
        
        // Move the projectile
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
        
        // Check if traveled max distance
        int distanceTraveled = Math.abs(worldx - startX) + Math.abs(worldy - startY);
        if (distanceTraveled >= maxDistance) {
            active = false;
            return;
        }
        
        // Check tile collision
        if (checkTileCollision()) {
            active = false;
            return;
        }
        
        // Check monster collision
        checkMonsterCollision();
    }
    
    private boolean checkTileCollision() {
        int entityLeftWorldx = worldx + solidarea.x;
        int entityRightWorldx = worldx + solidarea.x + solidarea.width;
        int entityTopWorldy = worldy + solidarea.y;
        int entityBottomWorldy = worldy + solidarea.y + solidarea.height;
        
        int entityleftcol = entityLeftWorldx / gp.tilesize;
        int entityrightcol = entityRightWorldx / gp.tilesize;
        int entitytoprow = entityTopWorldy / gp.tilesize;
        int entitybottomrow = entityBottomWorldy / gp.tilesize;
        
        int tilenum1, tilenum2;
        
        switch(direction) {
            case "up":
                entitytoprow = (entityTopWorldy - speed) / gp.tilesize;
                tilenum1 = gp.tileM.maptileno[gp.currentMap][entityleftcol][entitytoprow];
                tilenum2 = gp.tileM.maptileno[gp.currentMap][entityrightcol][entitytoprow];
                if (gp.tileM.tile[tilenum1].collision || gp.tileM.tile[tilenum2].collision) {
                    return true;
                }
                break;
            case "down":
                entitybottomrow = (entityBottomWorldy + speed) / gp.tilesize;
                tilenum1 = gp.tileM.maptileno[gp.currentMap][entityleftcol][entitybottomrow];
                tilenum2 = gp.tileM.maptileno[gp.currentMap][entityrightcol][entitybottomrow];
                if (gp.tileM.tile[tilenum1].collision || gp.tileM.tile[tilenum2].collision) {
                    return true;
                }
                break;
            case "left":
                entityleftcol = (entityLeftWorldx - speed) / gp.tilesize;
                tilenum1 = gp.tileM.maptileno[gp.currentMap][entityleftcol][entitytoprow];
                tilenum2 = gp.tileM.maptileno[gp.currentMap][entityleftcol][entitybottomrow];
                if (gp.tileM.tile[tilenum1].collision || gp.tileM.tile[tilenum2].collision) {
                    return true;
                }
                break;
            case "right":
                entityrightcol = (entityRightWorldx + speed) / gp.tilesize;
                tilenum1 = gp.tileM.maptileno[gp.currentMap][entityrightcol][entitytoprow];
                tilenum2 = gp.tileM.maptileno[gp.currentMap][entityrightcol][entitybottomrow];
                if (gp.tileM.tile[tilenum1].collision || gp.tileM.tile[tilenum2].collision) {
                    return true;
                }
                break;
        }
        return false;
    }
    private void checkMonsterCollision() {
        Rectangle projectileBox = new Rectangle(
            worldx + solidarea.x,
            worldy + solidarea.y,
            solidarea.width,
            solidarea.height
        );
        
        for (int i = 0; i < gp.monster[gp.currentMap].length; i++) {
            if (gp.monster[gp.currentMap][i] != null && gp.monster[gp.currentMap][i].life > 0) {
                Entity monster = gp.monster[gp.currentMap][i];
                
                Rectangle monsterBox = new Rectangle(
                    monster.worldx + monster.solidarea.x,
                    monster.worldy + monster.solidarea.y,
                    monster.solidarea.width,
                    monster.solidarea.height
                );
                
                if (projectileBox.intersects(monsterBox)) {
                    // Hit the monster!
                    monster.life -= damage;
                    monster.hurtCounter = 30;
                    
                    gp.ui.ShowMessage("Fireball hit! Monster HP: " + monster.life);
                    
                    if (monster.life <= 0) {
                        // Check if it's the MAIN BOSS on MAP 3
                        if (monster instanceof monster.MON_Main && gp.currentMap == 3) {
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
                    
                    // Deactivate projectile after hit
                    active = false;
                    return;
                }
            }
        }
    }
    public void draw(Graphics2D g2) {
        if (!active) return;
        
        int screenx = worldx - gp.player.worldx + gp.player.screenx;
        int screeny = worldy - gp.player.worldy + gp.player.screeny;
        
        // Only draw if on screen
        if (worldx + gp.tilesize > gp.player.worldx - gp.player.screenx &&
            worldx - gp.tilesize < gp.player.worldx + gp.player.screenx &&
            worldy + gp.tilesize > gp.player.worldy - gp.player.screeny &&
            worldy - gp.tilesize < gp.player.worldy + gp.player.screeny) {
            
            g2.drawImage(image, screenx, screeny, gp.tilesize, gp.tilesize, null);
        }
    }
}