package main;

import java.awt.Color;
import java.awt.Graphics2D;

public class EventHandler {

    Gamepannel gp;
    EventRect eventRect[][][];
    int previousEventX,previousEventY;
    boolean canTouchEvent = true;
    boolean bossMessageShown = false;
    
    public EventHandler(Gamepannel gp) {
        this.gp = gp;

        eventRect = new EventRect[gp.maxMap][gp.maxworldcol][gp.maxworldrow];
        int map = 0;
        int col = 0;
        int row = 0;
        
        while(map < gp.maxMap && col < gp.maxworldcol && row < gp.maxworldrow) {
            
            eventRect[map][col][row] = new EventRect();
            
            // ========== FIXED: Make events cover the ENTIRE tile ==========
            eventRect[map][col][row].x = 0;              // Was 23
            eventRect[map][col][row].y = 0;              // Was 23
            eventRect[map][col][row].width = gp.tilesize;   // Was 20 (now 48 pixels)
            eventRect[map][col][row].height = gp.tilesize;  // Was 20 (now 48 pixels)
            // ================================================================
            
            eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
            eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;

            col++;
            
            if(col == gp.maxworldcol) {
                col = 0;
                row++;
                
                if(row == gp.maxworldrow) {
                    row = 0;
                    map++;
                }
            }
        }
    }
    
    public void bossEntrance() {
        gp.gameState = gp.dialougeState;
        gp.ui.currentDialouge = "FINAL BOSS AHEAD! \nPrepare yourself!";
        bossMessageShown = true;
    }

    public void checkEvent() {
        
        // Check if the player is one tile more than the last event
        int xDistance = Math.abs(gp.player.worldx - previousEventX);
        int yDistance = Math.abs(gp.player.worldy - previousEventY);
        
        int distance = Math.max(xDistance, yDistance);
        
        if(distance > gp.tilesize) {
            canTouchEvent = true;
        }
        
        if (canTouchEvent == true) {
            
            if(hit(1, 58, 62, "up")) {
                damagePit(1, 58, 62, gp.dialougeState);
            }
            else if(hit(1, 59, 62, "up")) {
                damagePit(1, 59, 62, gp.dialougeState);
            }
            else if(hit(1, 53, 56, "any")) {
                healingpPool(1, 53, 56, gp.dialougeState);
            }  
            else if(hit(0, 35, 13, "any") == true) {  // Changed from "up" to "any"
                gp.ui.gameFinished = true;
                teleport(1, 52, 69);
            }
            else if(hit(0, 36, 13, "any") == true) {  // Changed from "up" to "any"
                gp.ui.gameFinished = true;
                teleport(1, 52, 69);
            }
            else if(hit(1, 70, 55, "any") == true) {
                Strength(1, 70, 55, gp.dialougeState);
            } 
            else if(hit(1, 35, 9, "any") == true) {
                gp.ui.gameFinished = true;
                teleport(2, 54, 56);
            }
            else if(hit(2, 59, 59, "any")) {
                healingpPool(2, 59, 59, gp.dialougeState);
            }  
            else if(hit(2, 33, 22, "any")) {
                healingpPool(2, 33, 22, gp.dialougeState);
            } 
            else if(hit(2, 50, 45, "any")) {
                healingpPool(2, 50, 45, gp.dialougeState);
            } 
            else if(hit(2, 25, 22, "any")) {
                healingpPool(2, 25, 22, gp.dialougeState);
            } 
            else if(hit(2, 50, 59, "any")) {
                healingpPool(2, 50, 59, gp.dialougeState);
            } 
            else if(hit(2, 35, 14, "any") == true) {
                teleport(3, 30, 37);
            }
            
            if(gp.currentMap == 3 && !bossMessageShown) {
                if(hit(3, 30, 37, "any")) {
                    bossEntrance();
                }
            }
        }
    }        
    
    public void torch(int map, int col, int row, int gameState) {
        gp.gameState = gameState;
        gp.ui.currentDialouge = "You Got A Torch.";
        
        canTouchEvent = false;
        eventRect[map][col][row].eventDone = true;
        
        removeEventObject(map, col, row);
    }
    
    public void Strength(int map, int col, int row, int gameState) {
        gp.gameState = gameState;
        gp.ui.currentDialouge = "You Got A Strength Attack Press M To Move The Rock.";
        
        canTouchEvent = false;
        eventRect[map][col][row].eventDone = true;
        removeEventObject(map, col, row);
    }

    public boolean hit(int map, int col, int row, String reqDir) {
        boolean hit = false;
        
        if(map == gp.currentMap) {

            // Convert player collision box to world pos
            gp.player.solidarea.x = gp.player.worldx + gp.player.solidarea.x;
            gp.player.solidarea.y = gp.player.worldy + gp.player.solidarea.y;

            eventRect[map][col][row].x = col * gp.tilesize + eventRect[map][col][row].x;
            eventRect[map][col][row].y = row * gp.tilesize + eventRect[map][col][row].y;
            
            if(gp.player.solidarea.intersects(eventRect[map][col][row]) && 
               eventRect[map][col][row].eventDone == false) {
                if(gp.player.direction.contentEquals(reqDir) || reqDir.contentEquals("any")) {
                    hit = true;
                    
                    previousEventX = gp.player.worldx;
                    previousEventY = gp.player.worldy;
                }
            }

            // Reset
            gp.player.solidarea.x = gp.player.solidareadefaultx;
            gp.player.solidarea.y = gp.player.solidareadefaulty;
            eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
            eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;
        }
        
        return hit;
    }

    public void damagePit(int map, int col, int row, int gameState) {
        gp.gameState = gameState;
        gp.ui.currentDialouge = "You fell into a pit!";
        gp.player.life -= 1;
        canTouchEvent = false;
    }
    
    public void healingpPool(int map, int col, int row, int gameState) {
        gp.gameState = gameState;
        gp.ui.currentDialouge = "Magic potion healed you!";
        if(gp.player.life < gp.player.maxLife) {
            gp.player.life += 2;
            if(gp.player.life > gp.player.maxLife)
                gp.player.life = gp.player.maxLife;
        }
        eventRect[map][col][row].eventDone = true;
        removeEventObject(map, col, row);
    }

    private void removeEventObject(int map, int col, int row) {
        for(int i = 0; i < gp.obj[1].length; i++) {
            if(gp.obj[gp.currentMap][i] != null) {
                int objCol = gp.obj[gp.currentMap][i].worldx / gp.tilesize;
                int objRow = gp.obj[gp.currentMap][i].worldy / gp.tilesize;

                if(objCol == col && objRow == row &&
                   (gp.obj[gp.currentMap][i].name.equals("Heal") ||
                    gp.obj[gp.currentMap][i].name.equals("Pit"))) {

                    gp.obj[gp.currentMap][i] = null;
                    break;
                }
            }
        }
    }

    public void draw(Graphics2D g2) {
        int map = gp.currentMap;

        g2.setColor(Color.BLACK);

        for(int col = 0; col < gp.maxworldcol; col++) {
            for(int row = 0; row < gp.maxworldrow; row++) {

                EventRect er = eventRect[map][col][row];

                if (!er.isEvent || er.eventDone)
                    continue;

                int drawX = col * gp.tilesize + er.x;
                int drawY = row * gp.tilesize + er.y;

                g2.fillRect(drawX, drawY, er.width, er.height);
            }
        }
    }
    
    public void teleport(int map, int col, int row) {
        // Auto-save before teleporting to new map
        gp.saveLoad.save();
        gp.ui.ShowMessage("Progress auto-saved!");
        
        gp.currentMap = map;
        gp.player.worldx = gp.tilesize * col;
        gp.player.worldy = gp.tilesize * row;
        
        previousEventX = gp.player.worldx;
        previousEventY = gp.player.worldy;
        canTouchEvent = false;
        
        resetEventFlags(map);
        gp.asetter.setObject();
    }
    
    public void resetEventFlags(int map) {
        for(int col = 0; col < gp.maxworldcol; col++) {
            for(int row = 0; row < gp.maxworldrow; row++) {
                eventRect[map][col][row].eventDone = false;
            }
        }
    }
}