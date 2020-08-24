package baekjoon.p11000;

import java.util.Scanner;

public class P11727 {
    public static final int MAX = 1000, MOD = 10007;
    private static Scanner scanner = new Scanner(System.in);
    private static int[] D = new int[MAX+1];

    public static void main(String[] args) {
        tiling(MAX);
        System.out.println(D[scanner.nextInt()]);
    }

    private static int tiling(int n) {
        if (n == 0) return 1;
        if (n < 0) return 0;
        if (D[n] != 0) return D[n];
        return D[n] = ((tiling(n-2) % MOD) +
                        (tiling(n-2) % MOD) +
                        + (tiling(n-1) % MOD)) % MOD;
    }
}
