//Name: Sathurshan Arulmohan
//Date: November 14, 2020

import java.awt.Color;
import javax.swing.JFrame;

public class TheGame 
{
    
    static JFrame window;
    
    public static void main (String[] args)
    {
        window = new JFrame();
        window.setSize(600, 700);
        window.setBackground(Color.cyan);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().add(new runGame());
        window.setVisible(true);
    }    
}
