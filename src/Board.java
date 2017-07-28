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

    public static final int BOARD_WIDTH = 600;
    public static final int BOARD_HEIGHT = 400;

    public static int lastPressedKeyCode = -1;


    //Constructor
    public Board(){

        //Add the panel to JFrame
        add(new GameDrawingPanel());
        setResizable(false);
        pack();

        setTitle("Snake Game");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); //This will center the Frame
        setBackground(Color.BLACK);

        addKeyListener(new ButtonsHandler());

        setUpThreads();
    }

    //The main method will just create a new instance of Board
    public static void main(String[] args) {
        Board gameBoard = new Board();
        gameBoard.setVisible(true);
    }

    private void setUpThreads(){

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

            if(e.getKeyCode() == 87 || e.getKeyCode() == 38){//W and Up arrow
                lastPressedKeyCode = 87;
            }

            if(e.getKeyCode() == 83 || e.getKeyCode() == 40){//S and Down arrow
                lastPressedKeyCode = 83;
            }

            if(e.getKeyCode() == 65 || e.getKeyCode() == 37){ //A and Left arrow
                lastPressedKeyCode = 65;
            }

            if(e.getKeyCode() == 68 || e.getKeyCode() == 39){ //D and Right arrow
                lastPressedKeyCode = 68;
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

    private int foodSize = 15;

    private LinkedList snake = new LinkedList(); //List of blocks that will make a snake
    private Block food = new Block(Block.generateXPosFood(foodSize, -1), Block.generateYPosFood(foodSize, -1) , foodSize, 0, 0); //The food item, also of type block

    //Constructor
    GameDrawingPanel(){

        setPreferredSize(new Dimension(Board.BOARD_WIDTH, Board.BOARD_HEIGHT));
        setDoubleBuffered(true);

        //Start from center/bottom screen
        int startingXPos = Board.BOARD_WIDTH / 2;
        int startingYPos = Board.BOARD_HEIGHT - 20;

        //Add the initial block
        snake.appendBlock(new Block(startingXPos, startingYPos, 20, 0, -Block.BASE_VELOCITY));
    }

    @Override
    public void paintComponent(Graphics g){

        Graphics2D graphicSettings = (Graphics2D)g;

        //The rendering rules
        graphicSettings.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //The color my snake will be drawn at
        graphicSettings.setPaint(Color.RED);

        //Draw the food
        graphicSettings.fillRect(food.getULeftXPos(), food.getULeftYPos(), food.getSideLength(), food.getSideLength());

        graphicSettings.setPaint(Color.WHITE);

        //Loop through the linkedList to draw the blocks
        for(LinkedList.Node ptr = snake.getHead(); ptr != null; ptr = ptr.getNext()){

            graphicSettings.draw(ptr.getBlock());

            if(Board.lastPressedKeyCode == 87){

                int tempYVelocity = ptr.getBlock().getYVelocity();

                if(tempYVelocity == 0) {
                    ptr.getBlock().setYVelocity(-Block.BASE_VELOCITY);
                    ptr.getBlock().setXVelocity(0);
                }

            }
            if(Board.lastPressedKeyCode == 83){

                int tempYVelocity = ptr.getBlock().getYVelocity();

                if(tempYVelocity == 0) {
                    ptr.getBlock().setYVelocity(Block.BASE_VELOCITY);
                    ptr.getBlock().setXVelocity(0);
                }

            }
            if(Board.lastPressedKeyCode == 65){

                int tempXVelocity = ptr.getBlock().getXVelocity();

                if(tempXVelocity == 0) {
                    ptr.getBlock().setXVelocity(-Block.BASE_VELOCITY);
                    ptr.getBlock().setYVelocity(0);
                }

            }
            if(Board.lastPressedKeyCode == 68){

                int tempXVelocity = ptr.getBlock().getXVelocity();

                if(tempXVelocity == 0) {
                    ptr.getBlock().setXVelocity(+Block.BASE_VELOCITY);
                    ptr.getBlock().setYVelocity(0);
                }

            }

            ptr.getBlock().move();
        }

        //Check to see if the snake eats the food
        //We only have to check the 'Head' of the snake for collision with the food block
        if(Block.didEat(snake.getFirst(), food)){
            food.setULeftXPos(Block.generateXPosFood(foodSize, snake.getFirst().getULeftXPos()));
            food.setULeftYPos(Block.generateYPosFood(foodSize, snake.getFirst().getULeftYPos()));
        }
    }
}
