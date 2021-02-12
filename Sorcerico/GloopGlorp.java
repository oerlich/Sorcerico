import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class GloopGlorp here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GloopGlorp extends PhysicsActor
{
    private String defaultAnim;
    
    private int deathTime;
    private int deathTimeLong;
    private int deathCounter;
    
    private int attackCounter;
    private int attackTimer;
    
    private boolean attackPlayer;
    private boolean attacked;
    private boolean dead;
    
    private int maxHealth = 10;
    private int currentHealth;
    
    private GreenfootSound slimeDeathSound;
    
    private Player player;
    
    public GloopGlorp(boolean attPlayer)
    {
        super(true);
        
        attackPlayer = attPlayer;
        attacked = false;
        dead = false;
        
        currentHealth = maxHealth;
        
        registerAnimation("Gloop Idle", "gloopIdle", ".png", 4, true, 8);
        registerAnimation("Gloop Move", "gloopIdle", ".png", 4, false, 5);
        registerAnimation("Gloop Hurt", "gloopHurt", ".png", 4, false, 4);
        registerAnimation("Gloop Die", "gloopDie", ".png", 8, false, 6);
        registerAnimation("Gloop Attack", "gloopAttackRight", ".png", 8, false, 5);
      
        
        defaultAnim = "Gloop Idle";
        
        deathTime = 64;
        deathTimeLong = 128;
        deathCounter = 0;
        
        attackCounter = 0;
        attackTimer = 40;
        
        slimeDeathSound = new GreenfootSound("slimeDeath.mp3");
        slimeDeathSound.setVolume(50); 
    }
    
    /**
     * Act - do whatever the GloopGlorp wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        
        player = this.getWorld().getObjects(Player.class).get(0);
        int playerY = player.getY();
        int myY = getY();

        String currentAnim = getCurrentAnim();
        boolean activeMovement = false;
        
        
        
        if(dead)
        {
            deathCounter++;
        }
        
        if(currentAnim.equals("Gloop Attack"))
        {
            attackCounter++;
        }
        
        if(playerY < myY - 100)
        {
            attackPlayer = false;
            velocity.x = velocity.y = 0;
        }
        else
            attackPlayer = true;
        
        List<Fireball> coll = getObjectsInRange(100, Fireball.class);
        if(!coll.isEmpty())
        {
            this.getWorld().removeObject(coll.get(0));
            currentHealth -= 1;
            
            if(currentHealth <= 0)
            {
                playAnimation("Gloop Die");
                deathCounter = 0;
                dead = true;
            }
            else
            {
                playAnimation("Gloop Hurt");
                attacked = false;
            }
            
            slimeDeathSound.stop();
            slimeDeathSound.play();
        }
        
        if(deathCounter >= deathTime)
        {
            this.setImage("empty.png");
            if(deathCounter >= deathTimeLong)
            {
                ((Level)this.getWorld()).bgMusic.stop();
                Greenfoot.setWorld(new WinScreen());
            }
            return;
        }
        
        if(attackCounter >= attackTimer)
        {
            attackCounter = 0;
            attacked = false;
        }
        
        if(!dead)
        {
            List<Player> playerCollision = getObjectsInRange(130, Player.class);
            if(!playerCollision.isEmpty() && !attacked)
            {
                setLocation(getX(), getY() - 35);
                playAnimation("Gloop Attack");
                attackCounter = 0;
                attacked = true;
            }
            
            if(attackPlayer)
            {
                Vec2 direction = new Vec2(player.getX() - getX(), player.getY() - getY());
                activeMovement = true;
                
                if(direction.x > 0)
                {
                    if(!currentAnim.equals("Gloop Move") && 
                        !currentAnim.equals("Gloop Hurt") &&
                        !currentAnim.equals("Gloop Attack"))
                    {
                        flippedOrientation();
                        velocity.x = 1;
                        playAnimation("Gloop Move");
                    }
                }
                else if(direction.x < 0)
                {
                    if(!currentAnim.equals("Gloop Move") && 
                        !currentAnim.equals("Gloop Hurt") &&
                        !currentAnim.equals("Gloop Attack"))
                    {
                        defaultOrientation();
                        velocity.x = -1;
                        playAnimation("Gloop Move");
                    }
                }
    
                
            }
            
            if(!getIsAnimating() && !activeMovement)
            {
                playAnimation(defaultAnim);
                attacked = false;
            }
        }
        super.act();
    }
    
    public boolean isDead()
    {
        return dead;
    }
    
    public boolean isAttackActive()
    {
        return attackCounter >= 25 && attackCounter <= 35;
    }
}
