package main;

import entity.NPC_OldMan;
import monster.MON_Main;
import monster.MON_mini;
import object.OBJ_Boots;
import object.OBJ_CaveRock;
import object.OBJ_Chest;
import object.OBJ_Fireball;
import object.OBJ_Gate;
import object.OBJ_Hammer;
import object.OBJ_HealProtion;
import object.OBJ_Key;
import object.OBJ_Pit;
import object.OBJ_Steps;
import object.OBJ_Strength;
import object.OBJ_Sword;
import object.OBJ_Torch;

public class AssetSetter {

	
	Gamepannel gp;   //we are using game panel in this class so we are importing kinda.
	
	public AssetSetter(Gamepannel gp) {  //constructor
		this.gp = gp;
		
	}
	
	
	public void setObject() {    // here creating default objects and place them in map
		
		int mapNum = 0;
		
		
		gp.obj[mapNum][0] = new OBJ_Key(gp);    ///OBJ_Key is a subclass of the SuperObject class so we can instanciate it as one of the array.
		gp.obj[mapNum][0].worldx = 9 * gp.tilesize;
		gp.obj[mapNum][0].worldy = 62 * gp.tilesize;
		
		gp.obj[mapNum][6] = new OBJ_Key(gp);    ///OBJ_Key is a subclass of the SuperObject class so we can instanciate it as one of the array.
		gp.obj[mapNum][6].worldx = 44 * gp.tilesize;
		gp.obj[mapNum][6].worldy = 16 * gp.tilesize;
		
		gp.obj[mapNum][4] = new OBJ_Hammer(gp);    ///OBJ_Key is a subclass of the SuperObject class so we can instanciate it as one of the array.
		gp.obj[mapNum][4].worldx = 55 * gp.tilesize;
		gp.obj[mapNum][4].worldy = 16 * gp.tilesize;
		
		gp.obj[mapNum][1] = new OBJ_Chest(gp);    ///OBJ_Key is a subclass of the SuperObject class so we can instanciate it as one of the array.
		gp.obj[mapNum][1].worldx = 10 * gp.tilesize;
		gp.obj[mapNum][1].worldy = 62 * gp.tilesize;
		
		gp.obj[mapNum][2] = new OBJ_Chest(gp);    ///OBJ_Key is a subclass of the SuperObject class so we can instanciate it as one of the array.
		gp.obj[mapNum][2].worldx = 43 * gp.tilesize;
		gp.obj[mapNum][2].worldy = 16 * gp.tilesize;
	
		gp.obj[mapNum][3] = new OBJ_Boots(gp);    ///OBJ_Key is a subclass of the SuperObject class so we can instanciate it as one of the array.
		gp.obj[mapNum][3].worldx = 55* gp.tilesize;
		gp.obj[mapNum][3].worldy = 65 * gp.tilesize;
		
		gp.obj[mapNum][7] = new OBJ_Hammer(gp);    ///OBJ_Key is a subclass of the SuperObject class so we can instanciate it as one of the array.
		gp.obj[mapNum][7].worldx = 61 * gp.tilesize;
		gp.obj[mapNum][7].worldy = 53 * gp.tilesize;
	
		gp.obj[mapNum][5] = new OBJ_Gate(gp);    ///OBJ_Key is a subclass of the SuperObject class so we can instanciate it as one of the array.
		gp.obj[mapNum][5].worldx = 24 * gp.tilesize;
		gp.obj[mapNum][5].worldy = 17 * gp.tilesize;
		
		gp.obj[mapNum][8] = new OBJ_Gate(gp);    ///OBJ_Key is a subclass of the SuperObject class so we can instanciate it as one of the array.
		gp.obj[mapNum][8].worldx = 30 * gp.tilesize;
		gp.obj[mapNum][8].worldy = 15 * gp.tilesize;
		
		gp.obj[mapNum][9] = new OBJ_Steps(gp);    ///OBJ_Key is a subclass of the SuperObject class so we can instanciate it as one of the array.
		gp.obj[mapNum][9].worldx = 35 * gp.tilesize;
		gp.obj[mapNum][9].worldy = 13 * gp.tilesize;
		
		gp.obj[mapNum][10] = new OBJ_Steps(gp);    ///OBJ_Key is a subclass of the SuperObject class so we can instanciate it as one of the array.
		gp.obj[mapNum][10].worldx = 36 * gp.tilesize;
		gp.obj[mapNum][10].worldy = 13 * gp.tilesize;
	

		
		
		// for map 2
	     mapNum = 1;
	     
	     
			gp.obj[mapNum][11] = new OBJ_Pit(gp);
			gp.obj[mapNum][11].worldx = 58 * gp.tilesize;
			gp.obj[mapNum][11].worldy = 62 * gp.tilesize;

			gp.obj[mapNum][14] = new OBJ_Pit(gp);
			gp.obj[mapNum][14].worldx = 59 * gp.tilesize;
			gp.obj[mapNum][14].worldy = 62 * gp.tilesize;
			
			gp.obj[mapNum][12] = new OBJ_HealProtion(gp);
			gp.obj[mapNum][12].worldx = 53 * gp.tilesize;
			gp.obj[mapNum][12].worldy = 56 * gp.tilesize;
			
			gp.obj[mapNum][15] = new OBJ_Torch(gp);    ///OBJ_Key is a subclass of the SuperObject class so we can instanciate it as one of the array.
			gp.obj[mapNum][15].worldx = 14* gp.tilesize;
			gp.obj[mapNum][15].worldy = 69 * gp.tilesize;
	     
			
			gp.obj[mapNum][16] = new OBJ_Torch(gp);    ///OBJ_Key is a subclass of the SuperObject class so we can instanciate it as one of the array.
			gp.obj[mapNum][16].worldx = 56* gp.tilesize;
			gp.obj[mapNum][16].worldy = 67 * gp.tilesize;
	     
			
			gp.obj[mapNum][17] = new OBJ_Torch(gp);    ///OBJ_Key is a subclass of the SuperObject class so we can instanciate it as one of the array.
			gp.obj[mapNum][17].worldx = 7* gp.tilesize;
			gp.obj[mapNum][17].worldy = 4 * gp.tilesize;
	     
			gp.obj[mapNum][13] = new OBJ_Strength(gp);
			gp.obj[mapNum][13].worldx = 70 * gp.tilesize;
			gp.obj[mapNum][13].worldy = 55 * gp.tilesize;
		     
			gp.obj[mapNum][18] = new OBJ_CaveRock(gp);
			gp.obj[mapNum][18].worldx = 24 * gp.tilesize;
			gp.obj[mapNum][18].worldy = 55 * gp.tilesize;
				
			     
			gp.obj[mapNum][19] = new OBJ_CaveRock(gp);
			gp.obj[mapNum][19].worldx = 23 * gp.tilesize;
			gp.obj[mapNum][19].worldy = 55 * gp.tilesize;
			     
			gp.obj[mapNum][20] = new OBJ_CaveRock(gp);
			gp.obj[mapNum][20].worldx = 34 * gp.tilesize;
			gp.obj[mapNum][20].worldy = 27 * gp.tilesize;
			    
			gp.obj[mapNum][21] = new OBJ_CaveRock(gp);
			gp.obj[mapNum][21].worldx = 35 * gp.tilesize;
			gp.obj[mapNum][21].worldy = 27 * gp.tilesize;
			     
			gp.obj[mapNum][22] = new OBJ_CaveRock(gp);
			gp.obj[mapNum][22].worldx = 36 * gp.tilesize;
			gp.obj[mapNum][22].worldy = 27 * gp.tilesize;
			
			//exit cave
			
			gp.obj[mapNum][23] = new OBJ_CaveRock(gp);
			gp.obj[mapNum][23].worldx = 32 * gp.tilesize;
			gp.obj[mapNum][23].worldy = 8 * gp.tilesize;
			
			gp.obj[mapNum][24] = new OBJ_CaveRock(gp);
			gp.obj[mapNum][24].worldx = 34 * gp.tilesize;
			gp.obj[mapNum][24].worldy = 8 * gp.tilesize;
			
			gp.obj[mapNum][25] = new OBJ_CaveRock(gp);
			gp.obj[mapNum][25].worldx = 35 * gp.tilesize;
			gp.obj[mapNum][25].worldy = 8 * gp.tilesize;
			
			gp.obj[mapNum][26] = new OBJ_CaveRock(gp);
			gp.obj[mapNum][26].worldx = 36 * gp.tilesize;
			gp.obj[mapNum][26].worldy = 8 * gp.tilesize;
			
			gp.obj[mapNum][27] = new OBJ_CaveRock(gp);
			gp.obj[mapNum][27].worldx = 37 * gp.tilesize;
			gp.obj[mapNum][27].worldy = 8 * gp.tilesize;
			
			gp.obj[mapNum][28] = new OBJ_CaveRock(gp);
			gp.obj[mapNum][28].worldx = 33 * gp.tilesize;
			gp.obj[mapNum][28].worldy = 9 * gp.tilesize;
			
			gp.obj[mapNum][29] = new OBJ_CaveRock(gp);
			gp.obj[mapNum][29].worldx = 34 * gp.tilesize;
			gp.obj[mapNum][29].worldy = 9 * gp.tilesize;
			
			gp.obj[mapNum][30] = new OBJ_CaveRock(gp);
			gp.obj[mapNum][30].worldx = 37 * gp.tilesize;
			gp.obj[mapNum][30].worldy = 9 * gp.tilesize;
			
			gp.obj[mapNum][31] = new OBJ_CaveRock(gp);
			gp.obj[mapNum][31].worldx = 31 * gp.tilesize;
			gp.obj[mapNum][31].worldy = 10 * gp.tilesize;
			
			gp.obj[mapNum][32] = new OBJ_CaveRock(gp);
			gp.obj[mapNum][32].worldx = 35 * gp.tilesize;
			gp.obj[mapNum][32].worldy = 10 * gp.tilesize;
			
			gp.obj[mapNum][33] = new OBJ_CaveRock(gp);
			gp.obj[mapNum][33].worldx = 36 * gp.tilesize;
			gp.obj[mapNum][33].worldy = 10 * gp.tilesize;
			
			gp.obj[mapNum][34] = new OBJ_CaveRock(gp);
			gp.obj[mapNum][34].worldx = 37 * gp.tilesize;
			gp.obj[mapNum][34].worldy = 10 * gp.tilesize;
			
			gp.obj[mapNum][35] = new OBJ_CaveRock(gp);
			gp.obj[mapNum][35].worldx = 33 * gp.tilesize;
			gp.obj[mapNum][35].worldy = 11 * gp.tilesize;
			
			gp.obj[mapNum][36] = new OBJ_CaveRock(gp);
			gp.obj[mapNum][36].worldx = 37 * gp.tilesize;
			gp.obj[mapNum][36].worldy = 11 * gp.tilesize;
			
			gp.obj[mapNum][37] = new OBJ_CaveRock(gp);
			gp.obj[mapNum][37].worldx = 33 * gp.tilesize;
			gp.obj[mapNum][37].worldy = 12 * gp.tilesize;
			
			gp.obj[mapNum][38] = new OBJ_CaveRock(gp);
			gp.obj[mapNum][38].worldx = 34 * gp.tilesize;
			gp.obj[mapNum][38].worldy = 12 * gp.tilesize;
			
			gp.obj[mapNum][39] = new OBJ_CaveRock(gp);
			gp.obj[mapNum][39].worldx = 35 * gp.tilesize;
			gp.obj[mapNum][39].worldy = 12 * gp.tilesize;
			
			gp.obj[mapNum][40] = new OBJ_CaveRock(gp);
			gp.obj[mapNum][40].worldx = 36 * gp.tilesize;
			gp.obj[mapNum][40].worldy = 12 * gp.tilesize;
			
			gp.obj[mapNum][41] = new OBJ_CaveRock(gp);
			gp.obj[mapNum][41].worldx = 37 * gp.tilesize;
			gp.obj[mapNum][41].worldy = 12 * gp.tilesize;
			
			gp.obj[mapNum][42] = new OBJ_CaveRock(gp);
			gp.obj[mapNum][42].worldx = 38 * gp.tilesize;
			gp.obj[mapNum][42].worldy = 12 * gp.tilesize;
			
			gp.obj[mapNum][43] = new OBJ_CaveRock(gp);
			gp.obj[mapNum][43].worldx = 32 * gp.tilesize;
			gp.obj[mapNum][43].worldy = 12 * gp.tilesize;
			
			gp.obj[mapNum][44] = new OBJ_CaveRock(gp);
			gp.obj[mapNum][44].worldx = 32 * gp.tilesize;
			gp.obj[mapNum][44].worldy = 11 * gp.tilesize;
			
			gp.obj[mapNum][45] = new OBJ_Sword(gp);
			gp.obj[mapNum][45].worldx = 64 * gp.tilesize;
			gp.obj[mapNum][45].worldy = 5 * gp.tilesize;
			
			
			mapNum = 2;
			
			gp.obj[mapNum][46] = new OBJ_HealProtion(gp);
			gp.obj[mapNum][46].worldx = 59 * gp.tilesize;
			gp.obj[mapNum][46].worldy = 59 * gp.tilesize;
			
			gp.obj[mapNum][51] = new OBJ_HealProtion(gp);
			gp.obj[mapNum][51].worldx = 50 * gp.tilesize;
			gp.obj[mapNum][51].worldy = 59 * gp.tilesize;

			gp.obj[mapNum][47] = new OBJ_HealProtion(gp);
			gp.obj[mapNum][47].worldx = 33 * gp.tilesize;
			gp.obj[mapNum][47].worldy = 22 * gp.tilesize;
			
			gp.obj[mapNum][48] = new OBJ_HealProtion(gp);
			gp.obj[mapNum][48].worldx = 50 * gp.tilesize;
			gp.obj[mapNum][48].worldy = 45 * gp.tilesize;
			
			gp.obj[mapNum][49] = new OBJ_HealProtion(gp);
			gp.obj[mapNum][49].worldx = 25 * gp.tilesize;
			gp.obj[mapNum][49].worldy = 22 * gp.tilesize;
			
			gp.obj[mapNum][50] = new OBJ_Fireball(gp);
			gp.obj[mapNum][50].worldx = 52 * gp.tilesize;
			gp.obj[mapNum][50].worldy = 35 * gp.tilesize;
	}
	
	
	public void setNPC() {
		
		int mapNum = 0;
		
		gp.npc[mapNum][0] = new NPC_OldMan(gp);
		gp.npc[mapNum][0].worldx = gp.tilesize * 59;
		gp.npc[mapNum][0].worldy = gp.tilesize * 63;
		
		
		
		
		
		// for map 2
	     mapNum = 1;
	     
			gp.npc[mapNum][1] = new NPC_OldMan(gp);
			gp.npc[mapNum][1].worldx = gp.tilesize * 54;
			gp.npc[mapNum][1].worldy = gp.tilesize * 66;
			
			// for map 3
		     mapNum = 2;
		     
				gp.npc[mapNum][2] = new NPC_OldMan(gp);
				gp.npc[mapNum][2].worldx = gp.tilesize * 27;
				gp.npc[mapNum][2].worldy = gp.tilesize * 22;
				
		     
	     
	     
		
	}
	
	
	public void setMonster() {
	    int mapNum = 2; // Map 2 (cave)
	    
	    // Spawn 3 monsters at different locations
	    gp.monster[mapNum][0] = new MON_mini(gp);
	    gp.monster[mapNum][0].worldx = gp.tilesize * 16;
	    gp.monster[mapNum][0].worldy = gp.tilesize * 50;
	    
	    gp.monster[mapNum][1] = new MON_mini(gp);
	    gp.monster[mapNum][1].worldx = gp.tilesize * 55;
	    gp.monster[mapNum][1].worldy = gp.tilesize * 22;
	    
	    gp.monster[mapNum][2] = new MON_mini(gp);
	    gp.monster[mapNum][2].worldx = gp.tilesize * 34;
	    gp.monster[mapNum][2].worldy = gp.tilesize * 39;
	    
	    mapNum = 3;
	    
	    gp.monster[mapNum][0] = new MON_Main(gp);
	    gp.monster[mapNum][0].worldx = gp.tilesize * 30;
	    gp.monster[mapNum][0].worldy = gp.tilesize * 25;
	    
	}


}
