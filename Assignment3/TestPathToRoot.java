import java.io.*;
import java.util.Iterator;

/**
 * This class will test all possible nodes in the tree for their path to the
 * root
 * 
 * The file specified by arg[0] is used.
 * 
 * @author beth
 */

public class TestPathToRoot {

	public static void main(String[] args) {
		LinkedBinaryTree<String> lbt = null;
		TreeBuilder tb = null;

		try {
			tb = new TreeBuilder(args[0]);
			lbt = tb.buildTree();
		} catch (MalformedTreeFileException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println("Error reading file: " + args[0] + "\n" + e.getMessage());
		}

		// Get an iterator of elements to find the path-to-root solution for
		Iterator<String> treeElements = lbt.iteratorInOrder();
		String current;

		while (treeElements.hasNext()) {
			current = treeElements.next();
			try {
				Iterator<String> pathFromCurrent = lbt.pathToRoot(current);
				System.out.print("For element: " + current + " - the path to the root is: ");
				while (pathFromCurrent.hasNext()) {
					System.out.print(pathFromCurrent.next() + " ");
				}
			} catch (ElementNotFoundException e) {
				System.out.println("Could not find element: " + current + "in the tree" + e.getMessage());
			}
			System.out.println();
		}

		// test an element not in the tree
		try {
			Iterator<String> pathFromCurrent = lbt.pathToRoot("!");
		} catch (ElementNotFoundException e) {
			System.out.println("Could not find element: ! (as expected): " + e.getMessage());
		}
	
	}

}
