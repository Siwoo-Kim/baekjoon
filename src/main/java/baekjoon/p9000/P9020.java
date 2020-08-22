package baekjoon.p9000;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class P9020 {
    private static Scanner scanner = new Scanner(System.in);
    private static int MAX = 10000, N;
    private static boolean[] isPrime = new boolean[MAX+1];
    private static List<Integer> primes = new ArrayList<>();

    static {
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        for (int i=2; i<=MAX; i++) {
            if (isPrime[i]) {
                for (int j=i*2; j<=MAX; j+=i)
                    isPrime[j] = false;
            }
        }
        IntStream.rangeClosed(2, MAX)
                .filter(p -> isPrime[p])
                .forEach(p -> primes.add(p));
    }

    public static void main(String[] args) {
        N = scanner.nextInt();
        for (int i=0; i<N; i++) {
            int x = scanner.nextInt();
            int diff = Integer.MAX_VALUE;
            int a1 = 0, a2 = 0;
            for (int p1: primes) {
                if (p1 > x) break;
                if (isPrime[x - p1]) {
                    int p2 = x - p1;
                    if (diff > Math.abs(p2 - p1)) {
                        diff = Math.abs(p2 - p1);
                        a1 = Math.min(p2, p1);
                        a2 = Math.max(p2, p1);
                        if (diff == 0) break;
                    }
                }
            }
            System.out.println(a1 + " " + a2);
        }
    }
}
