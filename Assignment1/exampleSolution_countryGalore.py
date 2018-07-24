
##
# Class: Country class. Each country has a name, population, area, and a continent it belongs to.
#
class Country:
    ##
    # Constructor
    #@param - name (name of Country)
    #@param - pop (population of Country)
    #@param - area (area of Country)
    #@param - continent (continent of Country)
    def __init__(self, name, pop, area, continent) :
        self._name=name #name of a country
        self._pop=pop #population of a country
        self._area=area #area of country
        self._continent=continent #continent of country
    ##
    # Convert to string a Country
    #
    def __repr__(self):
        return self._name + " in " + self._continent
    ##
    # sets the population of a country
    #@param - pop (a new population)
    #
    def setPopulation(self, pop):
        self._pop = pop
    ##
    # gets the name of the country
    #
    def getName(self):
        return self._name
    ##
    # gets the area of the country
    #
    def getArea(self):
        return self._area
    ##
    # gets the population of a country
    #
    def getPopulation(self):
        return self._pop
    ##
    # gets the continent of a country
    #
    def getContinent(self):
        return self._continent
    ##
    # computes the population density (pop/area) and returns this value.
    #
    def getPopDensity(self):
        return self._pop/self._area

    ##
    # given another country,
    #

##
# Class: CountryCatalogue.  It stores a catalogue of countries.
#
class CountryCatalogue:

    ##
    # The constructor
    #
    def __init__(self, continentFileName, countryFileName):
        self.cDictionary = {} #creating the empty dictionary and set, we will fill these with information from files continent and data.
        self.catalogue = set([])

        #step 1 - fill the dictionary from continent.txt
        continentFile = open(continentFileName,"r")
        continentFile.readline() #getting rid of the header line
        line = continentFile.readline() #first line!
        #now read each country-continent pair.
        while line!="":
            line = line.strip() #removing new line character at end of line
            continentTokens = line.split(",") #splitting on the comma.  First element is the country, second element is the continent.
            self.cDictionary[continentTokens[0]] = continentTokens[1] #add country as key with value that is the continent it belongs to.
            line=continentFile.readline()
        continentFile.close()

        #step 2 - fill the catalogue, reads from filename (a parameter of the constructor)
        countryFile = open(countryFileName,"r")
        countryFile.readline() #reading the header line
        line = countryFile.readline()
        while line!="":
            line = line.strip() #removing the new line character
            countryTokens = line.split("|") #split on the vertical bar!
            countryName=countryTokens[0] #first token is the country name
            countryPop = int(countryTokens[1].replace(",","")) #first remove the commas then convert to int
            countryArea = float(countryTokens[2].replace(",","")) #first remove the commas then convert to float
            country = Country(countryName,countryPop,countryArea,self.cDictionary[countryName]) #create the country using the data
            self.catalogue.add(country) #add it to the set
            line=countryFile.readline()
        countryFile.close()

    ##
    #  Returns a list of countries that are a part of the given continent
    #
    def filterCountriesByContinent(self,continentName):
        listContinent = []
        for country in self.catalogue:
            if continentName==country.getContinent():
                listContinent.append(country)
        return listContinent

    def printCountryCatalogue(self):
        print("Printing the country catalogue...")
        for country in self.catalogue:
            print(country)

    ##
    # finding a country, return the element if found, None otherwise is returned.
    #
    def findCountry(self,countryName):
        #is it in the dictionary?
        if countryName in self.cDictionary:
            #now we are going to find the country and print out its information
            for element in self.catalogue:
                if countryName == element.getName():
                    #we found the country
                    return element
        return None

    ##
    # delete a country - print to user if successful or not.
    #
    def deleteCountry(self,countryName):
        #check if it exists in the catalogue (important to note that the keys of the dictionary are all the country names, using this instead of searching the set).
        if countryName in self.cDictionary:
            #search for the country in the set
            for element in self.catalogue:
                if countryName==element.getName():
                    self.catalogue.remove(element) #remove this country from the set
                    break
            self.cDictionary.pop(countryName) #remove from the dictionary
            print("Country " + countryName + " has been removed successfully.")
        else:
            print("Country" + countryName + " could not be removed.")

    ##
    # add a country - it cannot add a country if we already have one with the same name.
    #
    def addCountry(self, countryName, countryPopulation, countryArea, countryContinent):

        country = Country(countryName,countryPopulation,countryArea,countryContinent) #create the country using the data
        #now we need to see if the country we have entered is new!
        if country in self.cDictionary:
            return False
        else:
            self.catalogue.add(country) #add it to the set
            self.cDictionary[countryName]=countryContinent #we have a new country, need to make sure to add it to our cDictionary with the continent.
            return True

    ##
    #  given a country name and a new population, update that country's population (if it exists).  Return True if updated, False otherwise.
    #
    def setPopulationOfASelectedCountry(self,countryName, countryPopulation):

        if countryName in self.cDictionary:
            #search for the country and update the information required.
            for country in self.catalogue:
                if countryName == country.getName():
                    #we found it!
                    country.setPopulation(countryPopulation)
                    return True
        return False

    ##
    # saves the catalogue
    # @param filename - file name for saving the file (with extension)
    # @return number of lines written to file.
    #
    def saveCountryCatalogue(self, filename):
        fileWrite = open(filename,"w")
        numberOfItems=0
        for country in self.catalogue:
            fileWrite.write(country.getName()+"|"+country.getContinent()+"|"+str(country.getPopulation())+"|"+str(country.getPopDensity())+"\n")
            numberOfItems+=1
        fileWrite.close()
        return numberOfItems
    ##
    # returns the name of the country with the largest population
    #
    def findCountryWithLargestPop(self):
        maximumPop = 0 #we can assume a country has at least one person.
        nameMax = "" #name of the country with maximum population
        for country in self.catalogue:
            if maximumPop < country.getPopulation():
                maximumPop = country.getPopulation()
                nameMax = country.getName()
        return nameMax
    ##
    # returns name of country with minimum area
    #
    def findCountryWithSmallestArea(self):
        minimumArea = -1 #we will set it to -1 so that we will set the first country automatically to be the minimum found so far
        nameMin = "" #name of the country with smallest area
        for country in self.catalogue:
            if minimumArea == -1:
                minimumArea = country.getArea()
                nameMin = country.getName()
            elif minimumArea > country.getArea():
                minimumArea = country.getArea()
                nameMin = country.getName()
        return nameMin

    ##
    # Finds the continent with the most number of people living in it...
    #
    def findMostPopulousContinent(self):
        #creating a dictionary for each continent...
        continentDict={}
        for country in self.cDictionary:
            continentDict[self.cDictionary[country]] = 0 #setting the population totals of each to 0...
        #now fill in the populations!
        for country in self.catalogue:
            continentDict[country.getContinent()] += country.getPopulation()
        #now find the maximum!
        mostContinent=""
        mostPopulation=0
        for continent in continentDict:
            if continentDict[continent] > mostPopulation:
                mostContinent = continent
                mostPopulation = continentDict[continent]
        return(mostContinent,mostPopulation)#return the continent and its population

    ##
    # returns a list of the countries between a numeric range (lower and upper bounds) with a certain population density between the two (inclusively)
    #
    def filterCountriesByPopDensity(self,lowerBound,upperBound):
        listPopDensity=[]
        for country in self.catalogue:
            if country.getPopDensity() >= lowerBound and country.getPopDensity() <= upperBound:
                listPopDensity.append(country) #it is a country with population density between lowerBound and upperBound, inclusively
        return listPopDensity
