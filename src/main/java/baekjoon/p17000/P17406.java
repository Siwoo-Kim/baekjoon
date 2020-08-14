package baekjoon.p17000;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class P17406 {
    private static final Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
    private static int[][] board;
    private static int[][] rotations;
    private static int N, M, K;
    private static int[][] D = { {0, 1}, {1, 0}, {0, -1}, {-1, 0} };
    private static int[][] DR = { {1, 0}, {0, 1}, {-1, 0}, {0, -1} };
    private static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) {
        N = scanner.nextInt();
        M = scanner.nextInt();
        K = scanner.nextInt();
        scanner.nextLine();
        board = new int[N][M];
        rotations = new int[K][3];
        for (int i=0; i<N; i++)
            board[i] = Arrays.stream(scanner.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        for (int i=0; i<K; i++)
            rotations[i] = Arrays.stream(scanner.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        boolean[] visit = new boolean[K];
        bruteForce(0, new LinkedList<>(), visit);
        System.out.println(answer);
    }

    private static void bruteForce(int index, LinkedList<Integer> q, boolean[] visit) {
        if (index == K) {
            answer = Integer.min(simulation(new LinkedList<>(q)), answer);
            return;
        }
        for (int i=0; i<K; i++) {
            if (visit[i]) continue;
            visit[i] = true;
            q.push(i);
            bruteForce(index+1, q, visit);
            q.pop();
            visit[i] = false;
        }
    }

    private static int simulation(Queue<Integer> q) {
        if (q.isEmpty()) {
            return Arrays.stream(board).mapToInt(row -> Arrays.stream(row).sum()).min().getAsInt();
        }
        int answer;
        int[] rotation = rotations[q.poll()];
        int size = rotation[2];
        Point center = new Point(rotation[0]-1, rotation[1]-1);
        while (size != 0) {
            Point start = new Point(center.x-size, center.y-size);
            int nextValue = start.value(), prevValue;
            for (int[] d: D) {
                for (int i=0; i<size*2; i++) {
                    Point next = start.plus(d);
                    prevValue = next.value();
                    next.setValue(nextValue);
                    nextValue = prevValue;
                    start = next;
                }
            }
            size--;
        }
        answer = simulation(q);
        while (size <= rotation[2]) {
            Point start = new Point(center.x-size, center.y-size);
            int nextValue = start.value(), prevValue;
            for (int[] d: DR) {
                for (int i=0; i<size*2; i++) {
                    Point next = start.plus(d);
                    prevValue = next.value();
                    next.setValue(nextValue);
                    nextValue = prevValue;
                    start = next;
                }
            }
            size++;
        }
        return answer;
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

        public void setValue(int v) {
            board[x][y] = v;
        }

        public int value() {
            return board[x][y];
        }
    }
}
