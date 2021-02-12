import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class WinDoor here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WinDoor extends AnimatedActor
{
    private int openLength = 120;
    private int openCounter;
    
    private boolean opened;
    
    private GreenfootSound doorSound;
    
    public WinDoor()
    {
        super();
        registerAnimation("Door Open", "door", ".png", 6, false, 6);
        
        openCounter = 0;
        doorSound = new GreenfootSound("door.mp3");
        doorSound.setVolume(50);
        opened = false;

    }
    
    /**
     * Act - do whatever the WinDoor wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        Actor coll = getOneIntersectingObject(Player.class);
        if(coll != null)
        {
            if(!getIsAnimating() && !opened)
            {
                doorSound.play();
                playAnimation("Door Open");
                opened = true;
            }
        }
        
        if(opened && openCounter < openLength)
        {
            openCounter++;
        }
        
        if(openCounter >= openLength && !doorSound.isPlaying())
        {
            ((Level)this.getWorld()).bgMusic.stop();
            Greenfoot.setWorld(((Level)this.getWorld()).nextLevel);
        }
        
        super.act();
    }    
}
