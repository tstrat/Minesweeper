import java.util.Arrays;

/**
 * Created by travis on 3/13/17.
 */
public class MineGrid {

    public class Cell {

        private boolean visible;
        private char value;

        public Cell(char value) {
            this.value = value;
            visible = false;
        }

        public void setValue(char newValue) {
            this.value = newValue;
        }

        public void toggleVisible() {
            visible = !visible;
        }

        public char getValue() {
            return value;
        }

        public boolean isVisible() {
            return visible;
        }

    }


    private Cell[][] board;  // the array representation of the game board
    private int numBombs = 10;   // the total number of bombs on the board (default 10)

    public MineGrid() {

    }

    /**
     *  Grid constructor used in testing boards to determine accurate game functionality
     * @param testBoard inserted board used for testing purposes
     */
    public MineGrid(char[][] testBoard) {
        // copy board directly (dont use variable, but copy of it)
        numBombs = 0;
        board = new Cell[testBoard.length][testBoard[0].length];
        // We can use testBoard[0].length since we know it is a square board

        for (int i = 0; i < board.length; i++) {
            for (int k = 0; k < board[i].length; k++) {
                if (testBoard[i][k] == '*') {
                    numBombs++;  // if bomb, count it
                }
                board[i][k] = new Cell(testBoard[i][k]);
            }
        }
    }

    public Cell[][] getBoard() {
        return board;
    }

    public int getBombCount() {
        return numBombs;
    }
}
