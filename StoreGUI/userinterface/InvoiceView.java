// specify the package
package userinterface;

// system imports
import javafx.event.Event;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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

import java.text.DecimalFormat;
import java.util.Properties;
import java.util.Vector;

// project imports

/**
 * The class containing the Invoice View for the Invoice Calculator application
 */
//==============================================================
public class InvoiceView extends View {

// Model, which this View talks to
	private Invoice myModel;

	// GUI components
	private TextField shirts;
	private TextField pants;
	private TextField ties;
	private TextField shoes;
	private ComboBox<String> sales;
	private TextField totalBill;
	private Button submitButton;
	private Button cancelButton;

	// For showing error message
	private MessageView statusLog;

	// constructor for this class -- takes a model object
	// ----------------------------------------------------------
	public InvoiceView(Invoice invoice) // SAME PATTERN AS LoginView
	{
		super("InvoiceView");
		myModel = invoice;

		// create a container for showing the contents
		VBox container = new VBox(10);
		container.setPadding(new Insets(15, 5, 5, 5));

		// create our GUI components, add them to this panel
		container.getChildren().add(createTitle());
		container.getChildren().add(createFormContent());

		// Error message area
		container.getChildren().add(createStatusLog("  "));

		getChildren().add(container);

		populateFields();
	}

	// Create the title container
	// -------------------------------------------------------------
	private Node createTitle() {
		HBox container = new HBox();
		container.setAlignment(Pos.CENTER);

		Text titleText = new Text(" Mom and Pop’s Clothing Store ");
		titleText.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		titleText.setWrappingWidth(400);
		titleText.setTextAlignment(TextAlignment.CENTER);
		titleText.setFill(Color.DARKGREEN);
		container.getChildren().add(titleText);

		return container;
	}

	// Create the main form content
	// -------------------------------------------------------------
	private VBox createFormContent() {
		VBox vbox = new VBox(10);

		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER_LEFT);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 0));

		Text shirtsLabel = new Text("Shirts ");
		shirtsLabel.setWrappingWidth(150);
		shirtsLabel.setTextAlignment(TextAlignment.RIGHT);
		grid.add(shirtsLabel, 0, 0);

		shirts = new TextField();
		shirts.setMinSize(100, 20);
		grid.add(shirts, 1, 0);

		Text PantsLabel = new Text("Pants ");
		PantsLabel.setWrappingWidth(150);
		PantsLabel.setTextAlignment(TextAlignment.RIGHT);
		grid.add(PantsLabel, 0, 1);

		pants = new TextField();
		pants.setMinSize(100, 20);
		grid.add(pants, 1, 1);

		Text tiesLabel = new Text("Ties ");
		tiesLabel.setWrappingWidth(150);
		tiesLabel.setTextAlignment(TextAlignment.RIGHT);
		grid.add(tiesLabel, 0, 2);

		ties = new TextField();
		ties.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				processAction(e);
			}
		});
		grid.add(ties, 1, 2);

		Text shoesLabel = new Text("Shoes ");
		shoesLabel.setWrappingWidth(150);
		shoesLabel.setTextAlignment(TextAlignment.RIGHT);
		grid.add(shoesLabel, 0, 3);

		shoes = new TextField();
		grid.add(shoes, 1, 3);

		Text salesLabel = new Text("Sales Tax ");
		salesLabel.setWrappingWidth(150);
		salesLabel.setTextAlignment(TextAlignment.RIGHT);
		grid.add(salesLabel, 0, 4);

		sales = new ComboBox<String>();
		grid.add(sales, 1, 4);

		Text totalBillLabel = new Text("Total Bill ");
		totalBillLabel.setWrappingWidth(150);
		totalBillLabel.setTextAlignment(TextAlignment.RIGHT);
		grid.add(totalBillLabel, 0, 5);

		totalBill = new TextField();
		grid.add(totalBill, 1, 5);

		submitButton = new Button("Calculate");
		submitButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				clearErrorMessage();
				// do the calculation
				processAction(e);
			}
		});

		cancelButton = new Button("Back");
		cancelButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {

				// --------------------------------------------
				clearErrorMessage();
				myModel.cancelTransaction();
			}
		});

		HBox btnContainer = new HBox(100);
		btnContainer.setAlignment(Pos.CENTER);
		btnContainer.getChildren().add(submitButton);
		btnContainer.getChildren().add(cancelButton);

		vbox.getChildren().add(grid);
		vbox.getChildren().add(btnContainer);

		return vbox;
	}

	// Create the status log field
	// -------------------------------------------------------------
	private MessageView createStatusLog(String initialMessage) {
		statusLog = new MessageView(initialMessage);

		return statusLog;
	}

	// -------------------------------------------------------------
	public void populateFields() {

		sales.getItems().add("3.0");
		sales.getItems().add("3.25");
		sales.getItems().add("3.5");
		sales.getItems().add("3.75");
		sales.getItems().add("4.0");
		sales.getItems().add("4.25");
		sales.getItems().add("4.5");
		sales.getItems().add("4.75");
		sales.getItems().add("5.0");
		sales.getItems().add("5.25");
		sales.getItems().add("5.5");
		sales.getItems().add("5.75");
		sales.getItems().add("6.0");
		sales.getItems().add("6.25");
		sales.getItems().add("6.5");
		sales.getItems().add("6.75");
		sales.getItems().add("7.0");
		sales.getItems().add("7.25");
		sales.getItems().add("7.5");
		sales.getItems().add("7.75");
		sales.getItems().add("8.0");
		sales.getItems().add("8.25");
		sales.getItems().add("8.5");
		sales.getItems().add("8.75");
		sales.getItems().add("9.0");

		sales.setValue(sales.getItems().get(0));
	}

	// process events generated from our GUI components
	// -------------------------------------------------------------
	public void processAction(Event evt) {
		clearErrorMessage();
		// do the transfer

		String priceofShirts = shirts.getText();
		String priceofPants = pants.getText();
		String priceofTies = ties.getText();
		String priceofShoes = shoes.getText();
		String selectedSalesTax = sales.getValue();

		// Here, we are doing USER DATA VALIDATION
		try {
			int shirtPrice = Integer.parseInt(priceofShirts);
			int pantsPrice = Integer.parseInt(priceofPants);
			int tiesPrice = Integer.parseInt(priceofTies);
			int shoesPrice = Integer.parseInt(priceofShoes);
			if (shirtPrice < 0 || pantsPrice < 0 || tiesPrice < 0 || shoesPrice < 0) {
				displayErrorMessage("Invalid amount: Please re-enter");
			} else {
				processInvoice(priceofShirts, priceofPants, priceofTies, priceofShoes, selectedSalesTax);
			}
		} catch (Exception ex) {
			displayErrorMessage("Error in processing invoice");
		}
		if ((shirts == null) || (priceofShirts.length() == 0))
			displayErrorMessage("Please enter price of shirt(s)");
		if ((pants == null) || (priceofPants.length() == 0))
			displayErrorMessage("Please enter price of pant(s)");
		if ((ties == null) || (priceofTies.length() == 0))
			displayErrorMessage("Please enter price of tie(s)");
		if ((shoes == null) || (priceofShoes.length() == 0))
			displayErrorMessage("Please enter a shoe amount price of shoes(s)");

	}

	/**
	 * Process the data selected and entered by user. Action is to pass this info on
	 * to the Loan (Model) object by calling the appropriate method on the Loan
	 * object.
	 */
	// ----------------------------------------------------------
	private void processInvoice(String amountofShirts, String amountofPants, String amountofTies, String amountofShoes,
			String selectedSalesTax) {

		Properties props = new Properties();
		props.setProperty("Shirts", amountofShirts);
		props.setProperty("Pants", amountofPants);
		props.setProperty("Ties", amountofTies);
		props.setProperty("Shoes", amountofShoes);
		props.setProperty("SalesTax", selectedSalesTax);
		myModel.processInvoice(props);
	}

	// ---------------------------------------------------------
	public void updateState(String key, Object value) {
		if (key.equals("TotalBill") == true) {
			DecimalFormat df = new DecimalFormat("#.##");
			String val = df.format(Double.parseDouble((String) value));
			totalBill.setText(val);
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
