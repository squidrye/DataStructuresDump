package DataStructures.Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Graph<E extends Comparable<E>> {
    //adjacency list
    private List<LinkedList<E>> adjList; 
    private List<E> nodeList;
    //Graph Constructor
    public Graph(){
       adjList = new ArrayList<LinkedList<E>>();
       nodeList = new ArrayList<>();
    }
    public void addVertex(E vertex){
        nodeList.add(vertex);
        adjList.add(new LinkedList<>());
    }
    public void addEdge(E source, E destination, boolean directed){
        if(!nodeList.contains(source)){
            addVertex(source);
        }
        if(!nodeList.contains(destination)){
            addVertex(destination);
        }
        int indexOfSource = nodeList.indexOf(source);
        adjList.get(indexOfSource).add(destination);
        if(!directed){
            int indexOfDest = nodeList.indexOf(destination);
            adjList.get(indexOfDest).add(source);
        }
    }
    public int getVertexCount(){
        return nodeList.size();
    }
    public int edgeCount(boolean directed){
        int count=0;
        for(List<E> l : adjList){
            count+=l.size();
        }
        if(!directed){
            count/=2;
        }
        return count;
    }
    public boolean hasVertex(E vertex){
        return nodeList.contains(vertex);
    }
    public boolean areConnected(E s, E d){
        if(adjList.get(nodeList.indexOf(s)).contains(d) || adjList.get(nodeList.indexOf(d)).contains(s)){
            return true;
        }else return false;
    }
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<nodeList.size();i++){
            sb.append(nodeList.get(i) + "->" + adjList.get(i));
            sb.append("\n");
        }
        return sb.toString();
    }
}
