package ProblemSolving.Recursion;

import java.util.ArrayList;

public class PrintSubsequenceWithSum {
    static class ArrayWithSum{
        ArrayList<Integer> list;
        int sum;

        ArrayWithSum(){
            list = new ArrayList<>();
            sum = 0;
        }
        public void add(int val){
            list.add(val);
        }
        public void remove(int val){
            list.remove((Integer)val);
        }
        @Override
        public String toString(){
            return list.toString();
        }
    }
    public static void main(String[] args) {
        int [] arr = {1,2,1};
        int n = 3;
        new PrintSubsequenceWithSum().printSubsequenceWithSumK(0, new ArrayWithSum(), arr,n,2);
        new PrintSubsequenceWithSum().printOneSqWithSumK(0, new ArrayWithSum(), arr,n,2);
    }
    void printSubsequenceWithSumK(int index, ArrayWithSum list, int[] arr, int length, int target){
        if(index >= length){
            if(list.sum == target) {
                System.out.println(list);
            }
            return;
        }
        list.add(arr[index]);
        list.sum+=arr[index];
        printSubsequenceWithSumK(index+1,list,arr,length,target);
        list.remove(arr[index]);
        list.sum-=arr[index];
        printSubsequenceWithSumK(index+1,list,arr,length,target);
    }

    boolean printOneSqWithSumK(int index, ArrayWithSum list, int [] arr, int length, int target){
        if(index >= length){
            if(list.sum == target){
                System.out.println("One Subsequence with sum k -> "+list);
                return true;
            }
            return false;
        }
        if(printOneSqWithSumK(index + 1, list, arr, length, target)){
            return true;
        }
        list.add(arr[index]);
        list.sum += arr[index];
        if(printOneSqWithSumK(index+1,list,arr,length,target)){
            return true;
        }
        list.remove(arr[index]);
        list.sum -= arr[index];
        return false;
    }

}

