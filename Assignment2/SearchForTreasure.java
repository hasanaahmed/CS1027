import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * The SearchForTreasure class finds the starting tile in the Labyrinth
 * specified and goes through all surrounding tiles, adding them to the stack,
 * and processing the tiles in the stack - adding any treasure present
 * 
 * @author sahme339
 * @throws UnknownLabyrinthCharacterException
 * @throws FileNotFoundException
 * @throws IOException
 */

public class SearchForTreasure {

	// the main method is declared
	public static void main(String[] args) {

		// Labyrinth object reference is created
		Labyrinth tiles;

		// try method is created
		try {

			// ensures that one Labyrinth file is inputed by checking length of argument
			if (args.length < 1) {
				throw new IllegalArgumentException("Please provide a file as a command line argument");
			}

			// inputs the correct file as inFile if argument length is correct
			else {
				String labFileName = args[0];
				tiles = new Labyrinth(labFileName);
			}

			// creates LinkedStack to add tiles as Labyrinth is being processed
			LinkedStack<Hexagon> labStack = new LinkedStack<Hexagon>();

			// finds the starting tile, add it to the stack so stack is not empty to start,
			// and set tile as pushed
			Hexagon startingTile = tiles.getStart();
			labStack.push(startingTile);
			startingTile.setPushed();

			// keep count of tiles processed and treasure collected
			int numTiles = 0;
			int numTreasure = 0;

			// while the stack is not empty, add all neighbouring stacks for processing
			while (!labStack.isEmpty()) {
				Hexagon currentTile;
				currentTile = labStack.pop();
				for (int i = 0; i < 6; i++) {
					Hexagon neighbourTile = currentTile.getNeighbour(i);
					if (neighbourTile != null && neighbourTile.isUnvisited() && !neighbourTile.isWall()) {
						labStack.push(neighbourTile);
						neighbourTile.setPushed();
					}
				}

				// if currentTile has treasure, get it, and add to numTreasure
				if (currentTile.hasTreasure()) {
					numTreasure += currentTile.getTreasure();
				}

				// process the currentTile and increase numTiles
				currentTile.setProcessed();
				numTiles++;

			}

			// once the stack is done procession, print numTiles and numTreasure
			if (labStack.isEmpty()) {
				System.out.println("Number of tiles in labyrinth: " + numTiles + "\n");
				System.out.println("Amount of treasure found: " + numTreasure + "\n");
			}

		}

		// declare catch and print appropriate messages for exceptions
		catch (UnknownLabyrinthCharacterException e) {
			System.out.println("Invalid Labyrinth Character, please try again!");
			e.printStackTrace();
		}
		catch (FileNotFoundException e) {
			System.out.println("File was not found, try again!");
			e.printStackTrace();
		} 
		catch (IOException e) {
			System.out.println("Invalid input or output file, try again!");
			e.printStackTrace();
		}
	}
}
