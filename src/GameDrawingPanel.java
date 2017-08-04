import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by William Madgwick on 7/29/2017.
 */
class GameDrawingPanel extends JPanel {

    static final int BOARD_WIDTH = 600;
    static final int BOARD_HEIGHT = 400;

    private Snake theSnake = new Snake();
    private ArrayList<Block> arrSnake = theSnake.getBlocksArray();

    //The x and y values for the first food block to display
    private int xFood = (int)(Math.random() * (BOARD_WIDTH - Block.BLOCK_SIZE));
    private int yFood = (int)(Math.random() * 100);

    private Block food = new Block(xFood, yFood, Block.FOOD_SIZE, 0, 0); //The food item, also of type block

    //Instance of the top panel, so I can change the score as the player plays
    private TopPanel pnlScore;

    //Constructor
    GameDrawingPanel(TopPanel pnlScore){

        setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
        setDoubleBuffered(true);
        this.pnlScore = pnlScore;

    }

    @Override
    public void paintComponent(Graphics g){

        Graphics2D graphicSettings = (Graphics2D)g;

        // Draw a black background that is as big as the game board
        graphicSettings.setColor(Color.BLACK);
        graphicSettings.fillRect(0, 0, BOARD_WIDTH, BOARD_HEIGHT);

        //The rendering rules
        graphicSettings.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //The color my food will be drawn at
        graphicSettings.setPaint(new Color(179, 66, 244));

        //Draw the food
        graphicSettings.fillRect(food.getULeftXPos(), food.getULeftYPos(), food.getSideLength(), food.getSideLength());

        graphicSettings.setPaint(Color.WHITE);

        //See if the snake runs into itself
        if(theSnake.collideHeadBody()){

            //Update the game state
            Board.gameState  = GameState.DIED;

            //Pause for 1.5 seconds
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //Reset player score to 0 and update label
            Board.getPlayer().setCurrentScore(0);
            pnlScore.setLabelScore();

            //Make a new snake
            theSnake = new Snake();
            arrSnake = theSnake.getBlocksArray();

            //Reset the food
            xFood = (int)(Math.random() * (BOARD_WIDTH - Block.BLOCK_SIZE));
            yFood = (int)(Math.random() * 100);

            food.setULeftXPos(xFood);
            food.setULeftYPos(yFood);

            //Change the state back to playing
            Board.gameState  = GameState.PLAYING;
        }

        //Loop through the arraylist to draw the snake
        for(int i = 0; i < arrSnake.size(); i++){

            //Make the head green
            if(i == 0){
                graphicSettings.setPaint(Board.THEME_COLOUR);
            }else{
                graphicSettings.setPaint(Color.WHITE);
            }
            graphicSettings.fill(arrSnake.get(i));

        }

        //Check to see if the snake eats the food
        //We only have to check the 'Head' of the snake for collision with the food block
        if(Block.didEat(arrSnake.get(0), food)){

            //Change the score and display on screen
            Board.getPlayer().setCurrentScore(Board.getPlayer().getCurrentScore() + 1);

            //See if the score is a high score, then make it the new high score
            if(Board.getPlayer().getCurrentScore() > Board.getPlayer().getHighScore())
                Board.getPlayer().setHighScore(Board.getPlayer().getCurrentScore());

            pnlScore.setLabelScore();

            //Get the new position for the food
            //We do this by making a new block object and changing the reference from the original food to the new food block
            food = Block.generateFoodPosition(arrSnake);

            //Add the new block
            theSnake.addBlockToSnake();
        }

        theSnake.move();
    }


}
