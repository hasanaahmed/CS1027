/** 
Course: CS1027 
Coded by: Syed Ahmed (sahme339) 
Date: 23 June 2018
Purpose: This is the Country file where the CountryCatalogue is set up
**/

public class Country{

	//set up variables
	private String name;
	private int population;
	private double area;
	private String continent; 
	
	//set up Country class
	public Country(String name, int population, double area, String continent){
		this.name = name;
		this.population = population;
		this.area = area;
		this.continent = continent;
	}
	
	//set up getters
	public String getName(){
		return name;
	}
	
	public int getPopulation(){
		return population;
	}
		
	public double getArea(){
		return area;
	}
		
	public String getContinent(){
		return continent;
	}
	
	public double getPopDensity(){
		return ((double)population)/area;
	}
	
	//set up setters
	public void setPopulation(int population){
		this.population = population;
	}
	
	public void setContinent(String continent) {
		this.continent = continent; 
	}
	
	//the format of writing the country to the file 
	public void writeToFile(ThingToWriteFile filename) {
		String s = getName() + "|" + getContinent() + "|" +getPopulation() + "|" +getPopDensity();
		filename.writeLine(s);
	}

	//format of printing the country details 
	public void printCountryDetails() {
		System.out.println(getName() + " is located in " + getContinent() + " has a population of " + getPopulation() + " an area of " + getArea() + ", and has a population density of " +getPopDensity() + "\n");
	}
	
	//toString setup anf format
	public String toString() {
		String s = name + " " + "in" + " " + continent;
		return s;
	}
	
	//main
	public static void main(String[] args) {	
	}
}