package baekjoon.p1000;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Stack;

public class P1182 {
    private static final Scanner scanner = new Scanner(
            new BufferedReader(new InputStreamReader(System.in)));
    private static int N;

    public static void main(String[] args) {
        N = scanner.nextInt();
        select(new Stack<>());
    }

    private static boolean select(Stack<Integer> stack) {
        if (stack.size() == N) {
            stack.forEach(System.out::print);
            System.out.println();
            return true;
        }
        for (int i=1; i<=3; i++) {
            stack.push(i);
            if (check(stack)) {
               if (select(stack))
                   return true;
            }
            stack.pop();
        }
        return false;
    }

    private static boolean check(Stack<Integer> stack) {
        StringBuilder sb = new StringBuilder();
        stack.forEach(sb::append);
        int start = sb.length()-1;
        int end = sb.length();
        for (int i=1; i<=sb.length()/2; i++) {
            if (sb.subSequence(start-i, end-i)
                    .equals(sb.subSequence(start, end))) {
                return false;
            }
            start--;
        }
        return true;
    }
}
