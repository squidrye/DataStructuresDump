package Algorithms.TreeAlgorithms;

import java.util.*;
/*center is always the middle
vertex or middle two vertices in every
longest path along the tree.*/
public class CenterUndirectedTree {
    public static List<Integer> findTreeCenter(List<List<Integer>> graph){
        int n = graph.size();
        int[] degree = new int[n];
        ArrayList<Integer> leafList = new ArrayList<>();
        //find initial set of leaf nodes and initialize degree arr
        for(int i=0;i<n;i++){
            degree[i]=graph.get(i).size();  //count number of edges of ith node to find degree of ith node
            if(degree[i]<=1){
                leafList.add(i);
                degree[i]=0;
            }
        }
        int processedLeafCount = leafList.size();
        while(processedLeafCount<n){
            ArrayList<Integer> newLeafList = new ArrayList<>();
            for(int eachLeaf:leafList){
                for(int neighbour:graph.get(eachLeaf)){
                    degree[neighbour]-=1;
                    if(degree[neighbour]==1){
                        newLeafList.add(neighbour);
                    }
                }
                degree[eachLeaf]=0;
            }
            processedLeafCount+=newLeafList.size();
            leafList=newLeafList;
        }
        return leafList;
    }
    public static List<List<Integer>> createTree(int size){
        List<List<Integer>> tree = new ArrayList<List<Integer>>();
        for(int i=0;i<size;i++){
            tree.add(new ArrayList<>());
        }
        return tree;
    }
    public static void addUndirectedEdges(List<List<Integer>> tree, int src, int dest){
        tree.get(src).add(dest);
        tree.get(dest).add(src);
    }
    public static void main(String[] args){
        List<List<Integer>> tree1 = createTree(10);
        addUndirectedEdges(tree1, 0, 1);
        addUndirectedEdges(tree1, 1,4 );
        addUndirectedEdges(tree1, 1,3 );
        addUndirectedEdges(tree1, 4,8 );
        addUndirectedEdges(tree1, 4,5 );
        addUndirectedEdges(tree1, 3,7 );
        addUndirectedEdges(tree1, 3,2 );
        addUndirectedEdges(tree1, 3,6 );
        addUndirectedEdges(tree1, 6,9 );
        System.out.println(findTreeCenter(tree1));
    }
}
