package baekjoon.p15000;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class P15683 {
    private static final Scanner scanner = new Scanner(
            new BufferedReader(new InputStreamReader(System.in)));
    private static Point[][] board;
    private static boolean[][] visit;
    private static int N, M;
    private static int answer = 0;

    public static void main(String[] args) {
        N = scanner.nextInt();
        M = scanner.nextInt();
        scanner.nextLine();
        visit = new boolean[N][M];
        board = new Point[N][M];
        int cctvs = 0, walls = 0;
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                int val = scanner.nextInt();
                if (val == 6) {
                    board[i][j] = new Wall(i, j);
                    walls++;
                }
                else if (val > 0) {
                    board[i][j] = CCTV.newCCTV(i, j, val);
                    cctvs++;
                }  else
                    board[i][j] = new Point(i, j);
            }
        }
        traverse(board[0][0], 0);
        System.out.println(N * M - answer - cctvs - walls);
    }

    private static void traverse(Point point, int count) {
        if (point == null) {
            answer = Math.max(answer, count);
            return;
        }
        if (!(point instanceof CCTV))
            traverse(point.next(), count);
        else {
            CCTV cctv = (CCTV) point;
            for (Point[] rotations: cctv.rotations()) {
                int cnt = 0;
                List<Point> pairs = new ArrayList<>();
                for (Point move: rotations) {
                    Point w = cctv;
                    while (true) {
                        w = w.plus(move);
                        if (w != null &&
                                !(w instanceof Wall)) {
                            if (!(w instanceof CCTV)
                                    && !visit[w.x][w.y]) {
                                cnt++;
                                visit[w.x][w.y] = true;
                                pairs.add(w);
                            }
                        } else {
                            break;
                        }
                    }
                }
                traverse(point.next(), count + cnt);
                for (Point v: pairs)
                    visit[v.x][v.y] = false;
            }
        }
    }

    private static class Point {
        private final int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int index() {
            return x * M + y;
        }

        public Point next() {
            if (index() == (N*M-1))
                return null;
            return y == M-1? board[x+1][0]: board[x][y+1];
        }

        public Point plus(Point p) {
            Point tmp = new Point(x + p.x, y + p.y);
            return tmp.valid()? board[tmp.x][tmp.y]: null;
        }

        private boolean valid() {
            return x >= 0 && x < N && y >= 0 && y < M;
        }

        protected Point sign() {
            return new Point(-x, -y);
        }

        protected Point swap() {
            return new Point(y, x);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass())
                return false;
            Point point = (Point) o;
            return x == point.x &&
                    y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    private static class Wall extends Point {
        public Wall(int x, int y) {
            super(x, y);
        }
    }

    private static class CCTV extends Point {
        private static Map<Integer, Point[]> map = new HashMap<>();
        private static Set<Point[]> threeCase = new HashSet<>();

        private int type;

        private CCTV(int x, int y, int type) {
            super(x, y);
            this.type = type;
        }

        static {
            map.put(1, new Point[]{new Point(0, 1) });
            map.put(2, new Point[]{new Point(0, 1), new Point(0, -1) });
            map.put(3, new Point[]{new Point(0, 1), new Point(-1, 0) });
            map.put(4, new Point[]{new Point(0, 1), new Point(0, -1), new Point(-1, 0) });
            map.put(5, new Point[]{new Point(0, 1), new Point(0, -1), new Point(-1, 0), new Point(1, 0) });
        }

        static {
            threeCase.add(new Point[]{ new Point(0, 1), new Point(-1, 0) });
            threeCase.add(new Point[]{ new Point(0, 1), new Point(1, 0) });
            threeCase.add(new Point[]{ new Point(-1, 0), new Point(0, -1) });
            threeCase.add(new Point[]{ new Point(1, 0), new Point(0, -1) });
        }

        public static CCTV newCCTV(int x, int y, int type) {
            return new CCTV(x, y, type);
        }

        public Set<Point[]> rotations() {
            //todo cover exceptional case
            if (type == 3)
                return threeCase;
            Set<Point[]> set = new HashSet<>();
            Point[] origin = moves();
            set.add(origin);
            origin = moves();
            for (int i=0; i<origin.length; i++)
                origin[i] = origin[i].sign();
            set.add(origin);
            origin = moves();
            for (int i=0; i<origin.length; i++)
                origin[i] = origin[i].swap();
            set.add(origin);
            origin = moves();
            for (int i=0; i<origin.length; i++)
                origin[i] = origin[i].swap().sign();
            set.add(origin);
            return set;
        }

        public Point[] moves() {
            return Arrays.copyOf(map.get(type),
                    map.get(type).length);
        }
    }
}
