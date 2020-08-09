package p2000;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class P2580 {
    private static final Scanner scanner = new Scanner(
            new BufferedReader(new InputStreamReader(System.in)));
    private static final PrintWriter writer = new PrintWriter(
            new BufferedWriter(new OutputStreamWriter(System.out)));

    private static int N = 9;
    private static int[][] sudoku = new int[N][N];

    public static void main(String[] args) {
        for (int i=0; i<N; i++) {
            sudoku[i] = Arrays.stream(scanner.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }
        play(new Point(0, 0));
    }

    private static boolean play(Point point) {
        if (point.index() == N*N) {
            Arrays.stream(sudoku).forEach(rows -> {
                Arrays.stream(rows).forEach(i -> writer.write(i + " "));
                writer.write("\n");
            });
            writer.flush();
            return true;
        }
        if (point.value() != 0)
            return play(point.next());
        for (int i=1; i<10; i++) {
            if (check(point, i)) {
                sudoku[point.x][point.y] = i;
                if (play(point.next()))
                    return true;
                sudoku[point.x][point.y] = 0;
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
        Point start = new Point(point.x/3 * 3, point.y/3 * 3);
        for (int i=0; i<3; i++)
            for (int j=0; j<3; j++) {
                Point p = new Point(start.x + i,
                        start.y + j);
                if (p.value() == val)
                    return false;
            }
        return true;
    }

    private static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int index() {
            return (x * N) + y;
        }

        public Point next() {
            return y == N-1? new Point(x+1, 0):
                    new Point(x, y+1);
        }

        public int value() {
            return sudoku[x][y];
        }
    }
}
