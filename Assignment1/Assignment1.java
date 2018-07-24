
public class Assignment1 {

	public static void main(String[] args) {
		
		CountryCatalogue cc = new CountryCatalogue("continent.txt", "data.txt");
		Country cntry;
		
		cntry = cc.findCountry("Canada");
		if(cntry != null){
			cntry.printCountryDetails();
		}
		
		cntry = cc.findCountry("USA");
		if(cntry != null){
			cntry.printCountryDetails();
		}
		
		cc.addCountry("England", 54316600, 130279, "Europe");
		
		cntry = cc.findCountry("England");
		if(cntry != null){
			cntry.printCountryDetails();
		}
		
		cc.deleteCountry("England");
		cntry = cc.findCountry("England");
		if(cntry != null){
			cntry.printCountryDetails();
		}
		
		cc.printCountryCatalogue();
		System.out.println("\nCountry with the largest population: " + cc.findCountryWithLargestPop());
		
		System.out.println("\nCountry with the smallest area: " + cc.findCountryWithSmallestArea());
		System.out.println();
		cc.filterCountriesByContinent("North America");
		System.out.println();
		cc.filterCountriesByPopDensity(0, 25);
		cc.printMostPopulousContinent();
		cc.saveCountryCatalogue("outDetails.txt");
	}
}
