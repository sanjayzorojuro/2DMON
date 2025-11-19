package main;

import object.OBJ_Boots;
import object.OBJ_Chest;
import object.OBJ_Key;

public class AssetSetter {

	
	Gamepannel gp;   //we are using game panel in this class so we are importing kinda.
	
	public AssetSetter(Gamepannel gp) {  //constructor
		this.gp = gp;
		
	}
	
	
	public void setObject() {    // here creating default objects and place them in map
		
		gp.obj[0] = new OBJ_Key();    ///OBJ_Key is a subclass of the SuperObject class so we can instanciate it as one of the array.
		gp.obj[0].worldx = 9 * gp.tilesize;
		gp.obj[0].worldy = 62 * gp.tilesize;
		
		gp.obj[4] = new OBJ_Key();    ///OBJ_Key is a subclass of the SuperObject class so we can instanciate it as one of the array.
		gp.obj[4].worldx = 55 * gp.tilesize;
		gp.obj[4].worldy = 16 * gp.tilesize;
		
		gp.obj[1] = new OBJ_Chest();    ///OBJ_Key is a subclass of the SuperObject class so we can instanciate it as one of the array.
		gp.obj[1].worldx = 3 * gp.tilesize;
		gp.obj[1].worldy = 38 * gp.tilesize;
		
		gp.obj[2] = new OBJ_Chest();    ///OBJ_Key is a subclass of the SuperObject class so we can instanciate it as one of the array.
		gp.obj[2].worldx = 35 * gp.tilesize;
		gp.obj[2].worldy = 9 * gp.tilesize;
	
		gp.obj[3] = new OBJ_Boots();    ///OBJ_Key is a subclass of the SuperObject class so we can instanciate it as one of the array.
		gp.obj[3].worldx = 3 * gp.tilesize;
		gp.obj[3].worldy = 9 * gp.tilesize;
	}

}
