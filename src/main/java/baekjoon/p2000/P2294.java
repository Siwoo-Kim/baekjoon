package baekjoon.p2000;

import util.Algorithm;
import util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

@Used(Algorithm.DP)
public class P2294 {
    private static final int MAX = 10001;
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N, K;
    private static int[] coins, D;

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        K = Integer.parseInt(tokenizer.nextToken());
        coins = new int[N];
        D = new int[MAX+1];
        Arrays.fill(D, -1);
        for (int i=0; i<N; i++)
            coins[i] = Integer.parseInt(reader.readLine());
        D[0] = 0;
        for (int c: coins) {
            for (int i=1; i<=K; i++) {
                if (i - c < 0) continue;
                if (D[i-c] == -1) continue;
                if (D[i] == -1 || D[i] > D[i - c] + 1)
                    D[i] = D[i - c] + 1;
            }
        }
        System.out.println(D[K]);
    }
}
