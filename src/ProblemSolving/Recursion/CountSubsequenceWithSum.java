package ProblemSolving.Recursion;

public class CountSubsequenceWithSum {
    public static void main(String[] args) {
        int [] arr = {1,1,2};
        int n = 3;
        int ans = new CountSubsequenceWithSum().countSqWithSumK(0, arr,2,n,0);
        System.out.println(ans);
    }
    int countSqWithSumK(int index, int [] arr, int target, int length,int sum){
        if(index >= length){
            if(sum == target){
                return 1;
            }
            return 0;
        }
        int l = countSqWithSumK(index+1,arr,target,length,sum);
        sum+=arr[index];
        int r = countSqWithSumK(index+1,arr,target,length,sum);
        return l+r;
    }

}

