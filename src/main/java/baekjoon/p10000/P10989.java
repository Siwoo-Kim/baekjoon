package baekjoon.p10000;

import java.io.*;

public class P10989 {
    private static final BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(scanner.readLine());
        int[] cnt = new int[10001];
        for (int i=0; i<N; i++)
            cnt[Integer.parseInt(scanner.readLine())]++;

        for (int i=1; i<=10000; i++) {
            while (cnt[i] != 0) {
                writer.write(i + "\n");
                cnt[i]--;
            }
        }
        writer.flush();
    }
}
