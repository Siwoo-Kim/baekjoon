package baekjoon.p1000;

import java.util.Scanner;

public class P1436 {

    private static int N;
    private static int[] D;

    public static void main(String[] args) {
        N = new Scanner(System.in).nextInt();
        D = new int[20+2];
        fibonacci(20);
        System.out.println(D[N]);
    }

    private static int fibonacci(int i) {
        if (i == 0 || i == 1)
            return D[i] = i;
        return D[i] = fibonacci(i - 2) + fibonacci(i - 1);
    }
}
