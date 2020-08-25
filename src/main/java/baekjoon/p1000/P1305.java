package baekjoon.p1000;

public class P1305 {

    public static void main(String[] args) {
        int[] fail = kmp("ABACABABC");
    }

    public static int[] kmp(String s) {
        int N = s.length();
        int[] fail = new int[N];
        int j = 0;
        for (int i=1; i<N; i++) {
            while (j > 0 && s.charAt(i) != s.charAt(j))
                j = fail[j-1];
            if (s.charAt(i) == s.charAt(j)) {
                fail[i] = j + 1;
                j += 1;
            } else {
                fail[i] = 0;
            }
        }
        return fail;
    }
}
