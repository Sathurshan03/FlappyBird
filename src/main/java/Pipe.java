import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Pipe 
{
    //Variables
    int X = 0;
    int Y = 0;
    final int Width;
    int Height;
    
    //Constructor
    public Pipe()
    {
        X = 600;
        Y = 0;
        Width = 35;
        Height = (int) (Math.random() * 510) + 10;
    }
    
    //Getters
    public int getX() //return the X position of the pipe
    {
        return X;
    }
    public Graphics getGraphics(Graphics g) //returns the pipes graphics
    {
        g.setColor(Color.green.darker());
        g.fillRect(X, Y, Width, Height);
        
        g.fillRect(X, Height + 80, Width, 610 - (Height + 80));
        
        return g;
    }
    public Rectangle getTopPipeBounds() //returns the Top pipes boundaries 
    {
        return new Rectangle(X,Y,Width,Height);
    }
    public Rectangle getBottomPipeBounds() //returns the Bottom pipes boundaries 
    {
        return new Rectangle(X, Height + 80, Width, 610 - (Height + 80));
    }
    
    //Behvaiour 
    public void move() // move the pipes to the left 
    {
        X--;
    }
}
