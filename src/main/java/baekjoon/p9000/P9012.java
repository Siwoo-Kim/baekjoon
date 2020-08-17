package baekjoon.p9000;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class P9012 {
    private static final Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) {
        int t = scanner.nextInt();
        scanner.nextLine();
        while (t != 0) {
            String line = scanner.nextLine();
            int depth = 0;
            boolean ok = true;
            for (int i=0; i<line.length(); i++) {
                if (line.charAt(i) == '(')
                    depth++;
                else
                    depth--;
                if (depth < 0) {
                    ok = false;
                    break;
                }
            }
            if (depth == 0 && ok)
                System.out.println("YES");
            else
                System.out.println("NO");
            t--;
        }
    }
}
