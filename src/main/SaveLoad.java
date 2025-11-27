package main;

import java.io.*;
import object.*;

public class SaveLoad {

    Gamepannel gp;

    public SaveLoad(Gamepannel gp) {
        this.gp = gp;
    }

    public void save() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("save.dat"));

            DataStorage ds = new DataStorage();

            // Save player data
            ds.level = gp.currentMap;
            ds.playerWorldX = gp.player.worldx;
            ds.playerWorldY = gp.player.worldy;
            ds.playerLife = gp.player.life;
            ds.playerMaxLife = gp.player.maxLife;
            ds.playerSpeed = gp.player.speed;
            ds.hasKey = gp.player.haskey;
            ds.hasHammer = gp.player.hashammer;
            ds.hasTorch = gp.player.hastorch;
            ds.hasStrength = gp.player.hasStrength;
            ds.hasSword = gp.player.hasSword;           // ADDED
            ds.hasFireball = gp.player.hasFireball;     // ADDED - This was missing!
            ds.playerDirection = gp.player.direction;
            ds.totalPlayTime = gp.totalPlayTime;

            // Save object data (including positions and existence)
            ds.objectWorldX = new int[gp.maxMap][gp.obj[0].length];
            ds.objectWorldY = new int[gp.maxMap][gp.obj[0].length];
            ds.objectNames = new String[gp.maxMap][gp.obj[0].length];

            for(int mapNum = 0; mapNum < gp.maxMap; mapNum++) {
                for(int i = 0; i < gp.obj[0].length; i++) {
                    if(gp.obj[mapNum][i] != null) {
                        ds.objectWorldX[mapNum][i] = gp.obj[mapNum][i].worldx;
                        ds.objectWorldY[mapNum][i] = gp.obj[mapNum][i].worldy;
                        ds.objectNames[mapNum][i] = gp.obj[mapNum][i].name;
                    } else {
                        ds.objectNames[mapNum][i] = "NULL";
                    }
                }
            }

            oos.writeObject(ds);
            oos.close();

            System.out.println("Game Saved!");

        } catch(Exception e) {
            System.out.println("Save Exception!");
            e.printStackTrace();
        }
    }

    public void load() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("save.dat"));

            DataStorage ds = (DataStorage)ois.readObject();

            // Restore player data
            gp.currentMap = ds.level;
            gp.player.worldx = ds.playerWorldX;
            gp.player.worldy = ds.playerWorldY;
            gp.player.life = ds.playerLife;
            gp.player.maxLife = ds.playerMaxLife;
            gp.player.speed = ds.playerSpeed;
            gp.player.haskey = ds.hasKey;
            gp.player.hashammer = ds.hasHammer;
            gp.player.hastorch = ds.hasTorch;
            gp.player.hasStrength = ds.hasStrength;
            gp.player.hasSword = ds.hasSword;           // ADDED
            gp.player.hasFireball = ds.hasFireball;     // ADDED - This was missing!
            gp.player.direction = ds.playerDirection;
            gp.totalPlayTime = ds.totalPlayTime;
            gp.gameStartTime = System.currentTimeMillis() - gp.totalPlayTime;
            gp.timerRunning = true;

            // CRITICAL FIX: Restore objects to their saved state
            for(int mapNum = 0; mapNum < gp.maxMap; mapNum++) {
                for(int i = 0; i < gp.obj[0].length; i++) {
                    
                    String objName = ds.objectNames[mapNum][i];
                    
                    if(objName.equals("NULL")) {
                        // Object was collected/removed - keep it null
                        gp.obj[mapNum][i] = null;
                    } else {
                        // Object exists - recreate it with saved position
                        gp.obj[mapNum][i] = createObject(objName, gp);
                        
                        if(gp.obj[mapNum][i] != null) {
                            gp.obj[mapNum][i].worldx = ds.objectWorldX[mapNum][i];
                            gp.obj[mapNum][i].worldy = ds.objectWorldY[mapNum][i];
                        }
                    }
                }
            }

            // Update lighting if in cave
            if(gp.currentMap == 1) {
                gp.eManager.updateLightning();
            }

            ois.close();

            System.out.println("Game Loaded!");

        } catch(Exception e) {
            System.out.println("Load Exception!");
            e.printStackTrace();
        }
    }

    // Helper method to recreate objects based on their name
    private SuperObject createObject(String name, Gamepannel gp) {
        switch(name) {
            case "Key":
                return new OBJ_Key(gp);
            case "Chest":
                return new OBJ_Chest(gp);
            case "Boots":
                return new OBJ_Boots(gp);
            case "Hammer":
                return new OBJ_Hammer(gp);
            case "Gate":
                return new OBJ_Gate(gp);
            case "Steps":
                return new OBJ_Steps(gp);
            case "Pit":
                return new OBJ_Pit(gp);
            case "Heal":
                return new OBJ_HealProtion(gp);
            case "Torch":
                return new OBJ_Torch(gp);
            case "Strength":
                return new OBJ_Strength(gp);
            case "CaveRock":
                return new OBJ_CaveRock(gp);
            case "Sword":
                return new OBJ_Sword(gp);
            case "Fireball":                            // ADDED - This was missing!
                return new OBJ_Fireball(gp);
            case "Final":
                return null;
            default:
                System.out.println("Unknown object: " + name);
                return null;
        }
    }

    public boolean saveFileExists() {
        File saveFile = new File("save.dat");
        return saveFile.exists();
    }

    public void deleteSave() {
        File saveFile = new File("save.dat");
        if(saveFile.exists()) {
            if(saveFile.delete()) {
                System.out.println("Save file deleted successfully!");
            } else {
                System.out.println("Failed to delete save file!");
            }
        } else {
            System.out.println("No save file to delete!");
        }
    }
}