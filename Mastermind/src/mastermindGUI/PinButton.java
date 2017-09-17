package mastermindGUI;

import javafx.scene.control.Button;

/**
 * Pin/Button combo object.
 * Represents both pins on the board, and the clickable buttons used to place them.
 * 
 * @author Jerrad Sroufe
 */
public class PinButton extends Button {
	
	private String color = "black";
	
	/**
	 * Constructor.
	 * 
	 * Sets a bunch of useful properties; Color, where the button exists, & what to do on click.
	 * If this is a row button, it will delete itself on click.
	 * Otherwise, it will add a row button of the same color to the GameStateManager's current row (as long as the row isn't full).
	 * 
	 * @param color 		The color you want the button to be, "white" is preferred to "#000000".
	 * @param isRowButton	Whether the button is in a row, or starts or one of the control buttons.
	 * @param gsm			The Game State Manager for this game.
	 */
	public PinButton(String color, Boolean isRowButton, GameStateManager gsm) {
		this.color = color;
		this.setStyle("-fx-pref-width: 50; -fx-pref-height: 50; -fx-background-color: " + color + ";");
		
		this.setOnMouseClicked(event -> {
			if (isRowButton) {
				if (this.getParent() == gsm.currentRow) {
					gsm.removeButton(this);	
				}
			} else {
				if (gsm.placedPins < 4) {
					gsm.currentRow.getChildren().add(new PinButton(color, true, gsm));
					gsm.placedPins++;
				}
				if (gsm.placedPins == 4) {
					gsm.placeConfirmButton();
				}
			}
		});
	}

	/**
	 * Returns the color of the pinButton.
	 * @return String color.
	 */
	public String getColor() {
		return color;
	}
}
