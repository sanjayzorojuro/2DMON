package entity;

import java.util.Random;

import main.Gamepannel;


public class NPC_OldMan extends Entity {
	
	public NPC_OldMan(Gamepannel gp) {
		
		super(gp);
		
		direction="down";
		speed = 1;
		
		getImage();
		setDialouge();
	}
	
	
	
	public void getImage(){
		
			up1 = setup("/npc/back1");
			up2 = setup("/npc/back2");
			down1= setup("/npc/front1");
			down2 = setup("/npc/front1");
			left1 = setup("/npc/leftstanding");
			left2 = setup("/npc/leftmoving");
			right1 = setup("/npc/rightstanding");
			right2 = setup("/npc/rightmoving");
		
	}
	
	public void setDialouge() {
		
		for(int i = 0; i < dialouges.length; i++) {
			dialouges[i] = null;
		}
		
		if(gp.currentMap == 0) {
			
			dialouges[0]="You have just landed on the island!! Navigate your way through obstacles.  ";
			dialouges[1]=" Find the hammers and axes to destroy obstacles and clear your path towards the \n cave!";
			dialouges[2]="You will come across some challenges along the way....";
		}
		else if(gp.currentMap == 1) {
			
			dialouges[0] = "Welcome to the cave!! I know its pretty dark in here..so find the torches. ";
			dialouges[1] = "There are some unique rocks. You will need the Strength Potion to move them.";
			dialouges[2] = "Explore the whole area. Watch out for a special weapon before exiting the cave..!";
		}
		else if(gp.currentMap == 2) {
			
			dialouges[0] = "You defeated the minions and reached the final stage!";
			dialouges[1] = "Go ahead, enter the Chamber of Burning Ash and defeat the final boss!!";
			
			
		}
		
		
	}
	
	
	public void setAction() {  //setting character behavior
		
		actionLockCounter ++;
		
		
		//120 frames is 2 sec
		if(actionLockCounter == 120) {   //this is because the NPC changes the tiles 60 times in a sec so to slow this down we check the counter.
			
			
			Random random = new Random();
			int i = random.nextInt(100)+1;  // by adding one it will start from 1 to 100 instead of 0 to 99. 
			
			
			if(i <= 25) {
				direction= "up";
			}	
			if(i > 25 &&  i <= 50) {
				direction ="down";
			}
			if(i>50 && i <= 75) {
				direction = "left";
				
			}
			if(i > 75 && i<= 100) {
				direction = "right";
				
			}
			
			actionLockCounter = 0;
			
		}
		

	}
	
	
	public void speak() {

		setDialouge();
		//there is a same method in the super class which is Entity class  where we can the same speak method.
		
		if(dialouges[dialougeIndex] == null) {
			dialougeIndex = 0;
		}
		
		super.speak();
	}


}
