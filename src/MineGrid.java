import java.util.Arrays;

/**
 * Created by travis on 3/13/17.
 */
public class MineGrid {

    public class Cell {

        private boolean visible;
        private boolean flagged;
        private char value;

        public Cell(char value) {
            this.value = value;
            this.visible = false;
            this.flagged = false;
        }

        public void setValue(char newValue) {
            this.value = newValue;
        }

        public void toggleVisible() {
            if (!flagged)
                visible = !visible;
        }

        public void toggleFlagged() {
            flagged = !flagged;
        }

        public char getValue() {
            return value;
        }

        public boolean isVisible() {
            return visible;
        }

        public boolean isFlagged() {
            return flagged;
        }

    }


    private Cell[][] board;  // the array representation of the game board
    private int numBombs = 10;   // the total number of bombs on the board (default 10)
    private int numFlags = 10;  // total flags equals bombs

    // Used for calculating win condition fast
    private int totalTiles;
    private int revealedTiles = 0; // revealed always starts at zero

    public MineGrid() {

    }

    /**
     *  Grid constructor used in testing boards to determine accurate game functionality
     * @param testBoard inserted board used for testing purposes
     */
    public MineGrid(char[][] testBoard) {
        // copy board directly (dont use variable, but copy of it)
        numBombs = 0;
        numFlags = 0;

        board = new Cell[testBoard.length][testBoard[0].length];
        // We can use testBoard[0].length since we know it is a square board

        for (int i = 0; i < board.length; i++) {
            for (int k = 0; k < board[i].length; k++) {
                if (testBoard[i][k] == '*') {
                    numBombs++;  // if bomb, count it
                    numFlags++;
                }
                board[i][k] = new Cell(testBoard[i][k]);
                totalTiles++;
            }
        }
    }

    public Cell[][] getBoard() {
        return board;
    }

    public int getBombCount() {
        return numBombs;
    }

    public int getNumFlags() {
        return numFlags;
    }

    /**
     * A Game is won if the board is entirely revealed except for the bombs.  If any bombs are revealed
     * the game is lost.  If any other cell is NOT revealed, the game is not over.
     * @return winning state
     */
    public boolean hasWon() {
        /*
        I could do a full scan and check for bombs being revealed
        however, that check can be done on the intial click and not
        on the win afterwords.
         */
        return (totalTiles - numBombs) == revealedTiles;
    }

    /**
     * Toggles the current cell to be visible.  If already visible no change.
     * @return whether game was lost on reveal
     */
    public boolean revealTile(int row, int col) {
        Cell current = board[row][col];
        if (current.isVisible() || current.isFlagged()){
            return false;
        }
        current.toggleVisible();
        revealedTiles++;
        if (current.getValue() == '*') {
            return true;  // lost the game
        }
        return false;
    }

    public void flagTile(int row, int col) {
        Cell current = board[row][col];
        if (!current.isVisible() && numFlags > 0) {
            // if it is flagged, get one back otherwise lose one
            if (current.isFlagged()){
                numFlags++;
            }
            else {
                numFlags--;
            }
            current.toggleFlagged();
        }

    }
}
