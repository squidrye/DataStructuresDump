package Algorithms.TreeAlgorithms;

import java.util.*;

import DataStructures.Queue.LinkedQueue;
//converts given undirected tree to out tree with chosen node as root node;
public class RootingTree {
    static class TreeNode{
        int identifier;
        List<TreeNode> children;
        TreeNode parent;
        TreeNode(int id){
            this.identifier = id;
            children =new LinkedList<>();
        }
        TreeNode(int id, TreeNode parent){
            this.identifier = id;
            this.parent= parent;
            children = new LinkedList<>();
        }
        public void addChildren(TreeNode...children){
            for(TreeNode child:children){
                this.children.add(child);
            }
        }
        @Override
        public String toString(){
            return String.valueOf(this.identifier);
        }
        @Override
        //only check id equality of two nodes rather than subtree;
        public boolean equals(Object ob){
            if(ob instanceof TreeNode){
                if(this.identifier ==((TreeNode)ob).identifier)
                 return true;
                 else return false;
            }   
            else return false;
        }
    }
    //will return root node of new tree
    public static TreeNode rootTree(List<List<Integer>> graph, int rootId){
        TreeNode root = new TreeNode(rootId, null);
        return buildTree(graph, root);
    }
    //start dfs at rootId in graph add new children to root node in tree
    public static TreeNode buildTree(List<List<Integer>> graph, TreeNode node){
        List<Integer> neighbours = graph.get(node.identifier);
        if(neighbours!=null)
        for(int neighbour: neighbours){
            if(node.parent !=null && node.parent.identifier ==neighbour)
            continue;
            
            TreeNode child = new TreeNode(neighbour ,node);
            //on way recursion
            node.addChildren(child);
            buildTree(graph, child);
            //backtrack
            // node.addChildren(buildTree(graph,child));
        }
        return node;
    }
    public static void levelOrderTraversal(TreeNode node){
        LinkedQueue<TreeNode> queue = new LinkedQueue<TreeNode>();
        queue.offer(node);     
        
        while(!queue.isEmpty()){
            TreeNode tbp = queue.poll();
            System.out.println(tbp.identifier);
            for(TreeNode child: tbp.children){
                if(child!=null)
                queue.offer(child);
            }
        } 
    }
    public static List<List<Integer>> createGraph(int n){
        List<List<Integer>> graph = new ArrayList<>(n);
        for(int i=0;i<n;i++){
            graph.add(new LinkedList<>());
        }
        return graph;
    }
    public static void addUndirectedEdges(List<List<Integer>> graph, int src, int dest){
        graph.get(src).add(dest);
        graph.get(dest).add(src);
    }
    public static void main(String[] args) {
        List<List<Integer>> graph = createGraph(7);
        addUndirectedEdges(graph, 0, 5);
        addUndirectedEdges(graph, 0, 1);
        addUndirectedEdges(graph, 0, 2);
        addUndirectedEdges(graph, 2, 3);
        addUndirectedEdges(graph, 5, 4);
        addUndirectedEdges(graph, 5, 6);
        System.out.println(graph);
        TreeNode root = rootTree(graph,0);
        levelOrderTraversal(root);
    }
}
