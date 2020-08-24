package baekjoon.p16000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P16194 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static int[] P;
    private static int[] D;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        P = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        D = new int[N+1];
        int answer = purchase(N);
        System.out.println(answer);
    }

    private static int purchase(int card) {
        if (card == 0) return 0;
        if (D[card] != 0) return D[card];
        int price = Integer.MAX_VALUE;
        for (int i=0; i<N; i++) {
            int remain = card-(i+1);
            if (remain >= 0)
                price = Math.min(purchase(remain) + P[i], price);
        }
        return D[card] = price;
    }
}
