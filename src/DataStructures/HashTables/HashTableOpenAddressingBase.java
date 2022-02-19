package DataStructures.HashTables;

import java.util.*;

abstract class HashTableOpenAdderessingBase<K,V>{

    private static final int DEFAULT_CAPACITY = 7;
    private static final double DEFAULT_LOAD_FACTOR = 0.65;

    protected double loadFactor;
    protected int capacity, threshold, modificationCount;

    //usedBucket counts the total number of used buckets inside the hash-table
    //key count track the number of unique keys currently inside the hash-table
    protected int usedBuckets, keyCount;

    //to store key value pairs
    protected K[] keys;
    protected V[] values;

    //Special marker token used to indicate the deletion of key-value pair it does count as occupied bucket
    protected final K TOMBSTONE = (K)(new Object());

    protected HashTableOpenAdderessingBase(){
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }
    protected HashTableOpenAdderessingBase(int capacity){
        this(capacity, DEFAULT_LOAD_FACTOR);
    }
    protected HashTableOpenAdderessingBase(int capacity, double loadFactor){
        if(capacity <= 0) throw new IllegalArgumentException();
        if(loadFactor <= 0)throw new IllegalArgumentException();

        this.loadFactor = loadFactor;
        this.capacity = Math.max(capacity, DEFAULT_CAPACITY);
        adjustCapacity();
        this.threshold = (int)(this.loadFactor * this.capacity);

        keys = (K[])new Object[this.capacity];
        values = (V[])new Object[this.capacity];
    }
    //below three methods dictate how the probing is to actually occur for an open addressing scheme;
    
    /*adjusts the capacity of the hashtable after it's been made larger or when it is being initialized
     since it is important for correct functioning of probing function. 
    */
    protected abstract void adjustCapacity();
    protected abstract void setupProbing(K key);
    protected abstract int probe(int x);

    //increase capacity of the hashtable
    protected void increaseCapacity(){
        capacity = capacity*2;
    }
    public void clear(){
        for(int i=0;i<capacity;i++){
            keys[i] = null;
            values[i] = null;
        }
        keyCount = usedBuckets = 0;
        modificationCount++;
    }
    public int size(){
        return keyCount;
    }
    public boolean isEmpty(){
        return size() == 0;
    }
    public V put(K key, V value){
        return insert(key,value);
    }
    public V add(K key, V value){
        return insert(key,value);
    }
    public boolean containsKey(K key){
        return hasKey(key);
    }
    public List<K> getKeys(){
        List<K> hashTableKeys = new ArrayList<>();
        for(int i=0;i<capacity; i++){
            if(keys[i] != null && keys[i]!=TOMBSTONE){
                hashTableKeys.add(keys[i]);
            }
        }
        return hashTableKeys;
    }
    public List<V> getValues(){
        List<V> hashTableValues = new ArrayList<>();
        for(int i=0; i< capacity;i++){
            if(keys[i] != null && keys[i]!=TOMBSTONE){
                hashTableValues.add(values[i]);
            }
        }
        return hashTableValues;
    }
    protected void resizeTable(){
        increaseCapacity();
        adjustCapacity();

        threshold = (int)(capacity * loadFactor);

        K[] oldKeyTable = (K[]) new Object[capacity];
        V[] oldValueTable = (V[]) new Object[capacity];

        K[] temp = keys;
        keys = oldKeyTable; // this sets our data member keys table equivalent to new capacity array with all empty slots, in which we can reallocate all previous values using insert function
        oldKeyTable = temp; // this sets oldKeyTable equivalent to keys table with previous capacity, retaining all the info it had, for reallocation in new keys table above

        V[] tempVals = values;
        values = oldValueTable;
        oldValueTable = tempVals;

        keyCount =0;
        usedBuckets =0;
        
        for(int i=0;i<oldKeyTable.length;i++){//oldkeytable.length is oldCapacity
            if(oldKeyTable[i] != null && oldKeyTable[i] != TOMBSTONE){
                insert(oldKeyTable[i], oldValueTable[i]);
            }
            oldValueTable[i] =null;
            oldKeyTable[i] = null;
        }
    }
    /**
     * Converts hash value to index. Strips the negative sign and places hash valued in the domain [0,n);
     */
    protected final int hashToIndex(int keyHash){
        return (keyHash & 0x7FFFFFFF)%capacity;
    }
    //finds gcd of a and b
    protected static final int gcd(int d, int D){
        if(d == 0) return D;
        return gcd(D%d, d);
    }
    //to insert a key value pair in keys and values data members
    //if value already exists in the hash-table then the value is updated;
    public V insert(K key, V value){
        if(key == null)throw new IllegalArgumentException();
        if(usedBuckets >= threshold)resizeTable();

        setupProbing(key);
        final int bucketIndex = hashToIndex(key.hashCode());
        int i = bucketIndex,j=-1,x=1;
        while(true){
            //the current slot was previously deleted
            if(keys[i] == TOMBSTONE){
                if(j == -1)
                    j = i;
            //current slot already contains a key
            }else if(keys[i] != null){
                //if key we are trying to insert is already in the hastable
                //so we update its value
                if(keys[i].equals(key)){
                    V oldValue = values[i];
                    if(j == -1){
                        values[i] = value;
                    }else{
                        keys[i] = TOMBSTONE;
                        values[i] =null;
                        keys[j] = key;
                        values[j] =value;
                    }
                    modificationCount++;
                    return oldValue;
                }
            }else{
                //current cell is null so an insertion can occur
                if(j == -1){
                    //no previous tombstones were found
                    usedBuckets++;
                    keyCount++;
                    keys[i] =key;
                    values[i] =value;
                }else{
                    //found a tombstone indicating a previously deleted element at a collision spot
                    //rather than inserting element at current locatin insert it at deleted spot to 
                    //reduce number of probes.
                    keyCount++;
                    keys[j] =key;
                    values[j] = value;
                }
                modificationCount++;
                return null;
            }
            i = hashToIndex(bucketIndex + probe(x));
            x++;
        } 
    }
    public boolean hasKey(K key){
        if(key == null) throw new IllegalArgumentException();

        setupProbing(key);
        final int bucketIndex = hashToIndex(key.hashCode());

        for(int i=bucketIndex,j=-1,x=1;;i=hashToIndex(bucketIndex + probe(x)),x++){
            if(keys[i] == TOMBSTONE){
                if(j == -1)
                j=i;
            }else if(keys[i] != null){
                if(keys[i].equals(keys)){
                    if(j != -1){
                        // if j != -1 this means we previously encountered a deleted cell
                        //we can perform an optimization by swapping the entries in cells 
                        // i and j so that next time we search for this key we will be able to
                        // do so in less number of probes
                        keys[j] = keys[i];
                        values[j] =values[i];
                        keys[i] = TOMBSTONE;
                        values[i] = null;
                    }
                    return true;
                }
            }else return false; //we didn't find key in the table since we encountered a null node

        }
    }
    //get the value associated with input key
    public V get(K key){
        if(key == null) throw new IllegalArgumentException();

        setupProbing(key);
        final int bucketIndex = hashToIndex(key.hashCode());

        for(int i=bucketIndex, j = -1, x=1;;i = hashToIndex(bucketIndex + probe(x)),x++){
            if(keys[i] == TOMBSTONE){
                if(j == -1){
                    j = i;
                }
            }else if(keys[i] != null){
                if(keys[i].equals(key)){
                    if(j!=-1){
                        keys[j] = keys[i];
                        values[j] = values[i];
                        keys[i] =TOMBSTONE;
                        values[i] = null;
                        return values[j];
                    }else{
                        return values[i];
                    }
                }
            }else return null;
        }
    }
    public V remove(K key){
        if(key == null) throw new IllegalArgumentException();

        setupProbing(key);
        final int bucketIndex = hashToIndex(key.hashCode());

        for(int i=bucketIndex,j=-1,x=1;;i=hashToIndex(bucketIndex + probe(x)),x++){
            if(keys[i] == TOMBSTONE)continue;
            if(keys[i] == null) return null;
            if(keys[i].equals(key)){
                keyCount--;
                modificationCount++;
                V oldValue = values[i];
                keys[i] =TOMBSTONE;
                values[i] =null;
                return oldValue;
            }
        }
    }
    public String toString(){
        StringBuilder sb = new StringBuilder();

        sb.append("{");
        for(int i=0;i<capacity;i++){
            if(keys[i] != null && keys[i] != TOMBSTONE)
                sb.append(keys[i] + "->" + values[i] + ", ");
        }
        sb.append("}");
        return sb.toString();
    }
}