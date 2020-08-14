package baekjoon.p17000;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class P17085 {
    private static final Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
    private static char[][] board;
    private static int[][] D = { {0, -1}, {0, 1}, {-1, 0}, {1, 0} };
    private static int N, M;

    public static void main(String[] args) {
        N = scanner.nextInt();
        M = scanner.nextInt();
        board = new char[N][M];
        scanner.nextLine();
        for (int i=0; i<N; i++)
            board[i] = scanner.nextLine().toCharArray();
        long answer = select(0);
        System.out.println(answer);
    }

    private static long select(int index) {
        if (index == N * M) return 0;
        int row = index / M;
        int col = index % M;
        long answer = 0;
        if (board[row][col] == '#') {
            for (int i=0; i<N*M; i++) {
                int dx = i / M;
                int dy = i % M;
                if (board[dx][dy] == '#') {
                    answer = Math.max(answer, simulate(row, col, dx, dy));
                }
            }
        }
        return Math.max(select(index+1), answer);
    }

    private static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Point plus(int[] d) {
            return new Point(x + d[0], y + d[1]);
        }

        public boolean valid() {
            return x >= 0 && x < N && y >= 0 && y < M;
        }

        public char value() {
            return board[x][y];
        }

        public int index() {
            return x * M + y;
        }
    }

    private static long simulate(int row1, int col1, int row2, int col2) {
        int leftSize = 0, rightSize = 0;
        boolean[] visit = new boolean[N*M];
        visit[row1 * M + col1] = true;
        visit[row2 * M + col2] = true;
        Point[] points = new Point[4];
        for (int i=0; i<4; i++)
            points[i] = new Point(row1, col1);
        while (true) {
            boolean ok = true;
            for (int i=0; i<points.length; i++) {
                points[i] = points[i].plus(D[i]);
            }
            for (int i=0; i<points.length; i++) {
                if (points[i].valid()
                        && !visit[points[i].index()]
                        && points[i].value() == '#') {
                    visit[points[i].index()] = true;
                } else {
                    ok = false;
                    break;
                }
            }
            if (!ok) {
                for (Point p: points)
                    if (p.valid())
                        visit[p.index()] = false;
                break;
            } else {
                leftSize++;
            }
        }
        visit[row1 * M + col1] = true;
        visit[row2 * M + col2] = true;
        points = new Point[4];
        for (int i=0; i<4; i++)
            points[i] = new Point(row2, col2);
        while (true) {
            boolean ok = true;
            for (int i=0; i<points.length; i++) {
                points[i] = points[i].plus(D[i]);
                if (points[i].valid()
                        && !visit[points[i].index()]
                        && points[i].value() == '#') {
                    visit[points[i].index()] = true;
                } else {
                    ok = false;
                    break;
                }
            }
            if (!ok) {
                break;
            } else {
                rightSize++;
            }
        }
        return (leftSize * 4 + 1) * (rightSize * 4 + 1);
    }
}
