package baekjoon.p13000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class P13576 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    
    public static void main(String[] args) throws IOException {
        KMP kmp = new KMP(reader.readLine());
        int N = kmp.s.length();
        int[] pi = kmp.pi;
        int[] cnt = new int[N+1];
        for (int i=0; i<N; i++)
            cnt[pi[i]]++;
        for (int i=N; i>0; i--)
            cnt[pi[i-1]] += cnt[i];
        List<Pair> pairs = new ArrayList<>();
        for (int i=N; i>0; i=pi[i-1])
            pairs.add(new Pair(i, cnt[i] + 1));
        System.out.println(pairs.size());
        for (int i=pairs.size()-1; i>=0; i--) {
            Pair pair = pairs.get(i);
            System.out.printf("%d %d%n", pair.i, pair.cnt);
        }
    }
    
    private static class Pair {
        int i;
        int cnt;

        public Pair(int i, int cnt) {
            this.i = i;
            this.cnt = cnt;
        }
    }
    
    private static class KMP {
        private int[] pi;
        private String s;

        public KMP(String s) {
            this.s = s;
            pi = new int[s.length()];
            fail();
        }

        private void fail() {
            int N = s.length();
            int j = 0;
            for (int i=1; i<N; i++) {
                while (j > 0 && s.charAt(i) != s.charAt(j))
                    j = pi[j-1];
                if (s.charAt(i) == s.charAt(j))
                    pi[i] = ++j;
            }
        }
    }
}
