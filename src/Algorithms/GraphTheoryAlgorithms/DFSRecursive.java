package Algorithms.GraphTheoryAlgorithms;

import java.util.*;

import javax.sql.rowset.spi.SyncResolver;

public class DFSRecursive {
    static class Edge{
        int src, dest, cost;
        public Edge(int src, int dest, int cost){
            this.src = src;
            this.dest = dest;
            this.cost =cost;
        }
    }
    private static void addDirectedEdge(Map<Integer, List<Edge>> graph, int src, int dest, int wt ){
        List<Edge> ls = graph.get(src);
        if(ls == null){
            ls = new ArrayList<Edge>();
            graph.put(src, ls);
        }
        ls.add(new Edge(src, dest, wt));
    }
    static void dfs(int at, boolean[] visited, Map<Integer, List<Edge>> graph){
        if(visited[at]){
            return;
        }
        visited[at]=true;
        // to visit all adjacent neighbours:
        System.out.println(at);
        List<Edge> neighbours = graph.get(at);
        if(neighbours != null){
            for(Edge eachEdge: neighbours){
                System.out.println("Visiting next: "+eachEdge.dest+ " from "+ at);
                dfs(eachEdge.dest, visited, graph);
            }
        }
    }
    public static void main(String[] args){
        Map<Integer, List<Edge>> graph = new HashMap<Integer, List<Edge>>();
        addDirectedEdge(graph, 0, 9, 0);
        addDirectedEdge(graph, 0, 1, 0);
        addDirectedEdge(graph, 1, 8, 0);
        addDirectedEdge(graph, 9, 8, 0);
        addDirectedEdge(graph, 8, 7, 0);
        addDirectedEdge(graph, 7, 10, 0);
        addDirectedEdge(graph, 10, 11, 0);
        addDirectedEdge(graph, 11, 7, 0);
        addDirectedEdge(graph, 7, 3, 0);
        addDirectedEdge(graph, 7, 6, 0);
        addDirectedEdge(graph, 6, 5, 0);
        addDirectedEdge(graph, 3, 2, 0);
        addDirectedEdge(graph, 3, 4, 0);
        addDirectedEdge(graph, 3, 5, 0);
        addDirectedEdge(graph, 12, 12, 0);
        // for(Integer i: graph.keySet()){
        //     System.out.print(i);
        //     for(Edge e: graph.get(i)){
        //         System.out.print(" -->["+e.dest+"]");
        //     }
        //     System.out.println();
        // }
        boolean [] bull = new boolean[13];
         Arrays.fill(bull, false);
        dfs(0, bull, graph);
    }
}
