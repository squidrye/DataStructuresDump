package DataStructures.Trees;

import java.util.*;

public class TreeNode{
    private int identifier;
    List<TreeNode> children;
    private TreeNode parent;
    TreeNode(int id, TreeNode parent){
        this.identifier = id;
        this.parent = parent;
        this.children = new ArrayList<>();
    }
    public void addChildren(TreeNode...children){
        for(TreeNode node:children){
        this.children.add(node);
        }
    }
    public int getId(){
        return identifier;
    }
    public TreeNode getParent(){
        return parent;
    }
    public List<TreeNode> getChildren(){
        return this.children;
    }
}