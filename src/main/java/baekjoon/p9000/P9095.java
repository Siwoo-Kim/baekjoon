package baekjoon.p9000;

import util.Algorithm;
import util.Tip;
import util.Used;

import java.util.Scanner;

@Used(Algorithm.DP)
@Tip("if D[i] = the number of case to make i" +
    "D[i] = D[i-1] + D[i-2] + D[i-3]")
public class P9095 {
    private static Scanner scanner = new Scanner(System.in);
    private static final int MAX = 11;
    private static int[] D = new int[MAX+1];

    public static void main(String[] args) {
        dp(MAX);
        int N = scanner.nextInt();
        for (int i=0; i<N; i++) {
            System.out.println(D[scanner.nextInt()]);
        }
    }

    private static int dp(int n) {
        if (n == 0) return 1;
        if (n < 0) return 0;
        if (D[n] != 0) return D[n];
        return D[n] = dp(n-3) + dp(n-2) + dp(n-1);
    }
}
