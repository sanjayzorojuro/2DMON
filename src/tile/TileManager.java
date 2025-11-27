package tile;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import main.Gamepannel;
import main.UtilityTool;


public class TileManager {
	Gamepannel gp;
	public Tile[] tile;
	public int maptileno[][][];
	
	
     public TileManager(Gamepannel gp) {   //constructor
	     this.gp = gp;
	
	     tile = new Tile[150];
	     maptileno = new int[gp.maxMap][gp.maxworldcol][gp.maxworldrow];
	     
	     getTileImage();
	     loadmap("/maps/map.txt",0);
	     loadmap("/maps/cavefinalmap.txt",1);
	     loadmap("/maps/lavafinalmap2.txt",2);
	     loadmap("/maps/finalchambermap1.txt",3);
	
     }
     public void getTileImage() {
    		 setup(0,"newgrass",false);
    		 setup(1,"newwater",false);
    		 setup(2,"newsand",false);
    		 setup(3,"tree1",true);
    		 setup(4,"waterright",true);
    		 setup(5,"waterup",true);
    		 setup(6,"waterbottom",true);
    		 setup(7,"leftpatchA",true);
    		 setup(8,"leftpatchC",true);
    		 setup(9,"rightpatchA",true);
    		 setup(10,"waterleft",true);
    		 setup(11,"rightpatchC",true);
    		 setup(12,"tunnel1",true);
    		 setup(13,"tunnel2",true);
    		 setup(14,"tunnel3",true);
    		 setup(15,"tunnel4",true);
    		 setup(16,"tunnel5",true);
    		 setup(17,"tunnel6",true);
    		 setup(18,"tunnel7",true);
    		 setup(19,"tunnel8",true);
    		 setup(20,"tunnel9",true);
    		 setup(21,"tunnel10",true);
    		 setup(22,"tunnel11",false);
    		 setup(23,"tunnel12",true);
    		 //second map
    		 setup(24,"zero",true); 
    		 setup(25,"eleven",true); 
    		 setup(26,"eleven1",true); 
    		 setup(27,"eleven3",true); 
    		 setup(28,"seven3",true); 
    		 setup(29,"seven2",true); 
    		 setup(30,"seven1",true); 
    		 setup(31,"two",true); 
    		 setup(32,"eleven2",true); 
    		 setup(33,"three",true); 
    		 setup(34,"four",false); 
    		 setup(35,"five",false); 
    		 setup(36,"six",false); 
    		 setup(37,"seven",true); 
    		 setup(38,"eight",true); 
    		 setup(39,"nine",true); 
    		 setup(40,"ten",true); 
    		 setup(41,"onethree",false); 
    		 setup(42,"onethree",false);
    		 //third map
    		 setup(43, "a44", false);
    		 setup(44, "a171", false);
    		 setup(45, "a151", true);
    		 setup(46, "a47", false);
    		 setup(47, "a48", false);
    		 setup(48, "a49", false);
    		 setup(49, "a152", true);
    		 setup(50, "a1", false);
    		 setup(51, "a2", false);
    		 setup(52, "a4", false);
    		 setup(53, "a5", false);
    		 setup(54, "a6", false);
    		 setup(55, "a7", false);
    		 setup(56, "a8", false);
    		 setup(57, "a9", false);
    		 setup(58, "a10", false);
    		 setup(59, "a11", false);
    		 setup(60, "a12", false);
    		 setup(61, "a13", false);
    		 setup(62, "a14", false);
    		 setup(63, "a18", false);
    		 setup(64, "a19", false);
    		 setup(65, "a20", false);
    		 setup(66, "a21", false);
    		 setup(67, "a25", false);
    		 setup(68, "a26", false);
    		 setup(69, "a27", false);
    		 setup(70, "a28", false);
    		 setup(71, "a17", false);
    		 setup(72, "a30", false);
    		 setup(73, "a32", true);
    		 setup(74, "a31", false);
    		 setup(75, "a40", false);
    		 setup(76, "a41", false);
    		 setup(77, "a42", false);
    		 //final chamber
    		 setup(78, "b15", false);
    		 setup(79, "b16", false);
    		 setup(80, "b31", false);
    		 setup(81, "b32", false);
    		 setup(82, "b112", true);
    		 setup(83, "b111", true);
    		 setup(84, "b113", true);
    		 setup(85, "b91", false);
    		 setup(86, "b1", false);
    		 setup(87, "b2", false);
    		 setup(88, "b3", false);
    		 setup(89, "b4", false);
    		 setup(90, "b13", false);
    		 setup(91, "b14", false);
    		 setup(92, "b9", false);
    		 setup(93, "b11", true);
    		 setup(94, "b13", false);
    		 setup(95, "b14", false);
    		 setup(96, "b15", false);
    		 setup(97, "b16", false);
    		 
    		 
	
     }

     public void setup(int index , String imagename, boolean collision){
    	 
    	 UtilityTool utool = new UtilityTool();
    	 
    	 try {
    		 
    		 tile[index] = new Tile();
    		 tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/"+ imagename + ".png"));
    		 tile[index].image = utool.scaledImage(tile[index].image,gp.tilesize,gp.tilesize);
    		 tile[index].collision = collision;
    		 
    		 
    		 
    	 }catch(IOException e){
    		 e.printStackTrace();
    	 }
    	 
     }
     
     public void loadmap(String filepath ,int map) {
	
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
				
    				 maptileno[map][col][row]= num;
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
		
		
    		 int tileno = maptileno[gp.currentMap][worldcol][worldrow];
    		 
    		 int worldx = worldcol * gp.tilesize;       // world-x=0 and world-y=0 means the first block of the whole map
    		 int worldy = worldrow * gp.tilesize;      
    		 int screenx = worldx - gp.player.worldx + gp.player.screenx;    //player.world-x means the position of the player in the world.
    		 int screeny = worldy - gp.player.worldy + gp.player.screeny;    //player.screenx=0 and screen-y=0 means the 1st block which is visible in the screen or panel.
    		 
    		 if(worldx + gp.tilesize > gp.player.worldx - gp.player.screenx && 
    			worldx - gp.tilesize < gp.player.worldx + gp.player.screenx &&
    			worldy + gp.tilesize > gp.player.worldy - gp.player.screeny &&
    			worldy - gp.tilesize < gp.player.worldy + gp.player.screeny) {
    			 
    			 g2.drawImage(tile[tileno].image , screenx,screeny ,null);
    		 }
    	
    		
    		 worldcol++;
    		
		
    		 if (worldcol == gp.maxworldcol) {
    			 worldcol = 0;
    			 worldrow ++;
  
			
    		 }
		
    	 }
     }
}
