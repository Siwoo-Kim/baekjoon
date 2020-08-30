package baekjoon.p10000;

import util.Status;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

@Status(result = Status.Result.FAIL)
public class P10422 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int T, MAX = 5000, MOD = 1000000007;
    private static long[] D;
    
    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(reader.readLine());
        D = new long[MAX+1];
        Arrays.fill(D, -1);
        D[0] = 1;
        dp(MAX);
        StringBuilder sb = new StringBuilder();
        
        for (int i=0; i<T; i++) {
            sb.append(D[Integer.parseInt(reader.readLine())]);
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    private static long dp(int n) {
        if (n == 0) return 1;
        if (n < 0) return 0;
        if (D[n] != -1)
            return D[n];
        long sum = 0;
        for (int i=2; i<=n; i++) {
            sum += dp(i - 2) * dp(n - i);
            sum %= MOD;
        }
        return D[n] = sum;
    }
}
