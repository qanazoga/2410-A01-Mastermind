package mastermindGUI;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

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
	 * Here's where we put all our buttons into boxes, all our boxes into panes, etc.
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
		// TODO: maybe remove this part? I'm not sure if I like this implementation
		HBox row1 = new HBox();
		HBox row2 = new HBox();
		HBox row3 = new HBox();
		HBox row4 = new HBox();
		HBox row5 = new HBox();
		HBox row6 = new HBox();
		HBox row7 = new HBox();
		HBox row8 = new HBox();
		HBox row9 = new HBox();
		HBox row10 = new HBox();
		
		
		// This is the checkmark icon we'll use for the Confirm button later on
		Image checkmark = new Image(getClass().getResourceAsStream("/rsc/checkmark.png"));
		
		// This is where we're going to hold all of our buttons
		HBox btnBox = new HBox(); 
		btnBox.getChildren().addAll(whiteBtn, blueBtn, blackBtn, redBtn, greenBtn,yellowBtn);
			
		// Put everything in our window root
		root.setCenter(rowField);
		root.setBottom(btnBox);
		
		rowField.getChildren().addAll(row1,row2,row3,row4,row5,row6,row7,row8,row9,row10);
		gsm.setCurrentRow(row10); //TODO: this line is for testing too
		
		
		
		// The final part of initializing the UI, setting the scene, and showing the stage.
		stage.setScene(scene);
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args); // Starts our JavaFX application.
	}

}
