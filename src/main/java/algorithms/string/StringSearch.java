package algorithms.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public abstract class StringSearch {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    
    public static void main(String[] args) throws IOException {
        String s = reader.readLine(),
                p = reader.readLine();
        int index = new KMP().match(s, p);
        if (index == -1)
            System.out.println(0);
        else
            System.out.println(1);
        
    }
    
    public abstract int match(String s, String p);
    
    public static class KMP extends StringSearch {

        @Override
        public int match(String s, String p) {
            int N = s.length(), M = p.length();
            int[] fail = fail(p);
            int j = 0;
            for (int i=0; i<N; i++) {
                while (j > 0 && s.charAt(i) != p.charAt(j))
                    j = fail[j-1];
                if (s.charAt(i) == p.charAt(j)) {
                    if (j == M-1) 
                        return i - M + 1;
                    else
                        j++;
                }
            }
            return -1;
        }
        
        public static int[] fail(String s) {
            int N = s.length();
            int[] fail = new int[N];
            int j = 0;
            for (int i=1; i<N; i++) {
                while (j > 0 && s.charAt(i) != s.charAt(j))
                    j = fail[j-1];
                if (s.charAt(i) == s.charAt(j))
                    fail[i] = ++j;
            }
            return fail;
        }
    }
    
    public static class RabinKarp extends StringSearch {
        private static final int BASE = 256, MOD = 127;
        
        @Override
        public int match(String s, String p) {
            final int N = s.length(), M = p.length();
            int hash_s = hash(s, 0, M-1);
            int hash_p = hash(p, 0, M-1);
            int first = 1;
            for (int i=0; i<M-1; i++)
                first = first * BASE % MOD;
            for (int i=0; i<=N-M; i++) {
                if (hash_s == hash_p)
                    if (p.equals(s.substring(i, M+i)))
                        return i;
                if (M+i < N) {
                    hash_s = hash_s - (s.charAt(i) * first % MOD);
                    hash_s = (hash_s + MOD) % MOD;
                    hash_s = (((hash_s * BASE) % MOD) + s.charAt(M+i)) % MOD;
                }
            }
            return -1;
        }
        
        private int hash(String s, int start, int end) {
            int r = 0;
            for (int i=start; i<=end; i++)
                r = (r * BASE + s.charAt(i)) % MOD;
            return r;
        }
    }
    
    public static class NaiveSearch extends StringSearch {
        @Override
        public int match(String s, String p) {
            int N = s.length(), P = p.length();
            for (int i=0; i<=N-P; i++) {
                boolean ok = true;
                for (int j=0; j<P; j++) {
                    if (s.charAt(i+j) != p.charAt(j)) {
                        ok = false;
                        break;
                    }
                }
                if (ok) 
                    return i;
            }
            return -1;
        }
    }
}
