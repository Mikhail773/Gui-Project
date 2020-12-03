/*
 * Class with main method. Launches the stage
 * 
 * @author Mikhail Mikhaylov
 * @version November 1,2020
 */

package Listing;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;

/** The class containing the main program for the Loan application */
//==============================================================
public class ListingsChooser extends Application {
	private Listings myListing;
	
	/** Main frame of the application */
	private Stage mainStage;

	// start method for this class, the main application object
	// ----------------------------------------------------------
	public void start(Stage primaryStage) {
  		// Create the top-level container (main stage) and add
		// contents to it.
		MainStageContainer.setStage(primaryStage, "Real Estate Listings");
		mainStage = MainStageContainer.getInstance();

		// Finish setting up the stage
		// (ENABLE THE GUI TO BE CLOSED USING THE TOP RIGHT 'X' IN THE
		// WINDOW), and show it.
		mainStage.setOnCloseRequest(new EventHandler<javafx.stage.WindowEvent>() {
			@Override
			public void handle(javafx.stage.WindowEvent event) {
				System.exit(0);
			}
		});
		
		try {
			myListing = new Listings();
		} catch (Exception exc) {
			System.err.println("ListingsChooser.ListingsChooser - could not create " + "listings!");
			exc.printStackTrace();
		}
		WindowPosition.placeCenter(mainStage);
		mainStage.show();
	}

	/**
	 * The "main" entry point for the application. Carries out actions to set up the
	 * application
	 */
	// ----------------------------------------------------------
	public static void main(String[] args) {
		launch(args);
	}
}