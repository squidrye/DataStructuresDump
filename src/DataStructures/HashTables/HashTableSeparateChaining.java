package DataStructures.HashTables;

import java.util.*;
class Entry<K,V>{
    int hash;
    K key;
    V value;
    Entry(K key, V value){
        this.key = key;
        this.value = value;
        this.hash = key.hashCode();
    }
    public boolean equals(Entry<K,V> entry){
        if(entry.key.hashCode() != this.hash){
            return false;
        }else return entry.key.equals(this.key);
    }
    public String toString(){
        return key + "=>" + value;
    }
}
@SuppressWarnings("unchecked")
class HashTableSeparateChaining<K,V>{
    private static int DEFAULT_CAPACITY = 3;
    private static double DEFAULT_LOAD_FACTOR = 0.75;

    private double maxLoadFactor;
    private int capacity, threshold, size = 0;
    private LinkedList<Entry<K,V>> []table;

    //setting up constructors
    public HashTableSeparateChaining(){
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }
    public HashTableSeparateChaining(int capacity){
        this(capacity, DEFAULT_LOAD_FACTOR);
    }
    public HashTableSeparateChaining(int capacity, double maxLoadFactor){
        if(capacity<0) throw new IllegalArgumentException("Illegal capacity");
        if(maxLoadFactor < 0)throw new IllegalArgumentException();  

        this.capacity = Math.max(capacity, DEFAULT_CAPACITY);
        this.maxLoadFactor = maxLoadFactor;
        this.threshold = (int)(capacity * maxLoadFactor);//snaps when size reaches 1/4th of capacity
        this.table = (LinkedList<Entry<K,V>>[]) new LinkedList[capacity];
    }
    public int size(){
        return size;
    }
    public boolean isEmpty(){
        return size()==0;
    }
    //key.hashCode() returns a hash value in primitive integer type which might range from -2e31 to 2e31
    //method purges negative sign from integer value and normalizes it to range between [0,n)
    public int hashToIndex(int keyHash){
        return (keyHash & 0x7FFFFFFF) % capacity;
    }
    public void clear(){
        Arrays.fill(table, null);
        size = 0;
    }
    public boolean hasKey(K key){
        int bucketIndex = hashToIndex(key.hashCode());
        return bucketHasEntry(bucketIndex, key)!=null;
    }
    public V get(K key){
        int bucketIndex = hashToIndex(key.hashCode());
        Entry<K,V> entry = bucketHasEntry(bucketIndex, key);
        if(entry == null) return null;
        else return entry.value;
    }
    private Entry<K,V> bucketHasEntry(int bucketIndex, K key){
        if(key == null) return null;
        if(table[bucketIndex] == null) return null;

        LinkedList<Entry<K,V>> bucket = table[bucketIndex];
        for(Entry<K,V> entry: bucket){
            if(entry.key.equals(key)){
                return entry;
            }
        }
        return null;
    }
    public void add(K key, V value){
        if(key == null) return;
        int bucketIndex = hashToIndex(key.hashCode());
        Entry<K,V> entry = new Entry<>(key, value);
        bucketInsertEntry(bucketIndex, entry);
    }
    private void bucketInsertEntry(int bucketIndex, Entry<K,V> entry){
        Entry<K,V> existingEntry = bucketHasEntry(bucketIndex, entry.key);
        if(existingEntry == null){
            LinkedList<Entry<K,V>> bucket = table[bucketIndex];
            if(bucket == null){
                table[bucketIndex] = bucket = new LinkedList<>();
            }
            bucket.add(entry);
            size++;
            if(size > threshold){
                resizeTable();
            }
        }else{
            existingEntry.value = entry.value;
        }
    }
    private void resizeTable(){
        capacity = capacity*2;
        threshold = (int)(capacity * maxLoadFactor); 
        LinkedList<Entry<K,V>> [] newTable = new LinkedList[capacity];
        for(int i=0;i<table.length;i++){
            if(table[i] != null){
                for(Entry<K,V> entry: table[i]){
                    int bucketIndex = hashToIndex(entry.hash);
                    LinkedList<Entry<K,V>> bucket = newTable[bucketIndex];
                    if(bucket == null){
                        newTable[bucketIndex] = bucket = new LinkedList<>();
                    }
                    bucket.add(entry);
                }
                table[i].clear();
                table[i]=null;
            }
        }
        table = newTable;
    }
    public V remove(K key){
        if(key == null) return null;
        int bucketIndex = hashToIndex(key.hashCode());
        return bucketRemoveEntry( bucketIndex, key);
    }
    private V bucketRemoveEntry(int bucketIndex, K key){
        Entry<K,V> entry = bucketHasEntry(bucketIndex, key);
        if(entry != null){
            List<Entry<K,V>> bucket = table[bucketIndex];
            bucket.remove(entry);
            size--;
            return entry.value;
        }else return null;
    }
    public List<K> keys(){
        List<K> keys = new ArrayList<>();
        for(int i=0;i<table.length;i++){
            if(table[i] != null){
                for(Entry<K,V> entry : table[i]){
                    keys.add(entry.key);
                }
            }
        }
        return keys;
    }
    public List<V> values(){
        List<V> values = new ArrayList<>();
        for(int i=0;i<table.length;i++){
            if(table[i]!=null){
                for(Entry<K,V> entry: table[i]){
                    values.add(entry.value);
                }
            }
        }
        return values;
    }
}