import java.io.*;
import java.util.*;
import java.lang.Math;
/**
** Find all pairs in A such that: a_i - a_j < j - i (1). From (1) follows a_i + i < a_j + j. 
** The problems reduces find LIS in new array b[i] = a[i] + i
**/
 
public class Main {
    // Binary search (note boundaries in the caller)
    // A[] is ceilIndex in the caller
    static int CeilIndex(int A[], int l, int r, int key) {
        while (r - l > 1) {
            int m = l + (r - l)/2;
            if (A[m]>=key)
                r = m;
            else
                l = m;
        }
 
        return r;
    }
 
    static int longestIncreasingSubsequenceLength(int A[], int size) {
        // Add boundary case, when array size is one
 
        int[] tailTable   = new int[size];
        int len; // always points empty slot
 
        tailTable[0] = A[0];
        len = 1;
        for (int i = 1; i < size; i++) {
            if (A[i] < tailTable[0])
                // new smallest value
                tailTable[0] = A[i];
 
            else if (A[i] > tailTable[len-1])
                // A[i] wants to extend largest subsequence
                tailTable[len++] = A[i];
 
            else
                // A[i] wants to be current end candidate of an existing
                // subsequence. It will replace ceil value in tailTable
                tailTable[CeilIndex(tailTable, -1, len-1, A[i])] = A[i];
        }
 
        return len;
    }
 

    public static void main(String[] args) throws IOException {
        String fileName = "secretroom";
        BufferedScanner in = new BufferedScanner(fileName);
        PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(fileName+".out")));
        
        int n = in.nextInt();
        int[] A = new int[n];
        for(int i=0; i < n; i++) {
            A[i] = in.nextInt() + i;
        }
        int ans = longestIncreasingSubsequenceLength(A, n);
        out.println(ans);
        out.close();
    }

    public static class BufferedScanner {
        BufferedReader br;
        StringTokenizer st;

        public BufferedScanner(String fileName) throws IOException {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName+".in")));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine(){
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }

    }
}