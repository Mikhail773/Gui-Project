/*
 * Methods to show messages to the user
 * Warns user of errors and other necessary information
 */

// specify the package
package Listing;

// system imports
import javafx.scene.paint.Color;
import javafx.scene.text.*;

//==============================================================
public class MessageView extends Text {
	// Class constructor
	// ----------------------------------------------------------
	public MessageView(String initialMessage) {
		super(initialMessage);
		setFont(Font.font("Helvetica", FontWeight.BOLD, 16));
		setFill(Color.BLUE);
		setTextAlignment(TextAlignment.LEFT);
	}

	/**
	 * Display ordinary message
	 * @param message : String to display
	 */
	// ----------------------------------------------------------
	public void displayMessage(String message) {
		// display the passed text in blue
		setFill(Color.BLUE);
		setText(message);
	}

	/**
	 * Display error message (errors are typically shown in red)
	 * @param message : String to display
	 */
	// ----------------------------------------------------------
	public void displayErrorMessage(String message) {
		// display the passed text in red
		setFill(Color.RED);
		setText(message);
	}

	/**
	 * Clear error message
	 */
	// ----------------------------------------------------------
	public void clearErrorMessage() {
		setText("                           ");
	}
}
