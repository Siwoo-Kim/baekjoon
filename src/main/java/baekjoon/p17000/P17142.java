package baekjoon.p17000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P17142 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static List<Point> virus = new ArrayList<>();
    private static int[][] board;
    private static int N, M, SIZE;

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        board = new int[N][M];
        for (int i=0; i<N; i++) {
            board[i] = Arrays.stream(reader.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            for (int j=0; j<N; j++) {
                if (board[i][j] == 2)
                    virus.add(new Point(i, j));
                else if (board[i][j] == 0)
                    SIZE++;
            }
        }

        List<int[]> batches = combinations(0, M, new Stack<>(), new ArrayList<>());
        boolean ok = false;
        int answer = Integer.MAX_VALUE;
        for (int[] batch: batches) {
            Result result = bfs(batch);
            if (result.ok) {
                ok = true;
                answer = Math.min(answer, result.distance);
            }
        }
        if (ok)
            System.out.println(answer);
        else
            System.out.println(-1);
    }

    private static Result bfs(int[] batch) {
        int[][] distance = new int[N][N];
        for (int i=0; i<N; i++)
            Arrays.fill(distance[i], -1);
        Deque<Point> q = new LinkedList<>();
        for (int i=0; i<batch.length; i++) {
            Point p = virus.get(batch[i]);
            q.add(p);
            distance[p.x][p.y] = 0;
        }
        int max = 0;
        int size = SIZE;
        while (!q.isEmpty()) {
            Point v = q.poll();
            if (board[v.x][v.y] == 0) {
                max = Math.max(distance[v.x][v.y], max);
                size--;
            }
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
        result.ok = size == 0;
        result.distance = max;
        return result;
    }

    private static class Result {
        boolean ok;
        int distance;
    }

    private static List<int[]> combinations(int index, int M, Stack<Integer> stack, ArrayList<int[]> result) {
        if (stack.size() == M) {
            int[] c = stack.stream().mapToInt(Integer::intValue).toArray();
            result.add(c);
            return result;
        }
        for (int i=index; i<virus.size(); i++) {
            stack.push(i);
            combinations(i+1, M, stack, result);
            stack.pop();
        }
        return result;
    }

    private static class Point {
        public static final Point[] D = {
                new Point(-1, 0), new Point(1, 0),
                new Point(0, -1), new Point(0, 1)
        };
        final int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public boolean valid() {
            return x >= 0 && x < N && y >= 0 && y < N;
        }
    }
}
