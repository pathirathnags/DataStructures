/*
 * This class builds a hash table that is capable of storing
 * integer keys. This is represented as an array of Node references,
 * each reference is a head of a linked list. All collisions are 
 * resolved through chaining.
 */
public class HashTable {

	private Node [] table;			// Hash table - array of references to linked lists.
	private int size;						// Size of the hash table.

	/* Constructor 0 initializes the hash table of the required size. */
	public HashTable (int _size) {
		size = _size;
		table = new Node[size];
	}

	/* Insert a key into the hash table. This is inserted as the new head of the 
	 * linked list at table[i], where i is the hash index ot the key.
	 */
	public void insert(int key) {
		int i = hash(key);
		table[i] = new Node(key, table[i]);
	}

	/* Print the contents of the hash table. Loop through all array elements,
	 * then loop through all the nodes in the linked list.
	 */
	public void print() {
		for (int i = 0; i < size; i++) {
			for (Node curr = table[i]; curr != null; curr = curr.next) 
				System.out.println(curr.key);
		}
	}

	/* Finds a key in the Hash table. Returns true if key is presernt, else
	 * returns false. Checks only the linked list given by the hash index.
	 */
	public boolean find(int key) {
		int i = hash(key);
		for (Node curr = table[i]; curr != null; curr = curr.next)
			if (curr.key == key) return true;
		return false;
	}
	
	/* hash an integer key k. This is along the lines of a universal hash function.
	 * To make it theoretically correct, need to take mod a large prime first, before
	 * doing mod m.
	 */
	private int hash(int k) {
		return (int) ((2917 * (long) k + 101923) % size);
	}

}
