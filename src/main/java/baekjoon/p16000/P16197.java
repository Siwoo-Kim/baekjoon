package baekjoon.p16000;

import java.util.Scanner;

public class P16197 {
    static char[][] board;
    static int N, M;
    static int COUNT = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        M = scanner.nextInt();
        board = new char[N][M];
        scanner.nextLine();

        Point p1 = null, p2 = null;
        for (int i=0; i<N; i++) {
            String line = scanner.nextLine();
            for (int j=0; j<line.length(); j++) {
                board[i][j] = line.charAt(j);
                if (board[i][j] == 'o') {
                    if (p1 == null)
                        p1 = new Point(i, j);
                    else
                        p2 = new Point(i, j);
                }
            }
        }
        select(0, p1.clone(), p2.clone());
        if (COUNT == Integer.MAX_VALUE)
            System.out.println(-1);
        else
            System.out.println(COUNT);
    }

    private static void select(int cnt, Point p1, Point p2) {
        if (cnt > 10) return;
        if (p1.isDrop() || p2.isDrop()) {
            if (p1.isDrop() && p2.isDrop())
                return;
            COUNT = Math.min(cnt, COUNT);
            return;
        }
        for (Point move: Point.MOVE) {
            Point movedP1 = p1.plus(move);
            Point movedP2 = p2.plus(move);
            if (movedP1.isBlocked())
                movedP1 = p1.clone();
            if (movedP2.isBlocked())
                movedP2 = p2.clone();
            select(cnt+1, movedP1, movedP2);
        }
    }

    private static class Point implements Cloneable {
        final int row, col;

        private static final Point[] MOVE = new Point[] {
                new Point(0, -1), new Point(0, 1),
                new Point(1, 0), new Point(-1, 0)
        };

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public boolean isDrop() {
            return row < 0 || row >= N || col < 0 || col >= M;
        }

        @Override
        protected Point clone() {
            return new Point(row, col);
        }

        public Point plus(Point p) {
            return new Point(row + p.row, col + p.col);
        }

        public boolean isBlocked() {
            return !isDrop() && board[row][col] == '#';
        }
    }
}
