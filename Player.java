import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
* Write a description of class Player here.
* 
* @author (your name) 
* @version (a version number or a date)
*/
public class Player extends PhysicsActor
{
    private String defaultAnim;
    private String currentAnim;
    
    private int specialAnimCounter;
    private int specialAnimTrigger;
    
    private int deathTimer;
    private int deathCounter;
    private boolean dead;
    
    private boolean activeMovement;
    private boolean canFire;
    
    private int JUMP_POWER = 7;
    
    private boolean fired;
    
    private GreenfootSound walkSound;
    private GreenfootSound attackSound;
    private GreenfootSound deathSound;
    
    public Player()
    {
        super(true);
        registerAnimation("Player Idle", "playerIdle", ".png", 6, true, 5);
        registerAnimation("Player Run", "playerRun", ".png", 8, false, 5);
        registerAnimation("Player Special", "playerSpecial", ".png", 8, false, 5);
        registerAnimation("Player Attack", "playerAttack", ".png", 8, false, 5);
        registerAnimation("Player Die", "playerDie", ".png", 7, false, 5);
        defaultAnim = "Player Idle";
        
        walkSound = new GreenfootSound("shuffle.mp3");
        attackSound = new GreenfootSound("fireball.mp3");
        deathSound = new GreenfootSound("death.mp3");
        
        specialAnimTrigger = 600;
        specialAnimCounter = 0;
        
        deathTimer = 35;
        deathCounter = 0;
        dead = false;
        deathSound.setVolume(50);
        
        fired = false;
    }
    
    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        currentAnim = getCurrentAnim();
        
        activeMovement = false;
        canFire = false;
        velocity.x = 0;
        
        if(checkDead())
        {
            ((Level)this.getWorld()).gameOver();
            this.getWorld().removeObject(this);
            return;
        }
        
        if(!dead)
        {
            checkAndMoveLeft();
            checkAndMoveRight();
            
            checkAndJump();
            checkCanFire();
            
            if(!isGrounded && walkSound.isPlaying())
            {
                walkSound.stop();
            }
            
            checkAndFire();
            
            specialIdle();
            
            defaultAnimation();
        }
        
        super.act();
    }
    
    private boolean checkDead()
    {
        List<Slime> coll = getObjectsInRange(50, Slime.class);
        List<Spikes> coll2 = getObjectsInRange(50, Spikes.class);
        
        List<Slime> goodColl = new ArrayList<Slime>();
        
        for(Slime s : coll)
        {
            if(!s.isDead())
            {
                goodColl.add(s);
            }
        }
        
        if((!goodColl.isEmpty() || !coll2.isEmpty()) && !dead)
        {
            deathCounter = 0;
            dead = true;
            deathSound.play();
            playAnimation("Player Die");
        }
        
        if(currentAnim.equals("Player Die") && deathCounter < deathTimer)
        {
            deathCounter++;
        }
        else if(currentAnim.equals("Player Die"))
        {
            return true;
        }
        
        return false;
    }
    
    private void checkAndMoveLeft()
    {
        if(Greenfoot.isKeyDown("d"))
        {
            activeMovement = true;
            defaultOrientation();
            velocity.x = 3;   
            
            if(!walkSound.isPlaying())
            {
                walkSound.play();
            }
            
            if(!currentAnim.equals("Player Run") &&
                !currentAnim.equals("Player Attack"))
            {
                specialAnimCounter = 0;
                
                playAnimation("Player Run");
            }
        }
    }
    
    private void checkAndMoveRight()
    {
        if(Greenfoot.isKeyDown("a"))
        {   
            activeMovement = true;
            flippedOrientation();
            velocity.x = -3;
            
            if(!walkSound.isPlaying() && isGrounded)
            {
                walkSound.play();
            }
            
            if(!currentAnim.equals("Player Run") &&
                !currentAnim.equals("Player Attack"))
            {
                specialAnimCounter = 0;
                
    
                playAnimation("Player Run");
            }
        }
    }
    
    private void checkAndJump()
    {
        if(Greenfoot.isKeyDown("space") && isGrounded)
        {
            /*
            List<MovingPlat> mPS = this.getWorld().getObjects(MovingPlat.class);
            
            for(MovingPlat mp : mPS)
            {
                mp.onPlat.remove(this);
            }*/
            
            specialAnimCounter = 0;
            activeMovement = true;
            setLocation(getX(), getY() - 3);
            isGrounded = false;
            velocity.y = -JUMP_POWER;
        }
    }
    
    private void checkCanFire()
    {
        if(getOrientation() && !xCollRight())
        {
            canFire = true;
        }
        
        if(!getOrientation() && !xCollLeft())
        {
            canFire = true;
        }
    }
    
    private void checkAndFire()
    {
        if(Greenfoot.getMouseInfo() != null && canFire == true)
        {
            MouseInfo mouse = Greenfoot.getMouseInfo();
            if(Greenfoot.getMouseInfo().getButton() == 1 && !fired)
            {
                fired = true;
                specialAnimCounter = 0;
                Fireball f = new Fireball();
                
                if(!getOrientation())
                {
                    f.getImage().mirrorHorizontally();
                }
                
                attackSound.play();
                
                int xOffset = (getOrientation()) ? 30 : -30;
                int xVelocity = (getOrientation()) ? 3 : -3;
                
                Vec2 spawnPoint = new Vec2(getX() + xOffset, getY() + 10);
                    
                f.velocity = new Vec2(this.velocity.x + xVelocity, 0);
    
                this.getWorld().addObject(f, (int)spawnPoint.x,
                    (int)spawnPoint.y);
                    
                playAnimation("Player Attack");
            }
        }
    }
    
    private void specialIdle()
    {
        if(currentAnim.equals(defaultAnim) && !activeMovement)
        {
            if(specialAnimCounter < specialAnimTrigger)
            {
                specialAnimCounter++;
            }
            else
            {
                setLocation(getX(), getY() - 30);
                playAnimation("Player Special");
                specialAnimCounter = 0;
            }
        }
    }
    
    private void defaultAnimation()
    {
        if(!currentAnim.equals(defaultAnim) && !activeMovement
                && !currentAnim.equals("Player Special")
                    && !currentAnim.equals("Player Attack")
                    && !currentAnim.equals("Player Die"))
        {
            fired = false;
            playAnimation(defaultAnim);
        }
    }
}
