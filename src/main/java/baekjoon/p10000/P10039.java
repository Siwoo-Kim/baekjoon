package baekjoon.p10000;

import java.util.Scanner;

public class P10039 {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int sum = 0;
        for (int i=0; i<5; i++) {
            sum += Math.max(40, scanner.nextInt());
        }
        System.out.println(sum / 5);
    }
}
