import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Level4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Level4 extends Level
{

    /**
     * Constructor for objects of class Level4.
     * 
     */
    public Level4()
    {
        super(4, null);
        
        Player p = new Player();
        addObject(p, 200, 90);
        
        Platform p1 = new LevelFourPlatform();
        addObject(p1, 297, 152);
        
        Floor floor = new Floor();
        addObject(floor, 300, 350);
        
        SmallSpikes smSp1 = new SmallSpikes();
        addObject(smSp1, 123, 121);
        
        SmallSpikes smSp2 = new SmallSpikes();
        addObject(smSp2, 470, 121);
        
        Spikes sp1 = new Spikes();
        addObject(sp1, 550, 305);
        
        Spikes sp2 = new Spikes();
        addObject(sp2, 50, 305);
        
        Slime slime1 = new Slime(true);
        addObject(slime1, 150, 295);
        
        Slime slime2 = new Slime(true);
        addObject(slime2, 400, 120);
        
        WinDoor wd = new WinDoor();
        addObject(wd, 285, 264);
        
        Sign sign = new Sign();
        addObject(sign, 285, 220);
    }
}
