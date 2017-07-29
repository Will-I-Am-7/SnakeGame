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

        //Add the initial block or head of the snake
        blocksArray.add(new Block(startingXPos, startingYPos, Block.BLOCK_SIZE, 0, -Block.BASE_VELOCITY));
    }

    public void move(){

        if(blocksArray.size() > 1){
            for(int i = blocksArray.size() - 1; i > 0; i--){
                blocksArray.get(i).setULeftXPos(blocksArray.get(i - 1).getULeftXPos());
                blocksArray.get(i).setULeftYPos(blocksArray.get(i - 1).getULeftYPos());
            }
        }


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

    public void addBlockToSnake(Block block){
        blocksArray.add(block);
    }

    public ArrayList<Block> getBlocksArray() { return blocksArray;}

}
