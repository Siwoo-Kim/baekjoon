package baekjoon.p2000;

import util.Algorithm;
import util.Tip;
import util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

@Used(Algorithm.DP)
public class P2293 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N, K;
    private static int[] coins;
    private static int[] D;
    
    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        K = Integer.parseInt(tokenizer.nextToken());
        coins = new int[N];
        for (int i=0; i<N; i++)
            coins[i] = Integer.parseInt(reader.readLine());
        D = new int[K+1];
        D[0] = 1;
        for (int c: coins) {
            for (int i = 1; i <= K; i++)
                if (i - c >= 0)
                    D[i] += D[i - c];
        }
        System.out.println(D[K]);
    }
}
