package baekjoon.p12000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P12869 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int MAX = 60;
    private static int[] SCVS = new int[3];
    private static int[][][] D = new int[MAX+1][MAX+1][MAX+1];
    private static int N;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int i=0; i<N; i++)
            SCVS[i] = Integer.parseInt(tokenizer.nextToken());
        int answer = dp(SCVS[0], SCVS[1], SCVS[2]);
        System.out.println(answer);
    }

    private static int dp(int scv1, int scv2, int scv3) {
        if (scv1 < 0) return dp(0, scv2, scv3);
        if (scv2 < 0) return dp(scv1, 0, scv3);
        if (scv3 < 0) return dp(scv1, scv2, 0);
        if (scv1 <= 0 && scv2 <= 0 && scv3 <= 0) return 0;
        if (D[scv1][scv2][scv3] != 0)
            return D[scv1][scv2][scv3];
        int[] p = {1, 3, 9};
        int answer = Integer.MAX_VALUE;
        do {
            answer = Math.min(answer, dp(scv1 - p[0], scv2 - p[1], scv3 - p[2]));
        } while (nextPermutation(p));
        return D[scv1][scv2][scv3] = answer + 1;
    }

    private static boolean nextPermutation(int[] p) {
        int i = p.length - 1;
        while (i > 0 && p[i-1] >= p[i])
            i--;
        if (i == 0) return false;
        int j = p.length - 1;
        while (j > 0 && p[i-1] >= p[j])
            j--;
        swap(i-1, j, p);
        j = p.length - 1;
        while (i < j)
            swap(i++, j--, p);
        return true;
    }

    private static void swap(int i, int j, int[] p) {
        int t = p[i];
        p[i] = p[j];
        p[j] = t;
    }

}
