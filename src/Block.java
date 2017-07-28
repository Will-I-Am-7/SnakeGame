/**
 * Created by William Madgwick on 7/28/2017.
 */
import java.awt.Rectangle;

public class Block extends Rectangle
{
    private int uLeftXPos;
    private int uLeftYPos;
    private int xVelocity;
    private int yVelocity;
    private int sideLength;
    private int startXPos;
    private int startYPos;

    //Constructor, all initial values will be sent over
    public Block(int startXPos, int startYPos, int sideLength, int xVelocity, int yVelocity){

        //Call the constructor from Rectangle
        super(startXPos, startYPos, sideLength, sideLength);

        this.startXPos = startXPos;
        this.startYPos = startYPos;

        this.sideLength = sideLength;

        //For now these will have the same values
        uLeftXPos = startXPos;
        uLeftYPos = startYPos;

        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;

    }

    public void move(){

    }

    public boolean didCollide(){
        return false;
    }

    //All the getter and setter methods
    public int getULeftXPos() { return uLeftXPos; }

    public void setULeftXPos(int uLeftXPos) { this.uLeftXPos = uLeftXPos; }

    public int getULeftYPos() { return uLeftYPos; }

    public void setULeftYPos(int uLeftYPos) { this.uLeftYPos = uLeftYPos; }

    public int getXVelocity() { return xVelocity; }

    public void setXVelocity(int xVelocity) { this.xVelocity = xVelocity; }

    public int getYVelocity() { return yVelocity; }

    public void setYVelocity(int yVelocity) { this.yVelocity = yVelocity; }

    public int getSideLength() { return sideLength; }

    public void setSideLength(int sideLength) { this.sideLength = sideLength; }

    public int getStartXPos() { return startXPos; }

    public void setStartXPos(int startXPos) { this.startXPos = startXPos; }

    public int getStartYPos() { return startYPos; }

    public void setStartYPos(int startYPos) { this.startYPos = startYPos; }
}
