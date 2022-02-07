package DataStructures.Queue;

public interface Queue<E>{
    public int size();
    public boolean isEmpty();
    public void offer(E elem);//push
    public E poll();//pop   
    public int isPresent(E obj);
    public E peek();
}