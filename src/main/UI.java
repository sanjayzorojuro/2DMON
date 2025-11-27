package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import object.OBJ_Hammer;
import object.OBJ_Heart;
import object.OBJ_Key;
import object.OBJ_Sword;
import object.OBJ_Torch;
import object.SuperObject;

public class UI {

	Gamepannel gp;
	Graphics2D g2;
	Font arial_40, arial_80B;
	BufferedImage keyImage;
	BufferedImage hammerImage;
	BufferedImage endimage;
	BufferedImage torchImage;
	BufferedImage swordImage;
	BufferedImage fireballImage;
	BufferedImage heartfull, hearthalf, heartblank;
	public boolean messageOn = false;
	public String message = "";
	int messageCounter = 0;
	public boolean gameFinished = false;
	public String currentDialouge = " "; 
	public int commandNum = 0;
	private boolean showControls = false;
	
	// Timer for congratulations screen
	private int congratsTimer = 0;
	private final int CONGRATS_DISPLAY_TIME = 240; // 4 seconds at 60 FPS
	
	public UI(Gamepannel gp) {
		this.gp = gp;
		
		arial_40 = new Font("Arial", Font.PLAIN, 40);
		arial_80B = new Font("Arial", Font.BOLD, 80);
		
		OBJ_Key key = new OBJ_Key(gp);
		keyImage = key.image;
		
		OBJ_Hammer hammer = new OBJ_Hammer(gp);
		hammerImage = hammer.image;
		
		OBJ_Torch torch = new OBJ_Torch(gp);
		torchImage = torch.image;
		
		OBJ_Sword sword = new OBJ_Sword(gp);
		swordImage = sword.image;
		
		// Create HUD object
		SuperObject heart = new OBJ_Heart(gp);
		heartfull = heart.image;
		hearthalf = heart.image2;
		heartblank = heart.image3;
	}
	
	public void ShowMessage(String text) {
		message = text;
		messageOn = true;
	}
	
	public void draw(Graphics2D g2) {
	    this.g2 = g2;
	    g2.setFont(arial_40);
	    g2.setColor(Color.white);
	    
	    // FINISH STATE - Boss defeated (MAP 3 ONLY)
	 // FINISH STATE - ONLY show timer for Final Boss on MAP 3
	    if (gameFinished == true && gp.currentMap == 3) {
	        // Clear screen
	        g2.setColor(Color.BLACK);
	        g2.fillRect(0, 0, gp.screenwidth, gp.screenheight);

	        String mainText = " VICTORY! ";
	        String subText = "You defeated the Final Boss!";
	        String subText2 = "Congratulations, Hero!";
	        String timeText = "Completion Time: " + formatTime(gp.finalCompletionTime); // USE finalCompletionTime

	        // Draw subtitle
	        g2.setFont(arial_40);
	        g2.setColor(Color.yellow);
	        int textWidth = (int) g2.getFontMetrics().getStringBounds(subText, g2).getWidth();
	        int x = gp.screenwidth/2 - textWidth/2;
	        int y = gp.screenheight/2 - (gp.tilesize * 3);
	        g2.drawString(subText, x, y);

	        // Draw main title
	        g2.setFont(arial_80B);
	        g2.setColor(Color.green);
	        textWidth = (int) g2.getFontMetrics().getStringBounds(mainText, g2).getWidth();
	        x = gp.screenwidth/2 - textWidth/2;
	        y = gp.screenheight/2;
	        g2.drawString(mainText, x, y);

	        // Draw second subtitle
	        g2.setFont(arial_40);
	        g2.setColor(Color.white);
	        textWidth = (int) g2.getFontMetrics().getStringBounds(subText2, g2).getWidth();
	        x = gp.screenwidth/2 - textWidth/2;
	        y += gp.tilesize * 1.5;
	        g2.drawString(subText2, x, y);

	        // Draw completion time - FROZEN at the moment boss was defeated
	        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 35F));
	        g2.setColor(Color.CYAN);
	        textWidth = (int) g2.getFontMetrics().getStringBounds(timeText, g2).getWidth();
	        x = gp.screenwidth/2 - textWidth/2;
	        y += gp.tilesize * 1.5;
	        g2.drawString(timeText, x, y);

	        congratsTimer++;

	        if (congratsTimer >= 360) { // 6 seconds
	            congratsTimer = 0;
	            gameFinished = false;
	            gp.gameState = gp.titleState;
	            gp.ui.commandNum = 0;
	        }

	        return;
	    }
	    // Regular map completion (maps 0, 1, 2) - Just show "Next Round!"
	    if (gameFinished == true) {
	        g2.setColor(Color.BLACK);
	        g2.fillRect(0, 0, gp.screenwidth, gp.screenheight);

	        String mainText = "Next Round!";

	        g2.setFont(arial_80B);
	        g2.setColor(Color.yellow);
	        int textWidth = (int) g2.getFontMetrics().getStringBounds(mainText, g2).getWidth();
	        int x = gp.screenwidth/2 - textWidth/2;
	        int y = gp.screenheight/2;
	        g2.drawString(mainText, x, y);

	        congratsTimer++;

	        if (congratsTimer >= 120) { // 2 seconds
	            congratsTimer = 0;
	            gameFinished = false;
	            gp.gameState = gp.playState;
	        }

	        return;
	    }

	    // TITLE SCREEN
	    if(gp.gameState == gp.titleState) {
	        drawTitleScreen();
	        
	        // Draw controls guide if ESC was pressed
	        if(showControls) {
	            drawControlsGuide();
	        }
	    }
	    // PLAY STATE
	    else if(gp.gameState == gp.playState) {
	        // MAP 0 - Draw inventory items
	        if(gp.currentMap == 0) {
	            g2.setFont(arial_40);
	            g2.setColor(Color.white);
	            g2.drawImage(keyImage, gp.tilesize/2, gp.tilesize/2, gp.tilesize, gp.tilesize, null);
	            g2.drawString("x" + gp.player.haskey, 74, 55);
	            g2.drawImage(hammerImage, gp.tilesize/2, gp.tilesize*2, gp.tilesize, gp.tilesize, null);
	            g2.drawString("x" + gp.player.hashammer, 74, 125);
	        }
	        else if(gp.currentMap == 1) {
	            g2.setFont(arial_40);
	            g2.setColor(Color.white);
	            g2.drawImage(torchImage, gp.tilesize/2, gp.tilesize/2, gp.tilesize, gp.tilesize, null);
	            g2.drawString("x" + gp.player.hastorch, 74, 58);
	        }
	        else if(gp.currentMap == 2) {
	            g2.setFont(arial_40);
	            g2.setColor(Color.white);
	            g2.drawImage(swordImage, gp.tilesize/2, gp.tilesize/2, gp.tilesize, gp.tilesize, null);
	        }
	        
	        // Show message
	        if (messageOn == true) {
	            g2.setFont(g2.getFont().deriveFont(30F));
	            g2.setColor(Color.white);
	            int messageX = gp.tilesize/2;
	            int messageY = gp.tilesize*5;
	            int padding = 10;
	            int messageWidth = (int)g2.getFontMetrics().getStringBounds(message, g2).getWidth();
	            g2.setColor(new Color(0, 0, 0, 180));
	            g2.fillRoundRect(messageX - padding, messageY - 25, 
	                            messageWidth + padding*2, 35, 10, 10);
	            g2.setColor(Color.white);
	            g2.drawString(message, messageX, messageY);
	            messageCounter++;
	            if(messageCounter > 120) {
	                messageCounter = 0;
	                messageOn = false;
	            }
	        }
	        
	        drawPlayerLife();
	    }
	    else if(gp.gameState == gp.pausedState) {
	        drawPlayerLife();
	        drawPausedScreen();	
	    }
	    else if(gp.gameState == gp.dialougeState) {
	        drawPlayerLife();
	        drawDialougeScreen();
	    }
	    else if(gp.gameState == gp.gameOver){
	        drawGameOverScreen();
	    }
	}	
	
	
	public String formatTime(long milliseconds) {
	    long totalSeconds = milliseconds / 1000;
	    long hours = totalSeconds / 3600;
	    long minutes = (totalSeconds % 3600) / 60;
	    long seconds = totalSeconds % 60;
	    long millis = (milliseconds % 1000) / 10; // Get centiseconds
	    
	    if(hours > 0) {
	        return String.format("%d:%02d:%02d.%02d", hours, minutes, seconds, millis);
	    } else {
	        return String.format("%d:%02d.%02d", minutes, seconds, millis);
	    }
	}
	
	
	public void drawControlsGuide() {
	    // Semi-transparent background
	    g2.setColor(new Color(0, 0, 0, 200));
	    int guideX = gp.screenwidth - 320;
	    int guideY = 50;
	    g2.fillRoundRect(guideX, guideY, 300, 450, 20, 20);
	    
	    // Border
	    g2.setColor(Color.yellow);
	    g2.setStroke(new BasicStroke(3));
	    g2.drawRoundRect(guideX, guideY, 300, 450, 20, 20);
	    
	    // Title
	    g2.setFont(g2.getFont().deriveFont(Font.BOLD, 30F));
	    g2.setColor(Color.yellow);
	    String title = "CONTROLS";
	    int x = guideX + 150 - (int)g2.getFontMetrics().getStringBounds(title, g2).getWidth()/2;
	    int y = guideY + 40;
	    g2.drawString(title, x, y);
	    
	    // Controls list
	    g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20F));
	    g2.setColor(Color.white);
	    y += 50;
	    
	    String[] controls = {
	        "W/A/S/D - Move",
	        "SPACE - Attack",
	        "F - Fireball",
	        "M - Push Rock",
	        "P - Pause",
	        "F5 - Save Game",
	        "ESC - Menu",
	        "ENTER - Interact",
	        "B - Activate/Deactivate Boots"
	    };
	    
	    for(String control : controls) {
	        g2.drawString(control, guideX + 20, y);
	        y += 35;
	    }
	    
	    // Footer
	    g2.setFont(g2.getFont().deriveFont(Font.ITALIC, 16F));
	    g2.setColor(Color.gray);
	    g2.drawString("Press ESC to close", guideX + 50, y + 30);
	}

	
	public void toggleControls() {
	    showControls = !showControls;
	}
	
	public void drawGameOverScreen() {
	    
	    // Draw semi-transparent black overlay
	    g2.setColor(new Color(0, 0, 0, 150));
	    g2.fillRect(0, 0, gp.screenwidth, gp.screenheight);
	    
	    int x;
	    int y;
	    String text;
	    
	    // Main "GAME OVER" text
	    g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110f));
	    
	    text = "Game Over!!";
	    
	    // Shadow
	    g2.setColor(Color.black);
	    x = getXforCentredText(text);
	    y = gp.tilesize * 4;
	    g2.drawString(text, x, y);
	    
	    // Main text
	    g2.setColor(Color.white);
	    g2.drawString(text, x - 4, y - 4);
	    
	    // Check if save exists and display appropriate message
	    g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 30f));
	    if(gp.saveLoad.saveFileExists()) {
	        g2.setColor(Color.yellow);
	        text = "Save file found - Will load from last save";
	    } else {
	        g2.setColor(Color.orange);
	        text = "No save file - Will restart from checkpoint";
	    }
	    x = getXforCentredText(text);
	    y += gp.tilesize;
	    g2.drawString(text, x, y);
	    
	    // RETRY option
	    g2.setFont(g2.getFont().deriveFont(Font.BOLD, 50f));
	    g2.setColor(Color.white);
	    
	    text = "Retry";
	    x = getXforCentredText(text);
	    y += gp.tilesize * 3;
	    g2.drawString(text, x, y);
	    if(commandNum == 0) {
	        g2.setColor(Color.yellow);
	        g2.drawString(">", x - 40, y);
	    }
	    
	    // QUIT option
	    g2.setColor(Color.white);
	    text = "Quit";
	    x = getXforCentredText(text);
	    y += 55;
	    g2.drawString(text, x, y);
	    if(commandNum == 1) {
	        g2.setColor(Color.yellow);
	        g2.drawString(">", x - 40, y);
	    }
	    
	    // Instructions at bottom
	    g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20f));
	    g2.setColor(Color.gray);
	    text = "Use W/S to select, ENTER to confirm";
	    x = getXforCentredText(text);
	    y = gp.screenheight - gp.tilesize;
	    g2.drawString(text, x, y);
	}
	
	
	
	
	public void drawPausedScreen() {
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F)); 
		String text = "PAUSED";
		int x = getXforCentredText(text);
		int y = gp.screenheight / 2;
		
		g2.drawString(text, x, y);
		
		// ADD INSTRUCTIONS
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 30F));
		text = "Press P to Resume";
		x = getXforCentredText(text);
		y += gp.tilesize;
		g2.drawString(text, x, y);
		
		text = "Press F5 to Save";
		x = getXforCentredText(text);
		y += gp.tilesize;
		g2.drawString(text, x, y);
		
			
		text = "Press ESC for Menu";
		x = getXforCentredText(text);
		y += gp.tilesize;
		g2.drawString(text, x, y);
		
		if(gp.saveLoad.saveFileExists()) {
			g2.setColor(Color.red);
			text = "Press DELETE to Delete Save";
			x = getXforCentredText(text);
			y += gp.tilesize;
			g2.drawString(text, x, y);
		}
	}
	
	// Life display
	public void drawPlayerLife() {
		// Max life
		int x = gp.screenwidth - gp.tilesize - 110;
		int y = gp.tilesize / 2;
		int i = 0;
		
		// Draw blank hearts
		while (i < gp.player.maxLife / 2) {
			g2.drawImage(heartblank, x, y, null);
			i++;
			x += gp.tilesize;
		}
		
		// Present life - reset position
		x = gp.screenwidth - gp.tilesize - 110;
		y = gp.tilesize / 2;
		i = 0;
		
		// Draw filled hearts
		while(i < gp.player.life) {
			g2.drawImage(hearthalf, x, y, null);
			i++;
			if(i < gp.player.life) {
				g2.drawImage(heartfull, x, y, null);
			}
			i++;
			x += gp.tilesize;
		}
	}
	
	public void drawTitleScreen() {
		g2.setColor(new Color(80, 130, 200));
		g2.fillRect(0, 0, gp.screenwidth, gp.screenheight);
		
		// Title name
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 55F));
		String text = "2DMines";
		int x = getXforCentredText(text);
		int y = gp.tilesize * 3;
		
		// Shadow
		g2.setColor(Color.black);
		g2.drawString(text, x+5, y+5);
		
		// Main Color
		g2.setColor(Color.white);
		g2.drawString(text, x, y);  
		
		// Character image
		x = gp.screenwidth/2 - (gp.tilesize*2)/2;
		y += gp.tilesize*2;
		g2.drawImage(gp.player.down1, x, y, gp.tilesize*2, gp.tilesize*2, null);
	   
		// Menu
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 35F));
		
		// NEW GAME
		text = "New Game";
		x = getXforCentredText(text);
		y += gp.tilesize*3.5;
		g2.drawString(text, x, y);
		if(commandNum == 0) {
			g2.drawString(">", x - gp.tilesize, y);
		}
		
		// LOAD GAME
		text = "Load Game";
		x = getXforCentredText(text);
		y += gp.tilesize;
		
		if(!gp.saveLoad.saveFileExists()) {
			g2.setColor(Color.GRAY);
		}
		
		g2.drawString(text, x, y);
		if(commandNum == 1) {
			if(gp.saveLoad.saveFileExists()) {
				g2.setColor(Color.white);
				g2.drawString(">", x - gp.tilesize, y);
			} else {
				g2.setColor(Color.GRAY);
				g2.drawString("X", x - gp.tilesize, y);
			}
		}
		
		// DELETE SAVE
		g2.setColor(Color.white);
		text = "Delete Save";
		x = getXforCentredText(text);
		y += gp.tilesize;
		
		if(!gp.saveLoad.saveFileExists()) {
			g2.setColor(Color.GRAY);
		}
		
		g2.drawString(text, x, y);
		if(commandNum == 2) {
			if(gp.saveLoad.saveFileExists()) {
				g2.setColor(Color.red);
				g2.drawString(">", x - gp.tilesize, y);
			} else {
				g2.setColor(Color.GRAY);
				g2.drawString("X", x - gp.tilesize, y);
			}
		}
		
		// QUIT
		g2.setColor(Color.white);
		text = "Quit";
		x = getXforCentredText(text);
		y += gp.tilesize;
		g2.drawString(text, x, y);
		if(commandNum == 3) {
			g2.drawString(">", x - gp.tilesize, y);
		}
		
		// Instruction at bottom
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20F));
		g2.setColor(Color.yellow);
		text = "Press ESC To See Controls.";
		x = gp.screenwidth - gp.tilesize - 250;
		y = gp.screenheight - gp.tilesize-2;;
		g2.drawString(text, x, y);
		
		// Instruction of old man at bottom
				g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20F));
				g2.setColor(Color.yellow);
				text = "Keep Asking Old Man For Guide.";
				x = gp.screenwidth - gp.tilesize - 251;
				y = gp.screenheight - gp.tilesize + 20;;
				g2.drawString(text, x, y);
	}
	
	public void drawDialougeScreen() {
		int x = gp.tilesize * 1;
		int y = gp.tilesize * 9;
		int width = gp.screenwidth - (gp.tilesize*2);
		int height = gp.tilesize*2;
		
		drawSubWindow(x, y, width, height);
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 15));
		x += gp.tilesize;
		y += gp.tilesize;
		
		// Line break so that the text stays in the same box
		for(String line : currentDialouge.split("\n")) {
			g2.drawString(line, x, y);
			y += 20;
		}
	}
	
	public void drawSubWindow(int x, int y, int width, int height) {
		Color c = new Color(0, 0, 0, 200);
		g2.setColor(c);
		g2.fillRoundRect(x, y, width, height, 35, 35);
		
		c = new Color(255, 255, 255);
		g2.setColor(c);
		g2.setStroke(new BasicStroke(4));
		g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
	}
	
	public int getXforCentredText(String text) {
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenwidth / 2 - length/2;
		return x;
	}
}