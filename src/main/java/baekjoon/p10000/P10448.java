package baekjoon.p10000;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Stack;

public class P10448 {
    public static final int MAX = 1000;
    private static final Scanner scanner = new Scanner(
            new BufferedReader(new InputStreamReader(System.in)));
    private static final boolean[] D = new boolean[MAX + 1];

    public static void main(String[] args) {
        int N = scanner.nextInt();
        for (int i = 0; i < N; i++) {
            int K = scanner.nextInt();
            boolean t = triangleNumber(K, new Stack<>());
            D[K] = t;
            System.out.println(t ? 1 : 0);
        }
    }

    private static boolean triangleNumber(int K, Stack<Integer> stack) {
        if (stack.size() == 3) {
            int s = stack.stream().mapToInt(t -> t * (t + 1) / 2).sum();
            return K == s;
        }
        if (stack.size() > 3)
            return false;
        for (int t = 1; t < 1000; t++) {
            if ((t + 1) * t / 2 > K) break;
            stack.push(t);
            if (triangleNumber(K, stack))
                return true;
            stack.pop();
        }
        return false;
    }

}
