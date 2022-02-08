package DataStructures.BinarySearchTree;

import DataStructures.Queue.*;

public class BinarySearchTree<E extends Comparable<E>> {
  private class Node{
      E data;
      Node right;
      Node left;
      Node(E data, Node right, Node left){
          this.data=data;
          this.right=right;
          this.left=left;
      }
  }   
  private int counter=0;
  private Node root=null;

  public int size(){
      return counter;
  }
  public boolean isEmpty(){
      return counter==0;
  }
  private boolean isPresent(Node subTroot, E elem){
    if(subTroot == null){
        return false;
    }else{
        int compare = elem.compareTo(subTroot.data);
        if(compare<0){
           return isPresent(subTroot.left, elem);
        }else if(compare>0){
            return isPresent(subTroot.right, elem);
        }else return true;
    }
  }
  //overloading
  public boolean isPresent(E elem){
    return isPresent(root, elem);
  }
  
  private Node add(Node subTroot, E elem){
    if (subTroot == null){
        subTroot = new Node(elem, null, null);
    }else{
        int compare = elem.compareTo(subTroot.data);
        if(compare<0){
            subTroot.left = add(subTroot.left, elem);
        }else{
            subTroot.right = add(subTroot.right, elem);
        }
    }
    return subTroot;
  }
  public boolean add(E elem){
      if(isPresent(elem)){
          return false;
      }else{
          root = add(root, elem);
          counter++;
          return true;
      }
  }

  private Node remove(Node subTroot, E elem){

        if (subTroot == null){ //base case
            return null;
        }
        int compare = elem.compareTo(subTroot.data);

        if(compare < 0){
            subTroot.left = remove(subTroot.left, elem);
        }else if(compare > 0){
            subTroot.right = remove(subTroot.right, elem);
        }else{
      /**
       * 1) Element to be removed has only left subtree
       * 2) Element to be removed has only right subtree
       * 3) Element to be removed has both left and right subtree
       * 4) Element to be removed is leaf node
       * 
       * 4th case can be handled by both first and second cases 
       */
            if(subTroot.right == null){ //case 1,4
                // replace this node to be deleted with its left subtre
                return subTroot.left;
            }else if(subTroot.left == null){//case2
                return subTroot.right;
            }else{//case 3
                //in this case we can find the largest element in left subtree or smallest one in right subtree
                //largest in left sub tree : rightmost node in left subtree
                Node temp = findMaxInSubtree(subTroot.left);
                subTroot.data = temp.data;
                subTroot.left = remove(subTroot.left, temp.data);

            }
        }
        return subTroot;
  }
  private Node findMaxInSubtree(Node subTreeRoot){
      while(subTreeRoot.right != null){
          subTreeRoot = subTreeRoot.right;
      }
      return subTreeRoot;
  }
  public boolean remove(E elem){
      if(!isPresent(elem)){
          return false;
      }else{
          counter--;
          root = remove(root, elem);
          return true;
      }
  }
  public void traverse(TreeTraversalType choice){
    switch(choice){
        case PRE_ORDER -> preOrderTraversal(root);
        case IN_ORDER -> inOrderTraversal(root);
        case POST_ORDER -> postOrderTraversal(root);
        case LEVEL_TRAV -> levelOrderTraversal(root);
    }
  }
  public void preOrderTraversal(Node node){
    if(node == null){
        return;
    }
    System.out.print(node.data +" ");
    preOrderTraversal(node.left);
    preOrderTraversal(node.right);
  }
  public void inOrderTraversal(Node node){
      if(node == null){
          return;
      }
      inOrderTraversal(node.left);
      System.out.print(node.data  + " ");
      inOrderTraversal(node.right);
  }
  public void postOrderTraversal(Node node){
      if(node== null){
          return;
      }
      postOrderTraversal(node.left);
      postOrderTraversal(node.right);
      System.out.print(node.data + " ");
  }
  public void levelOrderTraversal(Node node){
      LinkedQueue<Node> queue = new LinkedQueue<Node>();
      queue.offer(node);     
      
      while(!queue.isEmpty()){
          Node tbp = queue.poll();
          System.out.print(tbp.data + " ");
          if(tbp.left!=null){
          queue.offer(tbp.left);
          }
          if(tbp.right!=null)
          queue.offer(tbp.right);
      } 
  }
}
