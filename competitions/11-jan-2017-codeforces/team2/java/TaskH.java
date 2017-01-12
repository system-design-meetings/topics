import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 *
 * @author darkhan imangaliyev
 */
public class Main {
    public static void main(String[] args) {
        InputStream inputStream;
        try {
            inputStream = new FileInputStream("taskh.in");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        OutputStream outputStream;
        try {
            outputStream = new FileOutputStream("taskh.out");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        MyReader in = new MyReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        TaskH solver = new TaskH();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskH {
        int n;
        int k;
        char[] a;
        int[][] dp;
        final int oo = Integer.MAX_VALUE / 2;

        public void solve(int testNumber, MyReader in, PrintWriter out) {
            k = in.nextInt();
            a = in.next().toCharArray();
            n = a.length;

            dp = new int[n][n];
            for (int[] i : dp) {
                Arrays.fill(i, oo);
            }
            for (int i = 0; i < n; ++i) {
                dp[i][i] = 0;
            }
            for (int i = 0; i + 1 < n; ++i) {
                if (a[i] == a[i + 1]) {
                    dp[i][i + 1] = 0;
                } else {
                    dp[i][i + 1] = 1;
                }
            }
            for (int l = 3; l <= n; ++l) {
                for (int i = 0; i <= n - l; ++i) {
                    int j = i + l - 1;
                    if (a[i] == a[j]) {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                    dp[i][j] = min(dp[i][j], dp[i + 1][j] + 1, dp[i][j - 1] + 1, dp[i + 1][j - 1] + 2);
                }
            }

            for (int i = n; i > 0; --i) {
                if (can(i)) {
                    for (int j = 0; j <= n - i; ++j) {
                        if (dp[j][j + i - 1] <= k) {
                            out.println((j + 1) + " " + (j + i));
                            return;
                        }
                    }
                }
            }
        }

        private boolean can(int l) {
            for (int i = 0; i <= n - l; ++i) {
                int j = i + l - 1;
                if (dp[i][j] <= k) {
                    return true;
                }
            }
            return false;
        }

        private int min(int... a) {
            int min = a[0];
            for (int i = 1; i < a.length; ++i) {
                min = Math.min(min, a[i]);
            }
            return min;
        }

    }

    static class MyReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public MyReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

    }
}

