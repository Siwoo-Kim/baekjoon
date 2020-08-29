package baekjoon.p13000;

import util.Algorithm;
import util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Used(Algorithm.KMP)
public class P13506 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    
    public static void main(String[] args) throws IOException {
        KMP kmp = new KMP(reader.readLine());
        int N = kmp.s.length();
        int[] cnt = new int[N+1];
        for (int i=0; i<N-1; i++)
            cnt[kmp.fail[i]]++;
        for (int i=N; i!=0; i=kmp.fail[i-1]) {
            if (cnt[i] >= 1) {
                System.out.println(kmp.s.substring(0, i));
                return;
            }
        }
        System.out.println(-1);
    }
    
    private static class KMP {
        private final int[] fail;
        private final String s;

        public KMP(String s) {
            this.s = s;
            fail = new int[s.length()];
            fail();
        }

        private void fail() {
            int j = 0;
            for (int i=1; i<s.length(); i++) {
                while (j > 0 && s.charAt(i) != s.charAt(j))
                    j = fail[j-1];
                if (s.charAt(i) == s.charAt(j))
                    fail[i] = ++j;
                else
                    j = 0;
            }
        }
    }
}
