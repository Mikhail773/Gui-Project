/*
 * Adds main GUI components that will be placed on a panel for the stage
 * Processes errors that are caused by wrong user input
 * Disables and enables buttons as well as textFields as needed
 * Ensures a random and unique house that matches criteria is shown 
 * 
 * @author Mikhail Mikhaylov
 * @version November 1,2020
 */

package Listing;

import java.io.File;
import java.util.ArrayList;
import java.util.Properties;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import Listing.Listings;
import Listing.MessageView;

public class ListingView extends View {
	// Model, which this View talks to
	private Listings myListings;

	// GUI components
	private TextField textFile;
	private TextField minPrice;
	private TextField maxPrice;
	private TextField minArea;
	private TextField maxArea;
	private TextField minBeds;
	private TextField maxBeds;
	private TextField chosenHome;

	//Clickable buttons
	private Button findButton;
	private Button findAnotherButton;
	private Button resetButton;

	// For showing error message
	private MessageView statusLog;

	// constructor for this class -- takes a model object
	// ----------------------------------------------------------
	public ListingView(Listings listing) // SAME PATTERN AS LoginView
	{
		super("ListingView");
		myListings = listing;

		// create a container for showing the contents
		VBox container = new VBox(10);
		container.setPadding(new Insets(10, 5, 5, 5));

		// create our GUI components, add them to this panel
		container.getChildren().add(createTitle());
		container.getChildren().add(createFormContent());
		container.getChildren().add(resultsAndButtons());

		// Error message area
		container.getChildren().add(createStatusLog("  "));

		getChildren().add(container);

	}

	// Create the title container
	// -------------------------------------------------------------
	private Node createTitle() {
		HBox container = new HBox();
		container.setAlignment(Pos.CENTER);

		Text titleText = new Text(" Real Estate Listings ");
		titleText.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		titleText.setWrappingWidth(400);
		titleText.setTextAlignment(TextAlignment.CENTER);
		titleText.setFill(Color.BLACK);
		container.getChildren().add(titleText);

		return container;
	}

	// Create the main form content includes any textfield user interacts with
	// -------------------------------------------------------------
	private VBox createFormContent() {
		VBox vbox = new VBox(10);

		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 0, 0));

		Text textFileLabel = new Text(" Text File Location ");
		textFileLabel.setFont(Font.font("Arial", 15));
		textFileLabel.setWrappingWidth(150);
		textFileLabel.setTextAlignment(TextAlignment.RIGHT);
		grid.add(textFileLabel, 0, 0);

		textFile = new TextField();
		textFile.setMaxSize(200, 20);
		grid.add(textFile, 1, 0);
		
		Text minPriceLabel = new Text(" Minimum Price ");
		minPriceLabel.setFont(Font.font("Arial", 15));
		minPriceLabel.setWrappingWidth(150);
		minPriceLabel.setTextAlignment(TextAlignment.RIGHT);
		grid.add(minPriceLabel, 0, 1);

		minPrice = new TextField();
		minPrice.setMaxSize(100, 20);
		grid.add(minPrice, 1, 1);

		Text maxPriceLabel = new Text(" Maximum Price ");
		maxPriceLabel.setFont(Font.font("Arial", 15));
		maxPriceLabel.setWrappingWidth(150);
		maxPriceLabel.setTextAlignment(TextAlignment.RIGHT);
		grid.add(maxPriceLabel, 0, 2);

		maxPrice = new TextField();
		maxPrice.setMaxSize(100, 20);
		grid.add(maxPrice, 1, 2);

		Text minAreaLabel = new Text(" Minimum Area ");
		minAreaLabel.setFont(Font.font("Arial", 15));
		minAreaLabel.setWrappingWidth(150);
		minAreaLabel.setTextAlignment(TextAlignment.RIGHT);
		grid.add(minAreaLabel, 0, 3);

		minArea = new TextField();
		minArea.setMaxSize(100, 20);
		grid.add(minArea, 1, 3);

		Text maxAreaLabel = new Text(" Maximum Area ");
		maxAreaLabel.setFont(Font.font("Arial", 15));
		maxAreaLabel.setWrappingWidth(150);
		maxAreaLabel.setTextAlignment(TextAlignment.RIGHT);
		grid.add(maxAreaLabel, 0, 4);

		maxArea = new TextField();
		maxArea.setMaxSize(100, 20);
		grid.add(maxArea, 1, 4);

		Text minBedsLabel = new Text(" Minimum Beds ");
		minBedsLabel.setFont(Font.font("Arial", 15));
		minBedsLabel.setWrappingWidth(150);
		minBedsLabel.setTextAlignment(TextAlignment.RIGHT);
		grid.add(minBedsLabel, 0, 5);

		minBeds = new TextField();
		minBeds.setMaxSize(100, 20);
		grid.add(minBeds, 1, 5);

		Text maxBedsLabel = new Text(" Maximum Beds ");
		maxBedsLabel.setFont(Font.font("Arial", 15));
		maxBedsLabel.setWrappingWidth(150);
		maxBedsLabel.setTextAlignment(TextAlignment.RIGHT);
		grid.add(maxBedsLabel, 0, 6);

		maxBeds = new TextField();
		maxBeds.setMaxSize(100, 20);
		grid.add(maxBeds, 1, 6);

		vbox.getChildren().add(grid);
		return vbox;
	}

	// Creates the Results textfield, and buttons with actions
	// Ensures "Not my dream" button outputs a different and random house
	// -------------------------------------------------------------
	private VBox resultsAndButtons() {
		VBox vbox = new VBox(10);
		HBox btnContainer = new HBox(10);
		HBox btnContainerReset = new HBox(10);
		GridPane chosenGrid = new GridPane();

		Text chosenHomeLabel = new Text(" Chosen Home ");
		chosenHomeLabel.setFont(Font.font("Arial", 20));
		chosenHomeLabel.setWrappingWidth(150);
		chosenHomeLabel.setTextAlignment(TextAlignment.RIGHT);
		chosenGrid.add(chosenHomeLabel, 0, 0);

		chosenHome = new TextField();
		chosenHome.setFont(Font.font("Arial", 15));
		chosenHome.setMinSize(200, 30);
		chosenGrid.add(chosenHome, 1, 0);

		findAnotherButton = new Button("Not my dream - find me another!");
		findAnotherButton.setDisable(true);

		findButton = new Button("Find my dream house!");

		// myList is used to keep track of already shown houses
		// Prevents repeats when pressing "not my dream house"
		myListings.getFullList();
		ArrayList<String> myList = new ArrayList<String>();

		// Sets textfields as uneditable after pressing button to find dream home to
		// prevent errors
		// Adds any shown results to myListings and checks if no houses meet conditions
		findButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				clearErrorMessage();
				textFile.setEditable(false);
				minPrice.setEditable(false);
				maxPrice.setEditable(false);
				minArea.setEditable(false);
				maxArea.setEditable(false);
				minBeds.setEditable(false);
				maxBeds.setEditable(false);
				chosenHome.setEditable(false);

				// do the calculation
				processAction(e);
				myList.add(chosenHome.getText());
				findButton.setDisable(true);
				findAnotherButton.setDisable(false);
				if (chosenHome.getText().equals("No houses meet condition")) {
					findAnotherButton.setDisable(true);
					findButton.setDisable(false);
				}
				if (chosenHome.getText().equals("Error")) {
					findAnotherButton.setDisable(true);
					findButton.setDisable(false);
				}
				if (chosenHome.getText().equals("File is not found")) {
					findAnotherButton.setDisable(true);
					findButton.setDisable(true);
				}
			}
		});
		// Shows no more available houses if myList is as large as the fullList(all
		// houses meeting conditions)
		// Shows another result if already shown result is randomly shown
		findAnotherButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				processAction(e);
				if ((myList.size() >= myListings.getFullList().size())) {
					updateState("No more available houses");
					findAnotherButton.setDisable(true);
					findButton.setDisable(true);
				} else {
					while (myList.contains(chosenHome.getText())) {
						processAction(e);
					}
					myList.add(chosenHome.getText());
					clearErrorMessage();
				}
			}
		});

		// Clears all textfields, enables textfield again, clears error messages,
		// empties myList
		resetButton = new Button("Reset");
		resetButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				myList.clear();
				minPrice.clear();
				maxPrice.clear();
				minArea.clear();
				maxArea.clear();
				minBeds.clear();
				maxBeds.clear();
				chosenHome.clear();
				textFile.setEditable(true);
				minPrice.setEditable(true);
				maxPrice.setEditable(true);
				minArea.setEditable(true);
				maxArea.setEditable(true);
				minBeds.setEditable(true);
				maxBeds.setEditable(true);
				findButton.setDisable(false);
				findAnotherButton.setDisable(true);
				clearErrorMessage();
			}
		});

		//Adds buttons to panels and adds some settings
		vbox.getChildren().add(chosenGrid);
		vbox.getChildren().add(btnContainer);
		vbox.getChildren().add(btnContainerReset);
		btnContainer.getChildren().add(findButton);
		btnContainer.getChildren().add(findAnotherButton);
		btnContainerReset.getChildren().add(resetButton);
		btnContainer.setAlignment(Pos.CENTER);
		btnContainerReset.setAlignment(Pos.CENTER);
		chosenGrid.setAlignment(Pos.CENTER);
		chosenGrid.setPadding(new Insets(25, 25, 25, 0));

		return vbox;
	}

	/* Create the status log field
	 * @param initialMessage : Message to be returned
	 *
     */
	
	// -------------------------------------------------------------
	private MessageView createStatusLog(String initialMessage) {
		statusLog = new MessageView(initialMessage);
		return statusLog;
	}

	/* Process events generated from our GUI components
	*  changes values of empty textFields and performs data validation
	*  @param evet : Event trigger(in our event a mouse trigger)
	*/
	// -------------------------------------------------------------
	public void processAction(Event evt) {
		clearErrorMessage();

		if (minPrice.getText().length() == 0) 
			minPrice.setText("0");
		if (maxPrice.getText().length() == 0) 
			maxPrice.setText(Integer.toString(Integer.MAX_VALUE));
		if (minArea.getText().length() == 0) 
			minArea.setText("0");
		if (maxArea.getText().length() == 0) 
			maxArea.setText(Integer.toString(Integer.MAX_VALUE));
		if (minBeds.getText().length() == 0) 
			minBeds.setText("0");
		if (maxBeds.getText().length() == 0) 
			maxBeds.setText(Integer.toString(Integer.MAX_VALUE));

		// Here, we are doing USER DATA VALIDATION
		try {
			int priceMinimum = Integer.parseInt(minPrice.getText());
			int priceMaximum = Integer.parseInt(maxPrice.getText());
			int areaMinimum = Integer.parseInt(maxArea.getText());
			int areaMaximum = Integer.parseInt(maxArea.getText());
			int bedsMinimum = Integer.parseInt(minBeds.getText());
			int bedsMaximum = Integer.parseInt(maxBeds.getText());
			File file = new File(textFile.getText());
			if (priceMinimum < 0 || priceMaximum < 0 || areaMinimum < 0 || areaMaximum < 0 || bedsMinimum < 0
					|| bedsMaximum < 0) {
				displayErrorMessage("Invalid amount: Please re-enter");
				chosenHome.setText("Error");
			}
			else if(!(file.exists())) {
				chosenHome.setText("File is not found");
				displayErrorMessage("Error in processing invoice: Incorrect file name");		
			} else {
				Properties props = new Properties();
				HouseList availableHouses = new HouseList(textFile.getText());				
				props.setProperty("MinimumPrice", minPrice.getText());
				props.setProperty("MaximumPrice", maxPrice.getText());
				props.setProperty("MinimumArea", minArea.getText());
				props.setProperty("MaximumArea", maxArea.getText());
				props.setProperty("MinimumBeds", minBeds.getText());
				props.setProperty("MaximumBeds", maxBeds.getText());
				myListings.processListings(props, availableHouses);
			}
		} catch (Exception ex) {
			displayErrorMessage("Error in processing invoice");		
		}
	}

	/**
	 * Process the data selected and entered by user. Action is to pass this info on
	 * to the Loan (Model) object by calling the appropriate method on the Loan
	 * object.
	 */

	/* Updates textField values
	*  @param value : Value to update textField with
	*/
	// ---------------------------------------------------------
	public void updateState(String value) {
			chosenHome.setText(value);
	}

	/**
	 * Display error message
	 * @param message : Error to be displayed
	 */
	// ----------------------------------------------------------
	public void displayErrorMessage(String message) {
		statusLog.displayErrorMessage(message);
	}

	/**
	 * Clear error message
	 */
	// ----------------------------------------------------------
	public void clearErrorMessage() {
		statusLog.clearErrorMessage();
	}
}