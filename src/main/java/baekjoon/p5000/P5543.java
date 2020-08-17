package baekjoon.p5000;

import java.util.Scanner;

public class P5543 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int min = Integer.MAX_VALUE;
        for (int i=0; i<3; i++)
            min = Math.min(min, scanner.nextInt());
        int answer = min + Math.min(scanner.nextInt(), scanner.nextInt()) - 50;
        System.out.println(answer);
    }
}
