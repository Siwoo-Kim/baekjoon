package baekjoon.p4000;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class P4574 {
    private static final Scanner scanner = new Scanner(
            new BufferedReader(new InputStreamReader(System.in)));
    private static final PrintWriter writer = new PrintWriter(
            new BufferedWriter(new OutputStreamWriter(System.out)));
    private static final int N = 9;
    private static boolean[][] domino;
    private static int[][] sudoku;

    public static void main(String[] args) {
        int cnt = 1;
        while (true) {
            int M = Integer.parseInt(scanner.nextLine());
            if (M == 0) {
                writer.flush();
                return;
            }
            writer.write("Puzzle " + cnt++ + "\n");
            domino = new boolean[N+1][N+1];
            sudoku = new int[N][N];
            for (int i = 0; i < M; i++) {
                String[] line = scanner.nextLine().split("\\s+");
                int val1 = Integer.parseInt(line[0]);
                Point point1 = Point.newPoint(line[1], val1);
                int val2 = Integer.parseInt(line[2]);
                Point point2 = Point.newPoint(line[3], val2);
                domino[val1][val2] = true;
                domino[val2][val1] = true;
                sudoku[point1.x][point1.y] = point1.val;
                sudoku[point2.x][point2.y] = point2.val;
            }
            String[] line = scanner.nextLine().split("\\s+");
            for (int i = 1; i <= 9; i++) {
                Point point = Point.newPoint(line[i - 1], i);
                sudoku[point.x][point.y] = point.val;
            }
            play(new Point(0, 0, 0));
        }
    }

    public static boolean play(Point point) {
        if (point.index() == N*N) {
            Arrays.stream(sudoku).forEach(rows -> {
                Arrays.stream(rows).forEach(i -> writer.write(i + ""));
                writer.write("\n");
            });
            return true;
        }
        if (point.value() != 0)
            return play(point.next());
        else {
            for (Point m: Point.MV) {
                Point w = point.plus(m);
                if (!w.valid()) continue;
                if (w.value() != 0) continue;
                for (int i=1; i<=9; i++) {
                    for (int j=1; j<=9; j++) {
                        if (i == j) continue;
                        if (domino[i][j]) continue;
                        if (check(w, j) && check(point, i)) {
                            domino[i][j] = true;
                            domino[j][i] = true;
                            sudoku[point.x][point.y] = i;
                            sudoku[w.x][w.y] = j;
                            if (play(point.next()))
                                return true;
                            domino[i][j] = false;
                            domino[j][i] = false;
                            sudoku[point.x][point.y] = 0;
                            sudoku[w.x][w.y] = 0;
                        }
                    }
                }
            }
        }
        return false;
    }

    private static boolean check(Point point, int val) {
        for (int i=0; i<N; i++) {
            if (sudoku[i][point.y] == val)
                return false;
            if (sudoku[point.x][i] == val)
                return false;
        }
        Point start = new Point(point.x/3 * 3, point.y/3 * 3, 0);
        for (int i=0; i<3; i++)
            for (int j=0; j<3; j++) {
                Point p = new Point(start.x + i, start.y + j, 0);
                if (p.value() == val)
                    return false;
            }
        return true;
    }

    private static class Point {
        private static final Point[] MV = {
                new Point(0, 1, 0),
                new Point(1, 0, 0)
        };

        private int x, y, val;

        public static Point  newPoint(String position, int val) {
            int row = position.split("")[0].charAt(0) - 'A';
            int col = Integer.parseInt(position.split("")[1]) - 1;
            return new Point(row, col, val);
        }

        public Point(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }

        public int index() {
            return (x * N) + y;
        }

        public int value() {
            return sudoku[x][y];
        }

        public Point plus(Point point) {
            return new Point(x + point.x, y + point.y, 0);
        }

        public boolean valid() {
            return x >= 0 && x < N && y >= 0 && y < N;
        }

        public Point next() {
            return y == N-1? new Point(x+1, 0, 0):
                    new Point(x, y+1, 0);
        }
    }
}
