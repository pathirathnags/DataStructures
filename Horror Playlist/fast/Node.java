
/*
	 This class stores a chunk, a sequence of contiguous videos
	 of the same type.
*/
public class Node {
	public int video_type; // video type
	public int start; // starting index of a chunk
	public int end; // ending index of a chunk
	public Node next; // points to the next chunk of the same type
	public Node prev; // points to the previous chunk of the same type
	public Node before; // points to the chunk before this chunk in the playlist
	public Node after; // points to the chunk after this chunk in the playlist

	/**
		Used to create the very first chunk
	**/
	public Node(int t, int s, int e) {
		video_type = t;
		start = s;
		end = e;

		prev = next = null;
		before = after = null;
	}

	/**
		Constructor to create a chunk (except the very first one)
	**/
	public Node(int t, int s, int e, Node n, Node b) {
		video_type = t;
		start = s;
		end = e;
		
		next = n;
		if (next != null)
			next.prev = this;

		before = b;
		if (before != null)
			before.after = this;
	}

	/**
		Helper method for toString
	**/
	private String toStringHelper(Node x) {
		String ret = "";
		if (x == null)
			ret = ret + "null";
		else
			ret = ret + "<" + x.video_type + "," + x.start + "," + x.end + ">";
		return ret;
	}
	
	/**
		returns a string representation of this object.
		Used for testing.
	**/
	public String toString() {
		String ret = "(" + video_type + "," + start + "," + end + ",";
		ret = ret + toStringHelper(next) + "," + toStringHelper(prev) + "," + toStringHelper(before) + "," + toStringHelper(after) + ")";
		return ret;
	}
}
