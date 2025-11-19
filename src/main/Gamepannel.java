package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import entity.Player;
import object.SuperObject;
import tile.TileManager;

import java.awt.Graphics2D;


public class Gamepannel extends JPanel implements Runnable{
	
  //SCREEN SETTINGS
	final int originalTilesize = 16;  
	final int scale = 3;
	
	public final int tilesize = originalTilesize * scale;
	public final int maxscreencol = 16 ;
	public final int maxscreenrow = 12 ;
	public final int screenwidth = tilesize * maxscreencol;  
	public final int screenheight = tilesize * maxscreenrow;
	
	//FPS
	int FPS = 60;
	
	//world variables
	public final int maxworldcol = 72;
	public final int maxworldrow = 72;
	public final int worldwidth = tilesize * maxworldcol;
	public final int worldheight = tilesize * maxworldrow;
	
	
	
	TileManager tileM = new TileManager(this);
	keyHandler KeyH =new keyHandler();
	Thread gameThread;
	public Player player = new Player(this,KeyH);
	public Collisionchecker cChecker = new Collisionchecker(this);
	//objects key,chest
	public SuperObject obj[] = new SuperObject[10];   //this obj[] array is used in the AssetSetter class.
	
	//instinciate AssetSetter
	
	public AssetSetter asetter = new AssetSetter(this);
	
	//set player default position
	int playerx = 100;
	int playery = 100;
	int playerspeed = 3;
	
	
	
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
	
	//objects class
	public void setupGame() {
		asetter.setObject();   //calling the setObject method which is in the AssetSetter class.
	   //we want the objects in the game when the game started so we have to call in the main function.

		
		
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
				repaint();
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
		player.update();
		
	}


	//to draw something on the screen
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 =(Graphics2D)g;
		tileM.draw(g2);     //the background tile should  be called before the character tiles
		
		//we need to call the object method which is used for drawing the keys after the tile draw and before player draw so that the player can be on top of the key not the key on top of player.
		for(int i = 0;i < obj.length;i++) {  //the obj is from the above line 43
			//in for loop we are checking if there is any object in the array 
			if(obj[i]!= null) {      //we need to check if the array is null or else we might get error or null pointer.
				obj[i].draw(g2, this);
			}
			
		}
		
		player.draw(g2);    //drawing player
		
		
		g2.dispose();
		
		
	}
	
	
}
