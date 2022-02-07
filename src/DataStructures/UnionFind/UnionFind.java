package DataStructures.UnionFind;
/*UnionFind or Disjoint Set data structure implementation */
public class UnionFind {
    //parent[i] points to parent of i, where i is an integer which might/ might not be mapped to other integers or objects
    private int [] parent;
    //To track the size of each disjoint group
    private int[] sz;
    //number of elements in the union find: targets each disjoint set
    private int size;
    //number of components or disjoint groups in union find
    private int numComponent;

    public UnionFind(int size){
        if(size<=0){
            throw new IllegalArgumentException("Non-negative size expected");
        }
        this.size=numComponent=size;
        sz=new int[size];
        parent=new int[size];

        for(int i=0;i<size;i++){
            parent[i] = i; //each element is initially linked to itself
            sz[i] = 1; //each component is initially of size one
        }
    }
    //Find which component/set 'p' belongs to, finds the root of that set or component
    public int find(int p){
        int root=p;
        while(root!=parent[root]){ //an element is root of tree if parent of that element is element itself(self loop);
            root=parent[root];
        }

        //path compression
        while(parent[p]!=root){
            int toBeCheckedNext = parent[p];
            parent[p]=root;
            p=toBeCheckedNext;
        }
        return root;
    }
    public void union(int p, int q){
        if(connected(p,q)){
            return;
        }
        int root1=find(p);
        int root2=find(q);

        if(sz[root1]<sz[root2]){
            sz[root2]+=sz[root1];
            parent[root1]=root2;
            sz[root1]=0;
        }else{
            sz[root1]+=sz[root2];
            sz[root2]=0;
            parent[root2]=root1;
        }
        numComponent--;
    }
    public boolean connected(int p, int q){
        return find(p)==find(q);
    }
    public int componentSize(int p){
        return sz[find(p)];
    }
    public int size(){
        return size;
    }
    public int components(){
        return numComponent;
    }
}
