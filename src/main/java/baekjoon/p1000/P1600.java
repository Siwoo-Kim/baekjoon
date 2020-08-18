package baekjoon.p1000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P1600 {
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M, K;
    private static int[][] board;
    private static int[][][] distance;

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        K = Integer.parseInt(token.nextToken());
        token = new StringTokenizer(reader.readLine());
        M = Integer.parseInt(token.nextToken());
        N = Integer.parseInt(token.nextToken());
        distance = new int[N][M][K+1];
        board = new int[N][M];
        for (int i=0; i<N; i++)
            board[i] = Arrays.stream(reader.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

        Queue<Point> q = new LinkedList<>();
        q.add(new Point(0,0, 0));
        distance[0][0][0] = 0;
        while (!q.isEmpty()) {
            Point v = q.poll();
            if (v.x == N-1 && v.y == M-1) {
                System.out.println(distance[v.x][v.y][v.k]);
                return;
            }
            if (v.k < K) {
                for (Point d : Point.DM) {
                    Point w = new Point(v.k+1, v.x + d.x, v.y + d.y);
                    if (w.valid() &&
                            distance[w.x][w.y][w.k] == 0 &&
                            board[w.x][w.y] == 0) {
                        distance[w.x][w.y][w.k] = distance[v.x][v.y][v.k] + 1;
                        q.add(w);
                    }
                }
            }
            for (Point d : Point.DK) {
                Point w = new Point(v.k, v.x + d.x, v.y + d.y);
                if (w.valid() &&
                        distance[w.x][w.y][w.k] == 0 &&
                        board[w.x][w.y] == 0) {
                    distance[w.x][w.y][w.k] = distance[v.x][v.y][v.k] + 1;
                    q.add(w);
                }
            }
        }
        System.out.println(-1);
    }

    private static class Point {
        private static Point[] DM = {
                new Point(-2, -1), new Point(-1, -2),
                new Point(-2, 1), new Point(-1, 2),
                new Point(1, -2), new Point(2, -1),
                new Point(1, 2), new Point(2, 1)
        };
        private static Point[] DK = {
                new Point(-1, 0), new Point(1, 0),
                new Point(0, -1), new Point(0, 1)
        };
        private int k, x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Point(int k, int x, int y) {
            this.k = k;
            this.x = x;
            this.y = y;
        }

        public boolean valid() {
            return x >= 0 && x < N && y >= 0 && y < M;
        }
    }
}
