package main;

import java.io.Serializable;

public class DataStorage implements Serializable {

    // Player data
    int level;
    int playerWorldX;
    int playerWorldY;
    int playerLife;
    int playerMaxLife;
    int playerSpeed;
    int hasKey;
    int hasHammer;
    int hasTorch;
    boolean hasStrength;
    boolean hasSword;        // ADDED
    boolean hasFireball;     // ADDED - This was missing!
    String playerDirection;
    long totalPlayTime;

    // Object data
    String objectNames[][];
    int objectWorldX[][];
    int objectWorldY[][];
}