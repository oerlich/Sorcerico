import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Fireball here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Fireball extends PhysicsActor
{
    public Fireball()
    {
        super(false);
    }
    /**
     * Act - do whatever the Fireball wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        boolean otherCollision = false;
        
        if(velocity.x > 0 && xCollRight())
            otherCollision = true;
        else if(velocity.x < 0 && xCollLeft())
            otherCollision = true;
        
        if(this.isAtEdge() || otherCollision)
        {
            this.getWorld().removeObject(this);
        }
        else
        {
            super.act();
        }
    }    
}
