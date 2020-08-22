package baekjoon.p1000;

import java.util.Scanner;

public class P1676 {
    private static int answer;

    public static void main(String[] args) {
        factorial(new Scanner(System.in).nextInt());
        System.out.println(answer);
    }

    private static int factorial(int n) {
        if (n == 0) return 1;
        if (n % 5 == 0) {
            int t = n;
            while (t % 5 == 0) {
                t /= 5;
                answer++;
            }
        }
        return factorial(n-1) * n;
    }
}
