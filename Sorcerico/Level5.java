import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Level5 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Level5 extends Level
{

    /**
     * Constructor for objects of class Level5.
     * 
     */
    public Level5()
    {
        super(5, null, "bossmusic.mp3");
        
        Player p = new Player();
        addObject(p, 75, 270);
        
        Floor floor = new Floor();
        addObject(floor, 300, 350);
        
        GloopGlorp g = new GloopGlorp(false);
        addObject(g, 500, 267);
        
        MovingPlat mp1 = new MovingPlat(new Vec2(0, 300), new Vec2(0, 125), 2);
        addObject(mp1, 0, 0);
        
        MovingPlat mp2 = new MovingPlat(new Vec2(142, 150), new Vec2(429, 150), 2);
        addObject(mp2, 0, 0);
        
        MovingPlat mp3 = new MovingPlat(new Vec2(600, 300), new Vec2(600, 115), 3);
        addObject(mp3, 0, 0);
        
    }
}
