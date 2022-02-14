package Algorithms.TreeAlgorithms;

import java.util.*;

public class LeafNodeSum {
  Map<Integer, List<Integer>> tree;
  public LeafNodeSum(Map<Integer, List<Integer>> tree){
     this.tree =tree;
  }
  public int leafNodeSum(int node,int total){
     if(tree == null){
        return 0;
     }
     if(isLeaf(node)){
        total+=node;
        return total;
     }
     List<Integer> children = tree.get(node);
     if(children!=null){
        for(int child:children){
          total = leafNodeSum(child,total);
        }
     }
   //   System.out.println(total);
     return total;
  }
  public boolean isLeaf(int node){
     return tree.get(node) ==null || tree.get(node).size()==0;
  }
  public static void addChild(Map<Integer, List<Integer>> tree,int src, int dest){
     List<Integer> l = tree.get(src);
     if(l==null){
        l=new ArrayList<>();
        tree.put(src,l);
     }
     l.add(dest);
  }
  public static void main(String[] args){
     Map<Integer, List<Integer>> tree = new HashMap<Integer, List<Integer>>();
     addChild(tree,5,4);
     addChild(tree,5,3);
     addChild(tree,4,1);
     addChild(tree,4,-6);
     addChild(tree,3,0);
     addChild(tree,3,7);
     addChild(tree,3,-4);
     addChild(tree,1,2);
     addChild(tree,1,9);
     addChild(tree,7,8);
     System.out.println(tree);
     LeafNodeSum ob = new LeafNodeSum(tree);
     System.out.println(ob.leafNodeSum(5, 0));
     
  }
}
