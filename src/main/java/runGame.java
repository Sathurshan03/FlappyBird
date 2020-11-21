import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.ListIterator;
import javax.swing.JPanel;
import javax.swing.Timer;


public class runGame extends JPanel implements ActionListener
{
    //Variables 
    boolean start = false;
    Timer timer;
    Background background;
    Bird bird;
    LinkedList <Pipe> pipes;
    int Score = 0;
    
    public runGame()
    {
        //Instantiate the key and mouse listeners
        KeyAdapter keyListener = new runGame.myKeyListener();
        MouseAdapter mouseListener = new runGame.myMouseListener();
        addKeyListener(keyListener);
        addMouseListener(mouseListener);
        setFocusable(true);
        
        //instantiate the objets for the game
        bird = new Bird();
        pipes = new LinkedList();
        pipes.add(new Pipe());
        
        //start the timer
        timer = new Timer (1,this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        //Run calculation every time timer tiks
        
       if (start == true)//game is playing
       {
            //update bird fall
            bird.updateBirdDrop();

            //move all pipes to the left
            ListIterator <Pipe> info = pipes.listIterator();
            while (info.hasNext())  
            {
                info.next().move();
            }

            //check neccessary conditions for specific operations
            checkAddPipe();
            checkRemovePipe();
            checkScore();
            checkIntersection();
       }
    }
    
    @Override
    public void paint(Graphics g)//Paints the Frame
    {
        if (start == false)//Start Page
        {
            paintStartGame(g);
        }
        else if (timer.isRunning()) //As game in running
        {
            paintPlayGame(g);
        }
        else //Game has ended
        {
            paintEndGame(g);
        }
        
        repaint();
    }
    
    //Key listener
    public class myKeyListener extends KeyAdapter
    {
        @Override
        public void keyPressed(KeyEvent e)
        {
            //space bar makes the bird fly up
            if (e.getKeyCode() == KeyEvent.VK_SPACE)
            {
                bird.fly();
            }
        }
    }
    
    //Mouse listener
    public class myMouseListener extends MouseAdapter
    {
        @Override
        public void mousePressed(MouseEvent e)
        {
            start = true; //starts the game
        }
    }
    
    public void paintStartGame(Graphics g)
    {
        //Graphics output for the start screen
        
        //Output background
        background = new Background (this.getWidth(), this.getHeight());
        g.equals(background.getBackGroundGraphics(g));
        
        //Output prompt
        String startGame = "CLICK TO START";
        g.setColor(Color.white);
        Font large = new Font("Helvetica", Font.BOLD, 25);
        FontMetrics fm = getFontMetrics(large);
        g.setFont(large);
        g.drawString(startGame, (this.getWidth() - fm.stringWidth(startGame)) / 2, this.getHeight() /2);
    }
    public void paintPlayGame(Graphics g)
    {
        //Graphics output for the game 
        
        //Output background
        background = new Background (this.getWidth(), this.getHeight());
        g.equals(background.getBackGroundGraphics(g));
        
        //Output bird
        g.equals(bird.getGraphics(g));
        
        //Output Score
        String strScore = Integer.toString(Score);
        g.setColor(Color.white);
        Font Xlarge = new Font("Helvetica", Font.BOLD, 40);
        FontMetrics fm = getFontMetrics(Xlarge);
        g.setFont(Xlarge);
        g.drawString(strScore, (this.getWidth() - fm.stringWidth(strScore)) / 2, 652);
        
        //Output all the pipes
        ListIterator <Pipe> info = pipes.listIterator();
            
        while (info.hasNext())  
        {
            g.equals(info.next().getGraphics(g));
        }
    }
    public void paintEndGame(Graphics g)
    {
        //Graphics for when the game is over 
        
        //Output for the background
        background = new Background (this.getWidth(), this.getHeight());
        g.equals(background.getBackGroundGraphics(g));
        
        //Output message
        String gameOver = "GAME OVER";
        g.setColor(Color.red);
        Font XXlarge = new Font("Helvetica", Font.BOLD, 50);
        FontMetrics fm = getFontMetrics(XXlarge);
        g.setFont(XXlarge);
        g.drawString(gameOver, (this.getWidth() - fm.stringWidth(gameOver)) / 2, this.getHeight()/2);
    }
    public void checkAddPipe()
    {
        //Add a pipe once recent pipe passes a certian point

        if (pipes.getLast().getX() == 350) 
        {
            pipes.add(new Pipe());
        }
        
    }
    public void checkRemovePipe()
    {
        //Remove the pipe if it leaves off screen on left side 
        
        if (pipes.peek().getX() + 35 <= 0)
        {
            pipes.removeFirst();
        }
       
    }
    public void checkScore()
    {
        //Add score once Bird passes a pipe
        
        ListIterator <Pipe> info = pipes.listIterator();
            
        while (info.hasNext())  
        {
            if (info.next().getX() + 35 == bird.getX())
            {
                Score ++;
            }
        }
    }
    public void checkIntersection()
    {
        //Stop the timer once the bird intersects with any pipe 
            
        Rectangle birdBounds = bird.getBounds();
                
       for (int i = 0; i < pipes.size(); i++)
       {
            Rectangle pipeTopBounds = pipes.get(i).getTopPipeBounds();
            Rectangle pipeBottomBounds = pipes.get(i).getBottomPipeBounds();
            
            if (pipeTopBounds.intersects(birdBounds) || pipeBottomBounds.intersects(birdBounds))
            {
                timer.stop();
            }
        }
    }
    
}
