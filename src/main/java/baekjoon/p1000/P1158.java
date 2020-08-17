package baekjoon.p1000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class P1158 {
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N, K;

    public static void main(String[] args) throws IOException {
        String[] data = reader.readLine().split("\\s+");
        N = Integer.parseInt(data[0]);
        K = Integer.parseInt(data[1]);
        Queue<Integer> q = new LinkedList<>();
        for (int i=1; i<=N; i++)
            q.add(i);
        List<Integer> result = josephus(q, new ArrayList<>());
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        for (int i=0; i<result.size(); i++) {
            sb.append(result.get(i));
            if (i != result.size()-1)
                sb.append(", ");
        }
        sb.append(">");
        System.out.println(sb.toString());
    }

    private static List<Integer> josephus(Queue<Integer> q, List<Integer> result) {
        if (q.isEmpty()) return result;
        for (int i=1; i<K; i++)
            q.add(q.poll());
        result.add(q.poll());
        return josephus(q, result);
    }
}
