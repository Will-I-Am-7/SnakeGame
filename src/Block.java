/**
 * Created by William Madgwick on 7/28/2017.
 */
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

class Block extends Rectangle
{
    private int xVelocity;
    private int yVelocity;
    private int startXPos;
    private int startYPos;

    //The base X and Y velocity that will not change
    static final int BASE_VELOCITY = 20;
    static final int BLOCK_SIZE = 20;
    static final int FOOD_SIZE = 20;


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

    void move(){

        super.x += xVelocity;
        super.y += yVelocity;

        //Check for collision with the walls, X
        if((super.x + super.width) > GameDrawingPanel.BOARD_WIDTH)
            super.x = 0;
        if(super.x < 0)
            super.x = GameDrawingPanel.BOARD_WIDTH - BLOCK_SIZE;


        //Check for collision with the walls, Y
        if((super.y + super.height) > GameDrawingPanel.BOARD_HEIGHT)
            super.y = 0;
        if(super.y < 0)
            super.y = GameDrawingPanel.BOARD_HEIGHT - BLOCK_SIZE;

    }

    //This method will generate a xPos/yPos that is on the screen for the food
    //Will also make sure that it does not touch the body of the snake
    static Block generateFoodPosition(ArrayList<Block> listToTest){

        Random randNumGenerator = new Random();
        boolean foodInterSnake = true; //Otherwise we might skip the first iteration of the loop

        int theXPos = 0;
        int theYPos = 0;

        //Make sure the x and y position are on the screen
        while( ((theXPos + FOOD_SIZE) > GameDrawingPanel.BOARD_WIDTH) || ((theYPos + FOOD_SIZE) > GameDrawingPanel.BOARD_HEIGHT) || foodInterSnake ){

            theXPos = randNumGenerator.nextInt(GameDrawingPanel.BOARD_WIDTH);
            theYPos = randNumGenerator.nextInt(GameDrawingPanel.BOARD_HEIGHT);

            Rectangle tempFood = new Rectangle(theXPos, theYPos, FOOD_SIZE, FOOD_SIZE);

            //See if the food intersects with any of the body of the snake
            for(Block ptr : listToTest){

                if(tempFood.intersects(ptr)){
                    foodInterSnake = true;
                    break;
                }
                else{
                    foodInterSnake = false;
                }

            }

        }

        return new Block(theXPos, theYPos, FOOD_SIZE, 0, 0);
    }


    //Checks if a Snake block collided with the Food block
    static boolean didEat(Block snakeBlock, Block foodBlock){
        return snakeBlock.intersects(foodBlock);
    }

    //All the getter and setter methods
    int getULeftXPos() { return super.x; }

    void setULeftXPos(int uLeftXPos) { super.x = uLeftXPos; }

    int getULeftYPos() { return super.y; }

    void setULeftYPos(int uLeftYPos) { this.y = uLeftYPos; }

    int getXVelocity() { return xVelocity; }

    void setXVelocity(int xVelocity) { this.xVelocity = xVelocity; }

    int getYVelocity() { return yVelocity; }

    void setYVelocity(int yVelocity) { this.yVelocity = yVelocity; }

    int getSideLength() { return super.width; }

    public void setSideLength(int sideLength) { this.width = sideLength; this.height = sideLength;}

    public int getStartXPos() { return startXPos; }

    public void setStartXPos(int startXPos) { this.startXPos = startXPos; }

    public int getStartYPos() { return startYPos; }

    public void setStartYPos(int startYPos) { this.startYPos = startYPos; }
}
