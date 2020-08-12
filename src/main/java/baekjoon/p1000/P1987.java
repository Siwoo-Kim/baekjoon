package baekjoon.p1000;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class P1987 {
    private static final Scanner scanner = new Scanner(
            new BufferedReader(new InputStreamReader(System.in)));
    private static int N, M;
    private static String[][] alphabets;
    private static int answer = 0;

    public static void main(String[] args) {
        N = scanner.nextInt();
        M = scanner.nextInt();
        scanner.nextLine();
        alphabets = new String[N][M];
        for (int i=0; i<N; i++)
            alphabets[i] = scanner.nextLine().split("");

        Set<String> visit = new HashSet<>();
        select(1, new Point(0, 0), visit);
        System.out.println(answer);
    }

    private static void select(int cnt, Point point, Set<String> visit) {
        answer = Math.max(cnt, answer);
        visit.add(point.value());
        for (Point m: Point.MV) {
            Point w = new Point(point.x + m.x , point.y + m.y);
            if (!w.valid()) continue;
            if (!visit.contains(w.value())) {
                visit.add(w.value());
                select(cnt+1, w, visit);
                visit.remove(w.value());
            }
        }
    }

    private static class Point {
        private static Point[] MV = {
                new Point(0, -1), new Point(0, 1),
                new Point(-1, 0), new Point(1, 0),
        };
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public String value() {
            return alphabets[x][y];
        }

        public boolean valid() {
            return x >= 0 && x < N && y >= 0 && y < M;
        }
    }
}
