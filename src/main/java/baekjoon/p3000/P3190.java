package baekjoon.p3000;

import java.io.InputStreamReader;
import java.util.*;

public class P3190 {
    private static final Scanner scanner = new Scanner(new InputStreamReader(System.in));
    //east south west north
    private static int N;
    private static int[][] d = { {0, 1}, {1, 0}, {0, -1}, {-1, 0} };
    private static boolean[][] apples;
    private static boolean[][] snake;

    public static void main(String[] args) {
        N = scanner.nextInt();
        apples = new boolean[N][N];
        snake = new boolean[N][N];

        int A = scanner.nextInt();
        for (int i=0; i<A; i++)
            apples[scanner.nextInt()-1][scanner.nextInt()-1] = true;
        Queue<Pair> commands = new LinkedList<>();
        int K = scanner.nextInt();
        scanner.nextLine();
        for (int i=0; i<K; i++) {
            String[] line = scanner.nextLine().split("\\s+");
            commands.add(new Pair(Integer.parseInt(line[0]), line[1]));
        }
        int second = 0;
        int dir = 0;
        Point point = new Point(0, 0);
        Queue<Point> snakes = new LinkedList<>();
        snakes.add(point);
        while (true) {
            second++;
            point = new Point(d[dir][0] + point.x, d[dir][1] + point.y);
            if (!valid(point.x, point.y) || snake[point.x][point.y]) {
                System.out.println(second);
                return;
            }

            snake[point.x][point.y] = true;
            snakes.add(point);

            if (apples[point.x][point.y]) {
                apples[point.x][point.y] = false;
            } else {
                Point tail = snakes.poll();
                snake[tail.x][tail.y] = false;
            }
            if (!commands.isEmpty() && commands.peek().second == second) {
                Pair pair = commands.poll();
                if ("L".equals(pair.dir)) {
                    dir = (dir + 3) % 4;
                } else {
                    dir = (dir + 1) % 4;
                }
            }
        }
    }

    private static class Point {
        private int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static boolean valid(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }

    private static class Pair {
        int second;
        String dir;

        public Pair(int second, String dir) {
            this.second = second;
            this.dir = dir;
        }
    }
}
