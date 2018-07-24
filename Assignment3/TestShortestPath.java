
import java.io.*;
import java.util.Iterator;
/** 
 * This class will test all possible pairs in the
 * tree to find the shortest path between them
 * 
 * The file specified by arg[0] is used.
 * 
 * @author beth
 */

public class TestShortestPath {

	public static void main(String[] args) {
		LinkedBinaryTree<String> lbt = null;
		TreeBuilder tb = null;

		try{
			tb = new TreeBuilder(args[0]);
			lbt = tb.buildTree();
		}
		catch( MalformedTreeFileException e) {
			System.out.println( e.getMessage());
		}
		catch( IOException e){
			System.out.println("Error reading file: " + args[0] + "\n" + e.getMessage());
		}

		Iterator<String> treeElementsOne = lbt.iteratorInOrder();

		//These will hold the current elements we are testing
		String currentOne = null;
		String currentTwo = null;

		while(treeElementsOne.hasNext()){
			// Get the next from one iterator
			currentOne = treeElementsOne.next();

			// Create a second iterator of elements to compare this currentOne to
			Iterator<String> treeElementsTwo = lbt.iteratorInOrder();

			//go past all elements "before" the currentOne in the second iterator
			// to prevent duplicated pairs overall
			while(treeElementsTwo.hasNext()&& !treeElementsTwo.next().equals(currentOne));

			// Now we can start comparisons
			while(treeElementsTwo.hasNext()) {
				//Get the other element to compare to
				currentTwo = treeElementsTwo.next();
				try{
					Iterator<String> theShortestPath = lbt.shortestPath( currentOne, currentTwo );
					System.out.print( "Shortest path from element: "+ currentOne + " to " + currentTwo + " is: ");
					while(theShortestPath.hasNext()){
						System.out.print( theShortestPath.next() + " ");
					}
					System.out.println();
				}
				catch(ElementNotFoundException e){
					System.out.println( "Could not find element: " + currentOne + " or perhaps " +currentTwo+" in the tree " + e.getMessage());
				}
			}
		}
		//test an element not in the tree
		try{
			Iterator<String> theShortestPath = lbt.shortestPath( "!", "*" );
			}
		catch(ElementNotFoundException e){
			System.out.println( "Error finding path: ! to * (as expected): " + e.getMessage());
		}

	}

}

