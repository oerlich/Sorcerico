import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class Slime here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Slime extends PhysicsActor
{
    private String defaultAnim;
    
    private int deathTime;
    private int deathCounter;
    
    private boolean attackPlayer;
    private boolean attacked;
    private boolean dead;
    
    private GreenfootSound slimeDeathSound;
    
    private Player player;
    
    public Slime(boolean attPlayer){
        super(true);
        
        attackPlayer = attPlayer;
        attacked = false;
        dead = false;
        
        registerAnimation("Slime Idle", "slimeIdle", ".png", 4, true, 5);
        registerAnimation("Slime Move", "slimeMove", ".png", 4, false, 5);
        registerAnimation("Slime Die", "slimeDie", ".png", 4, false, 5);
        registerAnimation("Slime Attack", "slimeAttack", ".png", 4, false, 5);
        
        defaultAnim = "Slime Idle";
        deathTime = 20;
        deathCounter = 0;
        
        slimeDeathSound = new GreenfootSound("slimeDeath.mp3");
        slimeDeathSound.setVolume(50);        
    }
    
    /**
     * Act - do whatever the Slime wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        player = this.getWorld().getObjects(Player.class).get(0);

        String currentAnim = getCurrentAnim();
        boolean activeMovement = false;
        
        if(currentAnim.equals("Slime Die"))
        {
            deathCounter++;
        }
        
        List<Fireball> coll = getObjectsInRange(40, Fireball.class);
        if(!coll.isEmpty())
        {
            this.getWorld().removeObject(coll.get(0));
            deathCounter = 0;
            dead = true;
            playAnimation("Slime Die");
            slimeDeathSound.play();
        }
        
        if(deathCounter >= deathTime)
        {
            this.getWorld().removeObject(this);
            return;
        }
        
        if(!dead)
        {
            Actor playerCollision = getOneIntersectingObject(Player.class);
            if(playerCollision != null && !attacked)
            {
                playAnimation("Slime Attack");
                attacked = true;
            }
            
            if(attackPlayer)
            {
                Vec2 direction = new Vec2(player.getX() - getX(), player.getY() - getY());
                activeMovement = true;
                
                if(direction.x > 0)
                {
                    if(!currentAnim.equals("Slime Move") && 
                        !currentAnim.equals("Slime Die") &&
                        !currentAnim.equals("Slime Attack"))
                    {
                        flippedOrientation();
                        velocity.x = 1;
                        playAnimation("Slime Move");
                    }
                }
                else if(direction.x < 0)
                {
                    if(!currentAnim.equals("Slime Move") && 
                        !currentAnim.equals("Slime Die") &&
                        !currentAnim.equals("Slime Attack"))
                    {
                        defaultOrientation();
                        velocity.x = -1;
                        playAnimation("Slime Move");
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
}
