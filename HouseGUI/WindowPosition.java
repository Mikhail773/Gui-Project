/*
 * Infrastructure for GUI
 * Provides a means of placing the main stage of the application in the center
 * of the screen
 */

// Specify the package
package Listing;

// system imports
import javafx.geometry.Rectangle2D;
import javafx.stage.Stage;
import javafx.stage.Screen;

//==============================================================
public class WindowPosition {
	private static Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

	/**
	 * Used to place the stage in the center of the screen
	 *
	 * @param s Stage to place at the center of the screen
	 *
	 */
	// ---------------------------------------------------------------
	public static void placeCenter(Stage s) {
		if (s != null) {
			s.centerOnScreen();
		}
	}
}
