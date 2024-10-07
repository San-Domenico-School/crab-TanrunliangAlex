import greenfoot.*;  // (Actor, World, Greenfoot, GreenfootImage)
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CrabWorld extends World 
{
    private List<Worm> worms;  // Declare worms list
    private Crab crab; // Store reference to Crab

    public CrabWorld() 
    {
        super(800, 500, 1);
        worms = new ArrayList<>(); // Initialize worms list
        prepareScene();
    }

    private void prepareScene() 
    {
        addPlayer();
        addWorms(); // Call addWorms to add worms to the scene
    }

    private void addPlayer() 
    {
        crab = new Crab();
        addObject(crab, 100, 100);
        
        Lobster lobster = new Lobster();
        addObject(lobster, 500, 400);
    }

    private void addWorms() 
    {
        Random random = new Random();
        for (int i = 0; i < 20; i++) 
        {
            Worm worm = new Worm();
            int x = random.nextInt(800);
            int y = random.nextInt(500);
            addWormToScene(worm, x, y);
            worms.add(worm); // Add the worm to the list
        }
    }

    private void addWormToScene(Worm worm, int x, int y) 
    {
        addObject(worm, x, y); // Add worm to the world at (x, y)
    }

    public void act() 
    {
        checkCollisions();
    }

    private void checkCollisions() 
    {
        Lobster lobster = (Lobster) getObjects(Lobster.class).get(0); // Get the lobster

        // Check for collision with Lobster
        if (crab != null && lobster != null && isColliding(crab, lobster))
        {
            System.out.println("Crab has collided with Lobster. Ending game.");
            Greenfoot.stop(); // End the game if the Crab collides with Lobster
        }

        // Check for collision with Worms
        List<Worm> wormsInWorld = getObjects(Worm.class);
        for (Worm worm : wormsInWorld) 
        {
            if (crab != null && isColliding(crab, worm)) 
            {
                removeObject(worm); // Remove the worm from the scene
                worms.remove(worm); // Also remove from the list
                break; // Exit the loop to avoid ConcurrentModificationException
            }
        }

        // Check if all Worms are removed
        if (worms.isEmpty()) 
        {
            System.out.println("All worms have been collected. Ending game.");
            Greenfoot.stop(); // End the game if all worms are removed
        }
    }

    private boolean isColliding(Actor actor1, Actor actor2) 
    {
        // Check if the distance between the two actors is less than a threshold
        int distance = (int) Math.sqrt(Math.pow(actor1.getX() - actor2.getX(), 2) + Math.pow(actor1.getY() - actor2.getY(), 2));
        return distance < 30; // Adjust the threshold as needed
    }
}
