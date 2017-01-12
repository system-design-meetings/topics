import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 */
public class kings {
    public static void main(String[] args) {
        InputStream inputStream;
        try {
            inputStream = new FileInputStream("kings.in");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        OutputStream outputStream;
        try {
            outputStream = new FileOutputStream("kings.out");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Task solver = new Task();
        solver.solve(1, in, out);
        out.close();
    }

    static class Task {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int m = in.nextInt();
            int k = in.nextInt();
            int a = in.nextInt();
            int b = in.nextInt();
            int c = in.nextInt();
            List<King> kings = new ArrayList<>();
            kings.add(new King("Joffrey", n * a));
            kings.add(new King("Robb", m * b));
            kings.add(new King("Stannis", k * c));

            Collections.sort(kings, (k1, k2) -> Integer.compare(k2.army, k1.army));
            King top = kings.get(0);
            out.print(top.name);
            for (int i = 1; i < kings.size(); ++i) {
                King king = kings.get(i);
                if (top.army > king.army) {
                    break;
                }
                out.print(" " + king.name);
            }
            out.println();
        }

        class King {
            String name;
            int army;

            public King(String name, int army) {
                this.name = name;
                this.army = army;
            }

        }

    }

    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream));
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