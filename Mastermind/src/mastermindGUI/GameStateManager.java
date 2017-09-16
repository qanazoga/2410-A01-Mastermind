package mastermindGUI;


import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * The Game State Manager for Mastermind.
 * Holds a lot of useful information.
 * There should only ever be one, so I'm gonna use the singleton design pattern here (I think it's neat).
 * 
 * @author Jerrad Sroufe
 *
 */
public class GameStateManager {
	public static GameStateManager instance = null;
	
	public boolean gameOver = false;
	public int turn = 0;
	public int placedPins = 0;
	HBox[] rows = new HBox[10];
	HBox currentRow = null;
	Image checkmark = new Image(getClass().getResourceAsStream("/rsc/checkmark.png"));
	
	/**
	 * Constructor.
	 * Now nobody can accidentally make a GameStateManager.
	 */
	private GameStateManager() {
		
	}
	
	/**
	 * Use this instead of the constructor anywhere outside of this class.
	 * 
	 * @return instance		Our one and only instantiated copy of the GameStateManager throughout the entire program.
	 */
	public static GameStateManager getInstance() {
		if (instance == null) {
			instance = new GameStateManager();
		}
		return instance;
	}

	/**
	 * Places a confirm button at the end of the current row.
	 */
	public void placeConfirmButton() {
		Button confirm = new Button();
		confirm.setPrefSize(50, 50);
		confirm.setGraphic(new ImageView(checkmark));
		confirm.setOnMouseClicked(e -> {
			//TODO: write this part. The button should destroy itself and then return the black and white pins.
			for (int i = 0; i < rows.length; i++) {
				if (rows[i] == currentRow) {
					if (i == rows.length) {
						System.out.println("Game over"); //TODO: Make better interface for this
					} else {
						System.out.println(i);
						this.currentRow = rows[i+1];
					}
				}
			}
		}); 
		currentRow.getChildren().add(confirm);
	}
	
	public void setCurrentRow(HBox row) {
		this.currentRow = row;
	}
}
