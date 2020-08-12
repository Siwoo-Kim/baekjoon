package baekjoon.p16000;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class P16917 {
    private static final Scanner scanner = new Scanner(
            new BufferedReader(new InputStreamReader(System.in)));
    private static int SEASON;
    private static int PLAIN;
    private static int HALF_HALF;
    private static int X, Y;
    private static long answer = Long.MAX_VALUE;

    public static void main(String[] args) {
        SEASON = scanner.nextInt();
        PLAIN = scanner.nextInt();
        HALF_HALF = scanner.nextInt();
        X = scanner.nextInt();
        Y = scanner.nextInt();
        int i = 0;
        while (true) {
            int amount = i / 2;
            if (amount > Math.max(X, Y))
                break;
            int xremain = Math.max(0, X - amount);
            int yremain = Math.max(0, Y - amount);
            long sum = xremain * SEASON + yremain * PLAIN +
                    i * HALF_HALF;
            answer = Math.min(answer, sum);
            i+=2;
        }
        System.out.println(answer);
    }
}
