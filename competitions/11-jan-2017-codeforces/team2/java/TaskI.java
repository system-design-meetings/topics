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
            inputStream = new FileInputStream("taski.in");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        OutputStream outputStream;
        try {
            outputStream = new FileOutputStream("taski.out");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        MyReader in = new MyReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        TaskI solver = new TaskI();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskI {
        int n;
        int m;
        char[][] a;
        int[] di = new int[]{0, 0, 1, -1, 1, 1, -1, -1};
        int[] dj = new int[]{1, -1, 0, 0, -1, 1, -1, 1};

        public void solve(int testNumber, MyReader in, PrintWriter out) {
            n = in.nextInt();
            m = in.nextInt();
            a = new char[n][m];
            for (int i = 0; i < n; ++i) {
                a[i] = in.next().toCharArray();
            }
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < m; ++j) {
                    if (good(i, j)) {
                        out.println("Yes");
                        return;
                    }
                }
            }
            out.println("No");
        }

        private boolean good(int x, int y) {
            if (a[x][y] == '.') {
                return false;
            }
            for (int k = 0; k < di.length; ++k) {
                if (good(x, y, k)) {
                    return true;
                }
            }
            return false;
        }

        private boolean good(int x, int y, int d) {
            char c = a[x][y];
            for (int i = 1; i < 5; ++i) {
                int nx = x + di[d] * i;
                int ny = y + dj[d] * i;
                if (!inRange(nx, ny)) {
                    return false;
                }
                if (a[nx][ny] != c) {
                    return false;
                }
            }
            return true;
        }

        private boolean inRange(int x, int y) {
            if (x < 0 || y < 0) return false;
            if (x >= n || y >= m) return false;
            return true;
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

