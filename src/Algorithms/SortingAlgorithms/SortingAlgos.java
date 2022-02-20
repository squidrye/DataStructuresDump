package Algorithms.SortingAlgorithms;

import java.util.Arrays;

public class SortingAlgos {
    public static void main(String[] args) {
        int [] arr = {4,3,2,5,1,64,54,32,12,11};
        heapSort(arr);
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


    static int[] mergeSort(int [] arr){
        if(arr.length == 1){
            return arr;
        }
        int mid = ( arr.length)/2;
        int [] left = mergeSort(Arrays.copyOfRange(arr, 0, mid));
        int [] right = mergeSort(Arrays.copyOfRange(arr, mid, arr.length));

        return merge(left, right);
    }
    static int[] merge(int [] left, int [] right){
        int i = 0;
        int j = 0;
        int k = 0;
        int [] mix = new int[left.length + right.length];
        while(i<left.length && j<right.length){
            if(left[i] < right[j]){
                mix[k] = left[i];
                i++;
            }else{
                mix[k] = right[j];
                j++;
            }
            k++;
        }
        while(i < left.length){
            mix[k] = left[i];
            k++;
            i++;
        }
        while(j < right.length){
            mix[k] = right[j];
            k++;
            j++;
        }
        return mix;
    }

    static void mergeSortInPlace(int [] arr, int start, int end){
        if(end - start == 1){
            return;
        }
        int mid = (start + end)/2;
        mergeSortInPlace(arr, start, mid);
        mergeSortInPlace(arr, mid, end);

        mergeInPlace(arr, start, mid, end);
    }
    static void mergeInPlace(int [] arr, int start , int mid, int end){
        int [] mix = new int[end - start];

        int i = start;
        int j = mid;
        int k = 0;
        while(i < mid && j < end){
            if(arr[i] < arr[j]){
                mix[k] = arr[i];
                i++;
            }else{
                mix[k] = arr[j];
                j++;
            }
            k++;
        }
        //it may be possible that one of the arrays isn't completely traversed
        while(i < mid){
            mix[k] = arr[i];
            i++;
            k++;
        }
        while(j < end){
            mix[k] = arr[j];
            k++;
            j++;
        }
        for(int l=0;l<mix.length;l++){
            arr[start + l] = mix[l];
        }
    }

    static void quick(int [] arr, int low, int high){
        if(low >= high){
            return;
        }
        int start = low;
        int end = high;
        int mid = (start + end)/2;
        int pivot = arr[mid];

        while(start <= end){
            while(arr[start] < pivot){
                start++;
            }
            while(arr[end] > pivot){
                end--;
            }
            if(start <= end){
                int temp = arr[end];
                arr[end] = arr[start];
                arr[start] = temp;
                start++;
                end--;
            }
        }
        quick(arr, low, end);
        quick(arr, start, high);
    }
    // heap sort
    static void heapSort(int [] arr){
        int heapSize = arr.length;
        for (int i = heapSize/2 -1;i>=0;i--){
            sink(arr, i, heapSize);
        }
        for (int i = heapSize - 1;i >= 0;i--){
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            sink(arr, 0, i);
        }
    }

    private static void sink(int[] arr, int nodeIndex, int heapSize) {
        int largest = nodeIndex;
        int left = 2 * nodeIndex + 1;
        int right = 2 * nodeIndex + 2;

        if(left < heapSize && arr[left] > arr[largest]){
            largest = left;
        }

        if(right < heapSize && arr[right] > arr[largest]){
            largest = right;
        }
        if(largest != nodeIndex){
            int temp = arr[largest];
            arr[largest] = arr[nodeIndex];
            arr[nodeIndex] = temp;

            sink(arr, largest, heapSize);
        }
    }
}
