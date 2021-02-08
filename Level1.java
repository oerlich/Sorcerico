import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Level1 extends Level
{
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public Level1()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1, new Level2());
        
        Player p = new Player();
        addObject(p, 50, 310);
        
        Floor fl = new Floor();
        addObject(fl, 300, 375);
        
        Portal exit = new Portal();
        addObject(exit, 570, 95);
        
        Slime enemy = new Slime(false);
        addObject(enemy, 415, 175);
        
        RockPlatform plat1 = new RockPlatform();
        RockPlatform plat2 = new RockPlatform();
        RockPlatform plat3 = new RockPlatform();
        
        addObject(plat1, 264, 301);
        addObject(plat2, 415, 233);
        addObject(plat3, 548, 169);
      
    }
    
    public void act()
    {
        super.act();
    }
}
