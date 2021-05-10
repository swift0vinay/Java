import java.io.*;
import java.math.*;
import java.util.*;

/*
   author : Multi-Thread
*/
public class BellmanFord {

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
		int n = fs.nextInt(), m = fs.nextInt();
		// Max Weight of any edge
		long MAX_WEIGHT = (long) 1e15;

		// Dp storing shortest paths.
		long dp[] = new long[n];
		Arrays.fill(dp, MAX_WEIGHT);

		// Array storing the vertex which makes the shortest path from source.
		int path[] = new int[n];
		Arrays.fill(path, -1);
		// Starting source as always has shortest weight 0.
		int src = 0;
		dp[src] = 0;
		// ArrayList of Edge storing all weighted Edges.
		ArrayList<Edge> al = new ArrayList<Edge>();
		for (int i = 0; i < m; i++) {
			int x = fs.nextInt() - 1, y = fs.nextInt() - 1;
			long w = fs.nextLong();
			al.add(new Edge(x, y, w));
		}

		// Idea is to traverse all Edges for n-1 times as the largest simple path has
		// n-1 edges so we try to cover all the possibilities through this.
		// Same concept if curr Node distance is larger than previousNode + weight than
		// the weight is changed.
		// After doing for atmost n-1 time we get shortest time. If no change occur in
		// node weights then the loop can be broken early.
		// Does no work for Negative Weight Cycles but can detect it and retrieve the
		// cycle.

		int latestNode = -1;
		for (int i = 0; i < n; i++) {
			boolean any = false;
			latestNode = -1;
			for (int j = 0; j < m; j++) {
				if (dp[al.get(j).src] < INF) {
					dp[al.get(j).dest] = Long.min(dp[al.get(j).dest], al.get(j).weight + dp[al.get(j).src]);
					// Storing the latest previous node on current destination.
					// Because if the previous node has the shortest path till previous than adding
					// current node the previous path would give shortest path till current node.
					path[al.get(j).dest] = al.get(j).src;
					latestNode = al.get(j).dest;
					any = true;
				}
			}
			if (!any)
				break;
		}

		int dest = n - 1;

		// Finding Negative Weight Cycle
		// Find the latest relaxed node after nth Cycle.
		// If -1 then no negative cycle else cycle is there.
		// Generating path from src - > 0 to dest - > n-1
		// If no path then dp[dest]= MAX_WEIGHT


		System.out.println(Arrays.toString(dp));
		System.out.println(Arrays.toString(path));
		if (latestNode == -1) {
//			System.out.println("NO NEGATIVE CYCLE");
			out.print("NO\n");
			out.flush();
		} else {
			int y = latestNode;
			// Go back n time in path list so as to get closest node of a cycle from source.
			for (int i = 0; i < n; i++)
				y = path[i];
			ArrayList<Integer> pathFromStoD = new ArrayList<Integer>();
			for (int curr = y;; curr = path[curr]) {
				pathFromStoD.add(curr);
				if (curr == y && pathFromStoD.size() > 1)
					break;
			}
			Collections.reverse(pathFromStoD);
			out.print("YES\n");
			for (Integer z : pathFromStoD)
				out.print((z + 1) + " ");
			out.flush();
		}
//		if (dp[dest] == MAX_WEIGHT) {
//			System.out.println("NO CYCLE FOUND");
//		} else {
//			ArrayList<Integer> pathFromStoD = new ArrayList<Integer>();
//			while (path[dest] != -1) {
//				pathFromStoD.add(dest);
//				dest = path[dest];
//			}
//			pathFromStoD.add(0);
//			Collections.reverse(pathFromStoD);
//			System.out.println(pathFromStoD);
//		}
//		System.out.println(Arrays.toString(dp));
//		System.out.println(Arrays.toString(path));
	}

static class Edge {
	int src, dest;
	long weight;

	Edge(int src, int dest, long weight) {
		this.src = src;
		this.dest = dest;
		this.weight = weight;
	}

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
