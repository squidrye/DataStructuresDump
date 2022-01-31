import DataStructures.DynamicArray.DynamicArray;
public class Test {
    public static void main(String[] args){
        DynamicArray<Integer> a=new DynamicArray<Integer>(2);
      a.add(12);
        a.add(14);
        a.add(25);
        a.set(2,20);
        System.out.println(a.contains(12));
    }
}
