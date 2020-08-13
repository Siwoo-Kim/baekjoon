package baekjoon.p2000;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Scanner;

public class P2422 {
    private static final Scanner scanner = new Scanner(
            new BufferedReader(new InputStreamReader(System.in)));
    private static int N, M;
    private static boolean[][] skip;

    public static void main(String[] args) {
        N = scanner.nextInt();
        M = scanner.nextInt();
        skip = new boolean[N+1][N+1];
        for (int i=0; i<M; i++) {
            int x = scanner.nextInt(),
                    y = scanner.nextInt();
            skip[x][y] = skip[y][x] = true;
        }
        int result = combination(1, 3, new LinkedList<>());
        System.out.println(result);
    }

    private static int combination(int index, int r, LinkedList<Integer> stack) {
        if (r == 0) return 1;
        int sum = 0;
        for (int i=index; i<=N; i++) {
            if (check(stack, i)) {
                stack.push(i);
                sum += combination(i+1, r-1, stack);
                stack.pop();
            }
        }
        return sum;
    }

    private static boolean check(LinkedList<Integer> stack, int i) {
        for (int j=0; j<stack.size(); j++)
            if (skip[i][stack.get(j)])
                return false;
        return true;
    }
}
