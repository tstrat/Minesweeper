/**
 * Created by travis on 3/13/17.
 */
public class MineGrid {

    private int[][] board;  // the array representation of the game board
    private int numBombs;   // the total number of bombs on the board

    public MineGrid() {

    }

    /**
     *  Grid constructor used in testing boards to determine accurate game functionality
     * @param testBoard inserted board used for testing purposes
     */
    public MineGrid(int[][] testBoard) {
        // copy board directly (dont use variable, but copy of it)
        board = testBoard.clone();
    }
}
