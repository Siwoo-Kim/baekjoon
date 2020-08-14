package baekjoon.p17000;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class P17070 {
    private static final Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
    private static int N;
    private static int[][] board;
    private static long[][][] D;

    public static void main(String[] args) {
        N = scanner.nextInt();
        scanner.nextLine();
        board = new int[N][N];
        D = new long[N][N][3];
        for (int i=0; i<N; i++)
            board[i] = Arrays.stream(scanner.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        for (int i=0; i<N; i++)
            for (int j=0; j<N; j++) {
                D[i][j][0] = -1;
                D[i][j][1] = -1;
                D[i][j][2] = -1;
            }
        long answer = bruteForce(Pipe.getPipe(0, 1));
        System.out.println(answer);
    }

    private static long bruteForce(Pipe pipe) {
        if (pipe.tail.index() == N*N-1) return 1;
        if (D[pipe.tail.x][pipe.tail.y][pipe.getID()] != -1)
            return D[pipe.tail.x][pipe.tail.y][pipe.getID()];
        long sum = 0;
        for (Point m: pipe.getMove()) {
            Point head = pipe.head;
            head = head.plus(m);
            if (head.isValid() && head.value() != 1) {
                Pipe next = Pipe.getPipe(pipe.tail, head);
                if (next instanceof Pipe.DiagPipe) {
                    if (!check(next)) continue;
                }
                sum += bruteForce(next);
            }
        }
        return D[pipe.tail.x][pipe.tail.y][pipe.getID()] = sum;
    }

    private static boolean check(Pipe diagPipe) {
        Point head = diagPipe.head;
        for (int i=0; i<2; i++)
            for (int j=0; j<2; j++) {
                Point point = new Point(head.x + i, head.y + j);
                if (!point.isValid() || point.value() == 1)
                    return false;
            }
        return true;
    }

    private abstract static class Pipe {
        Point head, tail;

        public Pipe(int head, int tail) {
            this.head = Point.fromIndex(head);
            this.tail = Point.fromIndex(tail);
        }

        public static Pipe getPipe(int head, int tail) {
            if (head+1 == tail)
                return new HorizontalPipe(head, tail);
            if (head+N == tail)
                return new VerticalPipe(head, tail);
            else
                return new DiagPipe(head, tail);
        }

        public static Pipe getPipe(Point tail, Point head) {
            return getPipe(tail.index(), head.index());
        }

        abstract Point[] getMove();

        public abstract int getID();

        private static class DiagPipe extends Pipe {
            private static Point[] move = {new Point(1, 2), new Point(2, 1), new Point(2, 2)};

            public DiagPipe(int head, int tail) {
                super(head, tail);
            }

            @Override
            Point[] getMove() {
                return move;
            }

            @Override
            public int getID() {
                return 0;
            }
        }

        private static class HorizontalPipe extends Pipe {
            private static Point[] move = {new Point(0, 2), new Point(1, 2)};

            public HorizontalPipe(int head, int tail) {
                super(head, tail);
            }

            @Override
            Point[] getMove() {
                return move;
            }

            @Override
            public int getID() {
                return 1;
            }
        }

        private static class VerticalPipe extends Pipe {
            private static Point[] move = {new Point(2, 0), new Point(2, 1)};

            public VerticalPipe(int head, int tail) {
                super(head, tail);
            }

            @Override
            Point[] getMove() {
                return move;
            }

            @Override
            public int getID() {
                return 2;
            }
        }
    }

    private static class Point {
        final int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public static Point fromIndex(int index) {
            int row = index / N;
            int col = index % N;
            return new Point(row, col);
        }

        public int index() {
            return x * N + y;
        }

        public Point plus(Point p) {
            return new Point(x + p.x, y + p.y);
        }

        public boolean isValid() {
            return x >= 0 && x < N && y >= 0 && y < N;
        }

        public int value() {
            return board[x][y];
        }
    }
}
