package Algorithms.GraphTheoryAlgorithms.TopologicalSort;

import java.util.*;

public class TopSort {
    public static Map<Integer, List<Integer>> createGraph(int n){
        Map<Integer, List<Integer>> graph = new HashMap<>(n);
        return graph;
    }
    public static void addEdge(int src, int dest, Map<Integer, List<Integer>> graph){
        List<Integer> t;
        if(graph.get(src) == null){
            t = new ArrayList<>();
            t.add(dest);
        }else{
            t = graph.get(src);
            t.add(dest);
        }
        graph.put(src, t);
    }
    public static int[] topologicalSort(Map<Integer,List<Integer>> graph,int num){
        int [] ordering = new int[num];
        boolean [] visited = new boolean[num];
        int i = num-1;
        Arrays.fill(visited,false);
        for(int at = 0;at < num;at++){
            if(!visited[at]){
                dfs(i,graph,at,ordering,visited);
            }
        }
        return ordering;
    }
    public static int dfs(int i, Map<Integer,List<Integer>> graph,int at,int[] order, boolean[]visited){
        visited[at] = true;
        List<Integer> neighbours = graph.get(at);
        if(neighbours != null){
            for(int neighbour:neighbours){
                if(!visited[neighbour]){
                   i = dfs(i,graph,neighbour,order,visited);
                }
            }
            order[i] = at;
        }else{
            order[i] = at;
        }
        return i-1;
    }
    public static void main(String[] args) {
        Map<Integer, List<Integer>> graph = createGraph(3);
        addEdge(0,1,graph);
        addEdge(0,2,graph);
        addEdge(1,2,graph);
        System.out.println(Arrays.toString(topologicalSort(graph,3)));
    }
}
