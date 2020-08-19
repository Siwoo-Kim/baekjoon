package baekjoon.p17000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P17086 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M;
    private static int[][] board;

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        board = new int[N][M];
        for (int i=0; i<N; i++)
            board[i] = Arrays.stream(reader.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

        int answer = 0;
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                if (board[i][j] == 0) {
                    answer = Math.max(bfs(new Point(i, j)), answer);
                }
            }
        }
        System.out.println(answer);
    }

    private static int bfs(Point start) {
        Queue<Point> q = new LinkedList<>();
        q.add(start);
        int[][] distance = new int[N][M];
        for (int i=0; i<N; i++)
            Arrays.fill(distance[i], -1);
        distance[start.x][start.y] = 0;
        while (!q.isEmpty()) {
            Point v = q.poll();
            if (board[v.x][v.y] == 1) {
                return distance[v.x][v.y];
            }
            for (Point d: Point.D) {
                Point w = new Point(v.x + d.x, v.y + d.y);
                if (w.valid() && distance[w.x][w.y] == -1) {
                    distance[w.x][w.y] = distance[v.x][v.y] + 1;
                    q.add(w);
                }
            }
        }
        throw new RuntimeException();
    }

    private static class Point {
        private static Point[] D = {
                new Point(-1, 0), new Point(1, 0),
                new Point(0, -1), new Point(0, 1),
                new Point(-1, -1), new Point(-1, 1),
                new Point(1, -1), new Point(1, 1),
        };
        final int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public boolean valid() {
            return x >= 0 && x < N && y >= 0 && y < M;
        }
    }
}
