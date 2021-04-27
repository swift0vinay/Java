   public boolean isBipartite(int n, ArrayList<ArrayList<Integer>>adj)
    {
      // Creating visited Array
      boolean[] visited=new boolean[n];
      // Creating coloring array , stores which node get which color. 0 / 1 m s only.
      int coloring[]=new int[n];
      for(int i=0;i<n;i++){
          if(!visited[i]){
              // if not visited apply dfs.
              boolean rs=dfs(i,adj,visited,0,coloring);
              if(!rs)
              return false;
          }
      }
      return true;
    }
    static boolean dfs(int src,ArrayList<ArrayList<Integer>> adj,boolean[] visited,int color,int[] coloring){
        // setting node as visited.
        visited[src]=true;
        // setting color of node.
        coloring[src]=color;
        for(Integer z:adj.get(src)){
            if(!visited[z]){
                // if not visited then checking for child if it could be colored with a color different than node.
                boolean rs=dfs(z,adj,visited,(color^1),coloring);
                // if false child cannot be colored hence returned false.
                if(!rs)
                    return false;
                // else continue as it is.
            }else{
                // if visited then if coloring are different of node and child then good else return false.
                if(coloring[z]==coloring[src]){
                    return false;
                }
            }
        }
        // return true if everything sucessfully colored.
        return true;
    }
   class Solution {
    public boolean isBipartite(int[][] graph) {
        ArrayList<ArrayList<Integer>> adj=new ArrayList<>();
        int n=graph.length;
        for(int i=0;i<n;i++){
            ArrayList<Integer> temp=new ArrayList<Integer>();
            for(int j:graph[i]){    
                temp.add(j);
            }
            adj.add(temp);
        }
        boolean visited[]=new boolean[n];
        int coloring[]=new int[n];
        boolean rs=bfs(n,visited,adj,coloring);
        // System.out.println(Arrays.toString(coloring));
        return rs;
    }
      
    // Bipartite Test Using BFS
    boolean bfs(int n,boolean[] visited,ArrayList<ArrayList<Integer>> adj,int[] coloring){
        for(int j=0;j<n;j++){
            if(!visited[j]){
                ArrayDeque<Integer> dq=new ArrayDeque<Integer>();
                dq.add(j);
                int color=0;
                while(!dq.isEmpty()){
                    int size=dq.size();
                    for(int i=0;i<size;i++){
                        int z=dq.pollFirst();
                        visited[z]=true;
                        coloring[z]=color;
                        for(Integer child:adj.get(z)){
                            if(!visited[child]){
                                coloring[child]=color^1;
                                dq.add(child);
                            }else if(coloring[child]==coloring[z]){
                                return false;
                            }
                        }
                    }
                     color^=1;
                }
            }
        }
        return true;
    }
      
    // Bipartite Test Using DFS
    boolean dfs(int src,int color,boolean[] visited,ArrayList<ArrayList<Integer>> adj,int[] coloring){
        visited[src]=true;
        coloring[src]=color;
        for(Integer child:adj.get(src)){
            if(!visited[child]){
                boolean rs=dfs(child,(color^1),visited,adj,coloring);
                if(!rs)
                    return false;
            }else if(coloring[child]==coloring[src]){
                return false;
            }
        }
        return true;
    }
}
