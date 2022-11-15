/*
 * This class represents an individual node in hash table.
 */
public class Node {
	
	public int key; // Assumes that these are only positive.
	public Node next;
	
	Node (int _key, Node _next) {
		key = _key;
		next = _next;
	}
}
