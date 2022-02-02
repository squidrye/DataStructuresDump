package DataStructures.LinkedList;

import java.util.Iterator;

public class DoublyLinkedList<T> implements Iterable<T>{
    //creating node prototype.
    private class Node<E>{
        E data;
        Node<E> next,previous;
        
        public Node(E data, Node<E>next, Node<E> previous){
            this.data=data;
            this.next=next;
            this.previous=previous;
        }
    }
    Node<T> head=null;
    Node<T> tail=null;
    private int size=0;
    //methods of linked list
    public int size(){
        return size;
    }
    public boolean isEmpty(){
        return size()==0;
    }
    public void addLast(T dataElem){
        if(isEmpty()){
            Node<T> temp=new Node<T>(dataElem, null,null);
            head=tail=temp;
            temp=null;
        }else{
            Node<T> temp=new Node<T>(dataElem, null, tail);
            tail.next=temp;
            tail=temp;
            temp=null;
        }
        size++;
    }
    public void addFirst(T dataElem){
        if(isEmpty()){
            head=tail=new Node<T>(dataElem, null, null);
        }else{
            head.previous=new Node<T>(dataElem, head, null);
            head=head.previous;
        }
        size++;
    }
    public void add(T dataElem){
        addLast(dataElem);
    }
    public void clear(){
        Node<T> trav=head;
        while(trav!=null){
            Node<T> next=trav.next;
            trav.next=trav.previous=null;
            trav.data=null;
            trav=next;
        }
        head=tail=trav=null;
        size=0;
    }
    //add element at specified index
    public void addAt(int index, T data){
        if(index>size || index<0){
            throw new IllegalArgumentException();
        }else if(index==0){
            addFirst(data);
        }else if(index==size){
            addLast(data);
        }else{
            Node<T> trav=head;
            for(int i=0;i<index-1;i++){
                trav=trav.next;
            }
            Node<T> newNode=new Node<T>(data, trav.next,trav);
            newNode.previous.next=newNode;
            newNode.next.previous=newNode;
        }
        size++;
    }
    // check value of first node if it exists
    public T peekFirst(){
        if(isEmpty()){
            throw new IllegalArgumentException();
        }
        return head.data;
    }
    public T peekLast(){
        if(isEmpty()){
            throw new IllegalArgumentException();
        }
        return tail.data;
    }
    //deleting elements
    public T removeFirst(){
        if(isEmpty()){
            throw new IllegalArgumentException("Empty List");
        }
        T data=head.data;
        head=head.next;
        head.previous.next=null;
        head.previous.data=null;
        head.previous=null;
        size--;
        if(isEmpty()){
            tail=null;
        }
        return data;
    }
    public T removeLast(){
        if(isEmpty()){
            throw new IllegalArgumentException("Empty List");
        }
        T data=tail.data;
        tail=tail.previous;
        tail.next.previous=null;
        tail.next.data=null;
        tail.next=null;
        size--;
        if(isEmpty()){
            head=null;
        }
        return data;
    }
    private T removeNode(Node<T> node){
        T data=node.data;
        if(node.previous==null){
            removeFirst();
            return data;
        }
        if(node.next==null){
            removeLast();
            return data;
        }
        //reattaching pointers
        node.previous.next=node.next;
        node.next.previous=node.previous;
        node.next=null;
        node.previous=null;
        node.data=null;
        node=null;
        size--;
        return data;
    }
    public T removeAt(int index){
        if(index<0|| index>=size){
            throw new IllegalArgumentException();
        }
        Node<T> traverse=head;
        if(index<size/2){
            for(int i=0;i!=index;i++){
                traverse=traverse.next;
            }
        }else{
            traverse=tail;
            for(int i=size-1;i!=index;i--){
                traverse=traverse.previous;
            }
        }
        return removeNode(traverse);
    }
    public int indexOf(Object obj){
        int index=0;
        Node<T> trav=head;
        if(obj==null){
            for(trav=head;trav!=null;trav=trav.next,index++){
                if(trav.data==null){
                    return index;
                }
            }
        }else{
            for(trav=head;trav!=null;trav=trav.next,index++){
                if(obj.equals(trav.data)){
                    return index;
                }
            }
        }
        return -1;
    }
    public boolean remove(Object obj){
       Node<T> trav;
       if(obj==null){
           for(trav=head;trav!=null;trav=trav.next){
               if(trav.data==null){
                   removeNode(trav);
                   return true;
               }
           }
       }else{
           for(trav=head;trav!=null;trav=trav.next){
               if(trav.data.equals(obj)){
                   removeNode(trav);
                   return true;
               }
           }
       }
       return false;
    }
    public boolean contains(Object obj){
        return indexOf(obj)!=-1;
    }
    @Override
    public Iterator<T> iterator(){
        return new Iterator<T>(){
            private Node<T> trav=head;

            @Override
            public boolean hasNext(){
                return trav!=null;
            }
            @Override
            public T next(){
                T data=trav.data;
                trav=trav.next;
                return data;
            }
        };
    }
    @Override
    public String toString(){
        StringBuilder sb=new StringBuilder();
        sb.append("[");
        Node<T> trav=head;
        while(trav!=null){
            sb.append(trav.data);
            if(trav.next!=null){
                sb.append(",");
            }
            trav=trav.next;
        }
        sb.append("]");
        return sb.toString();
    }
}
