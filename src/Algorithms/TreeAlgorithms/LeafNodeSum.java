package Algorithms.TreeAlgorithms;

import java.util.*;

public class LeafNodeSum {
   static class TreeNode{
      int data;
      List<TreeNode> children;
      TreeNode(int data){
         this.data=data;
         this.children = new ArrayList<>();
      }
   }
   public static void addChildren(TreeNode parent,TreeNode...children){
      for(TreeNode child:children){
         parent.children.add(child);
      }
   }
   public static int leafNodeSum(TreeNode node){
      int sum=0;
      if(isLeaf(node)){
         return node.data;
      }
      for(TreeNode eachchild: node.children){
         sum+=leafNodeSum(eachchild);
      }
      return sum;
   }
   public static boolean isLeaf(TreeNode root){
      return root.children.size()==0;
   }
  public static void main(String[] args){
    TreeNode root = new TreeNode(5);

    TreeNode node4 = new TreeNode(4);

    TreeNode node1 = new TreeNode(1);

    TreeNode node3 = new TreeNode(3);
    TreeNode node2 = new TreeNode(2);

    TreeNode node6 = new TreeNode(6);
    addChildren(root, node4);
    addChildren(root, node1);
    addChildren(root, node6);
    addChildren(node4,node3);
    addChildren(node4,node2);
    System.out.println(leafNodeSum(root));
  }
}
