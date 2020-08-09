package p1000;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class P1051 {

    private static final Scanner scanner = new Scanner(
            new BufferedReader(new InputStreamReader(System.in)));
    private static int[][] board;
    private static int N, M;
    private static int answer = 0;

    public static void main(String[] args) {
        N = scanner.nextInt();
        M = scanner.nextInt();
        scanner.nextLine();
        board = new int[N][M];
        for (int i=0; i<N; i++) {
            board[i] = Arrays.stream(scanner.nextLine().split(""))
                    .filter(s -> !s.isEmpty())
                    .mapToInt(Integer::parseInt).toArray();
        }
        select(new Point(0, 0));
        System.out.println(answer);
    }

    private static void select(Point point) {
        if (!point.valid()) return;
        for (int size=N; size>0; size--) {
            Point v1 = new Point(point.x, point.y);
            Point v2 = new Point(point.x, point.y + size-1);
            Point v3 = new Point(point.x + size - 1, point.y);
            Point v4 = new Point(point.x + size - 1, point.y + size - 1);
            if (Point.validPoints(v1, v2, v3, v4)) {
                int v = v1.value();
                if (v2.value() == v &&
                        v3.value() == v &&
                        v4.value() == v) {
                    answer = Math.max(size * size, answer);
                    break;
                }
            }
        }
        select(point.next());
    }

    private static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public static boolean validPoints(Point... points) {
            return Arrays.stream(points).allMatch(Point::valid);
        }

        public Point next() {
            if (y == M-1)
                return new Point(x+1, 0);
            else
                return new Point(x, y+1);
        }

        public boolean valid() {
            return x >= 0 && x < N && y >= 0 && y < M;
        }

        public int value() {
            return board[x][y];
        }
    }
}
