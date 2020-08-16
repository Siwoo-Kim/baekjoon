package baekjoon.p1000;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class P1644 {
    private static final Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
    private static final int MAX = 4000000;
    private static List<Integer> primes = new ArrayList<>();
    private static boolean[] isPrime = new boolean[MAX+1];

    static {
        Arrays.fill(isPrime, true);
        for (int i=2; i<=MAX; i++) {
            if (isPrime[i]) {
                isPrime[i] = true;
                primes.add(i);
                for (int j = i + i; j <= MAX; j = j+i) {
                    isPrime[j] = false;
                }
            }
        }
    }

    public static void main(String[] args) {
        int N = scanner.nextInt();
        int start = 0, end = 0, sum = primes.get(0), cnt = 0;
        while (primes.get(end) <= N) {
            if (sum < N) {
                end++;
                if (end == primes.size()) {
                    break;
                }
                sum += primes.get(end);
            } else if (sum == N) {
                cnt++;
                end++;
                sum += primes.get(end);
            } else {
                sum -= primes.get(start);
                start++;
            }
        }
        System.out.println(cnt);
    }
}
