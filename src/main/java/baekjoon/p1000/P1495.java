package baekjoon.p1000;

import util.Algorithm;
import util.Tip;
import util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

@Used(Algorithm.DP)
@Tip("dp(i, volume) 을 i 번째 선택하기 위한 볼륨의 최대값이라 했을 때" +
    "dp(i, volume) = max(dp(i+1, volume + v[i]), dp(i+1, volume - v[i])" +
    "단 dp(i+1, volume + v[i]) <= M, d(i+1, volume - v[i]) >= 0")
public class P1495 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N, S, M;
    private static int D[][];
    private static boolean visit[][];
    private static int[] volumes;

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        S = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());
        D = new int[N][M+1];
        visit = new boolean[N][M+1];
        for (int i=0; i<N; i++)
            Arrays.fill(D[i], -1);
        volumes = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        int answer = dp(0, S);
        System.out.println(answer);
    }

    private static int dp(int index, int volume) {
        if (index == N) return volume;
        if (visit[index][volume])
            return D[index][volume];
        int answer = -1;
        if (volume + volumes[index] <= M)
            answer = dp(index + 1, volume + volumes[index]);
        if (volume - volumes[index] >= 0)
            answer = Math.max(dp(index + 1, volume - volumes[index]), answer);
        visit[index][volume] = true;
        return D[index][volume] = answer;
    }
}
