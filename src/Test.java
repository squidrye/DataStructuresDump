import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import DataStructures.DynamicArray.DynamicArray;
import DataStructures.LinkedList.DoublyLinkedList;
import DataStructures.Stack.LinkedStack;
import DataStructures.Queue.ArrayQueue;

public class Test {
    public static void main(String[] args)throws IOException{
      BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        DynamicArray<Integer> a=new DynamicArray<Integer>(2);
      a.add(12);
        a.add(14);
        a.add(25);
        a.set(2,20);
        // System.out.println(a.contains(12));

        DoublyLinkedList<Integer> list=new DoublyLinkedList<>();
        list.add(12);
        list.add(14);
        list.add(15);
        list.add(16);
        // System.out.println(list);
        list.remove(12);
        // System.out.println(list);

        // LinkedStack<Integer> stack=new LinkedStack<Integer>();
        // for(int i=0;i<5;i++){
        //   stack.push(Integer.parseInt(br.readLine()));
        // }
        // stack.pop();
        // System.out.println(stack);
        // System.out.println(stack.peek());

        ArrayQueue<Integer> aq=new ArrayQueue<Integer>(4);
        aq.offer(12);
        aq.poll();
        System.out.println(aq);
        System.out.println(aq.isPresent(20));
    }
}
