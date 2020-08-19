package baekjoon.p4000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P4991 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M;
    private static char[][] board;
    private static Map<Integer, Map<Integer, Integer>> distanceMap;

    // O(10!+NM)
    public static void main(String[] args) throws IOException {
        while (true) {
            StringTokenizer token = new StringTokenizer(reader.readLine());
            M = Integer.parseInt(token.nextToken());
            N = Integer.parseInt(token.nextToken());
            if (M == 0 && N == 0) return;
            distanceMap = new HashMap<>();
            List<Integer> permutation = new ArrayList<>();
            board = new char[N][M];
            Point start = null;
            int nodeSize = 0;
            for (int i = 0; i < N; i++) {
                board[i] = reader.readLine().toCharArray();
                for (int j = 0; j < M; j++) {
                    int index = i * M + j;
                    if (board[i][j] == 'o') {
                        start = new Point(i, j);
                        distanceMap.put(index, new HashMap<>());
                    }
                    if (board[i][j] == '*') {
                        permutation.add(i * M + j);
                        distanceMap.put(index, new HashMap<>());
                        nodeSize++;
                    }
                }
            }
            for (int index : distanceMap.keySet())
                bfs(new Point(index / M, index % M));
            Collections.sort(permutation);

            int answer = Integer.MAX_VALUE;
            if (distanceMap.get(start.index()).size() != nodeSize) {
                System.out.println(-1);
            } else {
                do {
                    int distance = distanceMap.get(start.index()).get(permutation.get(0));
                    for (int i = 1; i < permutation.size(); i++)
                        distance += distanceMap.get(permutation.get(i - 1)).get(permutation.get(i));
                    answer = Math.min(answer, distance);
                } while (nextPermutation(permutation));
                System.out.println(answer);
            }
        }
    }

    private static boolean nextPermutation(List<Integer> p) {
        int i = p.size() - 1;
        while (i > 0 && p.get(i-1) >= p.get(i))
            i--;
        if (i == 0) return false;
        int j = p.size() - 1;
        while (i < j && p.get(i-1) >= p.get(j))
            j--;
        swap(i-1, j, p);
        j = p.size()-1;
        while (i<j)
            swap(i++, j--, p);
        return true;
    }

    private static void swap(int i, int j, List<Integer> p) {
        int t = p.get(i);
        p.set(i, p.get(j));
        p.set(j, t);
    }

    private static void bfs(Point point) {
        int[][] distance = new int[N][M];
        for (int i=0; i<N; i++)
            Arrays.fill(distance[i], -1);
        Queue<Point> q = new LinkedList<>();
        q.add(point);
        distance[point.x][point.y] = 0;
        while (!q.isEmpty()) {
            Point v = q.poll();
            if (board[v.x][v.y] == '*')
                distanceMap.get(point.index()).put(v.index(), distance[v.x][v.y]);
            for (Point d: Point.D) {
                Point w = new Point(v.x + d.x, v.y + d.y);
                if (w.valid()
                        && board[w.x][w.y] != 'x'
                        && distance[w.x][w.y] == -1) {
                    distance[w.x][w.y] = distance[v.x][v.y] + 1;
                    q.add(w);
                }
            }
        }
    }

    private static class Point {
        private static Point[] D = {
                new Point(-1, 0), new Point(1, 0),
                new Point(0, -1), new Point(0, 1),
        };

        private int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Integer index() {
            return x * M + y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x &&
                    y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        public boolean valid() {
            return x >= 0 && x < N && y >= 0 && y < M;
        }
    }
}
