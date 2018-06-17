/**
 *
 */
package connect_4.textui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import connect_4.engine.Game;
import connect_4.engine.State;
import connect_4.engine.View;


/**
 * @author Paul J Jensen 1073380
 *
 */
public class Connect4TextUI implements View {


	/**
	 * Display the current board.
	 *
	 * @param state current state of the game.
	 */
	public void display (State state) {
		char[][] board = state.getBoard();
		System.out.println();
		for (int r = Game.ROWS-1; r >= 0; r--) {
			System.out.println(board[r][0] + " " + board[r][1] + " " + board[r][2] + " " + board[r][3] + " "
					+ board[r][4] + " " + board[r][5] + " " + board[r][6]);
		}
	}
	/**
	 * Plays a single game of Connect 4, using a textual user interface.
	 *
	 * @param args ignored.
	 */
	public static void main(String args[]) {
		View view= new Connect4TextUI();
		Game state = new Game();
		char[][] board = state.getBoard();
		view.display(state);
		Scanner input = new Scanner(System.in);
		int player = 0;
		while (!state.getWinner()) {
			System.out.println("Player " + (player+1) + " turn!");
			System.out.print("Choose column (1-7) for a disk: ");
			String s = input.next();
			if (s.startsWith("save")) {
				// exception handling to save the game into a .txt file
				try {
					state.saveTo("game.txt");
					System.out.println("game saved to game.txt");
				} catch (FileNotFoundException e ) {
					System.err.println("Oops. Sorry, could not save game: " + e.getLocalizedMessage());
				}
			} else if (s.startsWith("load")) {
				// exception handling to load the game from a .txt file
				try {
					state.loadFrom("game.txt");
				} catch (IOException q) {
					System.err.println("Oops. Sorry, could not load game: " + q.getLocalizedMessage());
				}

			} else if (s.startsWith("play")) {
				//Read the position of turn and check if value is correct.
				int column = input.nextInt();
				input.nextLine();
				if (column < 1 || column > 7) {
					System.out.println("Column should be from 1 to 7");
					continue;
				}

				while ((column < 1) || (column > 7) || (board[5][column-1] != '.')) {
					System.out.println("Board is full.");
					System.out.println("Player " + (player+1) + " select another column:");
					view.display(state);
					column = input.nextInt();
				}

				@SuppressWarnings("unused")
				boolean playerMove = state.canPlaceCoin(column, player);

				player = (player +1) % 2;
				view.display(state);
				continue;
			} else {
				// error message for unknown command
				System.out.println("Type ' play ' and column number to play. "
						+ "\n Type ' save ' to save game into  a text file. "
						+ "\n Type ' load ' to load game from a previous save.");
			}
		}
		// to check if there is a draw
		// prints out player wins message or it is a draw message
		boolean isDraw = state.getDraw();
		player = (player +1) % 2;
		if (!isDraw){
			System.out.println("Congratulation Player " + (player+1) + " wins!");
		} else {
			System.out.println("It is a draw!");
			input.close();
		}

	}

}
