package baekjoon.p2000;

import java.util.Scanner;

public class P2747 {
    private static int MAX = 45;
    private static long[] D = new long[MAX+1];

    public static void main(String[] args) {
        fibonacci(MAX);
        System.out.println(D[new Scanner(System.in).nextInt()]);
    }

    private static long fibonacci(int n) {
        if (n == 0 || n == 1)
            return D[n] = n;
        if (D[n] != 0)
            return D[n];
        return D[n] = fibonacci(n-2) + fibonacci(n-1);
    }
}
