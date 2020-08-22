package baekjoon.p2000;

import java.util.Arrays;
import java.util.Scanner;

public class P2581 {
    private static final int MAX = 10000;
    private static boolean[] primes = new boolean[MAX+1];
    private static Scanner scanner = new Scanner(System.in);
    private static int N, M;

    static {
        Arrays.fill(primes, true);
        primes[0] = primes[1] = false;
        for (int i=2; i<=Math.sqrt(MAX); i++) {
            if (!primes[i]) continue;
            primes[i] = true;
            for (int j=i+i; j<=Math.sqrt(MAX); j+=i)
                primes[j] = false;
        }
    }

    public static void main(String[] args) {
        M = scanner.nextInt();
        N = scanner.nextInt();
        int minPrime = Integer.MAX_VALUE,
                sum = 0;
        for (int i=M; i<=N; i++) {
            if (primes[i]) {
                minPrime = Math.min(minPrime, i);
                sum += i;
            }
        }
        if (sum == 0)
            System.out.println(-1);
        else {
            System.out.println(sum);
            System.out.println(minPrime);
        }
    }
}
