
/*************************************************************************
 *  Binary Search Tree class.
 *  Adapted from Sedgewick and Wayne.
 *
 *  @version 3.0 1/11/15 16:49:42
 *
 *  @author Joshua Stafford
 *
 *************************************************************************/

//Changed the Algorithms&DataStructures assignment BST to add LCA function.

//import java.util.NoSuchElementException;

public class BST<Key extends Comparable<Key>, Value> {
	private Node root; // root of BST

	/**
	 * Private node class.
	 */
	private class Node {
		private Key key; // sorted by key
		private Value val; // associated data
		private Node left, right; // left and right subtrees
		private int N; // number of nodes in subtree
		// private Node top; // the up-most Node

		public Node(Key key, Value val, int N) {
			this.key = key;
			this.val = val;
			this.N = N;
		}
	}

	// ***********************************************************************************
	// ***********************************************************************************

	// is the symbol table empty?
	public boolean isEmpty() {
		return size() == 0;
	}

	// ***********************************************************************************
	// ***********************************************************************************

	// return number of key-value pairs in BST
	public int size() {
		return size(root);
	}

	// ***********************************************************************************
	// ***********************************************************************************

	// return number of key-value pairs in BST rooted at x
	private int size(Node x) {
		if (x == null)
			return 0;
		else
			return x.N;
	}

	// ***********************************************************************************
	// ***********************************************************************************

	/**
	 * Search BST for given key. Does there exist a key-value pair with given
	 * key?
	 *
	 * @param key
	 *            the search key
	 * @return true if key is found and false otherwise
	 */
	public boolean contains(Key key) {
		/*
		 * if(key == null){ return false; }
		 */

		return get(key) != null;
	}

	// ***********************************************************************************
	// ***********************************************************************************

	/**
	 * Search BST for given key. What is the value associated with given key?
	 *
	 * @param key
	 *            the search key
	 * @return value associated with the given key if found, or null if no such
	 *         key exists.
	 */
	public Value get(Key key) {
		return get(root, key);
	}

	private Value get(Node x, Key key) {
		if (x == null)
			return null;
		int cmp = key.compareTo(x.key);
		if (cmp < 0)
			return get(x.left, key);
		else if (cmp > 0)
			return get(x.right, key);
		else
			return x.val;
	}

	// ***********************************************************************************
	// ***********************************************************************************

	/**
	 * Insert key-value pair into BST. If key already exists, update with new
	 * value.
	 *
	 * @param key
	 *            the key to insert
	 * @param val
	 *            the value associated with key
	 */
	public void put(Key key, Value val) {
		/*if (val == null) {
			delete(key);
			return;
		}*/
		root = put(root, key, val);
	}

	private Node put(Node x, Key key, Value val) {
		if (x == null){
			return new Node(key, val, 1);
		}
		int cmp = key.compareTo(x.key);
		if (cmp < 0){
			x.left = put(x.left, key, val);
		}
		else if (cmp > 0){
			x.right = put(x.right, key, val);
		}
		else{
			x.val = val;
		}
		x.N = 1 + size(x.left) + size(x.right);
		return x;
	}

	// ***********************************************************************************
	// ***********************************************************************************

	/**
	 * Tree height.
	 *
	 * Asymptotic worst-case running time using Theta notation: TODO Theta(N)
	 * 
	 *     This is because the function runs at Theta(1) but when it is called recursively it will be theta(N)
	 *
	 * @return the number of links from the root to the deepest leaf.
	 *
	 *         Example 1: for an empty tree this should return -1. Example 2:
	 *         for a tree with only one node it should return 0. Example 3: for
	 *         the following tree it should return 2. B / \ A C \ D
	 */
	public int height() {
		// TODO fill in the correct implementation.
		if (isEmpty()) {

			return -1;

		}
		
		else {

			return height(root);

		}
	}

	private int height(Node x) {

		if(x.left == null && x.right == null){
			
			return 0;
			
		}
		
		int leftSide = 0;
		int rightSide = 0;
		// Node k = root;
		
		if(x.left != null){
			
			leftSide = height(x.left);
			
		}
		if(x.right != null){
			
			rightSide = height(x.right);
			
		}
		if(leftSide >= rightSide){
			
			return 1 + leftSide;
			
		}
		else{
			
			return 1 + rightSide;
			
		}
		

	}

	// ***********************************************************************************
	// ***********************************************************************************
	
	/**
     * Median key.
     * If the tree has N keys k1 < k2 < k3 < ... < kN, then their median key 
     * is the element at position (N+1)/2 (where "/" here is integer division)
     *
     * @return the median key, or null if the tree is empty.
     */
    public Key median() {
    	//TODO fill in the correct implementation. The running time should be Theta(h), where h is the height of the tree.
    	
    	if (isEmpty()){
    		return null;
    	}
    	
    	if(size() == 1){
    		return root.key;
    	}
    	
    	int medianValue = (root.N + 1)/2; // (N+1) given above this method but (N-1) given on website
      
    	return median(medianValue, root);
    }

    
    private Key median(int medianValue, Node x){
    	
    		
    	int sizeLeft = size(x.left);
    	
    	if(sizeLeft < medianValue){
    		
    		return median(medianValue-sizeLeft-1, x.right);
    		
    	}
    	else if(sizeLeft > medianValue){
    		
    		return median(medianValue, x.left);
    		
    	}
    	else{
    		
    		return x.key;
    		
    	}
    	
    }
	// ***********************************************************************************
	// ***********************************************************************************

	/**
	 * Print all keys of the tree in a sequence, in-order. That is, for each
	 * node, the keys in the left subtree should appear before the key in the
	 * node. Also, for each node, the keys in the right subtree should appear
	 * before the key in the node. For each subtree, its keys should appear
	 * within a parenthesis.
	 *
	 * Example 1: Empty tree -- output: "()" Example 2: Tree containing only "A"
	 * -- output: "(()A())" Example 3: Tree: B / \ A C \ D
	 *
	 * output: "((()A())B(()C(()D())))"
	 *
	 * output of example in the assignment:
	 * (((()A(()C()))E((()H(()M()))R()))S(()X()))
	 *
	 * @return a String with all keys in the tree, in order, parenthesized.
	 */
	public String printKeysInOrder() {
		if (isEmpty()){
			return "()";
		}
		// TODO fill in the correct implementation

		if(size() == 1){
			
			return "(()" + root.val + "())";
			
		}
		
		Node start = root;
		String result = printKeysInOrder(start);
		
		
		return "(" + result + ")";
	
	}
	
	private String printKeysInOrder(Node x){
		
		String leftSide = "";
		String rightSide = "";
		
		if(x.left != null){
			leftSide = printKeysInOrder(x.left);
		}
		if(x.right != null){
			rightSide = printKeysInOrder(x.right);
		}
		String toReturn = "(" + leftSide + ")" + x.key + "(" + rightSide + ")";
		
		return toReturn;

	}

	// ***********************************************************************************
	// ***********************************************************************************

	/**
	 * Pretty Printing the tree. Each node is on one line -- see assignment for
	 * details.
	 *
	 * @return a multi-line string with the pretty ascii picture of the tree.
	 */
	public String prettyPrintKeys() {
		// TODO fill in the correct implementation.
		String prefix = "";
		
		if(isEmpty()){
			
			return "-null\n";
			
		}
		String result = prettyPrintKeys(root, prefix) + "\n";
		
		return result;

	}
	
	private String prettyPrintKeys(Node x, String prefix){
		
		String end = "";
		
		if(x != null){
			
			String leftSide = prefix + " |";
			String rightSide = prefix + " ";
			
			end = prefix + "-" + x.key + "\n";
			end = end + prettyPrintKeys(x.left, leftSide) + "\n";
			end = end + prettyPrintKeys(x.right, rightSide);
			
		}
		else{
			
			end = prefix + "-null";
			
		}
		
		return end;
	
	}

	// ***********************************************************************************
	// ***********************************************************************************

	/**
	 * Deteles a key from a tree (if the key is in the tree). Note that this
	 * method works symmetrically from the Hibbard deletion: If the node to be
	 * deleted has two child nodes, then it needs to be replaced with its
	 * predecessor (not its successor) node.
	 *
	 * @param key
	 *            the key to delete
	 */
	public void delete(Key key) {
		// TODO fill in the correct implementation.
		root = delete(root, key);

	}

	private Node delete(Node x, Key key){
    	
    	if(x == null){
    		return null;
    	}
    	
    	int compare = key.compareTo(x.key);
    	
    	if(compare < 0){
    		
    		x.left = delete(x.left, key);
    		 		
    	}
    	else if(compare > 0){
    		
    		x.right = delete(x.right, key);
    		
    	}
    	else{
    		
    		if(x.right == null){
    			
    			return x.left;
    			
    		}
    		if(x.left == null){
    			
    			return x.right;
    			
    		}
    		
    		Node copy = x;
    		Node tempNode = copy.left;
    		
    		if(tempNode.right == null){
    			
    			x = tempNode.left;
    			
    		}
    		
    		while(tempNode.right != null){
    			
    			x = tempNode.right;
    			tempNode = tempNode.right;
    			
    		}
    		
    		x.left = deleteMax(copy.left);
    		x.right = copy.right;
    		
    	}
    	
    	x.N = size(x.right) + 1 + size(x.left);
    	
    	return x;
    	
    }

	// ***********************************************************************************
	// ***********************************************************************************

	public void deleteMax(){
		
		root = deleteMax(root);
	
	}
	
	private Node deleteMax(Node x){
		
		if(x.right == null){
			
			return x.left;
			
		}
		x.right = deleteMax(x.right);
		x.N = size(x.left) + 1 + size(x.right);
		
		return x;
		
	}
	
	public Key lca(Key a, Key b){
		
		if(root == null){
			return null;
		}
		if(!contains(a)){
			return null;
		}
		if(!contains(b)){
			return null;
		}
		return lca(root, a, b);
		
	}
	
	private Key lca(Node n, Key a, Key b){
		
		int checkA = n.key.compareTo(a);
		int checkB = n.key.compareTo(b);
		
		if(checkA < 0 && checkB <0){
			return lca(n.right, a, b);
		}
		else if(checkA > 0 && checkB > 0){
			return lca(n.left, a, b);
		}
		else{
			return n.key;
		}
		
	}
	
}