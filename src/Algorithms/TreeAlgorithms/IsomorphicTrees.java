package Algorithms.TreeAlgorithms;

import DataStructures.Trees.*;
import java.util.*;

class IsomorphicTrees{
    public static boolean isIsomorphic(List<List<Integer>> t1, List<List<Integer>> t2){

       Tree tree1 = new Tree(t1); 
       Tree tree2 = new Tree(t2);

       List<Integer> center1 = tree1.findCenter();
       List<Integer> center2 = tree2.findCenter();

       TreeNode rt1 = tree1.rootTree(center1.get(0));

       String encodeT1 = encode(rt1);
       System.out.println(encodeT1);

       for(int center: center2){
           TreeNode rt2 = tree2.rootTree(center);

           String encodeT2 = encode(rt2);
           System.out.println(encodeT2);
           
           if(encodeT1.equals(encodeT2)){
               return true;
           }
       }
       return false;
    }
    public static String encode(TreeNode root){
        if(root == null){
            return "";
        }
        ArrayList<String> labels = new ArrayList<>();
        for(TreeNode child: root.getChildren()){
            labels.add(encode(child));
        }
        StringBuilder result = new StringBuilder("");
        Collections.sort(labels);
        for(String eachLabel : labels)
            result.append(eachLabel);
        return "("+result.toString()+")";
    }
    public static List<List<Integer>> createTree(int n){
        List<List<Integer>> undirectedTree = new ArrayList<>();
        for(int i=0;i<n;i++){
            undirectedTree.add(new ArrayList<>());
        }
        return undirectedTree;
    }
    public static void addUndirectedEdge(List<List<Integer>> tree, int src, int dest){
        tree.get(src).add(dest);
        tree.get(dest).add(src);
    }
    public static void levelOrderTraversal(TreeNode root){
        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        while(!q.isEmpty()){
            TreeNode tbp = q.poll();
            System.out.println(tbp.getId());
            for(TreeNode child: tbp.getChildren()){
                if(child!=null)
                q.offer(child);
            }
        }
    }
    public static void main(String[] args){
        List<List<Integer>> tree1 = createTree(5);
    addUndirectedEdge(tree1, 2, 0);
    addUndirectedEdge(tree1, 3, 4);
    addUndirectedEdge(tree1, 2, 1);
    addUndirectedEdge(tree1, 2, 3);

    List<List<Integer>> tree2 = createTree(5);
    addUndirectedEdge(tree2, 1, 0);
    addUndirectedEdge(tree2, 2, 4);
    addUndirectedEdge(tree2, 1, 3);
    addUndirectedEdge(tree2, 1, 2);
        System.out.println(isIsomorphic(tree1, tree2));
    }
}