import DataStructures.DynamicArray.DynamicArray;
import DataStructures.LinkedList.DoublyLinkedList;
public class Test {
    public static void main(String[] args){
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
        System.out.println(list);
        list.remove(12);
        System.out.println(list);
    }
}
