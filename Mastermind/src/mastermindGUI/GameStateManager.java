package mastermindGUI;


import javafx.scene.Node;
import java.util.Random;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import mastermind.Code;
import mastermind.GameState;

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
	public String[] winningRow = null;
	Image checkmark = new Image(getClass().getResourceAsStream("/rsc/checkmark.png"));
	
	/**
	 * Constructor.
	 * Now nobody can accidentally make a GameStateManager.
	 */
	private GameStateManager() {
		generateWinningRow();
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
	 * Set the starting rows. 
	 * This is important to do early.
	 * 
	 * @param rows		All the rows you want added (10 is the norm).	
	 */
	public void setRows(HBox...rows) {
		this.allRows = rows;
		this.currentRow = rows[0];
	}
	
	private void generateWinningRow() {
		String[] colors = {"white", "blue", "black", "red", "green", "yellow"};
		Random rand = new Random();
		this.winningRow = new String[4];
		for (int i = 0; i < 4; i++) {
			winningRow[i] = colors[rand.nextInt(colors.length)];
		}
		for (int i = 0; i < this.winningRow.length; i++) {
			System.out.println(this.winningRow[i] + " ");
		}
		
	}

	/**
	 * Places a confirm button at the end of the current row.
	 */
	public void placeConfirmButton() {
		Button confirm = new Button();
		confirm.setPrefSize(50, 50);
		confirm.setGraphic(new ImageView(checkmark));
		// Confrim that we can place the confirm button, and then do so.
		if (currentRow.getChildren().size() == 4) {
			currentRow.getChildren().add(confirm);
		}
		confirm.setOnMouseClicked(event -> {
			this.removeButton(null); // Delete the button on click.
			this.placedPins = 0;	// Reset the placed pin counter.
			// TODO: Show pin output.
			compare(currentRow);
			// Move to the next row.
			try {
				for (int i = 0; i < allRows.length; i++) {
					if (allRows[i] == currentRow) {
							this.currentRow = allRows[i+1];
							break;
					}
				}
			} catch (ArrayIndexOutOfBoundsException e) { // This is the worst possible way to do this.
				this.gameOver = true;
				// TODO: Show correct code.
			}
		}); 
	}
	
	
	/**
	 * Removes a pinButton from the game board.
	 * Also removes the confirmation button if it's there (you can't confirm a row with less than 4 buttons).
	 * 
	 * @param pinButton		The pinButton to be removed. (Can be null to allow just removing the confirmation button).
	 */
	public void removeButton(PinButton pinButton) {
		if (currentRow.getChildren().size() == 5) {
			currentRow.getChildren().remove(4);
		}
		if (pinButton != null) {
			currentRow.getChildren().remove(pinButton);	
		}
		this.placedPins--;
	}
		
	private void compare(HBox row) {
		String[] rowClone = new String[row.getChildren().size()];
		String[] winningClone = winningRow;
		int blackPins = 0;
		int whitePins = 0;
		
		for (int i = 0; i < rowClone.length; i++) {
			rowClone[i] = ((PinButton) row.getChildren().get(i)).getColor();
		}
		
		System.out.println("Checking for black pins...");
		for (int i = 0; i < rowClone.length; i++) {
			if (rowClone[i] == winningClone[i]) {
				System.out.println("Found a match, advancing black pins.");
				blackPins++;
				rowClone[i] = null;
				rowClone[i] = null;
			}
		}
		
		System.out.println("Checking for white pins...");
		for (int i = 0; i < rowClone.length; i++) {
			for (int j = 0; j < rowClone.length; j++) {
				if (rowClone[i] == winningClone[j] && rowClone[i] != null) {
					whitePins++;
					rowClone[i] = null;					
				}
			}
		}
		
		System.out.printf("Black pins: %d, White pins: %d", blackPins, whitePins);	
	}
}
