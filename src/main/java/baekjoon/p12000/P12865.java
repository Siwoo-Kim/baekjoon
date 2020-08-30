package baekjoon.p12000;

import util.Algorithm;
import util.Tip;
import util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

@Used(Algorithm.DP)
@Tip("dp(i, capacity) 을 i 번째 물건까지 선택했고, 넣은 물건 무게의 합이 capacity 라면, 가치의 최대값" +
    "1) i 의 물건을 선택한 경우" +
        "dp(i-1, capacity-w[i]) + v[i]" +
    "2) i 의 물건을 선택하지 않은 경우" +
        "dp(i-1, capacity)" +
    "dp(i, capacity) = max(dp(i-1, capacity), dp(i-1, capacity-w[i]) + v[i]))")
public class P12865 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static final int MAX = 100000;
    private static int N, K;
    private static int[] w, v;
    private static int[][] D;

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        K = Integer.parseInt(tokenizer.nextToken());
        D = new int[N][MAX+1];
        w = new int[N];
        v = new int[N];
        for (int i=0; i<N; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            w[i] = Integer.parseInt(tokenizer.nextToken());
            v[i] = Integer.parseInt(tokenizer.nextToken());
        }
        int answer = dp(N-1, K);
        System.out.println(answer);
    }

    private static int dp(int index, int capacity) {
        if (index == -1) return 0;
        if (D[index][capacity] != 0) 
            return D[index][capacity];
        int answer = dp(index-1, capacity);
        if (capacity >= w[index])
            answer = Math.max(dp(index-1, capacity-w[index]) + v[index], answer);
        return D[index][capacity] = answer;
    }
}
