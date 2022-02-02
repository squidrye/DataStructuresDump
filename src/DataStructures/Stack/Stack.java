package DataStructures.Stack;

public interface Stack<T> {
    public int size();
    public boolean isEmpty();
    public void push(T data);
    public T pop();
    public T peek();
}
