package baekjoon.p17000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P17141 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static List<Point> viruses;
    private static int[][] board;
    private static int N, M;
    private static int SIZE;

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        viruses = new ArrayList<>();
        board = new int[N][M];
        for (int i=0; i<N; i++) {
            board[i] = Arrays.stream(reader.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            for (int j=0; j<N; j++) {
                if (board[i][j] == 2)
                    viruses.add(new Point(i, j));
                if (board[i][j] != 1)
                    SIZE++;
            }
        }
        List<List<Integer>> batches = combination(0, M, new Stack<>(), new ArrayList<>());
        boolean ok = false;
        int min = Integer.MAX_VALUE;
        for (int i=0; i<batches.size(); i++) {
            Result result = bfs(batches.get(i));
             if (result.success) {
                 ok = true;
                 min = Math.min(result.distance, min);
             }
        }
        if (ok)
            System.out.println(min);
        else
            System.out.println(-1);
    }

    private static Result bfs(List<Integer> list) {
        Queue<Point> q = new LinkedList<>();
        int[][] distance = new int[N][N];
        for (int i=0; i<N; i++)
            Arrays.fill(distance[i], -1);
        for (int i=0; i<list.size(); i++) {
            Point virus = viruses.get(list.get(i));
            distance[virus.x][virus.y] = 0;
            q.add(virus);
        }
        int size = SIZE, maxDistance = 0;
        while (!q.isEmpty()) {
            Point v = q.poll();
            size--;
            maxDistance = Integer.max(distance[v.x][v.y], maxDistance);
            for (Point d: Point.D) {
                Point w = new Point(v.x + d.x, v.y + d.y);
                if (w.valid() &&
                    distance[w.x][w.y] == -1 &&
                    board[w.x][w.y] != 1) {
                    distance[w.x][w.y] = distance[v.x][v.y] + 1;
                    q.add(w);
                }
            }
        }
        Result result = new Result();
        result.success = size == 0;
        result.distance = maxDistance;
        return result;
    }

    private static class Result {
        boolean success;
        int distance;
    }

    private static List<List<Integer>> combination(int index, int M,
                                                   Stack<Integer> stack,
                                                   List<List<Integer>> result) {
        if (stack.size() == M) {
            result.add(new ArrayList<>(stack));
            return result;
        }
        for (int i=index; i<viruses.size(); i++) {
            stack.push(i);
            combination(i+1, M, stack, result);
            stack.pop();
        }
        return result;
    }

    private static class Point {
        public static final Point[] D = {
                new Point(-1, 0), new Point(1, 0),
                new Point(0, -1), new Point(0, 1),
        };
        final int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public boolean valid() {
            return x >= 0 && x < N && y >= 0 && y < N;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
}
