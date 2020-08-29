package baekjoon.p11000;

import util.Algorithm;
import util.TimeComplexity;
import util.Tip;
import util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

@Tip("if D[i][j] = max path from (1, 1) to (i, j)" +
    "then D[i][j] = max(D[i-1][j], D[i][j-1], D[i-1][j-1]) + A[i][j]")
@TimeComplexity("O(NM)")
@Used(Algorithm.DP)
public class P11048 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M;
    private static int[][] board;
    private static int[][] D;

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());
        D = new int[N][M];
        board = new int[N][M];
        for (int i=0; i<N; i++)
            board[i] = Arrays.stream(reader.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        dp(0, N * M);
        System.out.println(D[N-1][M-1]);
    }
    
    private static void dp(int index, int end) {
        if (index == end) return;
        int row = index / M;
        int col = index % M;
        D[row][col] = board[row][col];
        for (Point d: Point.D) {
            Point w = new Point(row + d.x, col + d.y);
            if (w.valid()) {
                D[row][col] = Math.max(D[w.x][w.y] + board[row][col], D[row][col]);
            }
        }
        dp(index + 1, end);
    }
    
    private static class Point {
        private static Point[] D = {
                new Point(0, -1), new Point(-1, 0)
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
        public String toString() {
            return "{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
}
