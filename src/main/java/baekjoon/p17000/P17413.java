package baekjoon.p17000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class P17413 {
    private static final BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        String line = scanner.readLine();
        Stack<Character> stack = new Stack<>();
        for (int i=0; i<line.length(); i++) {
            char c = line.charAt(i);
            if (c == '<') {
                if (!stack.isEmpty())
                    print(stack);
                while (line.charAt(i) != '>')
                    System.out.print(line.charAt(i++));
                System.out.print(line.charAt(i));
            } else {
                if (c == ' ') {
                    print(stack);
                    System.out.print(c);
                } else {
                    stack.push(c);
                }
            }
        }
        print(stack);
    }

    private static void print(Stack<Character> stack) {
        while (!stack.isEmpty())
            System.out.print(stack.pop());
    }
}
