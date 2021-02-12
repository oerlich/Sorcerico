import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Level3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Level3 extends Level
{

    /**
     * Constructor for objects of class Level3.
     * 
     */
    public Level3()
    {
        super(3, new Level4());

        Player p = new Player();
        addObject(p, 300, 260);
        
        Floor floor = new Floor();
        addObject(floor, 300, 500);
        
        Platform flatPlatform = new FlatPlatform();
        addObject(flatPlatform, 303, 350);
        
        Shrubs shrubs = new Shrubs();
        addObject(shrubs, 284, 281);
        
        Spikes s1 = new Spikes();
        Spikes s2 = new Spikes();
        Spikes s3 = new Spikes();
        Spikes s4 = new Spikes();
        
        SpikesHit sph1 = new SpikesHit();
        SpikesHit sph2 = new SpikesHit();
        SpikesHit sph3 = new SpikesHit();
        SpikesHit sph4 = new SpikesHit();
        
        addObject(s1, 473, 362);
        addObject(s2, 561, 362);
        addObject(s3, 138, 362);
        addObject(s4, 47, 362);
        
        addObject(sph1, 473, 346);
        addObject(sph2, 561, 346);
        addObject(sph3, 138, 346);
        addObject(sph4, 47, 346);
        
        Slime enemy1 = new Slime(true);
        addObject(enemy1, 125, 275);
        
        Slime enemy2 = new Slime(true);
        addObject(enemy2, 477, 275);
        
        RockPlatform rockPlat = new RockPlatform();
        addObject(rockPlat, 591, 189);
        
        MovingPlat mp1 = new MovingPlat(new Vec2(0, 250), new Vec2(0, 125), 1);
        addObject(mp1, 0, 0);
        
        MovingPlat mp2 = new MovingPlat(new Vec2(192, 180), new Vec2(389, 180), 1);
        addObject(mp2, 0, 0);
        
        SmallPlatform sp1 = new SmallPlatform();
        addObject(sp1, 475, 179);
        
        SmallSpikes smallSpikes = new SmallSpikes();
        addObject(smallSpikes, 474, 147);
        
        SmallSpikesHit smallSpikesH = new SmallSpikesHit();
        addObject(smallSpikesH, 474, 131);
        
        Portal exit = new Portal();
        addObject(exit, 570, 111);
    }
}
