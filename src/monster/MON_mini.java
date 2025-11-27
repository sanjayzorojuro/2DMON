package monster;

import java.util.Random;
import entity.Entity;
import main.Gamepannel;

public class MON_mini extends Entity {
    
    Random random = new Random();
    private int attackRange = 1; // Same as boss - 1 tile
    private int detectionRange = 15; // Tiles
    private int attackTimer = 0;
    private final int ATTACK_DELAY = 60; // 1 second between attacks
    
    public MON_mini(Gamepannel gp) {
        super(gp);
        direction = "down";
        speed = 1;
        maxLife = 4;
        life = maxLife;
        
        solidarea.x = 8;
        solidarea.y = 16;
        solidarea.width = 32;
        solidarea.height = 32;
        solidareadefaultx = solidarea.x;
        solidareadefaulty = solidarea.y;
        
        getImage();
    }
    
    public void getImage() {
        up1 = setup("/monster/mcback1");
        up2 = setup("/monster/mcback2");
        down1 = setup("/monster/mcfront1");
        down2 = setup("/monster/mcfront2");
        left1 = setup("/monster/mcleftmoving");
        left2 = setup("/monster/mcleftstanding");
        right1 = setup("/monster/mcrightmoving");
        right2 = setup("/monster/mcrightstanding");
        
        attackUp = setup("/monster/miniattackup");
        attackDown = setup("/monster/miniattackdown");
        attackLeft = setup("/monster/miniattackleft");
        attackRight = setup("/monster/miniattackright");
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
        
        actionLockCounter++;
        
        // If within attack range (1 tile), attack player
        if (tileDistance <= attackRange) {
            // Face the player
            if (Math.abs(worldx - gp.player.worldx) > Math.abs(worldy - gp.player.worldy)) {
                direction = (worldx < gp.player.worldx) ? "right" : "left";
            } else {
                direction = (worldy < gp.player.worldy) ? "down" : "up";
            }
            
            // Try to attack
            tryAttackPlayer();
            
            // Don't move when attacking
            collisionon = true;
            return;
        }
        
        // Chase player if within detection range
        if (tileDistance < detectionRange) {
            // Move towards player
            if (Math.abs(worldx - gp.player.worldx) > Math.abs(worldy - gp.player.worldy)) {
                direction = (worldx < gp.player.worldx) ? "right" : "left";
            } else {
                direction = (worldy < gp.player.worldy) ? "down" : "up";
            }
        }
        else if (actionLockCounter >= 120) {
            // Random movement when player is far
            int i = random.nextInt(4);
            switch(i){
                case 0: direction = "up"; break;
                case 1: direction = "down"; break;
                case 2: direction = "left"; break;
                case 3: direction = "right"; break;
            }
            actionLockCounter = 0;
        }
    }
    
    // NEW: Attack logic like the boss
    private void tryAttackPlayer() {
        if (attackTimer > 0) return; // Still on cooldown
        
        // Calculate positions
        int playerCol = gp.player.worldx / gp.tilesize;
        int playerRow = gp.player.worldy / gp.tilesize;
        int monsterCol = worldx / gp.tilesize;
        int monsterRow = worldy / gp.tilesize;
        
        boolean canAttack = false;
        
        // Check if player is 1 tile away in facing direction
        switch(direction) {
            case "up":
                canAttack = (monsterCol == playerCol && monsterRow - 1 == playerRow);
                break;
            case "down":
                canAttack = (monsterCol == playerCol && monsterRow + 1 == playerRow);
                break;
            case "left":
                canAttack = (monsterCol - 1 == playerCol && monsterRow == playerRow);
                break;
            case "right":
                canAttack = (monsterCol + 1 == playerCol && monsterRow == playerRow);
                break;
        }
        
        // Also check if on same tile (collision)
        if (monsterCol == playerCol && monsterRow == playerRow) {
            canAttack = true;
        }
        
        if (canAttack) {
            // Deal damage
            if (!gp.player.invincible) {
                gp.player.life -= 1; // Mini monster deals 1 heart
                gp.player.invincible = true;
                gp.ui.ShowMessage("Monster attacks!");
            }
            
            // Start attack animation
            attacking = true;
            attackTimer = ATTACK_DELAY;
        }
    }
    
    @Override
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
            return;
        }
        
        super.update();
        
        if (hurtCounter > 0) hurtCounter--;
    }
}
