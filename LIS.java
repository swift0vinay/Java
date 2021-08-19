import java.io.*;
import java.math.*;
import java.util.*;

/*
   author : Multi-Thread
*/
public class LongestIncreasingSub {
//public class Main {

	static long INF = (long) 1e9 + 7;
	static int MAX = Integer.MAX_VALUE;
	static int MIN = Integer.MIN_VALUE;

	public static void main(String[] args) {
//		int test = fs.nextInt();
		int test = 1;

		for (int cases = 0; cases < test; cases++) {

			// solve();
			System.out.print(solve() + "\n");

		}
	}

	static String solve() {
		return LIS(new int[] { 16, 7, 20, 11, 15, 13, 10, 14, 6, 8 }) + "";
	}

	static public int LIS(int[] ar) {
		int n = ar.length;
		int dp[] = new int[n];
		Arrays.fill(dp, Integer.MAX_VALUE);
		// So as to keep the array always sorted and binarySearch could be performed.
		int last = -1;
		// last index of dp array filled. [last+1] is the length of longest LIS.
		int max = 0;
		for (int i = 0; i < n; i++) {
			if (last == -1) {
				dp[++last] = ar[i];
				max = Integer.max(max, last + 1);
			} else if (ar[i] > dp[last]) {
				// since current element is larger than the last element of dp hence a new
				// element could be added in our increasing subsequence hence increment last.
				dp[++last] = ar[i];
				max = Integer.max(max, last + 1);
			} else {
				int index = getIndex(dp, ar[i]);
				// if index==-2 means element is already present and since we only
				// want strictly increasing subsequence hence ignore.

				// find the largest element less than ar[i] and put ar[i] just next to it.
				// 1 2 5 | 3 --> 1 2 3
				if (index == -2)
					continue;
				dp[index] = ar[i];
			}
		}
		return max;
	}

	static int getIndex(int[] dp, int item) {
		int start = 0, end = dp.length - 1;
		while (start <= end) {
			int mid = (start + end) / 2;
			if (dp[mid] == item) {
				return -2;
			}
			if (dp[mid] > item) {
				end = mid - 1;
			} else {
				start = mid + 1;
			}
		}
		return end + 1;
	}

	static void intSort(int[] a, boolean reverse) {
		ArrayList<Integer> al = new ArrayList<Integer>();
		for (int i : a)
			al.add(i);
		Collections.sort(al);
		if (reverse) {
			for (int i = 0; i < a.length; i++)
				a[i] = al.get(a.length - i - 1);
		} else {
			for (int i = 0; i < a.length; i++)
				a[i] = al.get(i);
		}
	}

	static class Pair {
		int first, second;

		public Pair(int first, int second) {
			this.first = first;
			this.second = second;
		}

		public String toString() {
			return "[" + first + "," + second + "]";
		}
	}

	static class LongPair {
		long first;
		long second;

		LongPair(long a, long b) {
			this.first = a;
			this.second = b;
		}

		public String toString() {
			return "[" + first + "," + second + "]";
		}
	}

	static class FastReader {
		BufferedReader br;
		StringTokenizer st;

		public FastReader() {
			br = new BufferedReader(new InputStreamReader(System.in));
		}

		String next() {
			while (st == null || !st.hasMoreElements()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}

		int nextInt() {
			return Integer.parseInt(next());
		}

		long nextLong() {
			return Long.parseLong(next());
		}

		double nextDouble() {
			return Double.parseDouble(next());
		}

		String nextLine() {
			String str = "";
			try {
				str = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return str;
		}
	}

	static class OutputWriter {
		private final PrintWriter writer;

		public OutputWriter(OutputStream outputStream) {
			writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
		}

		public OutputWriter(Writer writer) {
			this.writer = new PrintWriter(writer);
		}

		public void print(Object... objects) {
			for (int i = 0; i < objects.length; i++) {
				if (i != 0)
					writer.print(' ');
				writer.print(objects[i]);
			}
		}

		public void println() {
			writer.print("\n");
		}

		public void printLine(Object... objects) {
			print(objects);
			writer.println();
		}

		public void close() {
			writer.close();
		}

		public void flush() {
			writer.flush();
		}

	}

	private static final FastReader fs = new FastReader();
	private static final OutputWriter out = new OutputWriter(System.out);
}
