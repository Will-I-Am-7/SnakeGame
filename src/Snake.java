import java.util.ArrayList;

/**
 * Created by William Madgwick on 7/29/2017.
 */
public class Snake{

    private ArrayList<Block> blocksArray = new ArrayList<Block>();

    public Snake(){

        //Start from center/bottom screen
        int startingXPos = Board.BOARD_WIDTH / 2;
        int startingYPos = Board.BOARD_HEIGHT - Block.BLOCK_SIZE;

        //Add the initial block or head of the snake, size 2
        blocksArray.add(new Block(startingXPos - Block.BLOCK_SIZE, startingYPos - Block.BLOCK_SIZE, Block.BLOCK_SIZE, 0, -Block.BASE_VELOCITY));
        blocksArray.add(new Block(startingXPos, startingYPos, Block.BLOCK_SIZE, 0, -Block.BASE_VELOCITY));
    }

    //The movement of the snake as a whole
    //Simple algorithm used. Each previous block just gets the x and y positions from the block ontop of it
    public void move(){

        //The block move up a 'chain'
        if(blocksArray.size() > 1){
            for(int i = blocksArray.size() - 1; i > 0; i--){

                blocksArray.get(i).setULeftXPos(blocksArray.get(i - 1).getULeftXPos());
                blocksArray.get(i).setULeftYPos(blocksArray.get(i - 1).getULeftYPos());

            }
        }


        //Handle te direction that comes from key input
        if(Board.lastPressedKeyCode == 87){

            int tempYVelocity = blocksArray.get(0).getYVelocity();

            if(tempYVelocity == 0) {
                blocksArray.get(0).setYVelocity(-Block.BASE_VELOCITY);
                blocksArray.get(0).setXVelocity(0);
            }

        }
        if(Board.lastPressedKeyCode == 83){

            int tempYVelocity = blocksArray.get(0).getYVelocity();

            if(tempYVelocity == 0) {
                blocksArray.get(0).setYVelocity(Block.BASE_VELOCITY);
                blocksArray.get(0).setXVelocity(0);
            }

        }
        if(Board.lastPressedKeyCode == 65){

            int tempXVelocity = blocksArray.get(0).getXVelocity();

            if(tempXVelocity == 0) {
                blocksArray.get(0).setXVelocity(-Block.BASE_VELOCITY);
                blocksArray.get(0).setYVelocity(0);
            }

        }
        if(Board.lastPressedKeyCode == 68){

            int tempXVelocity = blocksArray.get(0).getXVelocity();

            if(tempXVelocity == 0) {
                blocksArray.get(0).setXVelocity(+Block.BASE_VELOCITY);
                blocksArray.get(0).setYVelocity(0);
            }

        }

        blocksArray.get(0).move();
    }

    //Ads new blocks to the tail of the snake
    public void addBlockToSnake(){

        int startX = blocksArray.get(blocksArray.size() - 1).getULeftXPos();
        int startY = blocksArray.get(blocksArray.size() - 1).getULeftYPos();

        int startXVel = blocksArray.get(blocksArray.size() - 1).getXVelocity();
        int startYVel = blocksArray.get(blocksArray.size() - 1).getYVelocity();

        //See in what direction the snake is moving
        //To know where I should add the block
        if(startYVel < 0){
            startY+=(Block.BLOCK_SIZE);
        }
        else if(startYVel > 0){
            startY-=(Block.BLOCK_SIZE);
        }
        if(startXVel < 0){
            startX+=(Block.BLOCK_SIZE);
        }
        else if(startXVel > 0){
            startX-=(Block.BLOCK_SIZE);
        }

        Block tailBlock = new Block(startX, startY, Block.BLOCK_SIZE, startXVel, startYVel);
        blocksArray.add(tailBlock);
    }

    public ArrayList<Block> getBlocksArray() { return blocksArray;}

}
