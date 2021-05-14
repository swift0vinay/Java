import java.io.*;
import java.math.*;
import java.util.*;

/*
   author : Multi-Thread
*/

public class TopologicalSort {

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
		HashMap<Integer, ArrayList<Integer>> hm = new HashMap<Integer, ArrayList<Integer>>();
		for (int i = 0; i < m; i++) {
			int x = fs.nextInt() - 1, y = fs.nextInt() - 1;
			if (hm.get(x) == null)
				hm.put(x, new ArrayList<>());
			hm.get(x).add(y);
			hm.get(y).add(x);
		}
		ArrayDeque<Integer> topo = topologicalSort(hm, n);
		System.out.println(topo);
		out.flush();
	}

	static ArrayDeque<Integer> topologicalSort(HashMap<Integer, ArrayList<Integer>> hm, int n) {

		// Must Conditions for TOPO-SORT:
		// Graph must be Directed Acyclic.
		// Topological sorting for Directed Acyclic Graph (DAG) is a linear ordering of
		// vertices such that for every directed edge u v, vertex u comes before v in
		// the ordering. Topological Sorting for a graph is not possible if the graph is
		// not a DAG.

		ArrayDeque<Integer> ans = new ArrayDeque<Integer>();
		ArrayDeque<Integer> dq = new ArrayDeque<Integer>();

		// All Vertexes with 0 indegree are pushed into dq.
		// If no vertex is found then we can't get a valid toposort.

		int deg[] = new int[n];
		for (Integer k : hm.keySet()) {
			for (Integer z : hm.get(k)) {
				deg[z]++;
			}
		}
		for (int i = 0; i < n; i++) {
			if (deg[i] == 0) {
				dq.add(i);
			}
		}
		while (!dq.isEmpty()) {
			int node = dq.poll();
			ans.add(node);
			if (hm.get(node) != null) {
				for (Integer z : hm.get(node)) {
					--deg[z];
					if (deg[z] == 0) {
						dq.add(z);
					}
				}
			}
		}

		// If we get all vertices in ans we get a valid topoloical sort order.
		// else not.

		return ans;
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
