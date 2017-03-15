
import org.hamcrest.core.*;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by travis on 3/13/17.
 */

public class MineGridTest {

    private char[][] getTestBoard() {
        return new char[][] {{' ', ' ', ' ', ' ', ' '},
                            {' ', '1', '1', '1', ' '},
                            {' ', '1', '*', '1', ' '}, // 1 bomb in mid of 5x5
                            {' ', '1', '1', '1', ' '},
                            {' ', ' ', ' ', ' ', ' '}};
    }

    /* -------------------------------
        CONSTRUCTOR/INITIATION TESTS
       ------------------------------- */
    @Test
    public void testConstructorWithGivenBoard() {
        char[][] testBoard = getTestBoard();
        MineGrid m = new MineGrid(testBoard);

        assertEquals(testBoard.length, m.getBoard().length);  // 5 long
        assertEquals(testBoard[0].length, m.getBoard()[0].length); // 5 wide
        assertFalse(testBoard.equals(m.getBoard())); // not the same board

        //Verify that all the elements of the board are equal
        for (int i = 0; i < testBoard.length; i++) {
            for (int k = 0; k < testBoard[i].length; k++) {
                assertEquals(m.getBoard()[i][k].getValue(), testBoard[i][k]);
            }
        }
    }

    @Test
    public void testBombCountInGivenBoard() {
        char[][] testBoard = getTestBoard();
        MineGrid m = new MineGrid(testBoard);

        assertEquals(1, m.getBombCount());
    }

    @Test
    public void testAllCellsInvisibleAtStart() {
        char[][] testBoard = getTestBoard();
        MineGrid m = new MineGrid(testBoard);
        MineGrid.Cell[][] board = m.getBoard();

        for (int i = 0; i < board.length; i++) {
            for (int k = 0; k < board[i].length; k++) {
                assertFalse(board[i][k].isVisible());
            }
        }

    }
}
