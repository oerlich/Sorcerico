import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
import java.lang.Math;


/**
 * Write a description of class MovingPlatTest here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MovingPlat extends Platform
{
    
    public List<PhysicsActor> onPlat;
    
    private Vec2 endPoint1;
    private Vec2 endPoint2;
    
    private Vec2 currDest;
    
    private boolean initSet;
    private int platformSpeed;
    
    public MovingPlat(Vec2 e1, Vec2 e2, int speed)
    {
        onPlat = new ArrayList<PhysicsActor>();
        platformSpeed = speed;
        
        endPoint1 = e1;
        endPoint2 = e2;
        
        currDest = e2;
        initSet = false;
    }
    
    /**
     * Act - do whatever the MovingPlatTest wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(!initSet)
        {
            setLocation((int)endPoint1.x, (int)endPoint1.y);
            initSet = true;
        }
        
        if((getX() >= currDest.x - platformSpeed &&
            getX() <= currDest.x + platformSpeed) &&
            (getY() <= currDest.y + platformSpeed &&
            (getY() >= currDest.y - platformSpeed)))
        {
            currDest = (currDest == endPoint1) ? endPoint2 : endPoint1;
        }
        
        Vec2 direction = new Vec2(currDest.x - this.getX(), currDest.y - this.getY()).normalized();
        double xMove = (direction.x * platformSpeed);
        double yMove = (direction.y * platformSpeed);
        setLocation(getX() + (int)Math.ceil(xMove),
            getY() + (int)Math.ceil(yMove));
        
        for(PhysicsActor a : onPlat)
        {
            a.setLocation(a.getX() + (int)(direction.x * platformSpeed),
            a.getY() + (int)(direction.y * platformSpeed));
        }
        
        onPlat = new ArrayList<PhysicsActor>();
    }    
}
