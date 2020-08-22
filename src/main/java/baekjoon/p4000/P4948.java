package baekjoon.p4000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class P4948 {
    private static int MAX = 123456;
    private static boolean[] primes = new boolean[(MAX*2)+1];
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    static {
        Arrays.fill(primes, true);
        primes[0] = primes[1] = false;
        for (int i=2; i<=(MAX*2); i++) {
            if (primes[i]) {
                for (int j=i*2; j<=(MAX*2); j+=i)
                    primes[j] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        while (true) {
            int N = Integer.parseInt(reader.readLine());
            if (N == 0) return;
            int cnt = 0;
            for (int i=N+1; i<=2*N; i++)
                if (primes[i])
                    cnt++;
            System.out.println(cnt);
        }
    }
}
