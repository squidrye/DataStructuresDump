package Algorithms.SortingAlgorithms;

import java.util.Arrays;

public class SortingAlgos {
    public static void main(String[] args) {
        int [] arr = {4,3,2,5,1};
        insertion(arr);
        System.out.println(Arrays.toString(arr));
    }
    static void bubble(int [] arr){
        boolean swap;
        for(int i = 0;i < arr.length -1 ;i++){
            swap = false;
            for(int j = 1; j < arr.length -i ;j++){
                if(arr[j] < arr[j-1]){
                    int temp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = temp;
                    swap = true;
                }
            }
            if(!swap){
                break;
            }
        }
    }
    static void selection(int [] arr){
        //selecting minimum elements and placing them at their correct positions
        for(int i = 0; i < arr.length - 1; i++){
            int minIdx = i;
            // find min element in unsorted sub-array from first to last
            for(int j = i + 1; j < arr.length; j++){
                if(arr[minIdx] > arr[j]){
                    minIdx = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[minIdx];
            arr[minIdx] = temp;
        }
    }
    static void insertion(int [] arr){
        for(int i = 0;i < arr.length -1; i++){
            for(int j = i+1;j > 0;j--){
                if(arr[j] < arr[j-1]){
                    int temp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = temp;
                }else break;
            }
        }
    }
 }
