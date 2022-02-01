package DataStructures.DynamicArray;
import java.util.Iterator;
@SuppressWarnings("unchecked")
public class DynamicArray<T> implements Iterable<T>{
    private T[] arr;
    private int length=0; //shown to user.
    private int capacity=0; // og length of array;

    public DynamicArray(){  
    this.arr=(T[])new Object[16];
    }
    public DynamicArray(int capacity){
        if(capacity < 0){
            throw new IllegalArgumentException("IllegalCapacity: "+ capacity);
        }
        this.capacity=capacity;
        this.arr=(T[])new Object[capacity];
    }
    public int size(){
        return this.length;
    }

    public boolean isEmpty(){
        return this.length==0;
    }

    public T get(int index){
        if(index>=length || index<0){
            throw new IndexOutOfBoundsException();
        }
        return arr[index];
    }

    public void set(int index, T elem){
        if(index>=length || index< 0){
            throw new IndexOutOfBoundsException();
        }
        arr[index]=elem;
    }

    public void clear(){
        for(int i=0;i<length;i++){
            arr[i]=null;
        }
        this.length=0;
    }

    public void add(T elem){
        if(length+1>=capacity){
            if(capacity==0){
                capacity=1;
            }else capacity=capacity*2;
            T[] newArr=(T[])new Object[capacity];
            for(int i=0;i<length;i++){
                newArr[i]=arr[i];
            }
            arr=newArr;
        }
        length++;
        arr[length]=elem;
    }

    public T removeAt(int rm_index){
        if(rm_index>=length || rm_index<0){
            throw new IndexOutOfBoundsException();
        }
        T data=arr[rm_index];
        T[] newArr=(T[]) new Object[length-1];
        for(int i=0,j=0;i<length;i++,j++){
            if(i==rm_index){
                j--;
            }else newArr[i]=arr[j];
        }
        arr=newArr;
        length--;
        return data;
    }

    public int indexOf(Object obj){
        for(int i=0;i<length;i++){
            if(obj==null){
                if(arr[i]==null){
                    return i;
                }
            }else{
                if(obj.equals(arr[i])){
                    return i;
                }
            }
        }
        return -1;
    }

    public boolean remove(Object obj){
        if(indexOf(obj)==-1){
            return false;
        }
        removeAt(indexOf(obj));
        return true;
    }

    public boolean contains(Object obj){
        return indexOf(obj)!=-1;
    }

    //Iterator is fast but not as fast as iterative for loop
    //Creates an iterator by using Iterator interface can be used for iterating over array.
    @Override
    public Iterator<T> iterator(){
        return new Iterator<T>(){
            int index=0;
            @Override
            public boolean hasNext(){
                return index<length;
            }
            @Override
            public T next(){
                T data=arr[index];
                index++;
                return data;
            }
            @Override
            public void remove(){
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override
    public String toString(){
        if(length==0){
            return "[]";
        }else{
            StringBuilder sb=new StringBuilder(length).append("[");
            for(int i=0;i<length-1;i++){
                sb.append(arr[i] + ",");
            }
            sb.append(arr[length-1]+"]");
            return sb.toString();
        }
    }
}