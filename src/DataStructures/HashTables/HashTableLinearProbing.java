package DataStructures.HashTables;

public class HashTableLinearProbing<K,V> extends HashTableOpenAdderessingBase<K,V>{
    //in linear probing a linear constant is used in probe function
    //constant can be any positive number, the table capacity will be adjusted such that
    //gcd(linear constant, capacity) is equal to one so that all the buckets could be probed in hashtable
    //thus avoiding any chance of infinite collisions
    private static final int LINEAR_CONSTANT = 17;
    public HashTableLinearProbing(){
        super();
    }
    public HashTableLinearProbing(int capacity){
        super(capacity);
    }
    public HashTableLinearProbing(int capacity, double loadFactor){
        super(capacity, loadFactor);
    }
    @Override
    protected void setupProbing(K key){

    }
    @Override
    protected int probe(int x){
        return LINEAR_CONSTANT * x;
    }
    @Override
    protected void adjustCapacity(){
        while(gcd(LINEAR_CONSTANT, super.capacity)!=1)
        super.capacity++;
    }
} 