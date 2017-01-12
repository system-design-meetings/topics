import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
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
            inputStream = new FileInputStream("problem.in");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        OutputStream outputStream;
        try {
            outputStream = new FileOutputStream("problem.out");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        MyReader in = new MyReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        TaskD solver = new TaskD();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskD {
        public void solve(int testNumber, MyReader in, PrintWriter out) {
            int n = in.nextInt();
            int[] x = new int[n];
            int[] y = new int[n];
            for (int i = 0; i < n; ++i) {
                x[i] = in.nextInt();
                y[i] = in.nextInt();
            }
            int[][] d = new int[n][n];
            for (int i = 0; i < n; ++i) {
                for (int j = i + 1; j < n; ++j) {
                    d[i][j] = d[j][i] = (x[i] - x[j]) * (x[i] - x[j]) + (y[i] - y[j]) * (y[i] - y[j]);
                }
            }

            int ret = Integer.MAX_VALUE;
            for (int i = 0; i < n; ++i) {
                boolean[] used = new boolean[n];
                int time = 0, v = i;
                while (true) {
                    used[v] = true;
                    int next = -1;
                    for (int j = 0; j < n; ++j) {
                        if (used[j]) continue;
                        if (next == -1 || d[v][next] > d[v][j]) {
                            next = j;
                        }
                    }
                    if (next == -1) break;
                    time += d[v][next];
                    v = next;
                }
                ret = Math.min(ret, time);
            }

            out.println(ret);
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

