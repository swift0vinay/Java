static int djikstra() {
		int n = fs.nextInt(), m = fs.nextInt();
		HashMap<Integer, HashSet<Integer>> hm = new HashMap<Integer, HashSet<Integer>>();
		for (int i = 0; i < m; i++) {
			int x = fs.nextInt() - 1, y = fs.nextInt() - 1;
			if (hm.get(x) == null)
				hm.put(x, new HashSet<>());
			if (hm.get(y) == null)
				hm.put(y, new HashSet<>());
			hm.get(x).add(y);
			hm.get(y).add(x);
		}
	// Creating Graph 
		boolean[] visited = new boolean[n];
	// Creating a visited array
		int levels[] = new int[n];
	// creating a level array which store the closest level at which node is present
		ArrayDeque<Integer> dq = new ArrayDeque<Integer>();
	// creating a dq for bfs.
		dq.add(0);
	// adding starting node as 0 and marking it visited.
		visited[0] = true;
		while (!dq.isEmpty()) {
			int x = dq.poll();
			if (hm.get(x) != null) {
				for (Integer z : hm.get(x)) {
					if (!visited[z]) {
						// if not visited adding it in the queue.
						dq.add(z);
						levels[z] = levels[x] + 1;
						// level of children node would be level of currnode + 1.
						visited[z] = true;
						//	marking the children visited.
					}
				}
			}
		}
		return levels[n - 1];
		// level would provide shortest distance from source node to any node .
	}
