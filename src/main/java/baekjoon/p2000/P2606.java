package baekjoon.p2000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P2606 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N, E;

    private static class UF {
        int size;
        int[] components;
        int[] heights;

        public UF(int size) {
            this.size = size;
            components = new int[size+1];
            heights = new int[size+1];
            for (int i=1; i<=size; i++) {
                components[i] = i;
                heights[i] = 1;
            }
        }

        public void connect(int v, int w) {
            if (connected(v, w)) return;
            int pv = get(v),
                    pw = get(w);
            if (heights[pv] < heights[pw]) {
                int t = pv;
                pv = pw;
                pw = t;
            }
            components[pw] = pv;
            if (heights[pv] == heights[pw])
                heights[pv]++;
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
        N = Integer.parseInt(reader.readLine());
        E = Integer.parseInt(reader.readLine());
        UF uf = new UF(N);
        for (int i=0; i<E; i++) {
            StringTokenizer token = new StringTokenizer(reader.readLine());
            int v = Integer.parseInt(token.nextToken());
            int w = Integer.parseInt(token.nextToken());
            uf.connect(v, w);
        }
        int viruses = 0;
        for (int i=2; i<=N; i++)
            if (uf.connected(1, i))
                viruses++;
        System.out.println(viruses);
    }
}
