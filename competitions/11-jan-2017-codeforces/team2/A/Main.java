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
            inputStream = new FileInputStream("number.in");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        OutputStream outputStream;
        try {
            outputStream = new FileOutputStream("number.out");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        MyReader in = new MyReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        TaskA solver = new TaskA();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskA {
        private final long MOD = (long) (1e9) + 7;

        public void solve(int testNumber, MyReader in, PrintWriter out) {
            long k = in.nextLong();
            long inv = pow(29, MOD - 2);
            long p = pow(10, k * 28);
            p -= 1;
            if (p < 0) {
                p += MOD;
            }
            p *= 6;
            p %= MOD;

            p *= inv;
            p %= MOD;
            out.println(p);
        }

        private long pow(long x, long n) {
            long ret = 1;
            while (n > 0) {
                if (n % 2 == 1) {
                    ret *= x;
                }
                x *= x;
                n /= 2;

                ret %= MOD;
                x %= MOD;
            }

            return ret;
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

        public long nextLong() {
            return Long.parseLong(next());
        }

    }
}

