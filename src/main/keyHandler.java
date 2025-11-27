package main;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class keyHandler implements KeyListener {
    Gamepannel gp;
    
    public boolean uppress,downpress,leftpress,rightpress,enterpressed;
    public boolean tabpress = false;
    boolean checkDrawTime = false;
    
    public keyHandler(Gamepannel gp) {
        this.gp = gp;
    }
    
    public void keyTyped(KeyEvent e) {
        
    }
    
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        
        // TITLE STATE
        if(gp.gameState == gp.titleState) {
        	
        	if (code == KeyEvent.VK_ESCAPE) {
        	    gp.ui.toggleControls();
        	}
            
            if (code == KeyEvent.VK_W) {
                gp.ui.commandNum--;
                if(gp.ui.commandNum < 0) {
                    gp.ui.commandNum = 3; 
                }
                
                if(gp.ui.commandNum == 1 && !gp.saveLoad.saveFileExists()) {
                    gp.ui.commandNum = 0;
                }
                
                if(gp.ui.commandNum == 2 && !gp.saveLoad.saveFileExists()) {
                    gp.ui.commandNum = 1;
                }
            }
            
            if (code == KeyEvent.VK_S) {
                gp.ui.commandNum++;
                if(gp.ui.commandNum > 3) {
                    gp.ui.commandNum = 0;
                }
                
                if(gp.ui.commandNum == 1 && !gp.saveLoad.saveFileExists()) {
                    gp.ui.commandNum = 2;
                }
                
                if(gp.ui.commandNum == 2 && !gp.saveLoad.saveFileExists()) {
                    gp.ui.commandNum = 3;
                }
            }
            
            if(code == KeyEvent.VK_ENTER) {
                if(gp.ui.commandNum == 0) {
                    // NEW GAME - FIXED
                    startNewGame();
                }
                if(gp.ui.commandNum == 1) {
                    // LOAD GAME
                    if(gp.saveLoad.saveFileExists()) {
                        gp.saveLoad.load();
                        gp.gameState = gp.playState;
                    }
                }
           
                if(gp.ui.commandNum == 2) {
                    // DELETE SAVE
                    if(gp.saveLoad.saveFileExists()) {
                        gp.saveLoad.deleteSave();
                        gp.ui.commandNum = 0;
                    }
                }
                if(gp.ui.commandNum == 3) { 
                    // QUIT
                    System.exit(0);
                }
            }
        }
        
        // GAME OVER STATE
        else if(gp.gameState == gp.gameOver) {
            gameOverState(code);
        }
        
        // PLAY STATE
        else if(gp.gameState == gp.playState) {
            
            if (code == KeyEvent.VK_W) {
                uppress = true;
            }
            if (code == KeyEvent.VK_S) {
                downpress = true;
            }
            if (code == KeyEvent.VK_A) {
                leftpress = true;
            }	
            if (code == KeyEvent.VK_D) {
                rightpress= true ;
            }

            if (code == KeyEvent.VK_B) {
            	gp.player.toggleBoots();
            }
            if(code == KeyEvent.VK_SPACE) {
                gp.player.attackMonster();
            }
            if (code == KeyEvent.VK_F) {
                System.out.println("F KEY PRESSED!"); 
                gp.player.shootFireball();
            }
            
            if (code == KeyEvent.VK_P) {
                gp.gameState = gp.pausedState;
            }
            
            if (code == KeyEvent.VK_ESCAPE) {
                gp.gameState = gp.titleState;
                gp.ui.commandNum = 0;
            }
            
            if (code == KeyEvent.VK_F5) {
                gp.saveLoad.save();
                gp.ui.ShowMessage("Game Saved!");
            }
            
            if (code == KeyEvent.VK_T) {
                if(checkDrawTime == false) {
                    checkDrawTime = true;
                }
                else if(checkDrawTime == true) {
                    checkDrawTime = false;
                }
            }
            
            if (code == KeyEvent.VK_M) {
                if(gp.currentMap == 1) {
                    gp.player.pushRock();
                }
            }
            
            if(code == KeyEvent.VK_R) {
                switch(gp.currentMap) {
                    case 0: 
                        gp.tileM.loadmap("/maps/map.txt",0);
                        break;
                    case 1:
                        gp.tileM.loadmap("/maps/cavefinalmap.txt",1);
                        break;
                }
            }
        }
        
        // PAUSE STATE
        else if(gp.gameState == gp.pausedState) {
            if (code == KeyEvent.VK_P) {
                gp.gameState = gp.playState;
            }
            
            if (code == KeyEvent.VK_F5) {
                gp.saveLoad.save();
                gp.ui.ShowMessage("Game Saved!");
            }
            
            if (code == KeyEvent.VK_ESCAPE) {
                gp.gameState = gp.titleState;
                gp.ui.commandNum = 0;
            }
            
            if (code == KeyEvent.VK_DELETE) {
                if(gp.saveLoad.saveFileExists()) {
                    gp.saveLoad.deleteSave();
                    gp.ui.ShowMessage("Save Deleted!");
                }
            }
        }
        
        // DIALOGUE STATE
        else if(gp.gameState == gp.dialougeState) {
            if (code == KeyEvent.VK_ENTER) {
                gp.gameState = gp.playState;
            }
        }
    }
    
    // ==================== NEW METHODS ====================
    
    public void startNewGame() {
        // Reset current map to starting map
        gp.currentMap = 0;
        
        gp.gameStartTime = System.currentTimeMillis();
        gp.totalPlayTime = 0;
        gp.finalCompletionTime = 0; // RESET final time
        gp.timerRunning = true;
        
        // Reset player position and stats
        gp.player.SetDefaultPosition();
        gp.player.restoreLife();
        
        // Reset player inventory
        gp.player.haskey = 0;
        gp.player.hashammer = 0;
        gp.player.hastorch = 0;
        gp.player.hasStrength = false;
        gp.player.hasSword = false;
        gp.player.hasFireball = false;
        
        // Reset player cooldowns and states
        gp.player.attackCooldown = 0;
        gp.player.fireballCooldown = 0;
        gp.player.invincible = false;
        gp.player.invincibleCounter = 0;
        gp.player.projectiles.clear();
        
        // Reset objects on all maps
        gp.asetter.setObject();
        
        // Reset NPCs
        gp.asetter.setNPC();
        
        // Reset monsters
        gp.asetter.setMonster();
        
        // Reset game state
        gp.gameState = gp.playState;
        gp.ui.gameFinished = false;
        
    
        
        
        // Reset event handler
        gp.eHandler.canTouchEvent = true;
        gp.eHandler.resetEventFlags(0);
        gp.eHandler.resetEventFlags(1);
        gp.eHandler.resetEventFlags(2);
        
        // Reset lighting if using lightning system
        if(gp.eManager != null) {
            gp.eManager.setup();
        }
        
        gp.ui.ShowMessage("New game started!");
    }
    
    public void gameOverState(int code) {
        if(code == KeyEvent.VK_W) {
            gp.ui.commandNum--;
            if(gp.ui.commandNum < 0) {
                gp.ui.commandNum = 1;
            }
        }
        
        if(code == KeyEvent.VK_S) {
            gp.ui.commandNum++;
            if(gp.ui.commandNum > 1) {
                gp.ui.commandNum = 0;
            }
        }
        
        if(code == KeyEvent.VK_ENTER) {
            if(gp.ui.commandNum == 0) {
                // RETRY
                retry();
            }
            else if(gp.ui.commandNum == 1) {
                // QUIT
                quitToTitleScreen();
            }
        }
    }
    
    public void retry() {
        if(gp.saveLoad.saveFileExists()) {
            gp.saveLoad.load();
            gp.gameState = gp.playState;
            gp.player.life=6;
            gp.ui.ShowMessage("Game loaded from save!");
        } else {
            restartFromCurrentMap();
        }
    }
    
    public void restartFromCurrentMap() {
        gp.player.SetDefaultPosition();
        gp.player.restoreLife();
        
        gp.player.projectiles.clear();
        gp.player.fireballCooldown = 0;
        gp.player.attackCooldown = 0;
        gp.player.invincible = false;
        gp.player.invincibleCounter = 0;
        
        gp.asetter.setMonster();
        
        gp.gameState = gp.playState;
        gp.ui.ShowMessage("Restarting from checkpoint...");
    }
    
    public void quitToTitleScreen() {
        // Reset player health so they don't immediately die again
        gp.player.restoreLife();
        
        // Clear any active projectiles
        gp.player.projectiles.clear();
        
        // Reset UI
        gp.ui.commandNum = 0;
        gp.ui.messageOn = false;
        
        // Go to title screen
        gp.gameState = gp.titleState;
    }
    

    
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        
        if (code == KeyEvent.VK_W) {
            uppress = false;
        }
        if (code == KeyEvent.VK_S) {
            downpress = false;
        }
        if (code == KeyEvent.VK_A) {
            leftpress = false;
        }
        if (code == KeyEvent.VK_D) {
            rightpress= false ;
        }
        if(code == KeyEvent.VK_TAB) {
            tabpress = false;
        }
    }
}