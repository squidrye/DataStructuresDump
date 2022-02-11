package Algorithms.GraphTheoryAlgorithms.dfs;

import java.util.ArrayList;
import java.util.List;

public class DFSIdentifyComponents {
      private final int n;  //no. of nodes in graph
      private boolean[] visited; 
      private int[] components;//component[i] tracks which component i belongs to
      private int componentCounter;//keeps track of total no. of components
      private boolean solved; //initiated graph solved? status
      private List<List<Integer>> graph;


      public static List<List<Integer>> createGraph(int n){
        List<List<Integer>> graph = new ArrayList<>(n);
        for(int i=0;i<n;i++)
        graph.add(new ArrayList<>());
        return graph;
     }
      public static void addUndirectedEdge(List<List<Integer>> graph, int src, int dest){
        graph.get(src).add(dest);
        graph.get(dest).add(src);
      }
      public DFSIdentifyComponents(List<List<Integer>> graph){
        this.graph = graph; 
        this.n = graph.size();
      }

      public void identifyComponents(){
        if(solved){
            return;
        }
        visited = new boolean[n];
        components = new int[n];
        for(int i=0;i<n;i++){
            if(!visited[i]){
                componentCounter++;
                dfs(i);
            }
        }
        solved = true;
      }
      public void dfs(int i){
          System.out.println(components);
        visited[i] =true;
        components[i] = componentCounter;
        List<Integer> neighbours = graph.get(i);
        for(int dest : neighbours){
            if(!visited[dest]){
                dfs(dest);
            }
        }
      }

      public int countComponents(){
          identifyComponents();
          return componentCounter;
      }
      public int[] getComponentNodeMapping(){
          identifyComponents();
          return components;
      }
      public static void main(String[]args){
          List<List<Integer>> graph1 = createGraph(18);
          addUndirectedEdge(graph1, 0, 14);
          addUndirectedEdge(graph1, 0, 13);
          addUndirectedEdge(graph1, 0, 8);
          addUndirectedEdge(graph1, 0, 4);
          addUndirectedEdge(graph1, 3, 9);
          addUndirectedEdge(graph1, 9, 15);
          addUndirectedEdge(graph1, 15, 2);
          addUndirectedEdge(graph1, 15, 10);
          addUndirectedEdge(graph1, 5, 1);
          addUndirectedEdge(graph1, 5, 16);
          addUndirectedEdge(graph1, 5, 17);
          addUndirectedEdge(graph1, 12, 12);
          addUndirectedEdge(graph1, 7, 6);
          addUndirectedEdge(graph1, 7, 11);
          addUndirectedEdge(graph1, 11, 6);
          System.out.println(graph1);
          DFSIdentifyComponents forGraph1 = new DFSIdentifyComponents(graph1);
          forGraph1.identifyComponents();
          int [] mapping =forGraph1.getComponentNodeMapping();
          
          System.out.println("Group | Node");
          for(int i=0;i<mapping.length;i++){
              System.out.println(mapping[i] +" | "+ i);
          } 
          System.out.println(forGraph1.countComponents());
        }
}
