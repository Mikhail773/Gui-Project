/*
 * Super-class of all the scene content that you may wish to put into your Scene objects
 * Container of all the actors and props, which will need to be laid out within the Scene
 */ 

// specify the package
package Listing;

// system imports
import javafx.scene.Group;

//==============================================================
public abstract class View extends Group {
	private String myClassName;

	// Class constructor
	// ----------------------------------------------------------
	public View(String classname) {

		myClassName = classname;
	}
}