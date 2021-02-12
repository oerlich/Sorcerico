import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class PhysicsActor here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PhysicsActor extends AnimatedActor
{
    protected Vec2 velocity;
    
    private float GRAVITY = -0.25f;
    
    protected boolean isGrounded;
    private boolean affectGravity;
    
    public PhysicsActor(boolean affectedGravity)
    {
        super();
        velocity = new Vec2(0.0, 0.0);
        isGrounded = false;
        affectGravity = affectedGravity;
    }
    /**
     * Act - do whatever the PhysicsActor wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        super.act();
            
        if(velocity.x > 0 && xCollRight())
            velocity.x = 0;
            
        if(velocity.x < 0 && xCollLeft())
            velocity.x = 0;
            
        if(velocity.y < 0 && yCollUp())
            velocity.y = 0;
              
        if(affectGravity)
            applyGravity();
        
        setLocation(getX() + (int)velocity.x, getY() + (int)velocity.y);
    }
    
    private void applyGravity()
    {
        velocity.y -= GRAVITY;
    
        GreenfootImage currImage = this.getImage();
        Vec2 botMidAnch = new Vec2(0, currImage.getHeight()/2);
        
        List<Platform> plats = getObjectsAtOffset((int)botMidAnch.x,
            (int)botMidAnch.y, Platform.class);
            
        List<MovingPlat> moving_plats = getObjectsAtOffset((int)botMidAnch.x,
            (int)botMidAnch.y, MovingPlat.class);
        
        if(!plats.isEmpty())
        {   
            for(MovingPlat p : moving_plats)
            {
                p.onPlat.add(this);
            }
            
            velocity.y = 0.0;
            isGrounded = true;
        }
        else
        {
            isGrounded = false;
        }
    }
    
    protected boolean xCollLeft()
    {
        GreenfootImage currImage = this.getImage();
        Vec2 left = new Vec2((-currImage.getWidth()/2) - 5, 0);
        
        List<Platform> plats = getObjectsAtOffset((int)left.x,
            (int)left.y, Platform.class);
            
        if(!plats.isEmpty())
            return true;
        
        return false;
    }
    
    protected boolean xCollRight()
    {
        GreenfootImage currImage = this.getImage();
        Vec2 right = new Vec2(currImage.getWidth()/2 + 5, 0);
        
        List<Platform> plats = getObjectsAtOffset((int)right.x,
            (int)right.y, Platform.class);
            
        if(!plats.isEmpty())
            return true;
        
        return false;
    }
    
    protected boolean yCollUp()
    {
        GreenfootImage currImage = this.getImage();
        Vec2 up = new Vec2(0, -currImage.getHeight()/2);
        
        List<Platform> plats = getObjectsAtOffset((int)up.x,
            (int)up.y, Platform.class);
            
        if(!plats.isEmpty())
            return true;
        
        return false;
    }
        
}
