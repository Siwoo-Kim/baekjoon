package baekjoon.p4000;

import java.util.Scanner;

public class P4134 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        for (int i=0; i<N; i++) {
            long s = scanner.nextLong();
            while (true) {
                if (prime(s)) {
                    System.out.println(s);
                    break;
                }
                s++;
            }
        }
    }

    public static boolean prime(long n) {
        if (n < 2) return false;
        for (long i=2; i*i<=n; i++) {
            if (n % i == 0)
                return false;
        }
        return true;
    }
}
