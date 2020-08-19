package baekjoon.p2000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Stream;

public class P2234 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Map<Integer, Integer> componentSize = new HashMap<>();
    private static int M, N;
    private static Barrier[][] board;
    private static int[][] components;

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        M = Integer.parseInt(token.nextToken());
        N = Integer.parseInt(token.nextToken());
        board = new Barrier[N][M];
        components = new int[N][M];
        for (int i=0; i<N; i++) {
            int[] beats = Arrays.stream(reader.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            for (int j=0; j<M; j++) {
                int binary = beats[j];
                Barrier b = new Barrier(
                        (binary & (1 << 0)) > 0,
                        (binary & (1 << 1)) > 0,
                        (binary & (1 << 2)) > 0,
                        (binary & (1 << 3)) > 0);
                board[i][j] = b;
            }
        }

        int id = 1;
        for (int i=0; i<N; i++)
            for (int j=0; j<M; j++) {
                if (components[i][j] == 0)
                    bfs(new Point(i, j), id++);
            }

        int max = 0;
        for (int i=0; i<N; i++)
            for (int j=0; j<M; j++) {
                for (Point d: Point.D) {
                    Point w = new Point(i + d.x, j + d.y);
                    if (w.valid() && components[i][j] != components[w.x][w.y])
                        max = Math.max(max,
                                componentSize.get(components[i][j]) +
                                componentSize.get(components[w.x][w.y]));
                }
            }

        System.out.println(componentSize.size());
        System.out.println(componentSize.values().stream().mapToInt(i -> i).max().getAsInt());
        System.out.println(max);
    }

    private static void bfs(Point point, int id) {
        Queue<Point> q = new LinkedList<>();
        q.add(point);
        components[point.x][point.y] = id;
        int size = 1;
        while (!q.isEmpty()) {
            Point v = q.poll();
            for (Point d: Point.D) {
                Point w = new Point(v.x + d.x, v.y + d.y);
                if (!w.valid()) continue;
                if (components[w.x][w.y] != 0) continue;
                if (board[v.x][v.y].isBlocked(d.direction)) continue;
                components[w.x][w.y] = id;
                q.add(w);
                size++;
            }
        }
        componentSize.put(id, size);
    }

    private static class Point {
        private static final Point[] D = {
                new Point(-1, 0, Direction.NORTH),
                new Point(1, 0, Direction.SOUTH),
                new Point(0, -1, Direction.WEST),
                new Point(0, 1, Direction.EAST)
        };
        final int x, y;
        Direction direction;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Point(int x, int y, Direction direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }

        public boolean valid() {
            return x >= 0 && x < N && y >= 0 && y < M;
        }
    }

    enum Direction {
        EAST, WEST, NORTH, SOUTH
    }

    private static class Barrier {
        private Direction east;
        private Direction west;
        private Direction north;
        private Direction south;

        public Barrier(boolean west, boolean north, boolean east, boolean south) {
            this.west = west? Direction.WEST: null;
            this.north = north? Direction.NORTH: null;
            this.east = east? Direction.EAST: null;
            this.south = south? Direction.SOUTH: null;
        }

        public boolean isBlocked(Direction d) {
            return Stream.of(east, west, north, south)
                    .filter(Objects::nonNull)
                    .anyMatch(dir -> dir==d);
        }
    }
}
