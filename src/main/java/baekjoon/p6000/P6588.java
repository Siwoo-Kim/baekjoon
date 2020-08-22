package baekjoon.p6000;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class P6588 {
    private static Scanner scanner = new Scanner(System.in);
    private static int MAX = 1000000;
    private static boolean[] primes = new boolean[MAX+1];
    private static List<Integer> ps = new ArrayList<>();

    static {
        Arrays.fill(primes, true);
        primes[0] = primes[1] = false;
        for (int i=2; i<=MAX; i++) {
            if (!primes[i]) continue;
            primes[i] = true;
            for (int j=i*2; j<=MAX; j+=i)
                primes[j] = false;
        }
        IntStream.rangeClosed(2, MAX)
                .filter(i -> primes[i])
                .forEach(i -> ps.add(i));
    }

    public static void main(String[] args) {
        while (true) {
            int N = scanner.nextInt();
            if (N == 0)
                return;
            for (int i=0; i<ps.size(); i++) {
                int p = ps.get(i);
                if (p >= N) {
                    System.out.println("Goldbach's conjecture is wrong.");
                    break;
                }
                if (primes[N - p]) {
                    System.out.printf("%d = %d + %d%n", N, p, N - p);
                    break;
                }
            }
        }
    }
}
