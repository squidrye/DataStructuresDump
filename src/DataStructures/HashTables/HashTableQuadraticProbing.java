package DataStructures.HashTables;

public class HashTableQuadraticProbing<K,V> extends HashTableOpenAdderessingBase<K,V> {
    HashTableQuadraticProbing(){
        super();
    }
    HashTableQuadraticProbing(int capacity){
        super(capacity);
    }
    HashTableQuadraticProbing(int capacity, double loadFactor){
        super(capacity, loadFactor);
    }
    @Override
    protected void setupProbing(K key){}
    @Override
    protected int probe(int x){
        return (x*(x+1)) >> 1;
    }
    @Override
    protected void increaseCapacity(){
        super.capacity = nextPowerOfTwo(capacity);
    }
    private int nextPowerOfTwo(int capacity){
        return Integer.highestOneBit(capacity) << 1;
    }
    @Override
    protected void adjustCapacity(){
        int pow2 = nextPowerOfTwo(capacity);
        if(capacity == pow2) return;
        capacity = pow2;
    }

}
