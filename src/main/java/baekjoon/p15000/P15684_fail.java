package baekjoon.p15000;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class P15684_fail {
    private static final Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
    private static Map<Integer, Set<Edge>> G = new HashMap<>();
    private static int N, M, H;

    public static void main(String[] args) {
        N = scanner.nextInt();
        M = scanner.nextInt();
        H = scanner.nextInt();
        for (int i=0; i<N; i++)
            G.put(i, new HashSet<>());
        for (int i=0; i<M; i++) {
            int height = scanner.nextInt()-1;
            int v = scanner.nextInt()-1;
            int w = v+1;
            Edge edge = new Edge(v, w, height);
            G.get(v).add(edge);
            G.get(w).add(edge.reverse());
        }
        int answer = select(0, new Stack<>());
        System.out.println(answer == Integer.MAX_VALUE? -1: answer);
    }

    private static int select(int dot, Stack<Integer> stack) {
        if (stack.size() > 3) return Integer.MAX_VALUE;
        if (dot >= (N-1) * H) return simulate(stack);
        int r = Integer.MAX_VALUE;
        int height = dot / (N-1);
        int v = dot % (N-1);
        Edge edge = new Edge(v, v+1, height);
        if (!G.get(v).contains(edge)) {
            stack.push(dot);
            r = select(dot + 2, stack);
            stack.pop();
        }
        return Integer.min(select(dot+1, stack), r);
    }

    private static int simulate(Stack<Integer> stack) {
        int[] ids = stack.stream().mapToInt(i -> i).toArray();
        List<Edge> edges = new ArrayList<>();
        for (int i=0; i<ids.length; i++) {
            int id = ids[i];
            int height = id / (N-1);
            int v = id % (N-1);
            edges.add(new Edge(v, v+1, height));
        }
        for (Edge edge: edges) {
            G.get(edge.x).add(edge);
            G.get(edge.y).add(edge.reverse());
        }
        boolean ok = true;
        for (int v: G.keySet()) {
            if (v != travel(v, 0)){
                ok = false;
                break;
            }
        }
        for (Edge edge: edges) {
            G.get(edge.x).remove(edge);
            G.get(edge.y).remove(edge.reverse());
        }
        return ok? stack.size(): Integer.MAX_VALUE;
    }

    private static int travel(int v, int height) {
        if (height == H) return v;
        for (Edge edge: G.get(v)) {
            if (edge.height == height) {
                int w = edge.y;
                return travel(w, height+1);
            }
        }
        return travel(v, height+1);
    }

    private static class Edge {
        int x, y, height;

        public Edge(int x, int y, int height) {
            this.x = x;
            this.y = y;
            this.height = height;
        }

        public Edge reverse() {
            return new Edge(y, x, height);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Edge edge = (Edge) o;
            return x == edge.x &&
                    y == edge.y &&
                    height == edge.height;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y, height);
        }
    }
}
