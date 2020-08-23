package baekjoon.p3000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P3085 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static char[][] board;
    private static int N, answer = 0;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        board = new char[N][N];
        for (int i=0; i<N; i++)
            board[i] = reader.readLine().toCharArray();
        swapCandy(0);
        System.out.println(answer);
    }

    private static void swapCandy(int index) {
        if (index == N*N) return;
        int row = index / N;
        int col = index % N;
        Point p = new Point(row, col);
        for (Point d: Point.D) {
            Point w = new Point(p.x + d.x, p.y + d.y);
            if (!w.valid()) continue;
            swap(p, w);
            answer = Math.max(calculate(), answer);
            swap(p, w);
        }
        swapCandy(index + 1);
    }

    private static void swap(Point p, Point w) {
        char t = board[p.x][p.y];
        board[p.x][p.y] = board[w.x][w.y];
        board[w.x][w.y] = t;
    }

    private static int calculate() {
        int answer = 0;
        for (int i=0; i<N; i++) {
            int cnt = 1;
            for (int j=1; j<N; j++) {
                if (board[i][j] == board[i][j-1])
                    cnt++;
                else
                    cnt = 1;
                answer = Math.max(answer, cnt);
            }
        }
        for (int i=0; i<N; i++) {
            int cnt = 1;
            for (int j=1; j<N; j++) {
                if (board[j][i] == board[j-1][i])
                    cnt++;
                else
                    cnt = 1;
                answer = Math.max(answer, cnt);
            }
        }
        return answer;
    }

    private static class Point {
        private static Point[] D = {new Point(1, 0), new Point(0, 1)};
        final int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public boolean valid() {
            return x >= 0 && x < N && y >= 0 && y < N;
        }
    }
}
