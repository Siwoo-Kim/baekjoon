package baekjoon.p1000;

import util.Algorithm;
import util.Tip;
import util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Used(Algorithm.DP)
@Tip("dp(x, y) 을 현재 도착한 하는 경로의 갯수라 할 때," +
    "dp(x, y) = dp(x, y+j) + dp(x+j, x)" +
    "이때 dp(N, N) = 1")
public class P1890 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static long[][] D;
    private static int[][] board;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        board = new int[N][N];
        D = new long[N][N];
        for (int i=0; i<N; i++) {
            board[i] = Arrays.stream(reader.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            Arrays.fill(D[i], -1);
        }
        long answer = dp(new Point(0, 0));
        System.out.println(answer);
    }

    private static long dp(Point point) {
        if (point.x == N-1 && point.y == N-1) return 1;
        if (D[point.x][point.y] != -1)
            return D[point.x][point.y];
        long sum = 0;
        for (Point m: point.move())
             sum += dp(m);
        return D[point.x][point.y] = sum;
    }
    
    private static class Point {
        final int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        public List<Point> move() {
            int val = board[x][y];
            if (val == 0) return Collections.emptyList();
            Point right = new Point(x, y + val);
            Point upper = new Point(x + val, y);
            return Stream.of(right, upper)
                    .filter(this::valid)
                    .collect(Collectors.toList());
        }

        private boolean valid(Point point) {
            return point.x >= 0 && point.x < N 
                    && point.y >= 0 && point.y < N;
        }
    }
}
