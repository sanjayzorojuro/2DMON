package monster;

import java.util.Random;
import entity.Entity;
import main.Gamepannel;

public class MON_Main extends Entity {

    Random random = new Random();
    private int attackRange = 1; // Tiles - can attack from 1 tile away
    private int detectionRange = 15; // Tiles - when boss "wakes up"
    private boolean isAwake = false; // Boss starts inactive
    private int attackTimer = 0;
    private final int ATTACK_DELAY = 60; // Frames between attacks (1.5 seconds)

    public MON_Main(Gamepannel gp) {
        super(gp);

        direction = "down";
        speed = 3;  
        maxLife = 15;  
        life = maxLife;

        // Bigger collision box
        solidarea.x = 4;
        solidarea.y = 12;
        solidarea.width = 40;
        solidarea.height = 40;
        solidareadefaultx = solidarea.x;
        solidareadefaulty = solidarea.y;

        getImage();
    }

    public void getImage() {
        up1 = setup("/monster/mainback1");
        up2 = setup("/monster/mainback2");
        down1 = setup("/monster/mainfront1");
        down2 = setup("/monster/mainfront2");
        left1 = setup("/monster/mainleft1");
        left2 = setup("/monster/mainleft2");
        right1 = setup("/monster/mainright1");
        right2 = setup("/monster/mainright2");

        attackUp = setup("/monster/mcattackfront");
        attackDown = setup("/monster/mcattackfront");
        attackLeft = setup("/monster/mcattackleft");
        attackRight = setup("/monster/mcattackright");
    }

    @Override
    public void setAction() {
        if (gp == null || gp.player == null) return;
        if (life <= 0) return;

        // Calculate distance to player
        int xDistance = Math.abs(worldx - gp.player.worldx);
        int yDistance = Math.abs(worldy - gp.player.worldy);
        int totalDistance = xDistance + yDistance;
        int tileDistance = totalDistance / gp.tilesize;

        // Wake up boss when player enters chamber
        if (!isAwake && tileDistance <= detectionRange) {
            isAwake = true;
            gp.ui.ShowMessage(" FINAL BOSS AWAKENS! ");
        }

        // Only act if awake
        if (!isAwake) {
            return; // Boss stands still
        }

        actionLockCounter++;

        // If within attack range (1 tile), perform attack
        if (tileDistance <= attackRange) {
            // Face the player
            if (Math.abs(worldx - gp.player.worldx) > Math.abs(worldy - gp.player.worldy)) {
                direction = (worldx < gp.player.worldx) ? "right" : "left";
            } else {
                direction = (worldy < gp.player.worldy) ? "down" : "up";
            }
            
            // Try to attack
            tryAttackPlayer();
            
            // Don't move when in attack range
            collisionon = true;
            return;
        }

        // Chase player if beyond attack range but within detection
        if (tileDistance <= detectionRange) {
            // Smooth chase logic - prioritize larger distance axis
            if (Math.abs(worldx - gp.player.worldx) > Math.abs(worldy - gp.player.worldy)) {
                // Move horizontally
                if (worldx < gp.player.worldx) {
                    direction = "right";
                } else if (worldx > gp.player.worldx) {
                    direction = "left";
                }
            } else {
                // Move vertically
                if (worldy < gp.player.worldy) {
                    direction = "down";
                } else if (worldy > gp.player.worldy) {
                    direction = "up";
                }
            }
        } 
        else if (actionLockCounter >= 60) {
            // Random movement when player is far
            int i = random.nextInt(4);
            switch(i) {
                case 0: direction = "up"; break;
                case 1: direction = "down"; break;
                case 2: direction = "left"; break;
                case 3: direction = "right"; break;
            }
            actionLockCounter = 0;
        }
        
    }

    // NEW METHOD: Boss attacks player from 1 tile away
    private void tryAttackPlayer() {
        if (attackTimer > 0) return; // Still on cooldown

        // Calculate player tile position
        int playerCol = gp.player.worldx / gp.tilesize;
        int playerRow = gp.player.worldy / gp.tilesize;
        
        // Calculate boss tile position
        int bossCol = worldx / gp.tilesize;
        int bossRow = worldy / gp.tilesize;

        // Check if player is exactly 1 tile away in the direction boss is facing
        boolean canAttack = false;
        
        switch(direction) {
            case "up":
                canAttack = (bossCol == playerCol && bossRow - 1 == playerRow);
                break;
            case "down":
                canAttack = (bossCol == playerCol && bossRow + 1 == playerRow);
                break;
            case "left":
                canAttack = (bossCol - 1 == playerCol && bossRow == playerRow);
                break;
            case "right":
                canAttack = (bossCol + 1 == playerCol && bossRow == playerRow);
                break;
        }

        // Also check if player is on same tile (collision)
        if (bossCol == playerCol && bossRow == playerRow) {
            canAttack = true;
        }

        if (canAttack) {
            // Deal damage to player
            if (!gp.player.invincible) {
                gp.player.life -= 2; // Boss deals 2 hearts
                gp.player.invincible = true;
                gp.ui.ShowMessage(" BOSS ATTACK! Heavy damage!");
            }
            
            // Start attack animation
            attacking = true;
            attackTimer = ATTACK_DELAY;
        }
    }

    public void update() {
        // Countdown timers
        if (attackTimer > 0) attackTimer--;
        if (attackCooldown > 0) attackCooldown--;
        
        // Attack animation
        if (attacking) {
            actionLockCounter++;
            if (actionLockCounter >= 20) {
                attacking = false;
                actionLockCounter = 0;
            }
        }

        if (life <= 0) {
            // Boss defeated - trigger win condition
            if (gp.currentMap == 3) {
                gp.ui.ShowMessage("FINAL BOSS DEFEATED!");
                gp.ui.gameFinished = true;
                // Don't change game state here - let UI handle it
            }
            return;
        }

        // Normal entity update (movement, collision)
        super.update();

        if (hurtCounter > 0) hurtCounter--;
    }
}