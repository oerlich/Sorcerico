import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class WinScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WinScreen extends World
{

    /**
     * Constructor for objects of class WinScreen.
     * 
     */
    public WinScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);
        
        while(Greenfoot.getKey() != null){}
    }
    
    public void act()
    {
        new GreenfootSound("bgmusic.mp3").stop();
        String key = Greenfoot.getKey();
        if(key != null && key.equals("escape"))
            Greenfoot.setWorld(new CreditsScreen());
        if(key != null && key.equals("c"))
            Greenfoot.setWorld(new StartScreen());
    }
}
