package baekjoon.p16000;

import util.Tip;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Tip("라빈 카프 (Rabin-Karp) 문자열 알고리즘" +
        "해쉬 (256 진법, 127 소수)" +
        "해쉬 재사용" +
    "KMP 문자열 알고리즘" +
        "fail 함수 사용. 가장 긴 prefix == suffix 의 길이 저장")
public class P16916 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static final int BASE = 256, MOD = 127;

    public static void main(String[] args) throws IOException {
        String s = reader.readLine(), p = reader.readLine();
        List<Integer> found = kmp(s, p);
        if (!found.isEmpty())
            System.out.println(1);
        else
            System.out.println(0);
    }

    private static List<Integer> kmp(String s, String p) {
        int[] fail = fail(p);
        List<Integer> answer = new ArrayList<>();
        int N = s.length(), M = p.length(), j = 0;
        for (int i=0; i<N; i++) {
            while (j > 0 && s.charAt(i) != p.charAt(j))
                j = fail[j-1];
            if (s.charAt(i) == p.charAt(j)) {
                if (j == M-1) {
                    answer.add(i-M+1);
                    j = fail[j];
                } else {
                    j++;
                }
            }
        }
        return answer;
    }

    private static int[] fail(String s) {
        int N = s.length();
        int[] fail = new int[s.length()];
        int j = 0;
        for (int i=1; i<N; i++) {
            while (j > 0 && s.charAt(j) != s.charAt(i))
                j = fail[j-1];
            if (s.charAt(j) == s.charAt(i))
                fail[i] = ++j;
            else
                fail[i] = 0;
        }
        return fail;
    }

    private static boolean match(String s, String p) {
        int n = s.length(), m = p.length();
        if (m > n) return false;
        int hash_s = hash(s, 0, m-1);
        int hash_p = hash(p, 0, m-1);
        int first = 1;
        for (int i=0; i<m-1; i++)
            first = (first * BASE) % MOD;
        for (int i=0; i<=n-m; i++) {
            if (hash_p == hash_s) {
                if (p.equals(s.substring(i, i+m)))
                    return true;
            }
            if (m+i < n) {
                hash_s = hash_s - (first * s.charAt(i) % MOD);
                hash_s = (hash_s + MOD) % MOD;
                hash_s = ((hash_s * BASE) % MOD + s.charAt(m+i)) % MOD;
            }
        }
        return false;
    }

    private static int hash(String s, int start, int end) {
       int hash = 0;
       for (int i=start; i<=end; i++)
           hash = (hash * BASE + s.charAt(i)) % MOD;
       return hash;
    }
}
