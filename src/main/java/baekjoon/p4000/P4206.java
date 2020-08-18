package baekjoon.p4000;

import java.util.Scanner;

public class P4206 {
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        while (true) {
            int N = Integer.parseInt(scanner.nextLine());
            String PATTERN = scanner.nextLine();
            StringBuilder sb = new StringBuilder();
            StringBuilder s1 = new StringBuilder("0");
            StringBuilder s2 = new StringBuilder("1");
            StringBuilder tmp;
            for (int i = 2; i<=N; i++) {
                tmp = new StringBuilder(s2);
                s2.append(s1);
                s1 = new StringBuilder(tmp);
            }
            System.out.println(s2);
        }
    }
}
