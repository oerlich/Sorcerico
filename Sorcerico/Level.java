import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Level here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Level extends World
{
    private int levelID;
    public Level nextLevel;
    
    public GreenfootSound bgMusic = new GreenfootSound("bgmusic.mp3");
    protected boolean musicPlayed;
    
    /**
     * Constructor for objects of class Level.
     * 
     */
    public Level(int id, Level _nextLevel)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);
        
        while(Greenfoot.getKey() != null){}
        
        setPaintOrder(Shrubs.class, Platform.class, Player.class, Slime.class, Spikes.class, SmallPlatform.class);
        
        levelID = id;
        
        nextLevel = _nextLevel;
        
        bgMusic.setVolume(25);
        musicPlayed = false;
    }
    
    public Level(int id, Level _nextLevel, String musicName)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);
        
        while(Greenfoot.getKey() != null){}
        
        setPaintOrder(Shrubs.class, Platform.class, Player.class, Slime.class, Spikes.class, SmallPlatform.class);
        
        levelID = id;
        
        nextLevel = _nextLevel;
        
        bgMusic = new GreenfootSound(musicName);
        bgMusic.setVolume(25);
        musicPlayed = false;
    }
    
    public void act()
    {
        if(!musicPlayed)
        {
            bgMusic.playLoop();
            musicPlayed = true;
        }
    }
    
    public void gameOver()
    {
        bgMusic.stop();
        Greenfoot.setWorld(new GameOverScreen());
    }
}
