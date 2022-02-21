package DataStructures.FenwickTree;

public class FenwickTree {
    int n;
    //contains the original fenwick tree values
    private long[] originalTree;
    //contains updated range values
    private long[] currentTree;

    //construction of fenwick tree with an initial set of values,
    //values must be one based meaning values[0] does not get used;
    //O(n) construction;
    public FenwickTree(long[]values){
        if(values == null) throw new IllegalArgumentException();

        n = values.length;
        values[0] = 0L;

        //creating clone of original array since we manipulate the array in place
        //destroying its original contents;
        long[] fenwickTree = values.clone();

        for(int i = 1; i < n; i++){
            int parent = i + lsb(i);
            if(parent < n){
                fenwickTree[parent] += fenwickTree[i];
            }
        }
        originalTree = fenwickTree;
        currentTree = fenwickTree.clone();
    }

    //add value v to index i
    private void add(int i, long v){
        while(i < n){
            currentTree[i] += v;
            i += lsb(i);
        }
    }
    private static int lsb(int i){
        return Integer.lowestOneBit(i);
    }
    //calculates prefix sum of all elements between [1,i] O(Log(N))
    private long prefixSum(int i, long[] tree){
        long sum = 0L;
        while(i != 0){
            sum += tree[i];
            i -= lsb(i);  // equivalent to i = i & (~lsb(i));
        }
        return sum;
    }
    //returns the sum of the interval [left, right] O(Log(N))
    public long sum(int left, int right){
        if(right < left) throw new IllegalArgumentException("Right should be greater than left");
        return prefixSum(right, currentTree) - prefixSum(left -1 ,currentTree);
    }
    //get value at index i
    public long get(int i){
        return sum(i,i);
    }
    //set index i to be equal to v
    public void set(int i, long v){
        add(i, v-get(i));
    }
    @Override
    public String toString(){
        return java.util.Arrays.toString(currentTree);
    }
}
