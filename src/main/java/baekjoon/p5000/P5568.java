package baekjoon.p5000;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class P5568 {

    private static final Scanner scanner = new Scanner(
            new BufferedReader(new InputStreamReader(System.in)));
    private static int[] S;
    private static int N;
    private static int K;
    private static boolean[] visit;
    private static Set<String> result = new HashSet<>();

    // O(nPr)
    public static void main(String[] args) {
        N = scanner.nextInt();
        K = scanner.nextInt();
        S = new int[N];
        for (int i=0; i<N; i++)
            S[i] = scanner.nextInt();
        visit = new boolean[N];
        select(0, new Stack<>());
        System.out.println(result.size());
    }

    private static void select(int select, Stack<Integer> stack) {
        if (select == K) {
            StringBuilder sb = new StringBuilder();
            stack.forEach(sb::append);
            result.add(sb.toString());
            return;
        }
        for (int i=0; i<N; i++) {
            if (!visit[i]) {
                stack.push(S[i]);
                visit[i] = true;
                select(select+1, stack);
                stack.pop();
                visit[i] = false;
            }
        }
    }
}
