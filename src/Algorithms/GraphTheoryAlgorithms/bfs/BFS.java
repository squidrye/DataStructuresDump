package Algorithms.GraphTheoryAlgorithms.bfs;

import java.util.*;
public class BFS {
    public static class Edge{
        int src, dest, wt;
        Edge(int src, int dest, int wt){
            this.src=src;
            this.dest = dest;
            this.wt = wt;
        }
    }
    private int n;
    private List<List<Edge>> graph;
    public int[]prev;

    public BFS(List<List<Edge>> graph){
        this.n=graph.size();
        this.graph = graph;
    }
    public void runBFS(int startNode){
        Queue<Integer> q = new LinkedList<Integer>();
        boolean [] visited = new boolean[n];
        Arrays.fill(visited, false);
        prev = new int[n];

        q.offer(startNode);
        visited[startNode] = true;
        prev[startNode] = startNode;

        while(!q.isEmpty()){
            int curNode = q.poll();
            List<Edge> neighbours = graph.get(curNode);
            if(neighbours != null){
                for(Edge e:neighbours){
                        if(!visited[e.dest]){
                        q.offer(e.dest);
                        prev[e.dest] = curNode;
                        visited[e.dest]=true;
                    }
                }
            }
        }
    }
    public void reconstructPath(int src, int dest){
        runBFS(src);
        int [] path = new int[n];
        int ctr=0;
        int x;
        for( x=dest;prev[x]!=x;x=prev[x]){
            path[ctr] = x;
            ctr++;
        }
        path[ctr]=x;
        if(path[ctr] == src){
            for(int i=ctr;i>=0;i--){
                System.out.println(path[i]);
            }
        }else System.err.println("No path exists");;
    }

    public static List<List<Edge>> createGraph(int n){
        List<List<Edge>> graph = new ArrayList<List<Edge>>(1);
        for(int i=0;i<n;i++){
            graph.add(new ArrayList<Edge>());
        }
        return graph;
    }
    public static void addUndirectedEdge(List<List<Edge>> graph,int src, int dest,int wt){
     graph.get(src).add(new Edge(src, dest, wt));
        //rev
        graph.get(dest).add(new Edge(dest,src, wt)); 
    }
    public static void main(String[]args){
        List<List<Edge>> forGraph1 = createGraph(13);
        addUndirectedEdge(forGraph1, 0, 9, 1);
        addUndirectedEdge(forGraph1, 0, 7, 1);
        addUndirectedEdge(forGraph1, 0, 11, 1);
        addUndirectedEdge(forGraph1, 9, 8, 1);
        addUndirectedEdge(forGraph1, 9, 10, 1);
        addUndirectedEdge(forGraph1, 7, 3, 1);
        addUndirectedEdge(forGraph1, 7, 6, 1);
        addUndirectedEdge(forGraph1, 11, 7, 1);
        addUndirectedEdge(forGraph1, 10, 1, 1);
        addUndirectedEdge(forGraph1, 8, 1, 1);
        addUndirectedEdge(forGraph1, 8, 12, 1);
        addUndirectedEdge(forGraph1, 12, 2, 1);
        addUndirectedEdge(forGraph1, 3, 2, 1);
        addUndirectedEdge(forGraph1, 3, 4, 1);
        addUndirectedEdge(forGraph1, 6, 5, 1);
        System.out.println(forGraph1.size());
        BFS forG1=new BFS(forGraph1);
        forG1.reconstructPath(7, 1);
    }
}
