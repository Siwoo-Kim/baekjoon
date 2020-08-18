package baekjoon.p5000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P5014 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N, S, G, U, D;
    private static boolean[] visit;
    private static Map<Integer, Integer> distance = new HashMap<>();

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(token.nextToken());
        S = Integer.parseInt(token.nextToken());
        G = Integer.parseInt(token.nextToken());
        U = Integer.parseInt(token.nextToken());
        D = Integer.parseInt(token.nextToken());
        visit = new boolean[N+1];

        boolean ok = false;
        distance.put(S, 0);
        visit[S] = true;
        Queue<Integer> q = new LinkedList<>();
        q.add(S);
        while (!q.isEmpty()) {
            int v = q.poll();
            if (v == G) {
                ok = true;
                break;
            }
            int w1 = v + U;
            if (valid(w1) && !visit[w1]) {
                visit[w1] = true;
                q.add(w1);
                distance.put(w1, distance.get(v) + 1);
            }
            int w2 = v - D;
            if (valid(w2) && !visit[w2]) {
                visit[w2] = true;
                q.add(w2);
                distance.put(w2, distance.get(v) + 1);
            }
        }
        if (ok)
            System.out.println(distance.get(G));
        else
            System.out.println("use the stairs");
    }

    private static boolean valid(int v) {
        return v >= 1 && v <= N;
    }
}
