import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Bird 
{
    //Variables
    final int X;
    int Y;
    final int WIDTH;
    final int HEIGHT;
    final double GRAVITY = 0.15;
    final int FLYFORCE = 5;
    double Velocity;
    
    //Constructor
    public Bird()
    {
        X = 25;
        Y = 275;
        WIDTH = 20;
        HEIGHT = 20;
        Velocity = 0;
    }
    
    //Getters
    public int getX() //return the x position
    {
        return X;
    }
    public Graphics getGraphics(Graphics g) //return Graphics for the bird
    {
        g.setColor(Color.red.brighter());
        g.fillOval(X, Y, WIDTH, HEIGHT);
        
        return g;
    }
    public Rectangle getBounds() //return the bird position box
    {
        return new Rectangle(X,Y,WIDTH,HEIGHT);
    }
    
    //Behaviours
    public void updateBirdDrop() //drop the bird according the gravity
    {
        Velocity += GRAVITY;
        Y += Velocity;
        
        //ensures bird does not go below the ground level
        if ( Y >= 590)
        {
            Y = 590;
            Velocity = 0;
        }
    }
    
    public void fly() //makes the bird fly
    {
        //bird can fly no faster than -6 down
        if (Velocity > -2)
        {
            Velocity -= FLYFORCE;
        }
        
        Y += Velocity;
        
        //ensure bird does not fly off the frame
        if (Y <= 0)
        {
            Y = 0;
            Velocity = 0;
        }
    }
}
