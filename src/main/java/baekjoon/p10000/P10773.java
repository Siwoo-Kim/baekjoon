package baekjoon.p10000;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Stack;

public class P10773 {
    private static final Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
    private static long answer;

    public static void main(String[] args) {
        int N = scanner.nextInt();
        Stack<Integer> stack = new Stack<>();
        for (int i=0; i<N; i++) {
            int x = scanner.nextInt();
            if (x == 0)
                stack.pop();
            else
                stack.push(x);
        }
        while (!stack.isEmpty())
            answer += stack.pop();
        System.out.println(answer);
    }
}
