package mastermindGUI;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * This is the GUI part of Mastermind GUI.
 * It initializes and launches the JavaFX application.
 * 
 * @author Jerrad Sroufe
 */
public class MastermindGuiMain extends Application {

	// Set the Stage.
	public static Stage stage;
	private GameStateManager gsm = GameStateManager.getInstance();

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Mastermind");
		initUI(stage);
	}
	
	
	/**
	 * This is where we build all of the initial parts of our UI.
	 * Buttons get made, get put into boxes, and shoved around the stage.
	 * A lot of things are unfortunately hard-coded right now, so look around a bit and test before you mess around here too much.
	 * (That said, most of what's here is visual, if you want to change how things work, play with the other classes!)
	 * 
	 * @param stage		This should be the Stage from {@link #start(Stage)}.
	 */
	private void initUI(Stage stage) {		
		BorderPane root = new BorderPane();
		// This is the main pane, most of our nodes will be somehow related to this one
		VBox rowField = new VBox(5); // This is our main window
		root.setPrefSize(360, 640); // Let's get a nice size for our window.
		stage.setResizable(false); // We don't want anyone to change that size we just set.
		root.setStyle("-fx-background-color: #8B4513"); // Give our board a nice wood color.
		Scene scene = new Scene(root);
		
		// Let's make all the buttons.
		// Most people would do this alphabetically, but any decent Magic: the Gathering nerd knows WUBRG,
		// and WUBRG is the correct way to do things!
		// There is a Y tho, hehe.	 
		PinButton whiteBtn 	= new PinButton("white", false, gsm);
		PinButton blueBtn 	= new PinButton("blue", false, gsm);
		PinButton blackBtn 	= new PinButton("black", false, gsm);
		PinButton redBtn 	= new PinButton("red", false, gsm);
		PinButton greenBtn 	= new PinButton("green", false, gsm);
		PinButton yellowBtn = new PinButton("yellow", false, gsm);
		
		// Rows?
		// TODO: maybe remove this part? I'm not sure if I like this implementation, but it really hates being instantiated has HBox[10].
		HBox row1 = new HBox(1);
		HBox row2 = new HBox(1);
		HBox row3 = new HBox(1);
		HBox row4 = new HBox(1);
		HBox row5 = new HBox(1);
		HBox row6 = new HBox(1);
		HBox row7 = new HBox(1);
		HBox row8 = new HBox(1);
		HBox row9 = new HBox(1);
		HBox row10 = new HBox(1);
		
		// This is where we're going to hold all of our buttons
		HBox btnBox = new HBox(1); 
		btnBox.getChildren().addAll(whiteBtn, blueBtn, blackBtn, redBtn, greenBtn,yellowBtn);
			
		// Put everything in our window root
		root.setCenter(rowField);
		root.setBottom(btnBox);
		
		rowField.getChildren().addAll(row1,row2,row3,row4,row5,row6,row7,row8,row9,row10);
		gsm.setRows(row1,row2,row3,row4,row5,row6,row7,row8,row9,row10);
		
		// The final part of initializing the UI, setting the scene, and showing the stage.
		stage.setScene(scene);
		stage.show();
	}
	
	/**
	 * Starts our JavaFX application.
	 * @param args	Any args given on launch. In this program they are ignored.
	 */
	public static void main(String[] args) {
		launch(args); 
	}
}
