import java.io.*;
import java.util.StringTokenizer;

/**
 * * Built using CHelper plug-in
 * * Actual solution is at the top
 */
public class tic {
    public static void main(String[] args) {
        InputStream inputStream;
        try {
            inputStream = new FileInputStream("tic-tac-toe.in");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        OutputStream outputStream;
        try {
            outputStream = new FileOutputStream("tic-tac-toe.out");
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
            char[][] field = new char[n][m];
            int xs = 0;
            int os = 0;
            for (int i = 0; i < n; i++) {
                String line = in.nextLine();
                for (int j = 0; j < m; j++) {
                    char c = line.charAt(j);
                    field[i][j] = c;
                }
            }
            String result = "No";

            if (checkField(field, 'X', n, m) || checkField(field, 'O', n, m)) {
                result = "Yes";
            }
            out.println(result);
        }

        public boolean checkField(char[][] field, char player, int n, int m) {
            for (int i = 0; i < n; i++) {
                int count = 0;
                for (int j = 0; j < m; j++) {
                    if (count >= 5) return true;
                    if (field[i][j] == player) {
                        count++;
                    } else {
                        count = 0;
                    }
                }
                if (count >= 5) return true;
            }
            for (int i = 0; i < m; i++) {
                int count = 0;
                for (int j = 0; j < n; j++) {
                    if (count >= 5) return true;
                    if (field[j][i] == player) {
                        count++;
                    } else {
                        count = 0;
                    }
                }
                if (count >= 5) return true;
            }

            for (int i = -n; i < n; ++i) {
                int count = 0;
                for (int j = 0; j < m; j++) {
                    if (count >= 5) return true;
                    if (infield(i + j, j, n, m) && field[i + j][j] == player) {
                        count++;
                    } else {
                        count = 0;
                    }
                }
                if (count >= 5) return true;
            }
            for (int i = n; i > -n; i--) {
                int count = 0;
                for (int j = m; j > -m; j--) {
                    if (!infield(i - j, j, n, m)) continue;
                    if (count >= 5) return true;
                    if (field[i - j][j] == player) {
                        count++;
                    } else {
                        count = 0;
                    }
                }
                if (count >= 5) return true;
            }
            return false;
        }

        private boolean infield(int i, int i1, int n, int m) {
            return i >= 0 && i1 >= 0 && i < n && i1 < m;
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


        public String nextLine() {
            try {
                return reader.readLine();
            } catch (IOException e) {

            }
            return "";
        }


    }
}
