import java.awt.Color;
import java.awt.Graphics;

public class Background 
{
    //Variables
    int Width;
    int Height;
    
    //constructor
    public Background(int width, int height)
    {
        Width = width;
        Height = height;
    }
    
    //Returns the graphics of the background
    public Graphics getBackGroundGraphics(Graphics g)
    {
        g.setColor(Color.cyan);
        g.fillRect(0, 0, Width, Height);
        
        g.setColor(Color.green);
        g.fillRect(0, 610, Width, 15);
        
        g.setColor(Color.orange);
        g.fillRect(0, 625, Width, 35);
        
        return g;
    }
}
