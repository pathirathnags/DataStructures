
import java.util.Scanner;

public class HorrorPlaylist {
	private static int n; // total number of videos in the playlist
	private static int k; // total types of videos
	private static int [] playlist; // video playlist
	private static int [] helper;
	private static int length; // keeps track of the length of the playlist after removing a video type

	/**
		Go through the playlist and compute # chunks of type t.
		Then remove these from the playlist (can be done by copying the sequence without 
		type t videos onto helper, then swap references of helper and playlist).
		Return the # chunks or clicks.
	**/
	private static int processType(int t) {

		int prev_type = playlist[0];
		int clicks = 0; // number of clicks
		int j = 0; // loop variable for helper

		// if the first video is type t, then click it.
		if (prev_type == t)
			clicks = 1;
		else 
			helper[j++] = playlist[0]; // only copy types not t
		
		// find number of clicks for the rest.

		for (int i = 1; i < length; ++i) {
			int type = playlist[i];
			if (prev_type == type) {
				if (type != t)
					helper[j++] = playlist[i]; // copying other types
				continue;
			}
			if (type == t) {
				++clicks;
			}
			else {
				helper[j++] = playlist[i];
			}
			prev_type = type;
		}
		// update length
		length = j;

		// swap playlist and helper
		int [] temp = playlist;
		playlist = helper;
		helper = temp;

		return clicks;
	}
	
	public static void main(String [] args) {
		// accept input
		Scanner kb = new Scanner(System.in);
		n = kb.nextInt();
		k = kb.nextInt();
		playlist = new int[n];
		helper = new int[n];

		for (int i = 0; i < n; ++i)
			playlist[i] = kb.nextInt();

		length = n;
		int clicks = 0;
		for (int i = 0; i < k; ++i) {
			int type = kb.nextInt();
			clicks += processType(type);
		}
		System.out.println(clicks);
	}
}
