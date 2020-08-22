package baekjoon.p17000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P17087 {
    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static int N, S;

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(token.nextToken());
        S = Integer.parseInt(token.nextToken());
        int[] a = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        int gcd = Math.abs(S - a[0]);
        for (int i=1; i<N; i++) {
             gcd = gcd(gcd,  Math.abs(S - a[i]));
             if (gcd == 1) {
                 System.out.println(1);
                 break;
             }
        }
        System.out.println(gcd);
    }

    public static int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}
