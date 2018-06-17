/**
 * 
 */
package connect_4.gui;

import connect_4.engine.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
//import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
/**
 * @author Paul J Jensen
 *
 */
public class Controller {

	private Image red = new Image("connect_4/gui/Connect4_Red.png");
	private Image yellow = new Image("connect_4/gui/Connect4_Yellow.png");
	Game game = new Game();
	private int player = 0;
	private boolean isWon = false;
	private boolean isDraw = false;

	@FXML 
	GridPane grid;
	@FXML
	Label label = new Label();
	@FXML
	Button btnSaveGame = new Button();
	@FXML
	TextField textField = new TextField();

	public void setEngine(Game e) {
		this.game = e;
	}

	public void savedPressed(ActionEvent e) {
		label.setText("Game saved!");
	}
	/*
	 * Restart button method.
	 * This method has the functionality of resetting the game when button is clicked.
	 */
	public void restartHandler(ActionEvent e) {
		game = new Game();
		isWon = false;
		isDraw = false;
		
		Node node = grid.getChildren().get(0);
		grid.getChildren().clear();
		grid.getChildren().add(0,node);
	}
	/*
	 * Grid method and main game play method.
	 * Sets the logic of the game, to win, draw
	 */
	@SuppressWarnings("static-access")
	public void gridClicked(MouseEvent e) {
		if (!isWon){
			int col = ((int) e.getX() / 65);
			System.out.println(col);
			player = (player +1) % 2;
			label.setText("Player " + (player+1) + " turn!");
			System.out.println("Player " + (player+1) + " turn!");
			if (this.game.canPlaceCoin(col+1, player)){
				int row = this.game.placeCoin(col+1, player);
				ImageView view = null;
				if (player == 0){
					view = new ImageView(red);
				} else {
					view = new ImageView(yellow);
				}
				grid.setRowIndex(view, row);
				grid.setColumnIndex(view, col);
				grid.getChildren().add(view);
			} 
			if (this.game.getWinner()){
				// say the current player is the winner
				isWon = true;
				label.setText("Player " + (player + 1) + " wins!");
			}
			if (this.game.getDraw()){
				// say the players get draw
				isDraw = true;
				label.setText("It is a draw!");
			}
		}
	}


}
