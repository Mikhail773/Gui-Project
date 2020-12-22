//specify the package
package model;

//system imports
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.util.ArrayList;
import java.util.Properties;

//project imports
import userinterface.MainStageContainer;
import userinterface.View;
import userinterface.LoginView;
import userinterface.InvoiceView;
import userinterface.WindowPosition;

/** The class containing the Loan for the LoanCalculator application */
//==============================================================
public class Invoice {

	private ArrayList<AccountHolder> myAccountHolders = new ArrayList<AccountHolder>();

	// GUI Components
	private Stage myStage;
	private View loginView;
	private View invoiceView;
	private Scene currentScene;

	private String loginErrorMessage = "";
	private String transactionErrorMessage = "";

	// constructor for this class
	// ----------------------------------------------------------
	public Invoice() {

		AccountHolder a = new AccountHolder("trao", "RaoT1", "T M Rao");
		myAccountHolders.add(a);
		a = new AccountHolder("smitra", "MitraS1", "S Mitra");
		myAccountHolders.add(a);
		a = new AccountHolder("mcoleman", "Melissa1", "M Coleman");
		myAccountHolders.add(a);
		a = new AccountHolder("rnsmith", "Rebekah1", "R Smith");
		myAccountHolders.add(a);
		a = new AccountHolder("user", "password", "R Smith");
		myAccountHolders.add(a);

		myStage = MainStageContainer.getInstance();

		// Set up the initial view
		createAndShowLoginView();
	}

	// ----------------------------------------------------------
	public void processLogin(Properties props) {
		String un = props.getProperty("ID");
//this is what we did in the View, so it must match
		boolean userFound = false;
		for (int cnt = 0; cnt < myAccountHolders.size(); cnt++) {
			String unInList = (myAccountHolders.get(cnt)).username;
			if (unInList.equals(un) == true) {
				String pwSent = props.getProperty("Password");
				String uPwd = (myAccountHolders.get(cnt)).password;
				if (uPwd.equals(pwSent) == true) {
					userFound = true;
					break;
				}
			}
		}

		if (userFound) // this means login successful
		{
			createAndShowInvoiceView();
		} else {
			((LoginView) loginView).updateState("LoginError", "Login failed!");
		}
	}

	// ----------------------------------------------------------
	public void processInvoice(Properties props) {
		String shirtsStr = props.getProperty("Shirts");
		String pantsStr = props.getProperty("Pants");
		String tiesStr = props.getProperty("Ties");
		String shoesStr = props.getProperty("Shoes");
		String salesTaxStr = props.getProperty("SalesTax");

		int shirts = Integer.parseInt(shirtsStr);
		int pants = Integer.parseInt(pantsStr);
		int ties = Integer.parseInt(tiesStr);
		int shoes = Integer.parseInt(shoesStr);
		double salesTax = Double.parseDouble(salesTaxStr) * 0.01;

		double totalBill = (shirts + pants + ties + shoes) * (1 + (salesTax));

		((InvoiceView) invoiceView).updateState("TotalBill", "" + totalBill);

	}

	// ----------------------------------------------------------
	public void cancelTransaction() {
		createAndShowLoginView();
	}

	// ----------------------------------------------------------
	private void createAndShowInvoiceView() {
		// create our new view
		invoiceView = new InvoiceView(this);
		currentScene = new Scene(invoiceView);

		// make the view visible by installing it into the stage
		swapToView(currentScene);
	}

	// ------------------------------------------------------------
	private void createAndShowLoginView() {
		// create our initial view
		loginView = new LoginView(this);
		// CREATE A SCENE WITH OUR NEW VIEW
		currentScene = new Scene(loginView);

//make the view visible by installing it into the stage
		swapToView(currentScene);
	}

	// ---------------------------------------------------------------
	public void swapToView(Scene newScene) {
		if (newScene == null) {
			System.out.println("Invoice.swapToView(): Missing view for display");
			return;
		}

		// SWAP THE SCENE ON THE STAGE
		myStage.setScene(newScene);
//RE-SIZE STAGE TO FIT NEW SCENE SIZE
		myStage.sizeToScene();
		// Place in center again
		WindowPosition.placeCenter(myStage);
	}
}