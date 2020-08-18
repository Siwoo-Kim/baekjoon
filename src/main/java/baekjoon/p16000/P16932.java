package baekjoon.p16000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P16932 {
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int[][] board;
    private static int[][] components;
    private static Map<Integer, Integer> counts = new HashMap<>();
    private static int N, M, ID;

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        board = new int[N][M];
        components = new int[N][M];

        for (int i=0; i<N; i++)
            board[i] = Arrays.stream(reader.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                if (board[i][j] == 1
                        && components[i][j] == 0)
                    bfs(new Point(i, j));
            }
        }
        int answer = 0;
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                Point p = new Point(i, j);
                if (board[p.x][p.y] != 0) continue;
                Set<Integer> used = new HashSet<>();
                int value = 0;
                for (Point d: Point.D) {
                    Point w = new Point(p.x + d.x, p.y + d.y);
                    if (w.valid() &&
                            components[w.x][w.y] != 0 &&
                            !used.contains(components[w.x][w.y])) {
                        value += counts.get(components[w.x][w.y]);
                        used.add(components[w.x][w.y]);
                    }
                }
                answer = Math.max(value, answer);
            }
        }
        System.out.println(answer + 1);
    }

    private static void bfs(Point start) {
        Queue<Point> q = new LinkedList<>();
        q.add(start);
        components[start.x][start.y] = ++ID;
        int size = 1;
        while (!q.isEmpty()) {
            Point v = q.poll();
            for (Point d: Point.D) {
                Point w = new Point(v.x + d.x, v.y + d.y);
                if (w.valid()
                        && board[w.x][w.y] == 1
                        && components[w.x][w.y] == 0) {
                    components[w.x][w.y] = ID;
                    q.add(w);
                    size++;
                }
            }
        }
        counts.put(ID, size);
    }

    private static class Point {
        private static final Point[] D = {
            new Point(-1, 0), new Point(1, 0),
            new Point(0, -1), new Point(0, 1)
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
