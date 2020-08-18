package baekjoon.p16000;

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class P16956 {

    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int N, M;
    private static char[][] board;
    private static boolean[][] visit;


    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        board = new char[N][M];
        visit = new boolean[N][M];
        Queue<Point> sheep = new LinkedList<>();
        for (int i=0; i<N; i++) {
            board[i] = reader.readLine().toCharArray();
            for (int j=0; j<M; j++)
                if (board[i][j] == 'S') {
                    sheep.add(new Point(i, j));
                    visit[i][j] = true;
                }
        }

        boolean ok = true;
        while (!sheep.isEmpty()) {
            Point v = sheep.poll();
            for (Point d: Point.D) {
                Point w = new Point(v.x + d.x, v.y + d.y);
                if (w.valid() && !visit[w.x][w.y]) {
                    if (board[w.x][w.y] == 'W') {
                        ok = false;
                        break;
                    }
                    if (board[w.x][w.y] == '.')
                        board[w.x][w.y] = 'D';
                }
            }
        }
        if (!ok)
            writer.write(0 + "");
        else {
            writer.write(1 + "\n");
            for (int i=0; i<N; i++) {
                for (int j = 0; j < M; j++)
                    writer.write(board[i][j]);
                writer.write("\n");
            }
        }
        writer.flush();
    }

    private static class Point {
        private static Point[] D = {new Point(0, -1), new Point(0, 1), new Point(-1, 0), new Point(1, 0)};
        private final int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public boolean valid() {
            return x >= 0 && x < N && y >= 0 && y < M;
        }
    }
}
