package algorithms.string;

import java.util.ArrayList;
import java.util.List;

/**
 * Search pattern p from String s.
 */
public class KMP {
    private final String s;
    private final String p;
    private final int[] fail;

    public KMP(String string, String pattern) {
        this.s = string;
        this.p = pattern;
        fail = new int[p.length()];
        fail();
    }

    private void fail() {
        int j = 0;
        for (int i=1; i<p.length(); i++) {
            char c = p.charAt(i);
            while (j > 0 && c != p.charAt(j))
                j = fail[j-1];
            if (c == p.charAt(j))
                fail[i] = ++j;
        }
    }
    
    public List<Integer> search() {
        List<Integer> result = new ArrayList<>();
        int j = 0;
        for (int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            while (j > 0 && p.charAt(j) != c)
                j = fail[j-1];
            if (p.charAt(j) == c) {
                if (j == p.length() - 1) {
                    result.add(i - p.length() +1);
                    j = fail[j];
                } else {
                    j++;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        KMP kmp = new KMP("ABCABDABCABCABEF", "ABCABE");
        List<Integer> result = kmp.search();
        assert result.contains(9);
    }
}
