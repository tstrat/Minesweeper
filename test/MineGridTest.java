
import org.hamcrest.core.*;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by travis on 3/13/17.
 */

public class MineGridTest {

    private int[][] getTestBoard() {
        return new int[][] {{0, 0, 0, 0, 0},
                            {0, 1, 1, 1, 0},
                            {0, 1, -1, 1, 0}, // 1 bomb in mid of 5x5
                            {0, 1, 1, 1, 0},
                            {0, 0, 0, 0, 0}};
    }
    @Test
    public void testConstructorWithGivenBoard() {
        int[][] testBoard = getTestBoard();
        MineGrid m = new MineGrid(testBoard);

        assertEquals(testBoard.length, m.getBoard().length);  // 5 long
        assertEquals(testBoard[0].length, m.getBoard()[0].length); // 5 wide
        assertFalse(testBoard.equals(m.getBoard())); // not the same board

        //Verify that all the elements of the board are equal
        for (int i = 0; i < testBoard.length; i++) {
            for (int k = 0; k < testBoard[i].length; k++) {
                assertEquals(m.getBoard()[i][k], testBoard[i][k]);
            }
        }
    }
}
