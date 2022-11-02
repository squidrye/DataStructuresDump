import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import DataStructures.DynamicArray.DynamicArray;
import DataStructures.LinkedList.DoublyLinkedList;
import DataStructures.Stack.LinkedStack;
import DataStructures.Queue.ArrayQueue;
import DataStructures.Queue.LinkedQueue;
import DataStructures.BinarySearchTree.*;

public class Test {
    public static void main(String[] args)throws IOException{
      // BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

      //   DynamicArray<Integer> a=new DynamicArray<Integer>(2);
      //   a.add(12);
      //   a.add(14);
      //   a.add(25);
      //   a.set(2,20);
      //   System.out.println(a.contains(12));

      //   DoublyLinkedList<Integer> list=new DoublyLinkedList<>();
      //   list.add(12);
      //   list.add(14);
      //   list.add(15);
      //   list.add(16);
      //   // System.out.println(list);
      //   list.remove(12);
      //   System.out.println(list);

      //   LinkedStack<Integer> stack=new LinkedStack<Integer>();
      //   for(int i=0;i<5;i++){
      //     stack.push(Integer.parseInt(br.readLine()));
      //   }
      //   stack.pop();
      //   System.out.println(stack);
      //   System.out.println(stack.peek());

      //   ArrayQueue<Integer> aq=new ArrayQueue<Integer>(4);
      //   aq.offer(11);
      //   aq.offer(12);
      //   aq.offer(13);
      //   aq.offer(14);
      //   System.out.println(aq);
      //   aq.poll();
      //   System.out.println(aq);
      //   aq.offer(12);
      //   System.out.println(aq);
      //   aq.poll();
      //   System.out.println(aq.isPresent(20));
      //   int[]list1={5,6,12,8,7,14,19,13,12,11};
      //   ArrayList<Integer> l=new ArrayList<>();
      //   for(int x:list1)
      //   l.add(x);
        // LinkedQueue<Integer> lq= new LinkedQueue<Integer>();
        // lq.offer(1);
        // lq.offer(2);
        // lq.offer(3);
        // lq.offer(4);
        // lq.poll();
        // System.out.println(lq.poll());
        // System.out.println(lq);
        // lq.offer(34);
        // System.out.println(lq);
      //   BinaryHeapPQ<Integer> bh=new BinaryHeapPQ<Integer>(l);
      //   bh.offer(1);
      //   bh.offer(13);
      //   System.out.println(bh.toString());

      BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
      bst.add(11);
      bst.add(6);
      bst.add(15);
      bst.add(3);
      bst.add(8);
      bst.add(13);
      bst.add(17);
      bst.traverse(TreeTraversalType.LEVEL_TRAV);
      }
}
