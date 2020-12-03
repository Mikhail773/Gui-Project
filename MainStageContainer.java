/*
 * The main stage for the GUI applications. All scenes are inside this
 * one stage only.
 * 
 */

// specify the package
package Listing;

// system imports
import javafx.stage.Stage;

//==============================================================
public class MainStageContainer {
	// data members

	private static Stage myInstance = null;

	// class constructor
	// ----------------------------------------------------------
	private MainStageContainer() {
	}

	// ----------------------------------------------------------
	public static Stage getInstance() {
		return myInstance;
	}

	/*
	 * Sets the stage with a specific title
	 * @param st : Stage to be set
	 * @param title: title to be displayed
	 */
	
	// -----------------------------------------------------------
	public static void setStage(Stage st, String title) {
		myInstance = st;
		myInstance.setTitle(title);
		myInstance.setResizable(false);
	}
}