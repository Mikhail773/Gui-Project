package Listing;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class HouseList {
	private ArrayList<House> houseList = new ArrayList<House>();


	public HouseList(String fileName) throws FileNotFoundException {
		Scanner input = new Scanner(new File(fileName));
		while (input.hasNextLine()) {
			String text = input.nextLine();
			String[] words = text.split("  ");
			houseList.add(new House(words[0], Integer.parseInt(words[1]), Integer.parseInt(words[2]),
					Integer.parseInt(words[3])));
		}
	}

	public String getHouses(Criteria c) {
		String houses = "";
		for (int i = 0; i < houseList.size(); i++) {
			if (houseList.get(i).satisfies(c)) 
				houses += houseList.get(i).toString();
		}
		return houses;
	}

	// ---------------------------------------------------------------------------------------------------
		/**
		 * Returns ArrayList with all houses that match criteria
		 * @param c: Criteria represents buyer's criteria
		 * @return ArrayList<String>: ArrayList of all houses that match criteria
		 */
	public ArrayList<String> createCriteriaList(Criteria c) {
		ArrayList<String> criteriaList = new ArrayList<>();
		for (int i = 0; i < houseList.size(); i++) {
			if ((houseList.get(i)).satisfies(c))
				criteriaList.add(houseList.get(i).getAddress());
		}
		return criteriaList;
	}
}