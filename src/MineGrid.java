import java.util.Arrays;
import java.util.Random;

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
    private int numBombs;   // the total number of bombs on the board (default 10)
    private int numFlags;  // total flags equals bombs
    private final char BOMB = '*';
    // Used for calculating win condition fast
    private int totalTiles;
    private int revealedTiles = 0; // revealed always starts at zero

    public MineGrid() {
        // Default will be beginner 9x9, 10bombs
        numBombs = 10;
        numFlags = 10;
        createBlankBoard(9,9);
        setBombs(numBombs, 9, 9);
        setCellAdjBombNum();
    }

    // TO-DO: FINISH THIS SETTING CONSTRUCTOR
    public MineGrid(String difficulty) {
        //  initialize game board with default scale size
        //  Microsoft has 3 levels, Beginner:   9x9, 10 bombs
        //                          Intermediate: 16x16, 40 bombs
        //                          Expert:     30x16, 99 bombs
        int rows, cols, bombCnt;
        switch (difficulty) {

            case "intermediate":
                rows = 9;
                cols = 9;
                bombCnt = 10;
                break;
            case "expert":
                rows = 9;
                cols = 9;
                bombCnt = 10;
                break;
            case "beginner":  // use cascading effect to get default
            default:
                rows = 9;
                cols = 9;
                bombCnt = 10;
                break;
        }
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
                if (testBoard[i][k] == BOMB) {
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

    private void createBlankBoard(int rows, int cols) {
        board = new Cell[rows][cols];
        for(int i = 0; i < rows; i++) {
            for (int k = 0; k < cols; k++) {
                board[i][k] = new Cell('.');
            }
        }
    }

    private void setBombs(int bombCnt, int rows, int cols) {
        Random rgen = new Random();
        int r,c;
        while(bombCnt > 0) {
            r = rgen.nextInt(rows);
            c = rgen.nextInt(cols);
            if (board[r][c].getValue() != '*'){
                board[r][c].setValue('*');
                bombCnt--;
            }
        }
    }

    private void setCellAdjBombNum() {
        for(int i = 0; i < board.length; i++) {
            for (int k = 0; k < board[i].length; k++) {
                if (board[i][k].getValue() != BOMB) {
                    board[i][k].setValue(Character.forDigit(getNeighborBombCnt(i, k), 9)); // 8 is max neighbor
                }
            }
        }
    }

    private int getNeighborBombCnt(int r, int c) {
        int cnt = 0;
        for(int i = r - 1; i <= r + 1; i++) {
            for (int k = c - 1; k <= c + 1; k++) {
                if(!(i==r && k == c)) {
                    if (!(i < 0 || i > board.length-1) && !(k < 0 || k > board[i].length-1)){
                        if (board[i][k].getValue() == BOMB) {
                            cnt++;
                        }
                    }
                }
            }
        }
        return cnt;
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
        if (current.getValue() == BOMB) {
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

    public String toString() {
        String str = "Minesweeper:\n";

        for(int i = 0; i < board.length; i++) {
            for (int k = 0; k < board[i].length; k++) {
                str += board[i][k].getValue() + " ";
            }
            str += "\n";
        }

        return str;
    }
}
