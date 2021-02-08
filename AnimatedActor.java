import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class AnimatedActor here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AnimatedActor extends Actor
{
    private Map<String, Animation> animationRegistry;
    private Animation currentAnimation;
    private String currentAnimID;
    private int currentFrame;
    private int delayTimer;
    
    private boolean isAnimating;
    private boolean usingDefaultOrientation;
    
    public AnimatedActor()
    {
        super();
        animationRegistry = new HashMap<String, Animation>();
        currentFrame = 0;
        isAnimating = false;
        delayTimer = 0;
        currentAnimID = "";
        
        usingDefaultOrientation = true;
    }
    
    /**
     * Act - do whatever the AnimatedActor wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(isAnimating && currentAnimation != null)
        {
            GreenfootImage tempImage = currentAnimation.frames.get(currentFrame);
            
            if(delayTimer >= currentAnimation.frameDelay)
            {
                this.setImage(tempImage);
                currentFrame++;
                delayTimer = 0;
            }
            else
            {
                delayTimer++;
            }
            
            if(currentFrame > currentAnimation.frames.size()-1 
                && !currentAnimation.isLoopingAnimation)
            {
                stopAnimation();
            }
            else if(currentFrame > currentAnimation.frames.size()-1 
                && currentAnimation.isLoopingAnimation)
            {
                currentFrame = 0;
                delayTimer = 0;
                isAnimating = true;
            }
        }
    }
    
    public void stopAnimation()
    {
        currentAnimation = null;
        
        if(currentAnimID == "Player Special")
        {
            setLocation(getX(), getY() + 30);
        }
        
        currentAnimID = "";
        
        currentFrame = 0;
        delayTimer = 0;
        isAnimating = false;
    }
    
    public void playAnimation(String animName)
    {
        currentAnimation = animationRegistry.get(animName);
        
        if(currentAnimation != null)
        {
            if(!usingDefaultOrientation && !currentAnimation.flipped)
            {
                for(GreenfootImage i : currentAnimation.frames)
                {
                    i.mirrorHorizontally();
                }
                currentAnimation.flipped = true;
            }
            else if(usingDefaultOrientation && currentAnimation.flipped)
            {
                for(GreenfootImage i : currentAnimation.frames)
                {
                    i.mirrorHorizontally();
                }
                currentAnimation.flipped = false;
            }
            
            currentAnimID = animName;
            currentFrame = 0;
            delayTimer = 0;
            isAnimating = true;
        }
    }
    
    protected void registerAnimation(String animName, 
        String baseName, String suffix, int numFrames, boolean loop, int delay)
    {
        Animation anim = new Animation(baseName, suffix, numFrames, loop, delay);
        
        animationRegistry.put(animName, anim);
    }
    
    public void defaultOrientation()
    {
        usingDefaultOrientation = true;
    }
    
    public void flippedOrientation()
    {
        usingDefaultOrientation = false;
    }
    
    public boolean getOrientation()
    {
        return usingDefaultOrientation;
    }
    
    public String getCurrentAnim()
    {
        return currentAnimID;
    }
    
    public boolean getIsAnimating()
    {
        return this.isAnimating;
    }
}
