package Listing;

public class Criteria {
	private int minimumPrice;
	private int maximumPrice;
	private int minimumArea;
	private int maximumArea;
	private int minimumNumberOfBedrooms;
	private int maximumNumberOfBedrooms;

	public Criteria(int minimumPrice, int maximumPrice, int minimumArea, int maximumArea, int minimumNumberOfBedrooms,
			int maximumNumberOfBedrooms) {
		this.minimumPrice = minimumPrice;
		this.maximumPrice = maximumPrice;
		this.minimumArea = minimumArea;
		this.maximumArea = maximumArea;
		this.minimumNumberOfBedrooms = minimumNumberOfBedrooms;
		this.maximumNumberOfBedrooms = maximumNumberOfBedrooms;
	}
	// ---------------------------------------------------------------------------------------------------
	public int getMinimumPrice() {
		return minimumPrice;
	}

	public int getMaximumPrice() {
		return maximumPrice;
	}

	public int getMinimumArea() {
		return minimumArea;
	}

	public int getMaximumArea() {
		return maximumArea;
	}

	public int getMinimumNumberOfBedrooms() {
		return minimumNumberOfBedrooms;
	}

	public int getMaximumNumberOfBedrooms() {
		return maximumNumberOfBedrooms;
	}
}
