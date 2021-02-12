import java.lang.Math;

public class Vec2  
{
    // instance variables - replace the example below with your own
    public double x;
    public double y;

    /**
     * Constructor for objects of class Vec2
     */
    public Vec2(double xIn, double yIn)
    {
        x = xIn;
        y = yIn;
    }

    public Vec2 normalized()
    {
        double magnitude = Math.sqrt(x*x + y*y);
        return new Vec2(x/magnitude, y/magnitude);
    }
}
