package baekjoon.p1000;

import java.util.Scanner;

public class P1463 {
    private static final Scanner scanner = new Scanner(System.in);
    private static int[] D;

    public static void main(String[] args) {
        int N = scanner.nextInt();
        D = new int[N+1];
        int answer = dp(N);
        System.out.println(answer);
    }

    private static int dp(int n) {
        if (n == 1) return 0;
        if (D[n] != 0) return D[n];
        int answer = dp(n-1) + 1;
        if (n % 3 == 0)
            answer = Math.min(dp(n/3) + 1, answer);
        if (n % 2 == 0)
            answer =  Math.min(dp(n/2) + 1, answer);
        return D[n] = answer;
    }
}
