import greenfoot.*;
import java.util.*;

public class Animation
{
    public List<GreenfootImage> frames;
    public boolean isLoopingAnimation;
    public int frameDelay;
    public boolean flipped;
    
    public Animation(String baseName, String suffix, int numFrames,
        boolean loop, int delay)
    {
        frames = new ArrayList<GreenfootImage>();
        
        for(int i = 0; i < numFrames; i++)
        {
            frames.add(new GreenfootImage(baseName + i + suffix));
        }
        
        isLoopingAnimation = loop;
        frameDelay = delay;
    }
}

