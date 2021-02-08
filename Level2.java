import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Level2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Level2 extends Level
{

    /**
     * Constructor for objects of class Level2.
     * 
     */
    public Level2()
    {
        super(2, new Level3());
       
        Player p = new Player();
        addObject(p, 550, 200);
        p.flippedOrientation();
        
        Platform p1 = new BlockEdgePlatRightTall();
        addObject(p1, 554, 306);
        
        Platform p2 = new RockPlatform();
        addObject(p2, 383, 269);
        
        Platform p3 = new FloatingPlat2();
        addObject(p3, 208, 348);
        
        Platform p4 = new RockPlatform();
        addObject(p4, 267, 294);
        
        MovingPlat p5 = new MovingPlat(new Vec2(0, 250), new Vec2(0, 125), 1);
        addObject(p5, 0, 0);
        
        Slime s = new Slime(true);
        addObject(s, 150, 285);
        
        Spikes spike1 = new Spikes();
        addObject(spike1, 464, 356);
        
        Portal exit = new Portal();
        addObject(exit, 150, 50);
        
        
        Floor fl = new Floor();
        addObject(fl, 300, 405);
    }
}
