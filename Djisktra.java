import java.io.*;
import java.math.*;
import java.util.*;

/*
   author : Multi-Thread
*/

public class Dijsktra {

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
		int totalCities = fs.nextInt(), totalFlights = fs.nextInt();
		// totalCities- total Vertex in a graph
		// totalFlight- total Edges in a graph

		// Directed or Undirected Graph doesn't affect the algo

		HashMap<Integer, ArrayList<Edge>> hm = new HashMap<Integer, ArrayList<Edge>>();
		for (int i = 0; i < totalFlights; i++) {
			int a = fs.nextInt() - 1, b = fs.nextInt() - 1;
			long c = fs.nextLong();
			if (hm.get(a) == null) {
				hm.put(a, new ArrayList<Edge>());
			}
			hm.get(a).add(new Edge(b, c));
		}
		// Creating an adjacency list of edges and weights.

		// Priority Queue which holds Edge such that node with least weight is at top.
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>(new Comparator<Edge>() {
			@Override
			public int compare(Edge o1, Edge o2) {
				return (int) (o1.weight - o2.weight);
			}
		});

		// Adding the vertex from which shortest path to each node is to be calculated.
		// Current starting vertex is 0;
		pq.add(new Edge(0, 0));

		// Array which stores shortest path weight from each starting vertex to node.
		// Similar to SSSP.
		long paths[] = new long[totalCities];

		// Filling array with Max Distance except starting vertex.
		Arrays.fill(paths, Long.MAX_VALUE);
		paths[0] = 0L;

		// Running loop similar to BFS except having only least weighted nodes.
		while (!pq.isEmpty()) {
			Edge lp = pq.poll();
			int node = lp.src;
			long weight = lp.weight;
			// Getting top Edge of Priority Queue.
			// if the current distance to the node is less than the weight than it's
			// unnecessary to again loop
			// through all adjacent vertexes again.
			// P.S -- If not added gives TLE.
			if (weight > paths[node])
				continue;

			if (hm.get(node) != null) {
				for (Edge edge : hm.get(node)) {
					long nodeToEdge = edge.weight;
					// if sum of distance to node and the weight between node and current node is
					// less than the weight of paths[node] we change the value as it is minimum
					// and add the Pair to Priority Queue for further process.
					if (nodeToEdge + weight < paths[edge.src]) {
						paths[edge.src] = nodeToEdge + weight;
						pq.add(new Edge(edge.src, paths[edge.src]));
					}
				}
			}
		}

		// printing out distances
		for (long distance : paths)
			out.print(distance + " ");
		out.flush();
	}

	static class Edge {
		int src;
		long weight;

		Edge(int src, long weight) {
			this.src = src;
			this.weight = weight;
		}

		public String toString() {
			return src + " " + weight;
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
