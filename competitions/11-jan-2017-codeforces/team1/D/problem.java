import java.io.*;
import java.util.*;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 */
public class problem {
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
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Task solver = new Task();
        solver.solve(1, in, out);
        out.close();
    }

    static class Task {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            List<Point> points = new ArrayList<>();
            int[][] dists = new int[n][n];
            for (int i = 0; i < n; i++) {
                points.add(new Point(i, in.nextInt(), in.nextInt()));
            }
            int max = 0;
            Point maxA = null;
            Point maxB = null;
            int sum = 0;
            for (int i = 0; i < n; i++) {
                Point a = points.get(i);
                for (int j = i + 1; j < n; j++) {
                    Point b = points.get(j);
                    int dist = a.distance(b);
                    dists[a.id][b.id] = dist;
                    dists[b.id][a.id] = dist;

                }
            }
            int min = Integer.MAX_VALUE / 2;
            for (int i = 0; i < n; i++) {
                min = Math.min(min, findMin(i, dists));
            }
            out.println(min);
        }

        private int findMin(int cur, int[][] dists) {
            int n = dists.length;
            int count = 1;
            int res = 0;
            Set<Integer> visited = new HashSet<>();
            visited.add(cur);
            while (visited.size() != n) {
                int next = findMin(dists[cur], visited);
                visited.add(next);
                res += dists[cur][next];
                cur = next;
            }
            return res;
        }

        private int findMin(int[] dist, Set<Integer> vi) {
            int min = Integer.MAX_VALUE;
            int idx = 0;
            for (int i = 0; i < dist.length; ++i) {
                if (min > dist[i] && !vi.contains(i)) {
                    min = dist[i];
                    idx = i;
                }
            }
            return idx;
        }

        class Point {
            int id;
            int x;
            int y;

            public Point(int id, int x, int y) {
                this.id = id;
                this.x = x;
                this.y = y;
            }


            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;

                Point point = (Point) o;

                if (x != point.x) return false;
                return y == point.y;
            }


            public int hashCode() {
                int result = x;
                result = 31 * result + y;
                return result;
            }

            public int distance(Point o) {
                return (this.x - o.x) * (this.x - o.x) + (this.y - o.y) * (this.y - o.y);
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