
import java.util.Scanner;

public class CandyCollector {
	private static int k; // number of different types of candy
	private static int n; // total number of candies collected

	public static void main(String [] args) {
		Scanner kb = new Scanner(System.in);
		k = kb.nextInt();
		n = kb.nextInt();

		HashTable t = new HashTable(k);
		int count = 0; // counting the different types of candy
		while (kb.hasNext()) {
			int x = kb.nextInt();
			if (!t.find(x)) {
				t.insert(x);
				++count;
			}
		}
		if (k == count)
			System.out.println("Yay! all " + k + " type(s) of candies collected.");
		else
			System.out.println("Missing " + (k-count) + " type(s) of candy.");
	}
}
