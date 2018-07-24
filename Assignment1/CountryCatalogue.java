/** 
Course: CS1027 
Coded by: Syed Ahmed (sahme339) 
Date: 23 June 2018
Purpose: This is the CountryCatalogue where the countries and continents actions are hard-coded
**/

import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

public class CountryCatalogue {

	private final int DEFAULT_SIZE = 25;
	private Country[] catalogue = new Country[DEFAULT_SIZE];
	private int numCountries = 0;
	final int NOT_FOUND = -1;	
	int search = NOT_FOUND;
	Map<String, String> continentMap = new HashMap<String, String>();
	Set<String> continents = new HashSet<String>();
	
	public CountryCatalogue(String continentFileName, String countryFileName) {
		catalogue = new Country[DEFAULT_SIZE];
		numCountries = 0;
		//continent file is read in and mapped appropriately		
		ThingToReadFile contDoc = new ThingToReadFile(continentFileName);
		contDoc.readLine();
 		
		while (contDoc.endOfFile() == false) {
			String text = contDoc.readLine();
			String[] split = text.split(",");
			continentMap.put(split[0], split[1]);
			if (!continents.contains(split[1])) {
				continents.add(split[1]);
			}
		}
		//country file is read in and mapped appropriately 
		ThingToReadFile cntryDoc = new ThingToReadFile(countryFileName);
		cntryDoc.readLine();
		
		while (cntryDoc.endOfFile() == false) {
			String[] split = cntryDoc.readLine().split("\\|");
			String name = split[0];
			String population = split[1];
			population = population.replaceAll(",", "");
			int pop = Integer.parseInt(population);
			String theArea = split[2];
			theArea = theArea.replaceAll(",", "");
			double area = Double.parseDouble(theArea);
			String cont = continentMap.get(split[0]);
			Country newCountry = new Country(name, pop, area, cont);
			addCatalogue(newCountry);
		}
		//open files are closed
		cntryDoc.close();
		contDoc.close();
	}	
	
	//add country to catalogue 
	public void addCatalogue(Country newCountry){
		catalogue[numCountries] = newCountry;
		numCountries++;
		if (numCountries >= catalogue.length){
			expandCapacity();			
		}
	}
	
	//add the country not from file 
	public boolean addCountry(String countryName, int countryPopulation, double countryArea, String countryContinent) {
		Country cntry = new Country(countryName, countryPopulation, countryArea, countryContinent);
		if (numCountries == catalogue.length) {
			expandCapacity();
		}
		
		for (int i = 0; i < numCountries; i++) {
			if (catalogue[i].getName() == countryName) {
				return false;
			}
		}
		catalogue[numCountries] = cntry;
		numCountries++;
		if (!continents.contains(cntry.getContinent())) {
			continents.add(cntry.getContinent());
		}
		continentMap.put(cntry.getName(), cntry.getContinent());
		return true;
	}
	
	//delete country from catalogue
	public void deleteCountry(String countryName) {
		int search = -1;
		for (int i = 0; i < numCountries; i++) {
			if (catalogue[i].getName() == countryName) {
				search = i;
			}
			if (search == i) {
				catalogue[search] = catalogue[numCountries - 1];
				catalogue[numCountries - 1] = null;
				numCountries --;
			}
		}	
		if (search == -1) {
			System.out.println("The country cannot be removed as it does not exist");
		}
		
	}

	//find the country in catalogue 
	public Country findCountry(String countryName) {
		int j = 0;
		for (int i = 0; i < numCountries; i++) {
			if (catalogue[i].getName().equals(countryName)) {
				j = 1;
				return catalogue[i];
			}
		}
		if (j == 0) {
			System.out.println("That country is not in the catalogue! \n");
		}
		return null;
	}	

	//print the country catalogue 
	public String printCountryCatalogue() {
		String s = "";
		for (int i = 0; i < numCountries; i++) {
			s = s + catalogue[i].toString()+ "\n";
		}
		return s;
	}
	
	//filter the countries by continent
	public void filterCountriesByContinent(String continentName) {
		System.out.println("Countries in " + continentName + ": ");
		for (int i = 0; i < numCountries; i++) {
			if (catalogue[i].getContinent().equals(continentName)) {
				System.out.println(catalogue[i].getName());
			}
		}
		System.out.println("");
	}
	
	//set the population of the country
	public boolean setPopulationOfACountry(String countryName, int countryPopulation) {
		Country search = new Country(countryName, -1, -1, "");
		Country newPop = new Country(countryName, countryPopulation, -1, "");
		for (int i = 0; i < numCountries; i++) {
			if (catalogue[i].equals(search)) {
				catalogue[i] = newPop;
				return true;
			}
		}
		return false;
	}
	
	//find the country with the largest population
	public String findCountryWithLargestPop() {
		int h = 0;
		String popCountry = "";
		for (int i = 0; i < numCountries; i++) {
			if (catalogue[i].getPopulation() >= h) {
				h = catalogue[i].getPopulation();
				popCountry = catalogue[i].getName(); 
			}
		}
		return popCountry;
	}

	//find the country with the smallest area
	public String findCountryWithSmallestArea() {
		double l = catalogue[0].getArea();
		String areaCountry = "";
		for (int i = 0; i < numCountries; i++) {
			if (catalogue[i].getArea() <= l) {
				l = catalogue[i].getArea();
				areaCountry = catalogue[i].getName();
			}
		}
		return areaCountry;
	}
	
	//filter countries by the population density 
	public void filterCountriesByPopDensity(int lowerBound, int upperBound) {
		System.out.println("The countries within the boundaries are: ");
		for (int i = 0; i < numCountries; i++) {
			if (catalogue[i].getPopDensity() >= lowerBound && catalogue[i].getPopDensity() <= upperBound)
				System.out.println(catalogue[i].getName());
		}
		System.out.println("");
	}
	
	//print the most populated continent 
	public void printMostPopulousContinent() {
		Map<String, Integer> cont = new HashMap<String, Integer>();
		
		for (int i = 0; i < numCountries; i++) {
			if (cont.containsKey(catalogue[i].getContinent()))
				cont.put(catalogue[i].getContinent(), catalogue[i].getPopulation() + cont.get(catalogue[i].getContinent()));
			else cont.put(catalogue[i].getContinent(), catalogue[i].getPopulation());
		}
		String mostPopCont = "";
		int popMaxCont = 0;
			for (String s: cont.keySet()) {
				if(cont.get(s) > popMaxCont ) {
					popMaxCont = cont.get(s);
					mostPopCont = s;
				}
			}
		System.out.println("The most populous continent is " + mostPopCont + ", at a population of " + popMaxCont + "\n");
	}
	
	//save the country catalogue 
	public void saveCountryCatalogue(String filename) {
		ThingToWriteFile outFile = new ThingToWriteFile(filename);
		for (int i = 0; i < numCountries; i++) {
			catalogue[i].writeToFile(outFile);
		}
		outFile.close();
	}
	
	//expand the capacity by three times when max is reached 
	private void expandCapacity() {
		Country[] newCatalogue = new Country[catalogue.length * 3];
		for (int i = 0; i < catalogue.length; i++) {
			newCatalogue[i] = catalogue[i];
		}
		catalogue = newCatalogue;	
	}
}