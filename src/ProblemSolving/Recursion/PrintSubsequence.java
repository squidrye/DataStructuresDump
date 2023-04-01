package ProblemSolving.Recursion;

import java.util.ArrayList;

public class PrintSubsequence {
    public static void main(String[] args) {
        int [] arr = {1,3,2};
        int n = 3;
        new PrintSubsequence().printSubsequence(0, new ArrayList<>(), arr,n);
    }

    void printSubsequence(int index, ArrayList<Integer> list,int[] arr, int length){
        if(index >= length){
            System.out.println(list);
            return;
        }
        printSubsequence(index+1,list,arr,length);
        list.add(arr[index]);
        printSubsequence(index+1,list,arr,length);
        list.remove((Integer)arr[index]);
    }

}

