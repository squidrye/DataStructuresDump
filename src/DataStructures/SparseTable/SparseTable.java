package DataStructures.SparseTable;

import java.util.ArrayList;
import java.util.Arrays;

public class SparseTable {
    int n;
    int p;
    long [][] sparseTable;
    long [][] indexTable;
    int [] log2;
    public SparseTable(long [] values) {
        //Initializing values in sparse table and index table
        n = values.length;
        p = (int) (Math.log(n) / Math.log(2));
        sparseTable = new long[p + 1][n];
        indexTable = new long[p + 1][n];

        for (int i = 0; i < n; i++) {
            sparseTable[0][i] = values[i];
            indexTable[0][i] = i;
        }
        log2 = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            log2[i] = log2[i / 2] + 1;
        }

        // Building Sparse Table now combining values of previous intervals
        /*
        Since each index (i,j) represents ranged query of [j, j + 2^i) or [j, j+2^(i-1)]
        we will traverse along each row i = 1 -> i = p
            for each row we will traverse along column from j = 0 -> j + 2^i;
        */
        for (int i = 1; i <= p; i++) {
            for (int j = 0; j + (1 << i) <= n; j++) { // j = 0 ->j + 1 * 2 ^ i
                long leftInterval = sparseTable[i - 1][j];
                long rightInterval = sparseTable[i - 1][j + (1 << (i - 1))];
                sparseTable[i][j] = Math.min(leftInterval, rightInterval);

                if (leftInterval <= rightInterval) {
                    indexTable[i][j] = indexTable[i - 1][j];
                } else {
                    indexTable[i][j] = indexTable[i - 1][j + (1 << (i - 1))];
                }
            }
        }
    }
    private long queryMin(int l, int r){
        int length = r - l + 1;
        int p = log2[length];
        int k = 1 << p; // 2 ^ p
        return Math.min(sparseTable[p][l], sparseTable[p][r-k+1]);
        }

    public static void main(String[] args) {
        System.out.println("check");
        long [] arr = {4,2,3,7,1,5,3,3,9,6,7,-1,4};
        SparseTable ob = new SparseTable(arr);
        System.out.println(ob.queryMin(1,11));
    }
}
