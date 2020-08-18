package baekjoon.p9000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P9376 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M;
    private static char[][] board;

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        int T = Integer.parseInt(token.nextToken());
        for (int t=0; t<T; t++) {
            token = new StringTokenizer(reader.readLine());
            N = Integer.parseInt(token.nextToken());
            M = Integer.parseInt(token.nextToken());
            board = new char[N + 2][M + 2];
            Point start1 = new Point(0, 0),
                    start2 = null, start3 = null;
            for (int i = 1; i < N + 1; i++) {
                String line = reader.readLine();
                for (int j = 1; j < M + 1; j++) {
                    board[i][j] = line.charAt(j - 1);
                    if (board[i][j] == '$') {
                        if (start2 == null)
                            start2 = new Point(i, j);
                        else
                            start3 = new Point(i, j);
                    }
                }
            }
            for (int i = 0; i < N + 2; i++) {
                board[i][0] = '.';
                board[i][M + 2 - 1] = '.';
            }
            for (int i = 0; i < M + 2; i++) {
                board[0][i] = '.';
                board[N + 2 - 1][i] = '.';
            }
            N += 2;
            M += 2;
            int[][] distance1 = bfs(start1);
            int[][] distance2 = bfs(start2);
            int[][] distance3 = bfs(start3);
            int answer = Integer.MAX_VALUE;
            for (int i=0; i<N; i++)
                for (int j=0; j<M; j++) {
                    if (board[i][j] == '*') continue;
                    int d = distance1[i][j] +
                            distance2[i][j] +
                            distance3[i][j];
                    if (board[i][j] == '#')
                        d -= 2;
                    answer = Math.min(answer, d);
                }
            System.out.println(answer);
        }
    }

    private static int[][] bfs(Point start) {
        int[][] distance = new int[N][M];
        for (int i=0; i<N; i++)
            for (int j=0; j<M; j++)
                distance[i][j] = -1;
        Deque<Point> deque = new LinkedList<>();
        deque.push(start);
        distance[start.x][start.y] = 0;
        while (!deque.isEmpty()) {
            Point v = deque.pollLast();
            for (Point d: Point.D) {
                Point w = new Point(v.x + d.x, v.y + d.y);
                if (!w.valid()) continue;
                if (distance[w.x][w.y] != -1) continue;
                if (board[w.x][w.y] == '*') continue;
                if (board[w.x][w.y] == '#') {
                    distance[w.x][w.y] = distance[v.x][v.y] + 1;
                    deque.addFirst(w);
                } else {
                    distance[w.x][w.y] = distance[v.x][v.y];
                    deque.addLast(w);
                }
            }
        }
        return distance;
    }

    private static class Point {
        private static Point[] D = {
                new Point(0, -1), new Point(0, 1),
                new Point(-1, 0), new Point(1, 0)
        };

        final int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public boolean valid() {
            return x >= 0 && x < N && y >= 0 && y < M;
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
    }
}
