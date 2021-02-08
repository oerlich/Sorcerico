import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GameOverScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameOverScreen extends World
{

    /**
     * Constructor for objects of class GameOverScreen.
     * 
     */
    public GameOverScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);
        
        while(Greenfoot.getKey() != null)
        {
            //do nothing;
        }
    }
    
    public void act()
    {
        new GreenfootSound("bgmusic.mp3").stop();
        if(Greenfoot.getKey() != null)
            Greenfoot.setWorld(new StartScreen());
    }
}
