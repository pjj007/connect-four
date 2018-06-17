/**
 * 
 */
package connect_4.gui;

import connect_4.engine.Game;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Control;
import javafx.scene.layout.BorderPane;
//import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * @author Paul J Jensen
 * Set a stage variable.
 */
public class GuiXml extends Application{

	public Stage stage;

	/**
	 * Start method to set the stage.
	 * Settings of stage and the display of Java window 
	 */
	@Override
	public void start(Stage stage) throws Exception {

		this.stage = stage;

		FXMLLoader fxmlLoader = new FXMLLoader();

		BorderPane root = (BorderPane)fxmlLoader.load(getClass().getResource("connect_4.fxml").openStream());
		Controller fooController = (Controller) fxmlLoader.getController();


		Scene scene = new Scene(root,600,500);

		stage.setTitle("Connect 4");
		fooController.label.setText("Player 1 turn!");
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}

	/**
	 * Starts the FX toolkit.
	 * Calls start on the FX Application.
	 * @param args
	 */
	public static void main (String[] args) {
		Application.launch(args);


	}
}
