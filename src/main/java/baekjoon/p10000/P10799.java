package baekjoon.p10000;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Stack;

public class P10799 {
    private static final Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) {
        String line = scanner.nextLine();
        Stack<Integer> stack = new Stack<>();
        int answer = 0;
        for (int i=0; i<line.length(); i++) {
            char c = line.charAt(i);
            if (c == '(')
                stack.push(i);
            else {
                int top = stack.pop();
                if (top == i-1)    //laser
                    answer += stack.size();
                else
                    answer++;
            }
        }
        System.out.println(answer);
    }
}
