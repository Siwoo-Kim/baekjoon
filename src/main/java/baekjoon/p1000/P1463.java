package baekjoon.p1000;

import java.util.Scanner;

public class P1463 {
    private static final Scanner scanner = new Scanner(System.in);
    private static final int MAX = 10000000;
    private static final int[] D = new int[MAX+1];

    public static void main(String[] args) {
        D[1] = 0;
        D[2] = D[3] = 1;
        for (int i=4; i<=MAX; i++) {
            int d = Integer.MAX_VALUE;
            if (i % 3 == 0)
                d = Math.min(d, D[i / 3]);
            if (i % 2 == 0)
                d = Math.min(d, D[i / 2]);
            d = Math.min(d, D[i-1]);
            D[i] = d + 1;
        }
        System.out.println(D[scanner.nextInt()]);
    }
}
