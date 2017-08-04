/**
 * Created by William Madgwick on 7/28/2017.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Board extends JFrame{

    static Direction direction = Direction.UP;
    static GameState gameState;
    private static int scheduleRate = 10;
    private static Player player;
    static final Color THEME_COLOUR = Color.decode("#006699");
    private TopPanel pnlTop;
    private GameDrawingPanel pnlGameBoard;
    private ScheduledThreadPoolExecutor executor;


    //Constructor
    private Board(){

        pnlTop = new TopPanel();
        pnlGameBoard = new GameDrawingPanel(pnlTop);

        //Add the panels to JFrame
        add(pnlGameBoard, BorderLayout.CENTER);
        add(pnlTop, BorderLayout.NORTH);
        setResizable(false);
        pack();

        setTitle("Snake Game");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); //This will center the Frame

        //Set up the closing action of the board
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (JOptionPane.showConfirmDialog(null,
                        "Are you sure to exit?", "Really Closing?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)
                {
                    //First I want to save the data
                    player.savePlayerInfo();

                    //Then exit
                    System.exit(0);
                }
            }
        });

        addKeyListener(new ButtonsHandler());

        setUpThreads();
    }

    //The main method will just create a new instance of Board
    public static void main(String[] args) {

        gameState = GameState.PLAYING;

        //If the file does not exist, display the input dialog
        if(! new File(Player.FileHandler.FILE_PATH).exists()){
            initializeFirstTimePlayer();
        }
        else {
            player = new Player();
        }

        Board gameBoard = new Board();
        gameBoard.setVisible(true);

    }

    //This method will display a custom JOptionPane the first time the user runs the software
    private static void initializeFirstTimePlayer(){

        UIManager UI=new UIManager();
        UI.put("OptionPane.background", Color.BLACK);
        UI.put("Panel.background", THEME_COLOUR);

        String name = JOptionPane.showInputDialog("Please enter your name", "NAME");

        //If the user leaves it blank for some reason
        if(name.trim().length() < 1){
            name = "Snaky";
        }

        //Create player object
        player = new Player(name, 0, 0);
        player.savePlayerInfo();
    }

    private void setUpThreads(){

        //Set up the thread pool that will run every 20 milliseconds, repainting the Board
        executor = new ScheduledThreadPoolExecutor(5);
        executor.scheduleAtFixedRate(new RepaintTheBoard(this), 0, scheduleRate, TimeUnit.MILLISECONDS);
        
    }

    //This class will handle all of my button clicks
    //Will be for W/A/D
    private class ButtonsHandler implements KeyListener{

        @Override
        public void keyTyped(KeyEvent e){}

        @Override
        public void keyReleased(KeyEvent e){}

        @Override
        public void keyPressed(KeyEvent e) {

            if(e.getKeyCode() == 87 || e.getKeyCode() == 38){//W and Up arrow
                direction = Direction.UP;
            }

            if(e.getKeyCode() == 83 || e.getKeyCode() == 40){//S and Down arrow
                direction = Direction.DOWN;
            }

            if(e.getKeyCode() == 65 || e.getKeyCode() == 37){ //A and Left arrow
                direction = Direction.LEFT;
            }

            if(e.getKeyCode() == 68 || e.getKeyCode() == 39){ //D and Right arrow
                direction = Direction.RIGHT;
            }

            if(e.getKeyCode() == 80){ //D and Right arrow
                if(gameState != GameState.PAUSE){
                    gameState = GameState.PAUSE;
                }else{
                    gameState = GameState.PLAYING;
                }

            }

        }
    }

    static int getScheduleRate(){ return scheduleRate; }

    static Player getPlayer(){
        return player;
    }
}

class RepaintTheBoard implements Runnable{

    private Board theBoard;

    //This delay will be used to speed up the game as time passed
    private int threadDelay = 100; //Original = 100
    private int countTime = 0;

    //Constructor
    RepaintTheBoard(Board board){
        theBoard = board;
    }

    @Override
    public void run(){

        //If the game is in the Playing state it should go as normal
        if(Board.gameState == GameState.PLAYING){

            theBoard.repaint();

            try{

                Thread.sleep(threadDelay);

            }
            catch(InterruptedException e) { e.printStackTrace(); }

            //Meaning this is the fastest that the snake will go
            if(threadDelay > 34){

                //Want to decrement the delay every 4.6 seconds
                //Meaning takes just about 5min to reach max speed
                if(countTime == 46){
                    threadDelay--;
                    countTime = 0;
                }

            }

            countTime++;
        }
        else if(Board.gameState == GameState.DIED){

            //Reset the value if the player died
            threadDelay = 100; //Original = 100
            countTime = 0;
        }
        //This will be the paused state
        else{
            //Do nada
        }
    }
}