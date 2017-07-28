/**
 * Created by William Madgwick on 7/28/2017.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Board extends JFrame{

    public static final int BOARD_WIDTH = 1000;
    public static final int BOARD_HEIGHT = 800;


    //Constructor
    public Board(){

        super.setTitle("Snake Game");
        super.setSize(BOARD_WIDTH, BOARD_HEIGHT);
        super.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        super.setLocationRelativeTo(null); //This will center the Frame

        super.addKeyListener(new ButtonsHandler());

        setUpGame();

        super.setVisible(true);
    }

    //The main method will just create a new instance of Board
    public static void main(String[] args) {
        new Board();
    }

    private void setUpGame(){

        //Add the panel to JFrame
        super.add(new GameDrawingPanel(), BorderLayout.CENTER);

        //Set up the thread pool that will run every 20 milliseconds, repainting the Board
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(5);
        executor.scheduleAtFixedRate(new RepaintTheBoard(this), 0, 20, TimeUnit.MILLISECONDS);

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

            //W and Up arrow
            if(e.getKeyCode() == 87 || e.getKeyCode() == 38){
                System.out.println("Up");
            }

            if(e.getKeyCode() == 65 || e.getKeyCode() == 37){ //A and Left arrow
                System.out.println("Left");
            }

            if(e.getKeyCode() == 68 || e.getKeyCode() == 39){ //D and Right arrow
                System.out.println("Right");
            }

        }
    }
}

class RepaintTheBoard implements Runnable{

    private Board theBoard;

    //Constructor
    RepaintTheBoard(Board board){
        theBoard = board;
    }

    @Override
    public void run(){
        theBoard.repaint();
    }
}

class GameDrawingPanel extends JPanel {

    private LinkedList snake = new LinkedList();

    //Constructor
    GameDrawingPanel(){

        //Start from center screen
        int startingXPos = Board.BOARD_WIDTH / 2;
        int startingYPos = Board.BOARD_HEIGHT / 2;

        //Add the initial block
        snake.appendBlock(new Block(startingXPos, startingYPos, 20, 1, 0));
    }

    @Override
    public void paint(Graphics g){

        Graphics2D graphicSettings = (Graphics2D)g;

        graphicSettings.setColor(Color.BLACK);

        //Draws a black background as big as the board
        graphicSettings.fillRect(0, 0, getWidth(), getHeight());

        //The rendering rules
        graphicSettings.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //The color my snake will be drawn at
        graphicSettings.setPaint(Color.WHITE);

        //Loop through the linkedList to draw the blocks
        for(LinkedList.Node ptr = snake.getHead(); ptr != null; ptr = ptr.getNext()){

            graphicSettings.draw(ptr.getBlock());

            ptr.getBlock().move();
        }
    }
}
