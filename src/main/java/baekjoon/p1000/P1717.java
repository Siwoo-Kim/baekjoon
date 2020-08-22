package baekjoon.p1000;

import java.io.*;
import java.util.StringTokenizer;

public class P1717 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int N, M;

    private static class UF {
        int[] components;
        int[] rank;

        public UF(int size) {
            components = new int[size+1];
            rank = new int[size+1];
            for (int i=0; i<components.length; i++)
                components[i] = i;
        }

        public void connect(int v, int w) {
            if (connected(v, w)) return;
            int pv = get(v);
            int pw = get(w);
            if (rank[pv] < rank[pw]) {
                int t = pv;
                pv = pw;
                pw = t;
            }
            components[pw] = pv;
            if (rank[pv] == rank[pw])
                rank[pv]++;
        }

        private boolean connected(int v, int w) {
            return get(v) == get(w);
        }

        private int get(int v) {
            if (components[v] == v)
                return v;
            else {
                int root = get(components[v]);
                components[v] = root;
                return root;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        UF uf = new UF(N);
        for (int i=0; i<M; i++) {
            token = new StringTokenizer(reader.readLine());
            int command = Integer.parseInt(token.nextToken());
            int a = Integer.parseInt(token.nextToken());
            int b = Integer.parseInt(token.nextToken());
            if (command == 0) {
                uf.connect(a, b);
            } else {
                if (uf.connected(a, b))
                    writer.write("YES\n");
                else
                    writer.write("NO\n");
            }
        }
        writer.flush();
    }
}
