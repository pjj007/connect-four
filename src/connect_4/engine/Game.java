/**
 *
 */
package connect_4.engine;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * @author Paul J Jensen 1073380
 *
 */
public class Game implements State {

	private char [][] board;
	private boolean winnerAllDirection = false;
	private boolean draw = false;

	public final static int ROWS = 6;
	public final static int COLS = 7;
	public final static char EMPTY = '.';
	public final static char DISK0 = 'X';
	public final static char DISK1 = 'O';
	public final static char [] DISKS = {DISK0, DISK1};

	/**
	 * Constructs a game in the initial state.
	 *
	 * Constructs a game given a board.
	 *
	 */
	public Game() {
		char [][] initBoard = new char [ROWS][COLS];
		for (int j = 0; j < ROWS; j++) {
			for(int k = 0; k < COLS; k++) {
				initBoard[j][k] = '.';
			}
		}
		board = initBoard;
	}

	/**
	 * Checks to see if board is already implemented.
	 *
	 * @return board.
	 */
	public char [][] getBoard() {
		return board;
	}

	/**
	 * Attempts to drop the disks in the column.
	 *
	 * @param col the column to get the new checker
	 *
	 * @return true if there is a disk placed in the right column
	 */
	public boolean canPlaceCoin(int column, int player) {
		int r = 0;
		while (board[r][column-1] != EMPTY) {
			r++;
			if (r == ROWS) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Attempts to drop the disks in the column.
	 *
	 * @param col the column to get the new checker
	 *
	 * @return true if there is a disk placed in the right column
	 */
	public int placeCoin(int column, int player) {
		int r = 0;
		while (board[r][column-1] != EMPTY) {
			r++;
			if (r == ROWS) {
				return -1;
			}
		}
		board[r][column-1] = DISKS[player];
		return 5-r;
	}

	/**
	 * Get the score of the board.
	 *
	 * Winnings can be calculated if there are four disks in any of the column.
	 *
	 * @return winnerAllDirection
	 */
	public boolean winnerAllDirection() {
		//Check for a horizontal win.
		for (int r = 0; r < 6; r++) { //every row
			for (int c = 0; c < 4; c++) {
				if (board [r][c] != EMPTY && board [r][c] != -1 &&
						board [r][c] == board [r][c+1] &&
						board [r][c] == board [r][c+2] &&
						board [r][c] == board [r][c+3]) {
					winnerAllDirection = true;
				}
			}
		}
		// Check for a vertical win.
		for (int r = 0; r < 3; r++) { //every row
			for (int c = 0; c < 7; c++) {
				if (board [r][c] != EMPTY && board[r][c] != -1 &&
						board [r][c] == board [r+1][c] &&
						board [r][c] == board [r+2][c] &&
						board [r][c] == board [r+3][c]) {
					winnerAllDirection = true;
				}

			}
		}
		// Check for a diagonal win positive slope,
		// top-right to bottom-left.
		for (int r = 0; r < 3; r++) { //every row
			for (int c = 0; c < 4; c++) {
				if (board [r][c] != EMPTY && board[r][c] != -1 &&
						board [r][c] == board [r+1][c+1] &&
						board [r][c] == board [r+2][c+2] &&
						board [r][c] == board [r+3][c+3]) {
					winnerAllDirection = true;
				}
			}
		}
		// Check for a diagonal win negative slope,
		// top-left to bottom-right.
		for (int r = 3; r < 6; r++) { //every row
			for (int c = 0; c < 4; c++) {
				if (board [r][c] != EMPTY && board [r][c] != -1 &&
						board [r][c] == board [r-1][c+1] &&
						board [r][c] == board [r-2][c+2] &&
						board [r][c] == board [r-3][c+3]) {
					winnerAllDirection = true;
				}
			}
		}
		return winnerAllDirection;
	}

	/**
	 * Get the score of the board.
	 *
	 * Winnings can be calculated if there are four disks in any of the rows and columns (diagonally).
	 *
	 * @return draw to pass the variable to getDraw() method to check if there is no winner.
	 */
	public boolean getWinner() {
		draw = false;
		boolean winner = winnerAllDirection();
		if (winner == true) return true;

		// Check if there are empty positions
		draw = getDraw();
		return draw;
	}
	/**
	 * Check if columns are full.
	 *
	 * @return draw if game is over.
	 */
	public boolean getDraw(){

		for (int i = 0; i < board.length; ++i)
			for (int j = 0; j < board[i].length; j++)
				if (board[i][j] == EMPTY) return false;
		draw = true;
		return draw;
	}
	/**
	 * Save a current game to a .txt file.
	 *
	 * The game is converted from '.', 'X', 'O' into 0's and 1's.
	 *
	 * It can save the game by typing the word 'save'.
	 *
	 * Automatically saves the game once the game is over.
	 *
	 * @param fileName
	 *
	 * @throws FileNotFoundException
	 */
	public void saveTo(String fileName) throws FileNotFoundException  {
		PrintWriter out = new PrintWriter(fileName);
		String eol = System.getProperty("line.separator");
		String tempBoard = "";
		for (int r = 0; r < ROWS; r++){
			for (int c = 0; c < COLS; c++){
				if (board[r][c] == DISK0){
					tempBoard = tempBoard + 1 + " ";
				} else if (board[r][c] == DISK1){
					tempBoard = tempBoard + 2 + " ";
				} if (board[r][c] == EMPTY){
					tempBoard = tempBoard + 0 + " ";
				}
			}
			tempBoard = tempBoard + eol;
		}
		out.println(tempBoard);
		out.close();
	}

	/**
	 * Load a game from a .txt input that converts 0's and 1's into '.', 'X', 'O'.
	 *
	 * It can load the game by typing the word 'load'.
	 *
	 * @param fileName
	 *
	 * @throws IOException
	 */
	public void loadFrom(String fileName) throws IOException {
		Scanner input = new Scanner(new File(fileName));
		// do this 42 times
		input.nextInt();
		//board = input.nextInt();
		input.close();
	}

}
