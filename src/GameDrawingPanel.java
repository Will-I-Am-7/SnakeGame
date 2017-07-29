import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by William Madgwick on 7/29/2017.
 */
class GameDrawingPanel extends JPanel {

    private int foodSize = 15;
    private int snakeBlockSize = 20;

    private ArrayList<Block> snake = new ArrayList<Block>(); //List of blocks that will make a snake
    private Block food = new Block(Block.generateXPosFood(foodSize, -1), Block.generateYPosFood(foodSize, -1) , foodSize, 0, 0); //The food item, also of type block

    //Constructor
    GameDrawingPanel(){

        setPreferredSize(new Dimension(Board.BOARD_WIDTH, Board.BOARD_HEIGHT));
        setDoubleBuffered(true);

        //Start from center/bottom screen
        int startingXPos = Board.BOARD_WIDTH / 2;
        int startingYPos = Board.BOARD_HEIGHT - snakeBlockSize;

        //Add the initial block
        snake.add(new Block(startingXPos, startingYPos, snakeBlockSize, 0, -Block.BASE_VELOCITY));
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
        for(Block iterator : snake){

            graphicSettings.draw(iterator);

            if(Board.lastPressedKeyCode == 87){

                int tempYVelocity = iterator.getYVelocity();

                if(tempYVelocity == 0) {
                    iterator.setYVelocity(-Block.BASE_VELOCITY);
                    iterator.setXVelocity(0);
                }

            }
            if(Board.lastPressedKeyCode == 83){

                int tempYVelocity = iterator.getYVelocity();

                if(tempYVelocity == 0) {
                    iterator.setYVelocity(Block.BASE_VELOCITY);
                    iterator.setXVelocity(0);
                }

            }
            if(Board.lastPressedKeyCode == 65){

                int tempXVelocity = iterator.getXVelocity();

                if(tempXVelocity == 0) {
                    iterator.setXVelocity(-Block.BASE_VELOCITY);
                    iterator.setYVelocity(0);
                }

            }
            if(Board.lastPressedKeyCode == 68){

                int tempXVelocity = iterator.getXVelocity();

                if(tempXVelocity == 0) {
                    iterator.setXVelocity(+Block.BASE_VELOCITY);
                    iterator.setYVelocity(0);
                }

            }

            iterator.move();
        }

        //Check to see if the snake eats the food
        //We only have to check the 'Head' of the snake for collision with the food block
        if(Block.didEat(snake.get(0), food)){
            food.setULeftXPos(Block.generateXPosFood(foodSize, snake.get(0).getULeftXPos()));
            food.setULeftYPos(Block.generateYPosFood(foodSize, snake.get(0).getULeftYPos()));

            //Add the new block
            addBlockToSnake();
        }
    }

    //Will create and add a new to block to the linked list
    private void addBlockToSnake(){

        int startX = snake.get(snake.size() - 1).getULeftXPos();
        int startY = snake.get(snake.size() - 1).getULeftYPos();

        int startXVel = snake.get(snake.size() - 1).getXVelocity();
        int startYVel = snake.get(snake.size() - 1).getYVelocity();

        //See in what direction the snake is moving
        //To know where I should add the block
        if(startYVel < 0){
            startY+=snakeBlockSize;
        }
        else if(startYVel > 0){
            startY-=snakeBlockSize;
        }
        if(startXVel < 0){
            startX+=snakeBlockSize;
        }
        else if(startXVel > 0){
            startX-=snakeBlockSize;
        }

        Block tailBlock = new Block(startX, startY, snakeBlockSize, startXVel, startYVel);
        snake.add(tailBlock);
    }
}
