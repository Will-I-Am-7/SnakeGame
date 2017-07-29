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

    public void addBlockToSnake(Block block){
        blocksArray.add(block);
    }

    public ArrayList<Block> getBlocksArray() { return blocksArray;}

}
