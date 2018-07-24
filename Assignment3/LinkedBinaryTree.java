/**
 * 
 */
/**
 * LinkedBinaryTree implements the BinaryTreeADT interface with the pathToRoot, pathToRootAgain, lowestCommonAncestor, and shortestPath methods added
 * 
 * @author Syed Ahmed 250897473
 * 
 * @version 1.1, 7/23/18
 * @throws ElementNotFoundException
 */

import java.util.Iterator;

public class LinkedBinaryTree<T> implements BinaryTreeADT<T> {
	protected int count;
	protected BinaryTreeNode<T> root;

	/**
	 * Creates an empty binary tree.
	 */
	public LinkedBinaryTree() {
		count = 0;
		root = null;
	}

	/**
	 * Creates a binary tree with the specified element as its root.
	 *
	 * @param element
	 *            the element that will become the root of the new binary tree
	 */
	public LinkedBinaryTree(T element) {
		count = 1;
		root = new BinaryTreeNode<T>(element);
	}

	/**
	 * Constructor creates tree from element as root and two subtrees as left and
	 * right subtrees of root.
	 * 
	 * @param element
	 *            element at root
	 * @param leftSubtree
	 *            left subtree
	 * @param rightSubtree
	 *            right subtree
	 */

	public LinkedBinaryTree(T element, LinkedBinaryTree<T> leftSubtree, LinkedBinaryTree<T> rightSubtree) {
		root = new BinaryTreeNode<T>(element);
		count = 1;
		if (leftSubtree != null) {
			count = count + leftSubtree.size();
			root.left = leftSubtree.root;
		} else
			root.left = null;

		if (rightSubtree != null) {
			count = count + rightSubtree.size();
			root.right = rightSubtree.root;
		} else
			root.right = null;

	}

	/**
	 * Returns a reference to the element at the root
	 *
	 * @return a reference to the specified target
	 * @throws EmptyCollectionException
	 *             if the tree is empty
	 */
	public T getRoot() throws EmptyCollectionException {
		return root.getElement();
	}

	/**
	 * Returns true if this binary tree is empty and false otherwise.
	 *
	 * @return true if this binary tree is empty
	 */
	public boolean isEmpty() {
		return count == 0;
	}

	/**
	 * Returns the integer size of this tree.
	 *
	 * @return the integer size of this tree
	 */
	public int size() {
		return count;
	}

	/**
	 * Returns true if this tree contains an element that matches the specified
	 * target element and false otherwise.
	 *
	 * @param targetElement
	 *            the element being sought in this tree
	 * @return true if the element in is this tree
	 * @throws ElementNotFoundException
	 *             if an element not found exception occurs
	 */
	public boolean contains(T targetElement) {
		try {
			T junk = this.find(targetElement);
		} catch (ElementNotFoundException e) {
			return false;
		}
		return true;
	}

	/**
	 * Returns a reference to the specified target element if it is found in this
	 * binary tree. Throws a NoSuchElementException if the specified target element
	 * is not found in the binary tree.
	 *
	 * @param targetElement
	 *            the element being sought in this tree
	 * @return a reference to the specified target
	 * @throws ElementNotFoundException
	 *             if an element not found exception occurs
	 */
	public T find(T targetElement) throws ElementNotFoundException {
		BinaryTreeNode<T> current = findAgain(targetElement, root);

		if (current == null)
			throw new ElementNotFoundException("binary tree");

		return (current.element);
	}

	/**
	 * Returns a reference to the specified target element if it is found in this
	 * binary tree.
	 *
	 * @param targetElement
	 *            the element being sought in this tree
	 * @param next
	 *            the element to begin searching from
	 */
	private BinaryTreeNode<T> findAgain(T targetElement, BinaryTreeNode<T> next) {
		if (next == null)
			return null;

		if (next.element.equals(targetElement))
			return next;

		BinaryTreeNode<T> temp = findAgain(targetElement, next.left);

		if (temp == null)
			temp = findAgain(targetElement, next.right);

		return temp;
	}

	/**
	 * Returns a string representation of this binary tree.
	 *
	 * @return a string representation of this binary tree
	 */
	public String toString() {
		Iterator<T> temp = iteratorInOrder();
		StringBuilder toReturn = new StringBuilder();
		while (temp.hasNext())
			toReturn.append(temp.next());
		return toReturn.toString();
	}

	/**
	 * Performs an inorder traversal on this binary tree by calling an overloaded,
	 * recursive inorder method that starts with the root.
	 *
	 * @return an in order iterator over this binary tree
	 */
	public Iterator<T> iteratorInOrder() {
		ArrayUnorderedList<T> tempList = new ArrayUnorderedList<T>();
		inorder(root, tempList);

		return tempList.iterator();
	}

	/**
	 * Performs a recursive inorder traversal.
	 *
	 * @param node
	 *            the node to be used as the root for this traversal
	 * @param tempList
	 *            the temporary list for use in this traversal
	 */
	protected void inorder(BinaryTreeNode<T> node, ArrayUnorderedList<T> tempList) {
		if (node != null) {
			inorder(node.left, tempList);
			tempList.addToRear(node.element);
			inorder(node.right, tempList);
		}
	}

	/**
	 * Performs an preorder traversal on this binary tree by calling an overloaded,
	 * recursive preorder method that starts with the root.
	 *
	 * @return an pre order iterator over this tree
	 */
	public Iterator<T> iteratorPreOrder() {
		ArrayUnorderedList<T> tempList = new ArrayUnorderedList<T>();

		preorder(root, tempList);

		return tempList.iterator();
	}

	/**
	 * Performs a recursive preorder traversal.
	 *
	 * @param node
	 *            the node to be used as the root for this traversal
	 * @param tempList
	 *            the temporary list for use in this traversal
	 */
	protected void preorder(BinaryTreeNode<T> node, ArrayUnorderedList<T> tempList) {
		if (node != null) {
			tempList.addToRear(node.getElement());
			preorder(node.left, tempList);
			preorder(node.right, tempList);
		}
	}

	/**
	 * Performs an postorder traversal on this binary tree by calling an overloaded,
	 * recursive postorder method that starts with the root.
	 *
	 * @return a post order iterator over this tree
	 */
	public Iterator<T> iteratorPostOrder() {
		ArrayUnorderedList<T> tempList = new ArrayUnorderedList<T>();
		postorder(root, tempList);
		return tempList.iterator();
	}

	/**
	 * Performs a recursive postorder traversal.
	 *
	 * @param node
	 *            the node to be used as the root for this traversal
	 * @param tempList
	 *            the temporary list for use in this traversal
	 */
	protected void postorder(BinaryTreeNode<T> node, ArrayUnorderedList<T> tempList) {
		if (node != null) {
			postorder(node.left, tempList);
			postorder(node.right, tempList);
			tempList.addToRear(node.getElement());
		}
	}

	/**
	 * Performs a levelorder traversal on this binary tree, using a templist.
	 *
	 * @return a levelorder iterator over this binary tree
	 */
	public Iterator<T> iteratorLevelOrder() {
		ArrayUnorderedList<BinaryTreeNode<T>> nodes = new ArrayUnorderedList<BinaryTreeNode<T>>();
		ArrayUnorderedList<T> result = new ArrayUnorderedList<T>();
		BinaryTreeNode<T> current;
		nodes.addToRear(root);
		while (!nodes.isEmpty()) {
			current = (BinaryTreeNode<T>) nodes.removeFirst();
			if (current != null) {
				result.addToRear(current.getElement());
				nodes.addToRear(current.left);
				nodes.addToRear(current.right);
			} else {
				result.addToRear(null);
			}
		}
		return result.iterator();
	}

	/**
	 * Performs a search for the target element and find nodes from root to target
	 * @param targetElement looks for element being searched 
	 * @return iterator which has elements from root to target 
	 * @throws ElementNotFoundException
	 */
	public Iterator<T> pathToRoot(T targetElement) throws ElementNotFoundException {
		
		//create array list to store the path to the root 
		ArrayUnorderedList<T> pathToRoot = new ArrayUnorderedList<T>();
		
		//use pathToRootAgain method to fill up pathToRoot
		pathToRootAgain(targetElement, root, pathToRoot);
		
		//catch exception if element is not found 
		if (pathToRoot.isEmpty() == true) {
			throw new ElementNotFoundException("Binary Tree");
		}
		
		//return iterator filled with elements
		return pathToRoot.iterator();
	}

	/**
	 * Searches pathToRootAgain for the target element and storing nodes
	 * @param targetElement looks for element being searched
	 * @param node
	 * @param pathToRoot refers to pathToRoot created in pathToRoot method 
	 */
	protected void pathToRootAgain(T targetElement, BinaryTreeNode<T> node, ArrayUnorderedList<T> pathToRoot) {
		
		//check that current node is not empty
		if (node != null) {
			
			//check to see if node is equal to targetElement 
			if (node.element.equals(targetElement)) {
				
				//add to pathToRoot if it equals 
				pathToRoot.addToRear(node.element);
			} else {
				
				//check left child 
				pathToRootAgain(targetElement, node.left, pathToRoot);
				
				// if it is empty, then check right child 
				if (pathToRoot.isEmpty()) {
					pathToRootAgain(targetElement, node.right, pathToRoot);
				}
				
				// if pathToRoot is not empty, add to rear
				if (!pathToRoot.isEmpty()) {
					pathToRoot.addToRear(node.element);
				}
			}
		}
	}

	/**
	 * Finds the lowestCommonAncestor of node which is between two ancestors 
	 * @param targetOne
	 * @param targetTwo
	 * @return
	 * @throws ElementNotFoundException
	 */
	public T lowestCommonAncestor(T targetOne, T targetTwo) throws ElementNotFoundException {
		
		//iterate through both targets 
		Iterator<T> one = pathToRoot(targetOne);
		Iterator<T> two = pathToRoot(targetTwo);

		//create list of elements on the first path
		ArrayUnorderedList<T> onPathOne = new ArrayUnorderedList<T>();

		//storing the elements and adding to the rear
		while (one.hasNext()) {
			onPathOne.addToRear(one.next());
		}

		//searches through the second list and stores it in temp 
		while (two.hasNext()) {
			T temp = two.next();

			//if element is on both paths, return temp
			if (onPathOne.contains(temp)) {
				return temp;
			}
		}

		//return the lowest common ancestor using above procedures 
		return root.element;
	}

	/**
	 * This method finds the shortest path possible between the two elements which are being targetted 
	 * @param targetOne
	 * @param targetTwo
	 * @return iterator of results conveying shortest path 
	 * @throws ElementNotFoundException
	 */
	public Iterator<T> shortestPath(T targetOne, T targetTwo) throws ElementNotFoundException {
		
		//create the list, result, to store 
		ArrayUnorderedList<T> result = new ArrayUnorderedList<T>();
		
		//find the lowest common ancestor between two targets 
		T ancestor = lowestCommonAncestor(targetOne, targetTwo);
		
		Iterator<T> iteratFirst = pathToRoot(targetOne);
		
		//variable used to check element in iteratFirst
		T checkFirst = null;
		
		//used to add to result list all elements until ancestor to rear
		while(iteratFirst.hasNext() && !ancestor.equals(checkFirst)) {
			checkFirst = iteratFirst.next();
			result.addToRear(checkFirst);
		}
		
		Iterator<T> iteratSecond = pathToRoot(targetTwo);
		
		//create temporary stack for iteratSecond in reverse 
		ArrayStack<T> tempStack = new ArrayStack<T>();
		
		//variable used to check element in iteratSecond 
		T checkSecond = null;
		
		//used to add to tempStack the elements in iteratSecond 
		while(iteratSecond.hasNext() && !ancestor.equals(checkSecond)) {
			checkSecond = iteratSecond.next();
			if (!checkSecond.equals(ancestor)) {
				tempStack.push(checkSecond);
			}
		}
		
		//add the tempStack values to result 
		while(!tempStack.isEmpty()) {
			result.addToRear(tempStack.pop());
		}
		
		//return iterator as result
		return result.iterator();
	}
}
