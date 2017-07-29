/**
 * Created by William Madgwick on 7/28/2017.
 */
import java.awt.Rectangle;
import java.util.Random;

public class Block extends Rectangle
{
    private int xVelocity;
    private int yVelocity;
    private int startXPos;
    private int startYPos;

    //The base X and Y velocity that will not change
    public static final int BASE_VELOCITY = 5;
    public static final int BLOCK_SIZE = 20;
    public static final int FOOD_SIZE = 15;

    private static Random randNumGenerator;

    //Constructor, all initial values will be sent over
    public Block(int startXPos, int startYPos, int sideLength, int xVelocity, int yVelocity){

        //Call the constructor from Rectangle
        super(startXPos, startYPos, sideLength, sideLength);

        this.startXPos = startXPos;
        this.startYPos = startYPos;

        //For now these will have the same values

        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;

    }

    public void move(){

        super.x += xVelocity;
        super.y += yVelocity;

        //Check for collision with the walls, X
        if(super.x < 0 || (super.x + super.width) > Board.BOARD_WIDTH){
            super.x = 0;
        }

        //Check for collision with the walls, Y
        if(super.y < 0|| (super.y + super.height) > Board.BOARD_HEIGHT){
            super.y = 0;
        }

    }

    //This method will generate a xPos that is on the screen for the food
    public static int generateXPosFood(int blockSize, int currentSnakeX){

        randNumGenerator = new Random();

        int theXPos = randNumGenerator.nextInt(Board.BOARD_WIDTH);

        while((theXPos + blockSize) > Board.BOARD_WIDTH || theXPos == currentSnakeX){
            theXPos = randNumGenerator.nextInt(Board.BOARD_WIDTH);
        }

        return theXPos;
    }

    //This method will generate a yPos that is on the screen for the food
    public static int generateYPosFood(int blockSize, int currentSnakeY){

        randNumGenerator = new Random();

        int theYPos = randNumGenerator.nextInt(Board.BOARD_HEIGHT);

        while((theYPos + blockSize) > Board.BOARD_HEIGHT || theYPos == currentSnakeY){
            theYPos = randNumGenerator.nextInt(Board.BOARD_HEIGHT);
        }

        return theYPos;
    }

    //Checks if a Snake block collided with the Food block
    public static boolean didEat(Block snakeBlock, Block foodBlock){
        return snakeBlock.intersects(foodBlock);
    }

    //All the getter and setter methods
    public int getULeftXPos() { return super.x; }

    public void setULeftXPos(int uLeftXPos) { super.x = uLeftXPos; }

    public int getULeftYPos() { return super.y; }

    public void setULeftYPos(int uLeftYPos) { this.y = uLeftYPos; }

    public int getXVelocity() { return xVelocity; }

    public void setXVelocity(int xVelocity) { this.xVelocity = xVelocity; }

    public int getYVelocity() { return yVelocity; }

    public void setYVelocity(int yVelocity) { this.yVelocity = yVelocity; }

    public int getSideLength() { return super.width; }

    public void setSideLength(int sideLength) { this.width = sideLength; this.height = sideLength;}

    public int getStartXPos() { return startXPos; }

    public void setStartXPos(int startXPos) { this.startXPos = startXPos; }

    public int getStartYPos() { return startYPos; }

    public void setStartYPos(int startYPos) { this.startYPos = startYPos; }
}
