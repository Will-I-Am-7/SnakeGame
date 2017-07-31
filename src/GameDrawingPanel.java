import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by William Madgwick on 7/29/2017.
 */
class GameDrawingPanel extends JPanel {

    private Snake theSnake = new Snake();
    private ArrayList<Block> arrSnake = theSnake.getBlocksArray();

    //The x and y values for the first food block to display
    private int xFood = (int)(Math.random() * (Board.BOARD_WIDTH - Block.BLOCK_SIZE));
    private int yFood = (int)(Math.random() * 100);

    private Block food = new Block(xFood, yFood, Block.FOOD_SIZE, 0, 0); //The food item, also of type block

    //Constructor
    GameDrawingPanel(){

        setPreferredSize(new Dimension(Board.BOARD_WIDTH, Board.BOARD_HEIGHT));
        setDoubleBuffered(true);

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

        //See if the snake runs into itself
        if(theSnake.collideHeadBody()){

            //Pause for a few seconds
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //Make a new snake
            theSnake = new Snake();
            arrSnake = theSnake.getBlocksArray();

            //Reset the food
            xFood = (int)(Math.random() * (Board.BOARD_WIDTH - Block.BLOCK_SIZE));
            yFood = (int)(Math.random() * 100);

            food.setULeftXPos(xFood);
            food.setULeftYPos(yFood);
        }

        //Loop through the arraylist to draw the snake
        for(int i = 0; i < arrSnake.size(); i++){

            //Make the head green
            if(i == 0){
                graphicSettings.setPaint(new Color(34,139,34));
            }else{
                graphicSettings.setPaint(Color.WHITE);
            }
            graphicSettings.fill(arrSnake.get(i));

        }

        //Check to see if the snake eats the food
        //We only have to check the 'Head' of the snake for collision with the food block
        if(Block.didEat(arrSnake.get(0), food)){

            //Get the new position for the food
            //We do this by making a new block object and changing the reference from the original food to the new food block
            food = Block.generateFoodPosition(arrSnake);

            //Add the new block
            theSnake.addBlockToSnake();
        }

        theSnake.move();
    }

}
