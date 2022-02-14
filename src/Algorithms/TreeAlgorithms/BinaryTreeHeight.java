package Algorithms.TreeAlgorithms;

public class BinaryTreeHeight{
    class TreeNode{
        int data;
        TreeNode left;
        TreeNode right;
        TreeNode(int data, TreeNode left, TreeNode right){
            this.data=data;
            this.left=left;
            this.right=right;
        }
    }
    private TreeNode root;
    BinaryTreeHeight(int data){
        this.root=new TreeNode(data, null, null);
    }
    public void addNode(int data){
        root = addNode(root,data);
    }
    private TreeNode addNode(TreeNode root, int data){
        if(root == null){
            return new TreeNode(data, null, null);
        }
        if(root.data<data){
           root.right = addNode(root.right, data);
        }else{
           root.left = addNode(root.left, data);
        }
        return root;
    }
    public int treeHeight(TreeNode root){
        if(root ==null){
            return -1;
        }
        if(root.left == null || root.right == null){
            return 0;
        }
        return Math.max(treeHeight(root.left), treeHeight(root.right))+1;
    }
    public void display(TreeNode node){
        if(node==null){
            return;
        }
        display(node.left);
        System.out.println(node.data);
        display(node.right);

    }
    public static void main(String[] args) {
        BinaryTreeHeight ob = new BinaryTreeHeight(10);
        ob.addNode(5);
        ob.addNode(20);
        ob.addNode(1);
        ob.addNode(7);
        ob.addNode(0);
        ob.addNode(2);
        ob.addNode(19);
        ob.addNode(24);
        ob.display(ob.root);
        System.out.println(ob.treeHeight(ob.root));
    }
}