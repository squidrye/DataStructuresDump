package Algorithms.GraphTheoryAlgorithms.DAG;

import java.util.*;

//can find single source shortest path or longest path on directed acyclic graphs in almost
//linear time.
//works on both negative and positive edge weights.
//uses topological sort ordering to find sssp
public class Shortest_LongestPath {
    static class Edge{
        int source;
        int dest;
        int weight;
        Edge(int src, int dest, int wt){
            this.source = src;
            this.dest = dest;
            this.weight = wt;
        }
    }
    public static List<List<Edge>> createGraph(int n){
        List<List<Edge>> graph = new ArrayList<>(n);
        for(int i=0;i<n;i++){
            graph.add(new ArrayList<>());
        }
        return graph;
    }
    public static void addDirectedEdge(List<List<Edge>> graph, int src, int dest, int wt){
        Edge e = new Edge(src, dest, wt);
        graph.get(src).add(e);
    }
    public static int[] kahn(List<List<Edge>> graph){
        int n = graph.size();
        int [] inDegree = new int[n];
        for(List<Edge> bucket: graph){
            for(Edge e: bucket){
                inDegree[e.dest]+=1;
            }
        }
        ArrayDeque<Integer> q = new ArrayDeque<Integer>();
        for(int i = 0;i<n;i++){
            if(inDegree[i] == 0){
                q.offer(i);
            }
        }
        int [] topOrder = new int[n];
        int index = 0;
        while(!q.isEmpty()){
            int el = q.poll();
            topOrder[index++] = el;
            List<Edge> neighbours= graph.get(el);
            for(Edge each:neighbours){
                inDegree[each.dest]--;
                if(inDegree[each.dest] == 0){
                    q.offer(each.dest);
                }
            }
        }
        return topOrder;
        
    }
    public static Integer[] sssp(List<List<Edge>> graph, int start){
        int [] topSort = kahn(graph);
        int n = graph.size();
        Integer [] sssp = new Integer[n]; // Infinity -> null map
        
        sssp[start] = 0;
        for(int i = 0;i < n;i++){
            int nodeIndex = topSort[i];
            if(sssp[nodeIndex] != null){
            List<Edge> neighbours = graph.get(nodeIndex);
            if(neighbours != null){
                for(Edge each:neighbours){
                    // relaxation
                    int newDist = sssp[nodeIndex] + each.weight;
                    if(sssp[each.dest]== null){
                        sssp[each.dest] = newDist;
                    }else {
                        sssp[each.dest] = Math.min(sssp[each.dest],newDist);
                    }
                }
            }
        }
    }
    return sssp;
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
        Integer[] sssp = sssp(graph,0);
        System.out.println(java.util.Arrays.toString(sssp));
        //0 2 3 6 1 5
    }
}
