package baekjoon.p2000;

import java.util.Scanner;

public class P2089 {

    public static void main(String[] args) {
        long N = new Scanner(System.in).nextLong();
        if (N == 0)
            System.out.println(0);
        else
            binary(N);
    }

    public static void binary(long n) {
        if (n == 0) return;
        if (n % 2 == 0) {
            binary(-(n/2));
            System.out.print(0);
        } else {
            if (n > 0) binary(-(n/2));
            else binary((-n+1)/2);
            System.out.print(1);
        }
    }
}
