package baekjoon.p1000;

import util.Algorithm;
import util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * KMP
 *  - String Matching Algorithm
 *  - fail function
 *      fail[i] = S 의 i 까지 부분 문자열에서 prefix == suffix 가 될 수 있는
 *      가장 긴 문자열의 길이.
 *
 *      eg). ABCABE
 *          i=0     A       fail=0
 *          i=1     AB      fail=0
 *          i=2     ABC     fail=0
 *          i=3     ABCA    fail=1
 *          i=4     ABCAB   fail=2
 *          i=5     ABCABE  fail=0
 *
 *
 *      point)
 *          i)  0   1   2   3    4  5   6   7   8   9
 *       S[i])  A   B   A   C   A   B   A   B   A   C
 *    fail[i])  0   0   1   0   1   2   3   ?
 *                      O   X               2
 */
@Used({Algorithm.KMP})
public class P1786 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        String s = reader.readLine(), p = reader.readLine();
        List<Integer> indexes = kmp(s, p);
        System.out.println(indexes.size());
        for (int i: indexes)
            System.out.println(i + 1);
    }

    public static List<Integer> kmp(String s, String p) {
        int[] fail = fail(p);
        List<Integer> indexes = new ArrayList<>();
        int N = s.length(), M = p.length(), j = 0;
        for (int i=0; i<N; i++) {
            while (j > 0 && s.charAt(i) != p.charAt(j))
                j = fail[j-1];
            if (s.charAt(i) == p.charAt(j))
                if (j == M-1) {
                    indexes.add(i-M+1);
                    j = fail[j];
                } else {
                    j++;
                }
        }
        return indexes;
    }

    public static int[] fail(String s) {
        int N = s.length();
        int[] fail = new int[N];
        int j = 0;
        for (int i=1; i<N; i++) {
            while (j > 0 && s.charAt(j) != s.charAt(i))
                j = fail[j-1];
            if (s.charAt(j) == s.charAt(i))
                fail[i] = ++j;
            else
                j = 0;
        }
        return fail;
    }
}
