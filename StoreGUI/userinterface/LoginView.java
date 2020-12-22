// specify the package
package userinterface;

// system imports
import java.text.NumberFormat;
import java.util.Properties;

import javafx.event.Event;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import model.Invoice;

// project imports
;

/** The class containing the Login View for the Loan Application */
//==============================================================
public class LoginView extends View {

	// Model, which this View talks to
	private Invoice myModel;

	// GUI stuff
	private TextField userid;
	private PasswordField password;
	private Button submitButton;

	// For showing error message
	private MessageView statusLog;

	// constructor for this class -- takes a model object
	// ----------------------------------------------------------
	public LoginView(Invoice invoice) {

		super("LoginView");
		myModel = invoice;

		// create a container for showing the contents
		// the main container inside this Group is a VBox
		VBox container = new VBox(10);

		container.setPadding(new Insets(15, 5, 5, 5));

		// create a Node (Text control) for showing the title
		// and add this control (aka Node) as a CHILD of the VBox
		container.getChildren().add(createTitle());

		// create a Node (GridPane) for showing data entry fields
		// GridPane is another type of container. So, you see from
		// the code below, you can have containers inside
		// containers
		container.getChildren().add(createFormContents());

		// Error message area
		container.getChildren().add(createStatusLog("                          "));

		// Now, add the VBox (called ‘container’) as a CHILD of
		// the main Group (View) itself. Note that the View is
		// what you put into the Scene, and you then put the Scene
		// on the Stage
		getChildren().add(container);

		// If you need to pre-populate your controls, do it in
		// this method. This method will go to the model to get the
		// data to put as values into your controls
		populateFields();

	}

	// Create the label control (called Text) for the title
// of the screen
	// -------------------------------------------------------------
	private Node createTitle() {

		Text titleText = new Text("     Mom and Pop’s Clothing Store        ");
		titleText.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		titleText.setTextAlignment(TextAlignment.CENTER);
		titleText.setFill(Color.DARKGREEN);

		return titleText;
	}

	// Create the main form contents
	// -------------------------------------------------------------
	private GridPane createFormContents() {
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		// data entry fields
		Label userName = new Label("User ID:");
		grid.add(userName, 0, 0);

		userid = new TextField();
		// If you enter data into the text field and hit ENTER
		// you (the user) are GENERATING AN EVENT. The system
		// must RESPOND to this event. This is done by adding
		// an EventHandler (defined by JavaFX) to the control
		userid.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			// Each event handler has a ‘handle()’ method
			// that does the “right thing”. In this case,
			// it gathers data and sends it to the Model
			// for processing
			public void handle(ActionEvent e) {
				processAction(e);
			}
		});
		grid.add(userid, 1, 0);

		Label pw = new Label("Password:");
		grid.add(pw, 0, 1);

		password = new PasswordField();
		password.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				processAction(e);
			}
		});
		grid.add(password, 1, 1);

		submitButton = new Button("Submit");
		submitButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				processAction(e);
			}
		});

		// HBox is another type of Container defined by JavaFX.
		// It lays the controls you add to it as children SIDE BY
		// SIDE.
		// You use the appropriate container you identify, based on
		// the designs given to you by your HCI folks
		HBox btnContainer = new HBox(10);
		btnContainer.setAlignment(Pos.BOTTOM_RIGHT);
		btnContainer.getChildren().add(submitButton);
		grid.add(btnContainer, 1, 3);

		return grid;
	}

	// Create the status log field
	// -------------------------------------------------------------
	private MessageView createStatusLog(String initialMessage) {

		statusLog = new MessageView(initialMessage);
		return statusLog;
	}

	// -------------------------------------------------------------
	public void populateFields() {
		userid.setText("");
		password.setText("");
	}

	// This method processes events generated from our GUI
	// components.Make the EventHandlers delegate to this method,
	// so the code to handle events that are responded to in the same
	// way is in ONE PLACE.
	// -------------------------------------------------------------
	public void processAction(Event evt) {

		clearErrorMessage();

		String useridEntered = userid.getText();

		if ((useridEntered == null) || (useridEntered.length() == 0)) {
			displayErrorMessage("Please enter a user id!");
			userid.requestFocus();
		} else {
			String passwordEntered = password.getText();
			processUserIDAndPassword(useridEntered, passwordEntered);
		}

	}

	/**
	 * Process userid and pwd supplied when Submit button is hit. Action is to pass
	 * this info on to the Loan object
	 */
	// ----------------------------------------------------------
	private void processUserIDAndPassword(String useridString, String passwordString) {
		Properties props = new Properties();
		props.setProperty("ID", useridString);
		props.setProperty("Password", passwordString);

		// clear fields for next time around
		userid.setText("");
		password.setText("");

		myModel.processLogin(props);
	}

	// This method is called back by the Model to update the
	// values displayed in the controls of the GUI
	// ---------------------------------------------------------
	public void updateState(String key, Object value)
	{
		if (key.equals("LoginError") == true)
		{
			// display the passed text
			displayErrorMessage((String)value);
		}

	}

	/**
	 * Display error message
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
