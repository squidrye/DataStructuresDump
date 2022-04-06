/*Uses eulerian tour and minimum sparse table for minimum queries*/
package Algorithms.TreeAlgorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import DataStructures.Queue.LinkedQueue;

class LowestCommonAncestor {
    /*
    Tree setup begins here
    */
    static class TreeNode {
        private int n; // size of tree and subtrees

        private int value; // identity of node in tree
        private TreeNode parent;
        private List<TreeNode> children;

        TreeNode(int id, TreeNode parent) {
            // first constructor
            this.value = id;
            this.parent = parent;
            children = new ArrayList<>();
        }

        TreeNode(int id) {
            this(id, null);
            // refers to first constructor
        }

        public void addChildren(TreeNode... nodes) {
            for (TreeNode node : nodes) {
                this.children.add(node);
            }
        }

        public void setSize(int n) {
            this.n = n;
        }

        // Number of nodes in subtree including node calling this function;
        public int size() {
            return n;
        }

        public int id() {
            return value;
        }

        public TreeNode parent() {
            return parent;
        }

        public List<TreeNode> children() {
            return children;
        }

        public static TreeNode rootTree(List<List<Integer>> graph, int rootId) {
            TreeNode root = new TreeNode(rootId);
            TreeNode rootedTree = buildTree(graph, root);
            return rootedTree;
        }

        public static TreeNode buildTree(List<List<Integer>> graph, TreeNode node) {
            int subTreeCount = 1;
            List<Integer> neighbours = graph.get(node.id());
            if (neighbours != null) {
                for (Integer nId : neighbours) {
                    if (node.parent() != null && node.parent().id() == nId) {
                        continue;
                    }
                    TreeNode child = new TreeNode(nId, node);
                    node.addChildren(child);

                    buildTree(graph, child);
                    subTreeCount += child.size();
                }
            }
            node.setSize(subTreeCount);
            return node;
        }
        public String toString(){
            return String.valueOf(id());
        }
    }
    /*
    Tree setup ends here
    */

    /*Graph static creation setup */
    public static List<List<Integer>> createGraph(int n) {
        List<List<Integer>> graph = new ArrayList<List<Integer>>(n);
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        return graph;
    }

    public static void addUndirectedEdge(List<List<Integer>> graph, int src, int dest) {
        graph.get(src).add(dest);
        graph.get(dest).add(src);
    }

    public static void levelOrderTraversal(TreeNode node) {
        LinkedQueue<TreeNode> queue = new LinkedQueue<TreeNode>();
        queue.offer(node);

        while (!queue.isEmpty()) {
            TreeNode tbp = queue.poll();
            System.out.println(tbp.id());
            for (TreeNode child : tbp.children) {
                if (child != null)
                    queue.offer(child);
            }
        }
    }
    /*Graph static creation setup end */

    /*Min Sparse Table setup*/
    class SparseTable{
        int n;
        int p;
        int [][] indexTable;
        long [][] dp;
        int [] log2;
        SparseTable(long[] values){
            n = values.length;
            p = (int)(Math.log(n)/Math.log(2));
            indexTable = new int[p+1][n];
            dp = new long[p+1][n];

            for(int i = 0;i < n;i++){
                indexTable[0][i] = i;
                dp[0][i] = values[i];
            }
            log2 = new int[n+1];
            for(int i = 2; i<=n;i++){
                log2[i] = log2[i/2] + 1;
            }
            for(int i = 1; i <= p; i++){
                for(int j = 0;j + (1 << i) <= n; j++){
                    long left = dp[i-1][j];
                    long right = dp[i-1][j + (1 << (i-1))];
                    dp[i][j] = Math.min(left,right);
                    
                    if(left <= right){
                        indexTable[i][j] = indexTable[i-1][j];
                    }else{
                        indexTable[i][j] = indexTable[i - 1][j + (1<<(i-1))];
                    }
                
                }
            }
        }
        private int queryMinIndex(int l, int r){
            int length = r-l+1;
            int p = log2[length];
            int k = 1 << p;
            long leftInterval = dp[p][l];
            long rightInterval = dp[p][r-k+1];
            if(leftInterval <= rightInterval){
                return indexTable[p][l];
            }else{
                return indexTable[p][r-k+1];
            }
        }
    }
    /*Min Sparse Table setup complete*/

    /*LCA setup begins*/
       private int n;
       private int tourIndex = 0;
   
       private long [] nodeDepth; // keeps track of depth at ith euler step
       private TreeNode [] nodeOrder; // keeps track of node at ith euler step
       private int[] last; // inverse mapping node -> euler tour index, keeps track of euler step on ith node
       private SparseTable minSparseTable; 

       public LowestCommonAncestor(TreeNode root){
           this.n = root.size();
           setup(root);
       }
       private void setup(TreeNode root){
           int eulerTourSize = 2*n -1 ;
           nodeOrder = new TreeNode[eulerTourSize];
           nodeDepth = new long[eulerTourSize];
           last = new int[n];
   
           // dfs to construct euler tour
           dfs(root, 0);
           minSparseTable = new SparseTable(nodeDepth);
        //    System.out.println(Arrays.toString(nodeDepth));
        //    System.out.println(Arrays.toString(nodeOrder));
        //    System.out.println(Arrays.toString(last));
       }
       private void dfs(TreeNode node, long depth){
           if(node.children() == null){
               return;
           }
           visit(node, depth);
           for(TreeNode child: node.children){
               dfs(child,depth + 1);
               visit(node,depth);
           }
       }
       private void visit(TreeNode node, long depth){
           nodeOrder[tourIndex] = node;
           nodeDepth[tourIndex] = depth;
           last[node.id()] = tourIndex;
           tourIndex++;
       }
       public TreeNode lca(int id1, int id2){
           int l = Math.min(last[id1], last[id2]);
           int r = Math.max(last[id1], last[id2]); //last -> eulerstep ->depth range->min depth->min depth euler step ->node at eulerstep is lca
           int i = minSparseTable.queryMinIndex(l,r);
           return nodeOrder[i];
       }
       /* LCA Setup done */
    public static void main(String[] args) {
        int n = 17;
    List<List<Integer>> tree = createGraph(n);

    addUndirectedEdge(tree, 0, 1);
    addUndirectedEdge(tree, 0, 2);
    addUndirectedEdge(tree, 1, 3);
    addUndirectedEdge(tree, 1, 4);
    addUndirectedEdge(tree, 2, 5);
    addUndirectedEdge(tree, 2, 6);
    addUndirectedEdge(tree, 2, 7);
    addUndirectedEdge(tree, 3, 8);
    addUndirectedEdge(tree, 3, 9);
    addUndirectedEdge(tree, 5, 10);
    addUndirectedEdge(tree, 5, 11);
    addUndirectedEdge(tree, 7, 12);
    addUndirectedEdge(tree, 7, 13);
    addUndirectedEdge(tree, 11, 14);
    addUndirectedEdge(tree, 11, 15);
    addUndirectedEdge(tree, 11, 16);
        
        TreeNode root = TreeNode.rootTree(tree, 0);
        LowestCommonAncestor solver = new LowestCommonAncestor(root);
        TreeNode lca = solver.lca(13,14);
        System.out.println(lca.id());
    }
}