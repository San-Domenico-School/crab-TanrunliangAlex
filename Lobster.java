import greenfoot.*;

/**
 * This class defines a crab. Crabs live on the beach.
 * Author: BGustin
 * Version: 6/1/2022 5:48am
 */

public class Lobster extends Actor 
{
    private int speed = 5; // Default speed of the Crab
    private int turnSpeed = 5; // Default turn speed of the Crab

    public void act() 
    {
        moveCrab();
        turnAtEdge();
    }

    private void moveCrab() 
    {
        if (Greenfoot.isKeyDown("w")) 
        { // Move forward
            move(speed);
        }
        if (Greenfoot.isKeyDown("s")) 
        { // Move backward
            move(-speed);
        }
        if (Greenfoot.isKeyDown("a")) 
        { // Turn left
            turn(-turnSpeed);
        }
        if (Greenfoot.isKeyDown("d")) 
        { // Turn right
            turn(turnSpeed);
        }
    }
    
    private void turnAtEdge() 
    {
        if (isAtEdge()) 
        { // Check if at edge
            turn(90); // Turn 90 degrees
        }
    }
    
    public boolean isAtEdge() 
    {
        return getX() <= 0 || getX() >= getWorld().getWidth() - 1 ||
               getY() <= 0 || getY() >= getWorld().getHeight() - 1;
    }
}
