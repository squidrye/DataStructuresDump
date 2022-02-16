package DataStructures.Trees;

import java.util.*;

public class Tree {
    
    private TreeNode root;
    private List<List<Integer>> tree;

    public Tree(List<List<Integer>> tree){
        this.tree = tree;
    }
    public List<Integer> findCenter(){
        int n=tree.size();
        int[] degree = new int[n];
        ArrayList<Integer> leafList = new ArrayList<>();
        for(int i=0;i<n;i++){
            degree[i] = tree.get(i).size();
            if(degree[i] == 0 || degree[i] == 1){
                leafList.add(i);
            }
        }
        int processedLeafCount = leafList.size();
        while(processedLeafCount < n){
            ArrayList<Integer> nextLeafLayer = new ArrayList<>();
            for(int node : leafList){
                for(int neighbour : tree.get(node)){
                    degree[neighbour]-=1;
                    if(degree[neighbour] == 1){
                        nextLeafLayer.add(neighbour);
                    }
                }
                degree[node] = 0;
            }
            processedLeafCount+=nextLeafLayer.size();
            leafList = nextLeafLayer;
        }
        return leafList;
    }

    public TreeNode rootTree(int rootId){
         root = new TreeNode(rootId, null);
        root = buildTree(root);
        return root;
    }
    private TreeNode buildTree(TreeNode node){
        List<Integer> neighbours = tree.get(node.getId());
        if(neighbours != null){
            for(int neighbour : neighbours){
                if(node.getParent() != null && node.getParent().getId() == neighbour){
                    continue;
                }
                TreeNode child = new TreeNode(neighbour, node);
                node.addChildren( buildTree(child));
            }
        }
        return node;
    }
}
