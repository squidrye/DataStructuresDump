package DataStructures.Queue;
/***array implementation of non circular queue is pretty useless and doesn't make much sense,
 * thus circular arrary queue implemented.
 * 
 *  needs capacity to initiate
*/

public class ArrayQueue<E> implements Queue<E>{
    private E[] array;
    private int front;
    private int rear;
   
    @SuppressWarnings("unchecked")
    public ArrayQueue(int capacity){
        array=(E[])new Object[capacity];
        front=-1;
        rear=-1;
    }

    public boolean isEmpty(){
        return front==-1;
    }

    public boolean isFull(){
        return (rear+1)%array.length==front;
    }

    public void offer(E elem){
        if(isFull()){
            throw new RuntimeException("Queue is full, can't add "+ elem);
        }else if(isEmpty()){
            front=rear=0;
        }else rear=(rear+1)%array.length;
        array[rear]=elem;
    }

    public E poll(){
        if(isEmpty()){
            throw new RuntimeException("Queue is empty");
        }
        E data;
        //if there is a single element
        if(front==rear){
            data=array[front];
            array[front]=null;
            front=rear=-1;
        }else{
            data=array[front];
            array[front]=null;
            front=(front+1)%array.length;
        }
        return data;
    }
    public int size(){
        if(!isEmpty()){
            if(rear>front){
                return rear-front+1;
            }else{
                return array.length+rear-front+1;
            }
        }else return 0;
    }
    public E peek(){
        if(isEmpty()){
            throw new RuntimeException("Queue is empty");
        }
        E data=array[front];
        return data;
    }
    public boolean isPresent(E data){
        if(isEmpty()){
            throw new RuntimeException("Queue is empty");
        }
        int end=front>rear?front:rear;
        int start=front<rear?front:rear;
        for(int i=start;i<=end;i++){
                if(array[i].equals(data))
                return true;
            }
        return false;
    }
    @Override
    public String toString(){
        StringBuilder sb=new StringBuilder();
        if(isEmpty()){
            return "[]";
        }
        sb.append("[");
        int end=front>rear?front:rear;
        int start=front<rear?front:rear;
        // System.out.println("DEBUG: START->"+start+"END->"+end);
        for(int i=start;i<end;i++){
            sb.append(array[i]+",");
        }
        sb.append(array[end]+"]");
        return sb.toString();
    }
}
