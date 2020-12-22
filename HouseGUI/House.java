/*
 * This class represents the details of a house for sale
 * 
 * @author Mikhail Mikhaylov
 * @version September 29,2020
 */

package Listing;

public class House {
	private String address;
	private int price;
	private int area;
	private int numBedrooms;

	public House(String address, int price, int area, int numBedrooms) {
		this.address = address;
		this.price = price;
		this.area = area;
		this.numBedrooms = numBedrooms;
	}

	// ---------------------------------------------------------------------------------------------------
	public String getAddress() {
		return address;
	}

	public int getPrice() {
		return price;
	}

	public int getArea() {
		return area;
	}

	public int getNumBedrooms() {
		return numBedrooms;
	}

	public boolean satisfies(Criteria c) {
		if (price < c.getMinimumPrice() || price > c.getMaximumPrice())
			return false;
		if (area < c.getMinimumArea() || area > c.getMaximumArea())
			return false;
		if ((numBedrooms < c.getMinimumNumberOfBedrooms()) || (numBedrooms > c.getMaximumNumberOfBedrooms()))
			return false;
		return true;
	}
}
