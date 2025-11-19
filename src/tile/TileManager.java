package tile;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import main.Gamepannel;


public class TileManager {
	Gamepannel gp;
	public Tile[] tile;
	public int maptileno[][];
	
	
     public TileManager(Gamepannel gp) {   //constructor
	     this.gp = gp;
	
	     tile = new Tile[30];
	     maptileno = new int[gp.maxworldcol][gp.maxworldrow];
	     getTileImage();
	     loadmap("/maps/map.txt");
	
     }
     public void getTileImage() {

    	 try {
		
    		 tile[0]=new Tile();
    		 tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/newgrass.png"));
		
		
    		 tile[1]=new Tile();
    		 tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/newwater.png"));
    		 tile[1].collision = true;
		
    		 tile[2]=new Tile();
    		 tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/newsand.png"));
		

    		 tile[3]=new Tile();
    		 tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/newrock.png"));
    		 tile[3].collision = false;

    		 tile[4]=new Tile();
    		 tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree1.png"));
    		 tile[4].collision = true;

    		 tile[5]=new Tile();
    		 tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree2.png"));
    		 tile[5].collision = true;

    		 tile[6]=new Tile();
    		 tile[6].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wellbottomleft.png"));
    		 tile[6].collision = true; 
    		 
    		 tile[7]=new Tile();
    		 tile[7].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wellbottomright.png"));
    		 tile[7].collision = true;
    		 
    		 tile[8]=new Tile();
    		 tile[8].image = ImageIO.read(getClass().getResourceAsStream("/tiles/welltopleft.png"));
    		 tile[8].collision = true;
    		 
    		 tile[9]=new Tile();
    		 tile[9].image = ImageIO.read(getClass().getResourceAsStream("/tiles/welltopright.png"));
    		 tile[9].collision = true;
    		 
    		 tile[10]=new Tile();
    		 tile[10].image = ImageIO.read(getClass().getResourceAsStream("/tiles/waterbottomleft.png"));
    		 tile[10].collision = true;
    		 
    		 tile[11]=new Tile();
    		 tile[11].image = ImageIO.read(getClass().getResourceAsStream("/tiles/waterbottomright.png"));
    		 tile[11].collision = true;
    		 
    		 tile[12]=new Tile();
    		 tile[12].image = ImageIO.read(getClass().getResourceAsStream("/tiles/watertopright.png"));
    		 tile[12].collision = true;
    		 
    		 tile[13]=new Tile();
    		 tile[13].image = ImageIO.read(getClass().getResourceAsStream("/tiles/watertopleft.png"));
    		 tile[13].collision = true;
    		 
    		 tile[14]=new Tile();
    		 tile[14].image = ImageIO.read(getClass().getResourceAsStream("/tiles/waterleft.png"));
    		 tile[14].collision = true;
    		 
    		 tile[15]=new Tile();
    		 tile[15].image = ImageIO.read(getClass().getResourceAsStream("/tiles/waterright.png"));
    		 tile[15].collision = true;
    		 
    		 tile[16]=new Tile();
    		 tile[16].image = ImageIO.read(getClass().getResourceAsStream("/tiles/waterup.png"));
    		 tile[16].collision = true;
    		 
    		 tile[17]=new Tile();
    		 tile[17].image = ImageIO.read(getClass().getResourceAsStream("/tiles/waterbottom.png"));
    		 tile[17].collision = true;
    		 
    		 tile[18]=new Tile();
    		 tile[18].image = ImageIO.read(getClass().getResourceAsStream("/tiles/leftpatchA.png"));
    		 tile[18].collision = true;
    		 
    		 tile[19]=new Tile();
    		 tile[19].image = ImageIO.read(getClass().getResourceAsStream("/tiles/leftpatchC.png"));
    		 tile[19].collision = true;
    		 
    		 tile[20]=new Tile();
    		 tile[20].image = ImageIO.read(getClass().getResourceAsStream("/tiles/rightpatchA.png"));
    		 tile[20].collision = true;
    		 
    		 tile[21]=new Tile();
    		 tile[21].image = ImageIO.read(getClass().getResourceAsStream("/tiles/rightpatchC.png"));
    		 tile[21].collision = true;
    		 
    		 tile[22]=new Tile();
    		 tile[22].image = ImageIO.read(getClass().getResourceAsStream("/tiles/bridgetopL.png"));
		
    		 tile[23]=new Tile();
    		 tile[23].image = ImageIO.read(getClass().getResourceAsStream("/tiles/bridgetopR.png"));
		
    		 tile[24]=new Tile();
    		 tile[24].image = ImageIO.read(getClass().getResourceAsStream("/tiles/bridgebottomL.png"));
		
    		 tile[25]=new Tile();
    		 tile[25].image = ImageIO.read(getClass().getResourceAsStream("/tiles/bridgebottomR.png"));
		
		
    		 tile[26]=new Tile();
    		 tile[26].image = ImageIO.read(getClass().getResourceAsStream("/tiles/treeup.png"));
    		 tile[26].collision = true;
    		 
    		 tile[27]=new Tile();
    		 tile[27].image = ImageIO.read(getClass().getResourceAsStream("/tiles/treeRoot.png"));
    		 tile[27].collision = true;
    		 
    		 
    		 
    	 }catch(IOException e) {
    		 e.printStackTrace();
		
    	 }
	
     }

     public void loadmap(String filepath) {
	
    	 try {
		
    		 InputStream is  = getClass().getResourceAsStream(filepath);
    		 BufferedReader br = new BufferedReader(new InputStreamReader(is));
		
		
    		 int col = 0;
    		 int row= 0;
		
		
		
    		 // getting the numbers one by one form the txt file
    		 while(col < gp.maxworldcol && row < gp.maxworldrow) {
			
    			 String line = br.readLine();
			
    			 while(col < gp.maxworldcol) {
				
    				 String numbers[] = line.split(" ");
    				 //changing from string to integer in the above and below line. 
    				 int num = Integer.parseInt(numbers[col]);
				
    				 maptileno[col][row]= num;
    				 col++;
				
				
    			 }
    			 if (col == gp.maxworldcol) {
    				 col = 0;
    				 row++;
				
    			 }
			
    		 }
    		 br.close();    //after reading all we close the txt file.
		
    	 }catch(Exception e) {
    		 e.printStackTrace();
    	 }
    	 
     }

     public void draw(Graphics2D g2) {   // drawing map
	
    	 int worldcol = 0;
    	 int worldrow = 0;
    	 
	
    	 while ( worldcol < gp.maxworldcol && worldrow < gp.maxworldrow) {
		
		
    		 int tileno = maptileno[worldcol][worldrow];
    		 
    		 int worldx = worldcol * gp.tilesize;       // world-x=0 and world-y=0 means the first block of the whole map
    		 int worldy = worldrow * gp.tilesize;      
    		 int screenx = worldx - gp.player.worldx + gp.player.screenx;    //player.world-x means the position of the player in the world.
    		 int screeny = worldy - gp.player.worldy + gp.player.screeny;    //player.screenx=0 and screen-y=0 means the 1st block which is visible in the screen or panel.
    		 
    		 if(worldx + gp.tilesize > gp.player.worldx - gp.player.screenx && 
    			worldx - gp.tilesize < gp.player.worldx + gp.player.screenx &&
    			worldy + gp.tilesize > gp.player.worldy - gp.player.screeny &&
    			worldy - gp.tilesize < gp.player.worldy + gp.player.screeny) {
    			 
    			 g2.drawImage(tile[tileno].image , screenx,screeny ,gp.tilesize,gp.tilesize,null);
    		 }
    	
    		
    		 worldcol++;
    		
		
    		 if (worldcol == gp.maxworldcol) {
    			 worldcol = 0;
    			 worldrow ++;
  
			
    		 }
		
    	 }
     }
}
