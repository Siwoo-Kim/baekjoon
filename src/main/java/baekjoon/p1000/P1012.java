package baekjoon.p1000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class P1012 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int T, N, M, K;
    private static int[][] board;

    private static class UF {
        private static int MAX = 2500;
        private int[] ids;
        private int size;

        public UF(int size) {
            this.size = size;
            ids  = new int[MAX+1];
            for (int i=0; i<ids.length; i++)
                ids[i] = i;
        }

        public void connect(int v, int w) {
            if (connected(v, w)) return;
            int pv = get(v);
            int pw = get(w);
            ids[pv] = pw;
            size--;
        }

        private boolean connected(int v, int w) {
            return get(v) == get(w);
        }

        private int get(int v) {
            while (ids[v] != v)
                v = ids[v];
            return v;
        }
    }

    private static int[][] D = {
            {-1, 0}, {1, 0}, {0, -1}, {0, 1}
    };

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        T = Integer.parseInt(token.nextToken());

        for (int t=0; t<T; t++) {
            token = new StringTokenizer(reader.readLine());
            M = Integer.parseInt(token.nextToken());
            N = Integer.parseInt(token.nextToken());
            K = Integer.parseInt(token.nextToken());
            board = new int[N][M];
            List<Integer> points = new ArrayList<>();
            for (int i=0; i<K; i++) {
                token = new StringTokenizer(reader.readLine());
                int col = Integer.parseInt(token.nextToken());
                int row = Integer.parseInt(token.nextToken());
                board[row][col] = 1;
                points.add(row * M + col);
            }

            UF uf = new UF(points.size());
            for (int v: points) {
                int row = v / M;
                int col = v % M;
                for (int[] d: D) {
                    int dx = row + d[0];
                    int dy = col + d[1];
                    if (valid(dx, dy) && board[dx][dy] == 1) {
                        uf.connect(row * M + col + 1, dx * M + dy + 1);
                    }
                }
            }
            System.out.println(uf.size);
        }
    }

    private static boolean valid(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }
}
