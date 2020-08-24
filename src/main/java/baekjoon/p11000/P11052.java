package baekjoon.p11000;

import util.Tip;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

@Tip("D[N] 을 N 개의 카드를 구매 했을 때의 최대값이라 할 때, " +
        "점화식은 D[N] = max(D[N-i]+P[i])")
public class P11052 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static int[] P;
    private static long[] D;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        P = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        D = new long[N+1];
        long answer = purchase(N);
        System.out.println(answer);
    }

    private static long purchase(int cnt) {
        if (cnt == 0) return 0;
        if (cnt < 0) return 0;
        if (D[cnt] != 0) return D[cnt];
        long max = 0;
        for (int i=0; i<N; i++) {
            int remain = cnt - (i+1);
            if (remain >= 0)
                max = Long.max(max, purchase(remain) + P[i]);
        }
        return D[cnt] = max;
    }
}
