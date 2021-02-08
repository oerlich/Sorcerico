import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class StorySplash here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StorySplash extends World
{

    /**
     * Constructor for objects of class StorySplash.
     * 
     */
    public StorySplash()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);
        
        while(Greenfoot.getKey() != null){}
    }
    
    public void act()
    {
        if(Greenfoot.getKey() != null)
            Greenfoot.setWorld(new Level1());
    }
}
