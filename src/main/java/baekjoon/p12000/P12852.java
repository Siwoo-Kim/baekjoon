package baekjoon.p12000;

import java.util.Scanner;

public class P12852 {
    private static Scanner scanner = new Scanner(System.in);
    private static int[] D;
    private static int[] DC;

    public static void main(String[] args) {
        int N = scanner.nextInt();
        D = new int[N+1];
        DC = new int[N+1];
        dp(N);
        System.out.println(D[N]);
        System.out.print(N + " ");
        while (DC[N] != 0) {
            System.out.print(DC[N] + " ");
            N = DC[N];
        }
    }

    private static int dp(int n) {
        if (n == 1) return 0;
        if (D[n] != 0)
            return D[n];
        int a1 = dp(n-1) + 1;
        int a2 = n-1;
        if (n % 3 == 0) {
            int tmp = dp(n / 3) + 1;
            if (tmp < a1) {
                a1 = tmp;
                a2 = n / 3;
            }
        }
        if (n % 2 == 0) {
            int tmp = dp(n / 2) + 1;
            if (tmp < a1) {
                a1 = tmp;
                a2 = n / 2;
            }
        }
        D[n] = a1;
        DC[n] = a2;
        return D[n];
    }
}
