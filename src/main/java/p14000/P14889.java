package p14000;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class P14889 {

    private static Map<Integer, List<Edge>> G = new HashMap<>();
    private static final Scanner scanner = new Scanner(
            new BufferedReader(new InputStreamReader(System.in)));

    // O(N!) / O(N/2!) * O(N/2!)
    public static void main(String[] args) {
        int N = Integer.parseInt(scanner.nextLine());
        for (int i=0; i<N; i++) {
            String[] data = scanner.nextLine().split(" ");
            G.computeIfAbsent(i, key -> new LinkedList<>());
            for (int j=0; j<data.length; j++) {
                if (i == j) continue;
                Edge edge = new Edge(i, j, Integer.parseInt(data[j]));
                G.get(i).add(edge);
            }
        }
        int[] P = new int[N];
        for (int i=N/2; i<N; i++)
            P[i] = 1;
        int answer = Integer.MAX_VALUE;
        do {
            int[] teams = new int[2];
            for (int v=0; v<N; v++) {
                int team = P[v];
                for (Edge edge: G.get(v)) {
                    int w = edge.w;
                    if (P[w] == team)
                        teams[team] += edge.power;
                }
            }
            answer = Math.min(Math.abs(teams[0] - teams[1]), answer);
        } while (nextPermutation(P));

        System.out.println(answer);
    }

    private static boolean nextPermutation(int[] p) {
        int i = p.length-1;
        while (i>0 && p[i-1] >= p[i])
            i--;
        if (i == 0)
            return false;
        int j = p.length-1;
        while (j>0 && p[i-1] >= p[j])
            j--;
        swap(j, i-1, p);
        j = p.length-1;
        while (i<j)
            swap(i++,  j--, p);
        return true;
    }

    private static void swap(int i, int j, int[] p) {
        int tmp = p[i];
        p[i] = p[j];
        p[j] = tmp;
    }

    private static class Edge {
        public int v, w, power;

        public Edge(int v, int w, int power) {
            this.v = v;
            this.w = w;
            this.power = power;
        }
    }
}
