/*
 * Processes the text file of houses and produces a list of houses matching the criteria
 * From there one element from that list is sent to be displayed
 * 
 * @author Mikhail Mikhaylov
 * @version November 1,2020
 */

package Listing;
import java.io.FileNotFoundException;
import java.util.*;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Listing.ListingView;
import Listing.MainStageContainer;
import Listing.View;
import Listing.WindowPosition;
import Listing.Criteria;
import Listing.HouseList;

public class Listings {
	//List of all houses that meet criteria
	ArrayList<String> fullList = new ArrayList<>();

	// GUI Components
	private Stage myStage;
	private View listingView;

	// constructor for this class
	// ----------------------------------------------------------
	public Listings() {
		myStage = MainStageContainer.getInstance();

		// Set up the initial view
		createAndShowListingsView();
	}

	/* Add houses that meet criteria to fullList and update status of chosenHome textField
	*  Randomly picks elements from array to show. ListingView checks for repeats
	*  @param props : Properties of textFields
	*/
	// ----------------------------------------------------------
	public void processListings(Properties props, HouseList availableHouses) {
		//Parse values in textField to use as criteria
		int minimumPrice = Integer.parseInt(props.getProperty("MinimumPrice"));
		int maximumPrice = Integer.parseInt(props.getProperty("MaximumPrice"));
		int minimumArea = Integer.parseInt(props.getProperty("MinimumArea"));
		int maximumArea = Integer.parseInt(props.getProperty("MaximumArea"));
		int minimumBeds = Integer.parseInt(props.getProperty("MinimumBeds"));
		int maximumBeds = Integer.parseInt(props.getProperty("MaximumBeds"));
			int number;
			Random ran = new Random();
			fullList = new ArrayList<>(availableHouses.createCriteriaList(
					new Criteria(minimumPrice, maximumPrice, minimumArea, maximumArea, minimumBeds, maximumBeds)));
			if (fullList.isEmpty())
				((ListingView) listingView).updateState("No houses meet condition");
			else {
				number = ran.nextInt(fullList.size());
				((ListingView) listingView).updateState(fullList.get(number));
			}

	}

	// ----------------------------------------------------------
	public ArrayList<String> getFullList() {
		return fullList;
	}

	//Show Listing View
	// ----------------------------------------------------------
	private void createAndShowListingsView() {
		// create our new view
		listingView = new ListingView(this);

		// make the view visible by installing it into the stage
		myStage.setScene(new Scene(listingView));
		// RE-SIZE STAGE TO FIT NEW SCENE SIZE
		myStage.sizeToScene();
		// Place in center again
		WindowPosition.placeCenter(myStage);
	}
}
