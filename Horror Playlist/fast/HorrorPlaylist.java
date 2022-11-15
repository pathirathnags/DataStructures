import java.util.Scanner;

public class HorrorPlaylist {
	private static int n; // # videos in the playlist
	private static int k; // # types of videos
	private static Node [] chunkslist; // array of linked lists. chunkslist[t] is a linked list of chunks of type t
	private static int [] counts; // array of counts. counts[t] stores the number of chunks for type t
	private static int num_clicks; // number of clicks

	/**
		Creates the very first chunk
	**/
	private static Node create_first_chunk(int type, int start, int end) {
		chunkslist[type] = new Node(type, start, end);
		++counts[type];
		return chunkslist[type];
	}

	/**
		Creates a subsequent chunk
	**/
	private static Node create_new_chunk(int type, int start, int end, Node before) {
		chunkslist[type] = new Node(type, start, end, chunkslist[type], before);
		++counts[type];
		return chunkslist[type];
	}

	/**
		Prints chunkslist. Used for testing.
	**/
	private static void printChunklist() {
		for (int i = 1; i <= k; ++i) {
			System.out.print(i + ": ");
			for (Node cur = chunkslist[i]; cur != null; cur = cur.next) {
				System.out.print("--> " + cur);
			}
			System.out.println();
		}
	}

	/**
		Updates chunk x. This is in essence looking at before and after chunks and updating their information.
	**/
	private static void updateChunk(Node x, int t) {
		Node bef = x.before; // get a handle to the chunk before
		Node aft = x.after; // get a handle to the chunk after 

		// take care of null cases first
		if (bef == null || aft == null) {
			if (aft != null) {
				aft.before = null;
			}
			else if (bef != null) {
				bef.after = null;
			}
			return;
		}
		
		// The chunk before and after x are of the same type.
		// So if we watched type t, then the before and after chunks need to be combined.
		if (bef.video_type == aft.video_type) {
			// need to combine x.before and x.after into one node.
			// copy x.before's data into x.after's data...
			aft.start = bef.start;
			aft.next = bef.next; // this should be the same as aft.next.next. In essence, removes bef.
			if (aft.next != null)
				aft.next.prev = aft;
			aft.before = bef.before; // update before
			if (aft.before != null)
				aft.before.after = aft;

			// Since we combined two nodes into one, we need to update the clicks
			--counts[aft.video_type];
		}
		else {
			// update bef.after and aft.before to point to each other.
			// these are of different types.
			bef.after = aft;
			aft.before = bef;
		}
		return;
	}
	
	/**
		Compute the number of clicks (or chunks) to watch type t.
		This is simply iterating over all chunks of type t (in t's linked list).
		Need to update these chunks quickly (if you removed a chunk, then
		you need to update the before and after chunks.
	**/
	private static void processType(int t) {
		num_clicks += counts[t];
		for (Node cur = chunkslist[t]; cur != null; cur = cur.next) {
			updateChunk(cur, t);
		}
	}

	public static void main(String [] args) {
		// accept input
		Scanner kb = new Scanner(System.in);
		n = kb.nextInt();
		k = kb.nextInt();
		chunkslist = new Node[k+1];
		counts = new int[k+1];

		// get the first video and the first chunk
		int prev_type = kb.nextInt();
		int start = 0;
		int i = 1; // loop variable to go through the playlist and create chunks
		int type = prev_type;
		while (i < n && type == prev_type) {
			type = kb.nextInt();
			++i;
		}
		// create first chunk
		Node before_chunk = create_first_chunk(prev_type, start, i-1);
		prev_type = type;
		start = i;

		while (i < n) {
			type = kb.nextInt();
			if (type != prev_type) {
				// create a chunk for the prev_type
				before_chunk = create_new_chunk(prev_type, start, i-1, before_chunk);
				prev_type = type;
				start = i;
			}
			++i;
		}
		// create last chunk
		before_chunk = create_new_chunk(prev_type, start, n-1, before_chunk);

		num_clicks = 0;
		for (i = 0; i < k; ++i) {
			type = kb.nextInt();
			processType(type); // watch this type. update the chunks and find number of clicks 
			// to watch this type
		}
		System.out.println(num_clicks);
	}
}
