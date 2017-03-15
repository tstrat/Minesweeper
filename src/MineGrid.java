import java.util.Arrays;

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
        board = new int[testBoard.length][];
        for (int i = 0; i < board.length; i++) {
            board[i] = Arrays.copyOf(testBoard[i], testBoard[i].length);
        }
    }

    public int[][] getBoard() {
        if (board == null) {
            return board;
        }
        int[][] copyOfBoard;
        copyOfBoard = new int[board.length][];
        for (int i = 0; i < board.length; i++) {
            copyOfBoard[i] = Arrays.copyOf(board[i], board[i].length);
        }
        return copyOfBoard;
    }
}
