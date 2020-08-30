package baekjoon.p11000;

import util.Algorithm;
import util.Tip;
import util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

@Used(Algorithm.DP)
@Tip("dp(i, j) 을 i .. j 까지의 파일을 합친 최소 비용이라 했을때," +
    "D[i][j] = min(dp(i, i+1 .. k) + dp(k+1 .. j-1, j) + sum(i,j)) 단 i <= k < j")
public class P11066 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int[] CD;
    private static int T, N;
    private static int[][] D;

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(reader.readLine());
        for (int t=0; t<T; t++) {
            N = Integer.parseInt(reader.readLine());
            CD = Arrays.stream(reader.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            D = new int[N][N];
            for (int i=0; i<N; i++)
                Arrays.fill(D[i], -1);
            int answer = dp(0, N-1);
            System.out.println(answer);
        }
    }

    private static int dp(int from, int to) {
        if (from == to) return 0;
        if (D[from][to] != -1)
            return D[from][to];
        int sum = 0;
        for (int i=from; i<=to; i++)
            sum += CD[i];
        int answer = -1;
        for (int k=from; k<=to-1; k++) {
            int tmp = dp(from, k) + dp(k+1, to) + sum;
            if (answer == -1 || answer > tmp)
                answer = tmp;
        }
        return D[from][to] = answer;
    }
}
