
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyListener;
import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 *
 * @author smith3577
 */


public class finalProject extends JComponent{

    // Height and Width of our game
    static final int WIDTH = 800;
    static final int HEIGHT = 600;
    
    // sets the framerate and delay for our game
    // you just need to select an approproate framerate
    long desiredFPS = 60;
    long desiredTime = (1000)/desiredFPS;
    
    //game variables
    Color ground = new Color(209, 130, 46);
    Rectangle car = new Rectangle(345, 400, 125, 175);
    Rectangle lane1 = new Rectangle(100, -25, 200, 25);
    Rectangle lane2 = new Rectangle(310, -625, 190, 25);
    Rectangle lane3 = new Rectangle(510, -325, 190, 25);
    int moveLeft = -200;
    int moveRight = 200;
    int speed = 4;
    boolean start = true;
    
    // drawing of the game happens in here
    // we use the Graphics object, g, to perform the drawing
    // NOTE: This is already double buffered!(helps with framerate/speed)
    @Override
    public void paintComponent(Graphics g)
    {
        // always clear the screen first!
        g.clearRect(0, 0, WIDTH, HEIGHT);
        
        // GAME DRAWING GOES HERE 
        g.setColor(ground);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        
        g.setColor(Color.GRAY);
        g.fillRect(100, 0, 600, HEIGHT);
        
        g.setColor(Color.WHITE);
        g.fillRect(300, 0, 10, HEIGHT);
        g.fillRect(500, 0, 10, HEIGHT);
        
        g.setColor(Color.RED);
        g.fillRect(car.x, car.y, car.width, car.height);
        
        g.setColor(Color.ORANGE);
        g.fillRect(lane1.x, lane1.y, lane1.width, lane1.height);
        g.fillRect(lane2.x, lane2.y, lane2.width, lane2.height);
        g.fillRect(lane3.x, lane3.y, lane3.width, lane3.height);
        
        
        // GAME DRAWING ENDS HERE
    }
    
    
    // The main game loop
    // In here is where all the logic for my game will go
    public void run()
    {
        // Used to keep track of time used to draw and update the game
        // This is used to limit the framerate later on
        long startTime;
        long deltaTime;
        
        // the main game loop section
        // game will end if you set done = false;
        boolean done = false; 
        while(!done)
        {
            // determines when we started so we can keep a framerate
            startTime = System.currentTimeMillis();
            
            // all your game rules and move is done in here
            // GAME LOGIC STARTS HERE 
            if (start){
            lane1.y = lane1.y + speed;
            lane2.y = lane2.y + speed;
            lane3.y = lane3.y + speed;
            }
            // GAME LOGIC ENDS HERE 
            
            // update the drawing (calls paintComponent)
            repaint();
            
            
            
            // SLOWS DOWN THE GAME BASED ON THE FRAMERATE ABOVE
            // USING SOME SIMPLE MATH
            deltaTime = System.currentTimeMillis() - startTime;
            try
            {
               if(deltaTime > desiredTime)
               {
                   //took too much time, don't wait
                   Thread.sleep(1);
               }else{
                  // sleep to make up the extra time
                 Thread.sleep(desiredTime - deltaTime);
               }
            }catch(Exception e){};
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // creates a windows to show my game
        JFrame frame = new JFrame("My Game");
       
        // creates an instance of my game
        finalProject game = new finalProject();
        // sets the size of my game
        game.setPreferredSize(new Dimension(WIDTH,HEIGHT));
        // adds the game to the window
        frame.add(game);
         
        
        // sets some options and size of the window automatically
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        // shows the window to the user
        frame.setVisible(true);
        
        // starts my game loop
        game.run();
    }
}