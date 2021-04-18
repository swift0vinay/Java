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
