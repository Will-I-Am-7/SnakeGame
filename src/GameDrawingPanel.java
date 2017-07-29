import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by William Madgwick on 7/29/2017.
 */
class GameDrawingPanel extends JPanel {

    private Snake theSnake = new Snake();
    private ArrayList<Block> arrSnake = theSnake.getBlocksArray();
    private Block food = new Block(Block.generateXPosFood(Block.FOOD_SIZE, -1), Block.generateYPosFood(Block.FOOD_SIZE, -1) , Block.FOOD_SIZE, 0, 0); //The food item, also of type block

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

        //Loop through the linkedList to draw the blocks
        for(int i = 0; i < arrSnake.size(); i++){

            if(i == 0){
                graphicSettings.setPaint(Color.GREEN);
            }
            graphicSettings.draw(arrSnake.get(i));

        }

        //Check to see if the snake eats the food
        //We only have to check the 'Head' of the snake for collision with the food block
        if(Block.didEat(arrSnake.get(0), food)){
            food.setULeftXPos(Block.generateXPosFood(Block.FOOD_SIZE, arrSnake.get(0).getULeftXPos()));
            food.setULeftYPos(Block.generateYPosFood(Block.FOOD_SIZE, arrSnake.get(0).getULeftYPos()));

            //Add the new block
            addBlockToSnake();
        }

        theSnake.move();
    }

    //Will create and add a new to block to the linked list
    private void addBlockToSnake(){

        int startX = arrSnake.get(arrSnake.size() - 1).getULeftXPos();
        int startY = arrSnake.get(arrSnake.size() - 1).getULeftYPos();

        int startXVel = arrSnake.get(arrSnake.size() - 1).getXVelocity();
        int startYVel = arrSnake.get(arrSnake.size() - 1).getYVelocity();

        //See in what direction the snake is moving
        //To know where I should add the block
        if(startYVel < 0){
            startY+=Block.BLOCK_SIZE;
        }
        else if(startYVel > 0){
            startY-=Block.BLOCK_SIZE;
        }
        if(startXVel < 0){
            startX+=Block.BLOCK_SIZE;
        }
        else if(startXVel > 0){
            startX-=Block.BLOCK_SIZE;
        }

        Block tailBlock = new Block(startX, startY, Block.BLOCK_SIZE, startXVel, startYVel);
        theSnake.addBlockToSnake(tailBlock);
    }
}
