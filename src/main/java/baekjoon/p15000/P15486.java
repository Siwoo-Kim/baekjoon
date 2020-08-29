package baekjoon.p15000;

import util.Algorithm;
import util.TimeComplexity;
import util.Tip;
import util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

@Used(Algorithm.DP)
@Tip("D[i] = max profit of iths date" +
    "D[i+1] = max(D[i], D[i+1]), D[i+T[i]] = max(D[i] + P[i], D[i+T[i])")
@TimeComplexity("O(N)")
public class P15486 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int[] T, P, D;
    private static int N;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        D = new int[N+50];
        T = new int[N];
        P = new int[N];
        for (int i=0; i<N; i++) {
            StringTokenizer token = new StringTokenizer(reader.readLine());
            T[i] = Integer.parseInt(token.nextToken());
            P[i] = Integer.parseInt(token.nextToken());
        }
        for (int i=0; i<N; i++) {
            D[i+T[i]] = Math.max(D[i+T[i]], D[i] + P[i]);
            D[i + 1] = Math.max(D[i+1], D[i]);
        }
        System.out.println(D[N]);
    }

}
