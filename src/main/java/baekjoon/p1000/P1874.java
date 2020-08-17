package baekjoon.p1000;

import java.io.*;
import java.util.Scanner;
import java.util.Stack;

public class P1874 {
    private static final Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
    private static final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = scanner.nextInt();
        int track = 1;
        Stack<Integer> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        for (int i=1; i<=N; i++) {
            int s = scanner.nextInt();
            while (track <= s) {
                stack.push(track++);
                sb.append("+\n");
            }
            if (!stack.isEmpty() && stack.peek() == s) {
                stack.pop();
                sb.append("-\n");
            } else {
                System.out.println("NO");
                return;
            }
        }
        writer.write(sb.toString());
        writer.flush();
    }
}
