package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import entity.Entity;
import entity.Player;
import environment.EnvironmentManager;
import object.SuperObject;
import tile.TileManager;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;


public class Gamepannel extends JPanel implements Runnable{
	
  //SCREEN SETTINGS
	final int originalTilesize = 16;  
	final int scale = 3;
	
	public final int tilesize = originalTilesize * scale;
	public final int maxscreencol = 20 ;
	public final int maxscreenrow = 12 ;
	public final int screenwidth = tilesize * maxscreencol;  //960px
	public final int screenheight = tilesize * maxscreenrow; //576px
	
	//FPS
	int FPS = 60;
	
	//world variables
	public final int maxworldcol = 72;
	public final int maxworldrow = 72;
	public final int worldwidth = tilesize * maxworldcol;
	public final int worldheight = tilesize * maxworldrow;
	
	
	//Maps
	public final int maxMap = 10;
	public int currentMap = 0;
	
	

	
	//for full screen
	int screenWidth2 = screenwidth;
	int screenHeight2 = screenheight;
	BufferedImage tempScreen;
	Graphics2D g2;
	
	
	
	//set player default position
	int playerx = 100;
	int playery = 100;
	int playerspeed = 3;
	
	
	//Game State
	public int gameState;
	public final int titleState = 0;
	public final int playState = 1;
	public final int pausedState = 2;
	public final int dialougeState = 3;
	public final int finishState = 4;
	public final int gameOver = 5;
	
	public long gameStartTime = 0;
	public long totalPlayTime = 0; // in milliseconds
	public boolean timerRunning = false;
	public long finalCompletionTime = 0;
	
	//Lighting
	public int currentLight;
	public int lightRadius;
	public final int type_light = 1;
	
	
	
	
	
	
	
	
	//CALLING METHODS INSTANCIATE
	
	//system
	public TileManager tileM = new TileManager(this);
	keyHandler KeyH =new keyHandler(this);
	Thread gameThread;
	
	//Instanced AssetSetter
	public AssetSetter asetter = new AssetSetter(this);
			
	//entity and object
	public Player player = new Player(this,KeyH);
	public Collisionchecker cChecker = new Collisionchecker(this);
	
	//objects key,chest
	public SuperObject obj[][] = new SuperObject[maxMap][100];   //this obj[] array is used in the AssetSetter class.
	
	//Entity same like objects
	public Entity npc[][] = new Entity[maxMap][20];
	
	//calling UI functions
	public UI ui = new UI(this);
	
	//calling Event handler
	public EventHandler eHandler = new EventHandler(this);
	
	
	
	//Environment settings
	
	public EnvironmentManager eManager = new EnvironmentManager(this);
	
	
	//Calling save load
    public SaveLoad saveLoad = new SaveLoad(this); 
	

    
    //calling monster
    public Entity monster[][] = new Entity[maxMap][20];
	
	
	public Gamepannel() {
		this.setPreferredSize(new Dimension(screenwidth , screenheight));
		this.setBackground(Color.BLACK);
		this.setDoubleBuffered(true);    
		this.addKeyListener(KeyH);
		this.setFocusable(true);
		
	}
	
	public void startGameThread() {
		gameThread = new Thread(this); 
		gameThread.start();
		
	}
	
	public void setupGame() {
	    asetter.setObject();
	    asetter.setNPC();
	    eManager.setup();
	    asetter.setMonster();
	    
	    // Start the game timer
	    gameStartTime = System.currentTimeMillis();
	    timerRunning = true;
	    totalPlayTime = 0;
	    
	    gameState = titleState;
	    tempScreen = new BufferedImage(screenwidth,screenheight , BufferedImage.TYPE_INT_ARGB);
	    g2 = (Graphics2D)tempScreen.getGraphics();
	    setFullScreen();
	}
	
	//full screen methid
	public void setFullScreen() {
		
		//Get local screen device
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gd = ge.getDefaultScreenDevice();
		gd.setFullScreenWindow(Main.window);
		
		
		//Get full screen width and height    they return monitors full screen width and height
		screenWidth2 = Main.window.getWidth();		
		screenHeight2 = Main.window.getHeight();
	}
	
	// for implements runnable 
	public void run() {
		
		double drawInterval = 1000000000/FPS;   //nano-seconds  //o.o166sec
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		
		//showing FPS
		long timer = 0;
		long drawCount = 0;
		
		
		while (gameThread != null) {
			currentTime=System.nanoTime();
			delta +=(currentTime - lastTime )/ drawInterval;
			
			//showing FPS
			timer += currentTime -lastTime;
			lastTime = currentTime;
			
			if(delta >= 1) {
				update();
				drawToTempScreen();  //draw everything intp buffered image
				drawToScreen();      //draw buffered image into screen
				delta--;
				drawCount++;	
			}
			
			//Showing FPS 
			if (timer >= 1000000000) {
				System.out.println("FPs"+drawCount);
				drawCount = 0;
				timer=0;
			}
			
			
		}
		
	}
	
	
	public void update() {
	    
	    // Update timer when game is running
	    if(gameState == playState && timerRunning) {
	        totalPlayTime = System.currentTimeMillis() - gameStartTime;
	    }
	    
	    // Rest of your existing update code...
	    if(gameState == playState) {
	        player.update();
	        
	        for(int i = 0 ; i< npc[1].length ;i++) {
	            if(npc[currentMap][i] != null) {
	                npc[currentMap][i].update();
	            }
	        }
	        
	        for(int i = 0; i < monster[1].length; i++) {
	            if(monster[currentMap][i] != null) {
	                if(monster[currentMap][i].life > 0) {
	                    monster[currentMap][i].update();
	                } else {
	                    monster[currentMap][i] = null;
	                }
	            }
	        }
	        
	        if(currentMap == 3) {
	            if(monster[3][0] != null && monster[3][0].life <= 0) {
	                // SAVE THE FINAL TIME before stopping
	                finalCompletionTime = totalPlayTime;
	                timerRunning = false; // STOP TIMER
	                gameState = finishState;
	                ui.gameFinished = true;
	                ui.ShowMessage("🎉 BOSS DEFEATED! YOU WIN! 🎉");
	            }
	        }
	    }
	    
	    if(gameState == pausedState) {
	        // Timer pauses automatically
	    }
	}

	
	public void drawToTempScreen() {
		
		g2.clearRect(0, 0, screenwidth, screenheight);
		

		//Debug
		long drawStart = 0;
		if(KeyH.checkDrawTime == true) {
			drawStart = System.nanoTime();
			
			
			
		}

		//TITLE SCREEN
		if(gameState == titleState) {
			
			
			ui.draw(g2);
			
			
			
			
		}else {
			
			
			
			
			//TILE
			tileM.draw(g2);     //the background tile should  be called before the character tiles
			
			
			//OBJECT
			//we need to call the object method which is used for drawing the keys after the tile draw and before player draw so that the player can be on top of the key not the key on top of player.
			for(int i = 0;i < obj[1].length;i++) {  //the obj is from the above line 43
				//in for loop we are checking if there is any object in the array 
				if(obj[currentMap][i]!= null) {      //we need to check if the array is null or else we might get error or null pointer.
					obj[currentMap][i].draw(g2, this);
				}
				
			}
			
			//Pit
			eHandler.draw(g2);

			
			
			//NPC
			for  (int i = 0; i < npc[1].length ; i++) {
				if(npc[currentMap][i] != null) {
					npc[currentMap][i].draw(g2);
				}
			}
			
			//monster
			for(int i = 0; i < monster[1].length; i++) {
			    if(monster[currentMap][i] != null) {
			        monster[currentMap][i].draw(g2);
			    }
			}
			
			
			//PLAYER
			player.draw(g2);    //drawing player
			
			
			//Environment
			eManager.draw(g2);
			
			
			// drawing ui
			ui.draw(g2);
			
			
			
		}
		

		
		
		//Debug
		//checking how much time have passed.
		if(KeyH.checkDrawTime == true) {
			
			long drawEnd = System.nanoTime();
			long passed = drawEnd - drawStart;
			g2.setColor(Color.white);
			g2.drawString("Draw Time:"+passed, 10, 400);
			System.out.println("DrawTime"+passed);
			
			
			
		}
	}
	
	
	
	
	public void drawToScreen() {
		Graphics g = getGraphics();
		
		g.drawImage(tempScreen, 0, 0, screenWidth2, screenHeight2, null);
		g.dispose();
		
		
	}
}
