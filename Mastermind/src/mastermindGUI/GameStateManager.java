package mastermindGUI;


import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

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
	public HBox[] allRows = null;
	public HBox currentRow = null;
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
		confirm.setOnMouseClicked(event -> {
			this.placedPins = 0;
			try {
				for (int i = 0; i < allRows.length; i++) {
					if (allRows[i] == currentRow) {
							this.currentRow = allRows[i+1];
							break;
					}
				}
			} catch (ArrayIndexOutOfBoundsException e) { // This is the worst possible way to do this
				this.gameOver = true;
				// TODO: Show correct code.
			}
			
		}); 
		if (currentRow.getChildren().size() == 4) {
			currentRow.getChildren().add(confirm);
		}
	}
	
	public void setCurrentRow(HBox row) {
		this.currentRow = row;
	}
	
	public void setRows(HBox...rows) {
		this.allRows = rows;
		this.currentRow = rows[0];
	}

	public void removeButton(PinButton pinButton) {
		HBox parent = (HBox) pinButton.getParent();
		if (parent.getChildren().size() == 5) { // Remove the checkmark button if it exists.
			parent.getChildren().remove(4);
		}
		parent.getChildren().remove(pinButton);
		this.placedPins--;
	}
}
