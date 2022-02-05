package DataStructures.MinPriorityQueue;
/*implementation of priority queue adt using binary heap
*/ 
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

public class BinaryHeapPQ<E extends Comparator<E>>{
//heap will be implemented with a normal list.So,
//A dynamic list to track the elements inside the heap
    private ArrayList<E> heap=null;

//default empty heap
    BinaryHeapPQ(){
        heap=new ArrayList<>();
    }
    //binary heap with provided size
    BinaryHeapPQ(int size){
        heap=new ArrayList<>(size);
    }
    //binary heap pq using heapify on provided array of elements in On time
    BinaryHeapPQ(E[] elements){

        int heapSize=elements.length;
        heap=new ArrayList<>(heapSize);

        //placing elements in heap
        for(int i=0;i<heapSize;i++){
            heap.add(elements[i]);
        }
        //heapifying so that our structure validates heap invariant prop
        for(int i=Math.max(0,(heapSize/2)-1);i>=0;i--){
            sink(i);
        }
    }
    BinaryHeapPQ(Collection<E> elems){
        int heapSize=elems.size();
        heap=new ArrayList<E>(heapSize);
        //adding all elements to the heap
        heap.addAll(elems);
        for(int i=Math.max(0,(heapSize/2)-1);i>=0;i--){
            sink(i);
        }
    }
    public boolean isEmpty(){
        return size()==0;
    }
    public void clear(){
        heap.clear();
    }
    public int size(){
        return heap.size();
    }
    //returns element with highest priority that is lowest element in min heap
    public E peek(){
        if(isEmpty()){
            return null;
        }
        return heap.get(0);
    }
    //removes max priority element olognq
    public E poll(){
        if(isEmpty()){
            throw new RuntimeException();
        }
        return removeAt(0);
    }
    public E removeAt(int k){
        int lastIndex=size()-1;
        E tbr=heap.get(k);//element to be deleted recorded before swap
        swap(lastIndex,k);
        E elem=heap.get(k);//element at k after replacement to check sink or swim
        heap.remove(lastIndex);
        sink(k);
        //if sink doesn't work we'll swim
        if(heap.get(k).equals(elem))
        swim(k);
        return tbr;
    }
    //since relational operators won't be able to comapre generic E
    public boolean isLess(int index1, int index2){
        //returns true if value at index1 is smaller than value at index2
        E node1=heap.get(index1);
        E node2=heap.get(index2);
        if(node1.compare(node1,node2)<=0){
            return true;
        }else return false;
    }
    //swap two nodes:
    private void swap(int i,int j){
        E node1=heap.get(i);
        E node2=heap.get(j);
        heap.set(i,node2);
        heap.set(j,node1);
    }
    private void swim(int curr){
        while(curr>0){
            int parent=(curr-1)/2;
            if(isLess(curr,parent)){
                swap(curr,parent);
                curr=parent;
            }else break;
            parent=(curr-1)/2;
        }
    }
    private void sink(int curr){
        int heapSize=size();
        while(true){
            int left=2*curr+1;
            int right=2*curr+2;
            int smaller=left; //assuming left is smaller node in priority to initialize variable

            //if right is smaller
            if(right<heapSize && isLess(right,left)){
                smaller=right;
            }
            //break case of loop:
            if(left>=heapSize || isLess(curr,smaller)){
                break;
            }
            swap(smaller, curr);
            curr=smaller;
        }
    }

}