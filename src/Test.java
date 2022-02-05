import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import DataStructures.DynamicArray.DynamicArray;
import DataStructures.LinkedList.DoublyLinkedList;
import DataStructures.MinPriorityQueue.BinaryHeapPQ;
import DataStructures.Stack.LinkedStack;
import DataStructures.Queue.ArrayQueue;

public class Test {
    public static void main(String[] args)throws IOException{
      BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        // DynamicArray<Integer> a=new DynamicArray<Integer>(2);
        // a.add(12);
        // a.add(14);
        // a.add(25);
        // a.set(2,20);
        // System.out.println(a.contains(12));

        // DoublyLinkedList<Integer> list=new DoublyLinkedList<>();
        // list.add(12);
        // list.add(14);
        // list.add(15);
        // list.add(16);
        // // System.out.println(list);
        // list.remove(12);
        // System.out.println(list);

        // LinkedStack<Integer> stack=new LinkedStack<Integer>();
        // for(int i=0;i<5;i++){
        //   stack.push(Integer.parseInt(br.readLine()));
        // }
        // stack.pop();
        // System.out.println(stack);
        // System.out.println(stack.peek());

        // ArrayQueue<Integer> aq=new ArrayQueue<Integer>(4);
        // aq.offer(11);
        // aq.offer(12);
        // aq.offer(13);
        // aq.offer(14);
        // System.out.println(aq);
        // aq.poll();
        // System.out.println(aq);
        // aq.offer(12);
        // System.out.println(aq);
        // aq.poll();
        // System.out.println(aq.isPresent(20));
        int[]list={1,5,1,8,6,2,2,13,12,11,7,2,15,3,10};
        ArrayList<Integer> l=new ArrayList<>();
        for(int x:list)
        l.add(x);

        BinaryHeapPQ<Integer> bh=new BinaryHeapPQ<Integer>(l);
        bh.poll();
        bh.remove(12);
        bh.remove(3);
        bh.poll();
        bh.remove(6);
        System.out.println(bh.toString());
      }
}
