# HouseGUI
Description- Allows the user to input a file location containing a list of houses for sale with values for specific criteria as well as criteria to search for in said list

# Breakdown of classes

Criteria.java- Contains the criteria specified by the user to select houses.	

House.java - House object with a method to check if criteria is met

HouseList.java - Outputs the details of houses that meet the criteria specified

ListingsView.java
 * Adds main GUI components that will be placed on a panel for the stage
 * Processes errors that are caused by wrong user input
 * Disables and enables buttons as well as textFields as needed
 * Ensures a random and unique house that matches criteria is shown 
 
Listings.java - Processes the text file of houses and produces a list of houses matching the criteria

ListingsChooser.java - Contains main method. Launches the stage

MainStageContainer.java - The main stage for the GUI applications. All scenes are inside this one stage.

MessageView.java - Methods to show messages to the user. Warns user of errors and other necessary information.

View.java 
 * Super-class of all the scene content that you may wish to put into your Scene objects
 * Container of all the actors and props, which will need to be laid out within the Scene
 
WindowPosition.java - Provides a means of placing the main stage of the application in the center of the screen


