package baekjoon.p16000;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class P16953 {
    private static final Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) {
        long A = scanner.nextLong();
        long B = scanner.nextLong();

        boolean ok = select(A, B, 1);
        if (!ok)
            System.out.println(-1);
    }

    private static boolean select(long a, long b, int cnt) {
        if (a == b) {
            System.out.println(cnt);
            return true;
        }
        if (a > b) return false;
        return select(a * 2, b, cnt+1) || select(a * 10 +  1, b, cnt+1);
    }
}
