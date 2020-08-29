package baekjoon.p10000;

import util.Algorithm;
import util.TimeComplexity;
import util.Tip;
import util.Used;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

@Tip("D[i][j] means s[i] ~ s[j] is palindrome" +
    "query(D[i][j]) = query(i+1, j-1) & s[i] == s[j] (D[i][i] = 1)")
@Used(Algorithm.DP)
@TimeComplexity("O(N^2+M)")
public class P10942 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int N, M;
    private static int[] s;
    private static int[][] D;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        s = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        D = new int[N][N];
        M = Integer.parseInt(reader.readLine());
        for (int i=0; i<N; i++)
            Arrays.fill(D[i], -1);
        for (int q=0; q<M; q++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int i = Integer.parseInt(tokenizer.nextToken()) - 1;
            int j = Integer.parseInt(tokenizer.nextToken()) - 1;
            int result = query(i, j);
            writer.write(result + "");
            writer.write("\n");
        }
        writer.flush();
    }

    private static int query(int i, int j) {
        if (i == j) return 1;   //D[i][i] = true
        else if (i + 1 == j) {
            if (s[i] == s[j]) return 1;
            else return 0;
        }
        if (D[i][j] != -1) return D[i][j];
        if (s[i] != s[j]) 
            return D[i][j] = 0;
        else
            return D[i][j] = query(i+1, j-1);
    }
}
