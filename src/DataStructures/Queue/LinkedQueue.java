package DataStructures.Queue;

import DataStructures.LinkedList.DoublyLinkedList;
import java.util.Iterator;
public class LinkedQueue<E> implements Queue<E>, Iterable<E>{
    DoublyLinkedList<E> list=new DoublyLinkedList<>();
    public LinkedQueue(){};
    public LinkedQueue(E elem){
        offer(elem);
    }
    public int size(){
        return list.size();
    }
    public void offer(E elem){
        list.addLast(elem);
    }
    public boolean isEmpty(){
        return size()==0;
    }
    public E poll(){
        if(isEmpty()){
            throw new RuntimeException("Queue is empty");
        }else{
            return list.removeFirst();
        }
    }
    public E peek(){
        if(isEmpty()){
            throw new RuntimeException("Queue is empty");
        }else{
            return list.peekFirst();
        }
    }
    public Iterator<E> iterator(){
        return list.iterator();
    }
    public int isPresent(E elem){
        return list.contains(elem);
    }
    @Override
    public String toString(){
        return list.toString();
    }
}
