package baekjoon.p2000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P2151 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static char[][] board;
    private static int[][] distance;
    private static Map<Integer, Set<Point>> G;
    private static int N;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        board = new char[N][N];
        distance = new int[N][N];
        G = new HashMap<>();
        int start = -1, end = -1;
        for (int i=0; i<N; i++) {
            board[i] = reader.readLine().toCharArray();
            for (int j=0; j<N; j++) {
                if (board[i][j] == '#') {
                    int id = i * N + j;
                    if (start == -1)
                        start = id;
                    else
                        end = id;
                    G.put(id, new HashSet<>());
                }
                if (board[i][j] == '!') {
                    int id = i * N + j;
                    G.put(id, new HashSet<>());
                }
            }
        }
        for (int v: G.keySet()) {
            for (Point d: Point.D) {
                Point w = new Point((v / N) + d.x, (v % N) + d.y);
                while (w.valid()) {
                    if (board[w.x][w.y] == '*') break;
                    if (board[w.x][w.y] == '!'
                            || board[w.x][w.y] == '#') {
                        G.get(v).add(w);
                    }
                    w = new Point(w.x + d.x, w.y + d.y);
                }
            }
        }
        for (int i=0; i<N; i++)
            Arrays.fill(distance[i], -1);
        Queue<Point> q = new LinkedList<>();
        Point p = new Point(start / N, start % N);
        q.add(p);
        distance[p.x][p.y] = 0;
        while (!q.isEmpty()) {
            p = q.poll();
            for (Point w: G.get(p.index())) {
                if (distance[w.x][w.y] == -1) {
                    distance[w.x][w.y] = distance[p.x][p.y] + 1;
                    q.add(w);
                }
            }
        }
        System.out.println(distance[end / N][end % N] - 1);
    }

    private static class Point {
        private static Point[] D = {
                new Point(-1, 0), new Point(1, 0),
                new Point(0, -1), new Point(0, 1),
        };
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public boolean valid() {
            return x >= 0 && x < N && y >= 0 && y < N;
        }

        public Integer index() {
            return x * N + y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x &&
                    y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
}
