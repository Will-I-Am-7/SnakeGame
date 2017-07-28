/**
 * Created by William Madgwick on 7/28/2017.
 */
import java.awt.Rectangle;

public class Block extends Rectangle
{
    private int xVelocity;
    private int yVelocity;
    private int startXPos;
    private int startYPos;

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

    public boolean didCollideWBlock(){
        return false;
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
