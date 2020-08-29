package baekjoon.p11000;

import util.Algorithm;
import util.TimeComplexity;
import util.Tip;
import util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

@Used(Algorithm.DP)
@Tip("D[i] = min jumps to reach i" +
    "then for x (1..A[i]) = D[i + x] = min(D[i + x], D[i] + 1)")
@TimeComplexity("O(N * max(A))")
public class P11060 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int[] D;
    private static int[] A;
    private static int N;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        A = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        if (N == 1) {
            System.out.println(0);
            return;
        }
        D = new int[N];
        Arrays.fill(D, -1);
        D[0] = 0;
        jump(0);
        if (D[N-1] == 0)
            System.out.println(-1);
        else
            System.out.println(D[N-1]);
    }

    private static void jump(int ith) {
        if (ith == N-1) return;
        if (D[ith] == -1) jump(ith + 1);
        else {
            int max = A[ith];
            for (int jump = 1; jump <= max; jump++) {
                if (jump + ith < N) {
                    if (D[jump + ith] == -1)
                        D[jump + ith] = D[ith] + 1;
                    else
                        D[jump + ith] = Math.min(D[ith] + 1, D[jump + ith]);
                }
            }
            jump(ith + 1);
        }
    }
}
