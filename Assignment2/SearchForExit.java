import java.io.FileNotFoundException;
import java.io.IOException;

/** 
 * The SearchForExit class finds the starting tile in the Labyrinth specified and goes through all surrounding 
 * tiles, adding them to the stack, and processing the tiles in the stack until the exit is found
 * 
 * @author sahme339
 * @throws UnknownLabyrinthCharacterException
 * @throws FileNotFoundException
 * @throws IOException
 */

public class SearchForExit {

	// the main method is declared
	public static void main(String[] args) {
		
		//Labyrinth object reference is created 
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
			
			// setting name of inFile to variable for later reference
			String labFileName = args[0];
			
			// creates ArrayStack to add tiles as Labyrinth is being processed 
			ArrayStack<Hexagon> labStack = new ArrayStack<Hexagon>();
			
			// finds the starting tile, add it to the stack so stack is not empty to start, and set tile as pushed
			Hexagon startingTile = tiles.getStart();
			labStack.push(startingTile);
			startingTile.setPushed();

			// boolean created to keep track of exit being found  
			boolean found = false;
			
			// keep count of the number of steps taken 
			int count = 0;

			// while loop created which keeps searching if stack is not empty and exit is not found
			while (!labStack.isEmpty() && found != true) {
				Hexagon currentTile;
				
				// pops the topmost tile to analyze it 
				currentTile = labStack.pop();
				
				// if statement which checks if current tile is end tile and stops searching
				if (currentTile.isEnd()) {
					found = true;
				}
				
				// if statement which checks if current tile is not end tile and adds neighbouring tiles to stack
				if (!currentTile.isEnd()) {
					for (int i = 0; i < 6; i++) {
						Hexagon neighbourTile = currentTile.getNeighbour(i);
						if (neighbourTile != null && neighbourTile.isUnvisited() && !neighbourTile.isWall()) {
							labStack.push(neighbourTile);
							neighbourTile.setPushed();
						}
					}	
				}
				
				// regardless of end tile or not, this section sets currentTile as processed and adds +1 to the steps taken
				currentTile.setProcessed();
				count++;
			}
			
			// if the stack is empty and exit is still not found, print appropriate message
			if (labStack.isEmpty() && found != true) {
				System.out.println("The exit was not found!");

			}
			
			// if the exit is found, statement is printed with the steps takes, and size of stack
			else {
				System.out.println("The exit was found in " + count + " steps, with " + labStack.size() + " tiles left in the stack!");
			}
			
			// saves the Labyrinth after it is processed to text file 
			tiles.saveLabyrith("processed_" + labFileName);
			
			// repaints the tiles so program can be run again 
			tiles.repaint();

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
