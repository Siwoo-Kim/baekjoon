package baekjoon.p17000;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Stack;

public class P17608 {
    private static final Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) {
        int N = scanner.nextInt();
        Stack<Integer> stack = new Stack<>();
        for (int i=0; i<N; i++) {
            int h = scanner.nextInt();
            if (stack.isEmpty())
                stack.push(h);
            else {
                while (!stack.isEmpty() && stack.peek() <= h)
                    stack.pop();
                stack.push(h);
            }
        }
        System.out.println(stack.size());
    }
}
