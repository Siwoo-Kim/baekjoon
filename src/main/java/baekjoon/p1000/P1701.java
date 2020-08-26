package baekjoon.p1000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P1701 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        String s = reader.readLine();
        int answer = 0;
        do {
            int[] fail = fail(s);
            answer = Math.max(Arrays.stream(fail).max().getAsInt(), answer);
            s = s.substring(1);
        } while (s.length() != 0);
        System.out.println(answer);
    }
    
    private static int[] fail(String s) {
        int N = s.length();
        int[] fail = new int[N];
        int j = 0;
        for (int i=1; i<N; i++) {
            while (j > 0 && s.charAt(i) != s.charAt(j))
                j = fail[j-1];
            if (s.charAt(j) == s.charAt(i))
                fail[i] = ++j;
        }
        return fail;
    }
}
