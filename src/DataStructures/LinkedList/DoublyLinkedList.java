package DataStructures.LinkedList;

public class DoublyLinkedList<T> {
    //creating node prototype.
    private class Node<T>{
        T data;
        Node<T> next,previous;
        
        public Node(T data, Node<T>next, Node<T> previous){
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
    //will add remove at and remove obj soon...
}
