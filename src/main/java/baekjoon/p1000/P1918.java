package baekjoon.p1000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class P1918 {
    private static final BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        String line = scanner.readLine();
        Stack<Character> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<line.length(); i++) {
            char c = line.charAt(i);
            if (Character.isAlphabetic(c))
                sb.append(c);
            else if (c == '(')
                stack.push(c);
            else if (c == ')') {
                while (stack.peek() != '(')
                    sb.append(stack.pop());
                stack.pop();
            } else {
                int p = getPrecedence(c);
                while (!stack.isEmpty() && getPrecedence(stack.peek()) >= p)
                    sb.append(stack.pop());
                stack.push(c);
            }
        }
        while (!stack.isEmpty())
            sb.append(stack.pop());
        System.out.println(sb.toString());
    }

    private static int getPrecedence(char c) {
        if (c == '*' || c == '/')
            return 2;
        else if (c == '(')
            return -1;
        else
            return 0;
    }
}
