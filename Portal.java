import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class Portal here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Portal extends AnimatedActor
{
    private GreenfootSound portalSound;
    public Portal()
    {
        super();
        portalSound = new GreenfootSound("portal.mp3");
        registerAnimation("Portal Idle", "portalIdle", ".png", 8, true, 7);
        
    }
    
    /**
     * Act - do whatever the Portal wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        Actor coll = getOneIntersectingObject(Player.class);
        if(coll != null)
        {
            Level currLevel = (Level)this.getWorld();
            if(currLevel.nextLevel != null)
            {
                portalSound.play();
                currLevel.bgMusic.stop();
                Greenfoot.setWorld(currLevel.nextLevel);
            }
        }
        
        if(!getIsAnimating())
            playAnimation("Portal Idle");
            
        super.act();
    }    
}
