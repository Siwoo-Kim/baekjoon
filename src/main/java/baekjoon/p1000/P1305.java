package baekjoon.p1000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P1305 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(reader.readLine());
        String s = reader.readLine();
        int[] fail = fail(s);
        System.out.println(N - fail[N-1]);
    }

    private static int[] fail(String s) {
        int N = s.length();
        int[] fail = new int[N];
        int j = 0;
        for (int i=1; i<N; i++) {
            while (j > 0 && s.charAt(i) != s.charAt(j))
                j = fail[j-1];
            if (s.charAt(i) == s.charAt(j))
                fail[i] = ++j;
            else
                fail[i] = 0;
        }
        return fail;
    }
}
