package DataStructures.Stack;

import java.util.Iterator;
import DataStructures.LinkedList.DoublyLinkedList;

public class LinkedStack<T> implements Stack<T>, Iterable<T>{
    DoublyLinkedList<T> list=new DoublyLinkedList<T>();

    @Override
    public int size(){
        return list.size();
    }
    @Override
    public boolean isEmpty(){
        return size()==0;
    }
    @Override
    public void push(T elem){
        list.addFirst(elem);
    }
    @Override
    public T pop(){
        if(isEmpty()){
            throw new IllegalArgumentException("Stack is empty");
        }else{
            T data=list.removeFirst();
            return data;
        }
    }
    @Override
    public T peek(){
        if(isEmpty()){
            throw new IllegalArgumentException("Stack is empty");
        }else{
            T data=list.peekFirst();
            return data;
        }
    }

    public int search(T elem){
        return list.indexOf(elem);
    }

    @Override
    public Iterator<T> iterator(){
        return list.iterator();
    }
    @Override
    public String toString(){
        return list.toString();
    }
}
