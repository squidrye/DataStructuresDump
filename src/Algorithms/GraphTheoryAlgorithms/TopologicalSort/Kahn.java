package Algorithms.GraphTheoryAlgorithms.TopologicalSort;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;


public class Kahn {
    public static int[] kahns(List<List<Integer>> graph){
        int n = graph.size();
        int [] inDegree = new int[n];
        for(List<Integer> bucket: graph){
            for(int to:bucket){
                inDegree[to]+=1;
            }
        }
        ArrayDeque<Integer> q = new ArrayDeque<Integer>();
        
        for(int i = 0;i < n;i++){
            if(inDegree[i] == 0){
                q.offer(i);
            }
        }
        int [] order = new int[n];
        int index = 0;
        while(!q.isEmpty()){
            int at = q.poll();
            order[index++] = at;
            List<Integer> neighbours = graph.get(at);
            for(int each:neighbours){
                inDegree[each]-=1;
                if(inDegree[each] == 0){
                    q.offer(each);
                }
            }
        }
        if(index != n){
            //cycle detected
            return null;
        }
        return order;
    }
    public static List<List<Integer>> createGraph(int n){
        List<List<Integer>> graph = new ArrayList<>(n);
        for(int i = 0;i < n;i++){
            graph.add(new ArrayList<>());
        }
        return graph;
    }
    public static void addDirectedEdge( List<List<Integer>> graph,int src, int dest){
        graph.get(src).add(dest);
    }
    public static void main(String[] args) {
        List<List<Integer>> g = createGraph(14);
        addDirectedEdge(g, 0, 2);
        addDirectedEdge(g, 0, 3);
        addDirectedEdge(g, 0, 6);
        addDirectedEdge(g, 1, 4);
        addDirectedEdge(g, 2, 6);
        addDirectedEdge(g, 3, 1);
        addDirectedEdge(g, 3, 4);
        addDirectedEdge(g, 4, 5);
        addDirectedEdge(g, 4, 8);
        addDirectedEdge(g, 6, 7);
        addDirectedEdge(g, 6, 11);
        addDirectedEdge(g, 7, 4);
        addDirectedEdge(g, 7, 12);
        addDirectedEdge(g, 9, 2);
        addDirectedEdge(g, 9, 10);
        addDirectedEdge(g, 10, 6);
        addDirectedEdge(g, 11, 12);
        addDirectedEdge(g, 12, 8);
        
        //[0, 9, 13, 3, 2, 10, 1, 6, 7, 11, 4, 12, 5, 8]
        int [] topOrder = kahns(g);
        System.out.println(java.util.Arrays.toString(topOrder));
    }
    
}
