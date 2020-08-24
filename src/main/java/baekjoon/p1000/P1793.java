package baekjoon.p1000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class P1793 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static final int MAX = 250;
    private static BigInteger[] D = new BigInteger[MAX+1];

    public static void main(String[] args) throws IOException {
        String line;
        D[0] = D[1] = BigInteger.ONE;
        tiling(MAX);
        while ((line = reader.readLine()) != null) {
            int N = Integer.parseInt(line);
            System.out.println(D[N]);
        }
    }

    private static BigInteger tiling(int n) {
        if (D[n] != null) return D[n];
        return D[n] = tiling(n-2)
                .add(tiling(n-2))
                .add(tiling(n-1));
    }
}
