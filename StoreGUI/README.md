# StoreGUI
A login screen is prompting the user to add a username and password is shown with data validation built in. If username and password is collect then another scene is shown.
This second scene has texboxes for quantity of shirts, pants, ties, shoes, and sales tax. Upon pressing "Calculate" the total bill is calculated and displayed 

# Classes Breakdown
# Default Package

InvoiceCalculator.java - Contains the main method for launching this program

# model package - Contains valid usernames and passwords
AccountHolder.java - Contains AccountHolder constructor

Invoice.java - Contains arrayList with username and passwords. Procceses login data as well as input data in next scene

# usereinterface package - Contains many behind the scenes classes to customize appearance of GUI
InvoiceView.java - Main part of code. This class add buttons, textfields, calls processInvoice, and does data validation for input/ error notification

LoginView.java - Main part of the login scene. Sets up all the formatting and calls to process data for validation.

MainStageContainer.java - The main stage for the Brockport GUI applications. All scenes are inside this one stage.

MessageView.java - Contains code that controlls the appearance of error messages 

View.java - Abstract class that contains View constructor

WindowPosition.java - Provides a means of placing the main stage of the application in the center, of the screen, top left corner, bottom right corner, top right corner, bottom, left corner






