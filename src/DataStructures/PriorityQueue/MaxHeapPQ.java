package DataStructures.PriorityQueue;

import java.util.ArrayList;

import javax.management.InvalidAttributeValueException;
import javax.management.RuntimeErrorException;

import DataStructures.Queue.Queue;
public class MaxHeapPQ<E extends Comparable<E>> implements Queue<E> {
    private ArrayList<E> heap=null;

    public MaxHeapPQ(){
        heap=new ArrayList<>(1);
    }

    public MaxHeapPQ(int size){
        heap= new ArrayList<>(size);
    }
    public MaxHeapPQ(E [] elems){
        int heapSize=elems.length;
        heap = new ArrayList<>(heapSize);
        for(E x: elems){
            heap.add(x);
        }
        for(int i=Math.max((heapSize/2)-1,0);i>=0;i--){
            sink(i);
        }
    }
    public boolean isEmpty(){
        return size()==0;
    }
    public int size(){
        return heap.size();
    }
    public boolean larger(int x, int y){
        E node1 = heap.get(x);
        E node2 = heap.get(y);
        if(node1.compareTo(node2)>1){
            return true;
        }else return false;
    }
    public void swap(int x, int y){
        E node1= heap.get(x);
        E node2 = heap.get(y);
        heap.set(x,node2);
        heap.set(y, node1);
    }
    public void sink(int curr){
        int heapSize = size();
        while(true){
        int left = 2 * curr + 1;
        int right = 2 * curr + 2;
        int larger = left;
        if(right<heapSize && larger(right, left)){
            larger = right;
        }
        if(left>=heapSize || larger(curr, larger)){
            break;
        }
        swap(larger, curr);
        curr = larger;
        }
    }
    public void swim(int curr){
        int parent = (curr - 1)/2;
        while(parent>=0 && larger(curr, parent)){
            swap(curr, parent);
            curr=parent;
            parent=(curr -1)/2;
        }
    }
    public void offer(E elem){
        if(elem == null){
            throw new IllegalArgumentException();
        }
        heap.add(elem);
        swim(size()-1);
    }
    public E poll(){
        if(isEmpty()){
            throw new RuntimeException("Empty queue");
        }
        int last = size()-1;
        E ele = heap.get(0);
        swap(0,last);
        heap.remove(last);
        sink(0);
        return ele;
    }
    public E peek(){
        if(isEmpty()){
            throw new RuntimeException("Empty queue");
        }
        E ele = heap.get(0);
        return ele;
    }
    public int isPresent(E elem){
        if(isEmpty()){
            throw new RuntimeException("Empty queue");
        }
        for(int i=0;i<size();i++){
            if(heap.get(i).equals(elem))
            return i;
        }
        return -1;
    }
}
