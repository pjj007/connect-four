/**
 *
 */
package connect_4.engine;

/**
 * @author Paul J Jensen 1073380
 *
 */
public interface State {

	/**
	 * Gets a 2D array representing the board.
	 * The bottom of the board is row 0 and the top is row ROWS-1.
	 * The left side of the board is column 0 and the right side is column COLS-1.
	 * @return the board.
	 */
	public char [][] getBoard();


}
