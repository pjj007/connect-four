/**
 * 
 */
package connect_4.engine;

import java.util.Scanner;

import connect_4.textui.Connect4TextUI;

/**
 * @author pauljjensen
 *
 */
public class Main {

	public static void main(String args[]) {
		//Scanner input = new Scanner(System.in);
		View view= new Connect4TextUI();
		Game state = new Game();
		view.display(state);





	}
}
