package DataStructures.UnionFind;
//take the root of component containing first item in union(f,s) and make it child of root of component containing second item.
//trees can get tall
//find operation can become too expensive on long skinny trees
public class QuickUnionLazy {
    private int[] id;

    public QuickUnionLazy(int n){
        id = new int[n];
        for(int i = 0;i < n;i++) id[i] = i;
    }
    private int root(int i){
        while(i != id[i]){
            i = id[i];
        }
        return i;
    }
    public boolean connected(int p, int q){
        return root(p) == root(q);
    }
    public void union(int p, int q){
        int i = root(p);
        int j = root(q);
        id[i] = j;
    }

}
