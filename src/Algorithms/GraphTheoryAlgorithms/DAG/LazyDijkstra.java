package Algorithms.GraphTheoryAlgorithms.DAG;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class LazyDijkstra {
    static class Edge{
        int src;
        int dest;
        int wt;
        Edge(int s, int d, int w){
            this.src = s;
            this.dest = d;
            this.wt= w;
        }
    }
    static class Pair{
        int node;
        int distance;
        Pair(int node, int dist){
            this.node = node;
            this.distance = dist;
        } 
      }
    static private Comparator<Pair> comparator = new Comparator<Pair>(){
        @Override
        public int compare(Pair p1, Pair p2){
            if(Math.abs(p1.distance - p2.distance) > 0){
                return +1;
            }else return -1;
        }
    };
    static List<List<Edge>> createGraph(int n){
        List<List<Edge>> graph = new ArrayList<List<Edge>>(n);
        for(int i =0 ;i<n;i++){
            graph.add(new ArrayList<>());
        }
        return graph;
    }
    static void addDirectedEdge(List<List<Edge>> graph, int src, int dest, int wt){
        graph.get(src).add(new Edge(src, dest, wt));
    }
    static Integer[] dijkstra(List<List<Edge>> graph, int start){
        int n = graph.size();
        boolean [] visited = new boolean[n];
        Integer[] dist = new Integer[n];
        dist[start] = 0;
        PriorityQueue<Pair> pq = new PriorityQueue<>(comparator);
        pq.add(new Pair(start,0));
        while(!pq.isEmpty()){
            Pair p1 = pq.poll();
            visited[p1.node] = true;
            if(dist[p1.node] != null){
                for(Edge e:graph.get(p1.node)){ //get all adjacent edges of polled node and traverse over them
                    if(e != null){
                        if(visited[e.dest]){
                            continue;
                        }
                        int newDist = dist[p1.node] + e.wt;
                        if(dist[e.dest] ==null || newDist < dist[e.dest]){
                            dist[e.dest] = newDist;
                            pq.add(new Pair(e.dest, newDist));
                        }
                    }
                }
            }
        }
        return dist;
    }
    public static void main(String[] args) {
        List<List<Edge>> graph = createGraph(6);
        addDirectedEdge(graph, 0, 1, 2);
        addDirectedEdge(graph, 0, 4, 1);
        addDirectedEdge(graph, 1, 2, 3);
        addDirectedEdge(graph, 4, 2, 2);
        addDirectedEdge(graph, 4, 5, 4);
        addDirectedEdge(graph, 2, 3, 6);
        addDirectedEdge(graph, 5, 3, 1);
        Integer [] sssp = dijkstra(graph, 0);
        System.out.println(java.util.Arrays.toString(sssp));
    }
}
