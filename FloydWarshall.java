import java.io.*;
import java.math.*;
import java.util.*;

/*
   author : Multi-Thread
*/
public class FloydWarshall {

	// static int INF = 998244353;
	static int INF = (int) 1e9 + 7;
	static int MAX = Integer.MAX_VALUE;
	static int MIN = Integer.MIN_VALUE;

	public static void main(String[] args) {
//		int test = fs.nextInt();
		int test = 1;
		for (int cases = 0; cases < test; cases++) {

			solve();
//			System.out.println(solve());

		}
	}

	static void solve() {
		int totalCities = fs.nextInt(), totalFlights = fs.nextInt(), totalQueries = fs.nextInt();
		long MAX_WEIGHT = Long.MAX_VALUE;
		long dp[][] = new long[totalCities][totalCities];
		for (int i = 0; i < totalCities; i++) {
			for (int j = 0; j < totalCities; j++) {
				// if i==j then shortest path is of length 0 and it would never change
				// else assign MAX_WEIGHT to each point.
				dp[i][j] = i == j ? 0L : MAX_WEIGHT;
			}
		}
		for (int i = 0; i < totalFlights; i++) {
			int x = fs.nextInt() - 1, y = fs.nextInt() - 1;
			long c = fs.nextLong();
			// each point stores the minimum weight from x to y and y to x.
			dp[x][y] = Long.min(dp[x][y], c);
			dp[y][x] = Long.min(dp[y][x], c);
		}

		// Intermediate Vertex Concept :
		// for node A to B
		// Shortest Path could be
		// 1) From A to B
		// 2) An random vertex say C, then From A to C + C to B
		// Minimum of 1 and 2 is the shortest path from A to B
		for (int intermediateVertex = 0; intermediateVertex < totalCities; intermediateVertex++) {
			// For every intermediate Vertex the row and column of intermediate Vertex
			// remain same from previous iteration.
			// Instead of creating multiple arrays a single instance works good.
			for (int i = 0; i < totalCities; i++) {
				for (int j = i + 1; j < totalCities; j++) {
					// as Undirected i->j is same as j->i
					if (dp[j][intermediateVertex] != Long.MAX_VALUE && dp[intermediateVertex][i] != Long.MAX_VALUE) {
						dp[i][j] = Long.min(dp[i][j], dp[j][intermediateVertex] + dp[intermediateVertex][i]);
						// setting minimum while going from left to right i->n.
						dp[j][i] = dp[i][j];
						// same as dp[i][j].
					}
				}
			}

		}
		for (int i = 0; i < totalQueries; i++) {
			int start = fs.nextInt() - 1, end = fs.nextInt() - 1;
			out.print((dp[start][end] >= Long.MAX_VALUE ? -1 : dp[start][end]) + "\n");
		}
		out.flush();
	}

	static int _min(int... ar) {
		intSort(ar, true);
		return ar[ar.length - 1];
	}

	static int _max(int... ar) {
		intSort(ar, true);
		return ar[ar.length - 1];
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

	static void longSort(long[] a, boolean reverse) {
		ArrayList<Long> al = new ArrayList<>();
		for (long i : a)
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

	public static int[] radixSort(int[] f) {
		return radixSort(f, f.length);
	}

	public static int[] radixSort(int[] f, int n) {
		int[] to = new int[n];
		{
			int[] b = new int[65537];
			for (int i = 0; i < n; i++)
				b[1 + (f[i] & 0xffff)]++;
			for (int i = 1; i <= 65536; i++)
				b[i] += b[i - 1];
			for (int i = 0; i < n; i++)
				to[b[f[i] & 0xffff]++] = f[i];
			int[] d = f;
			f = to;
			to = d;
		}
		{
			int[] b = new int[65537];
			for (int i = 0; i < n; i++)
				b[1 + (f[i] >>> 16)]++;
			for (int i = 1; i <= 65536; i++)
				b[i] += b[i - 1];
			for (int i = 0; i < n; i++)
				to[b[f[i] >>> 16]++] = f[i];
			int[] d = f;
			f = to;
			to = d;
		}
		return f;
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
