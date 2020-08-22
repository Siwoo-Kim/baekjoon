package baekjoon.p6000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class P6198 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());

        Stack<Long> stack = new Stack<>();
        long answer = 0;
        for (int i=0; i<N; i++) {
            long h = Long.parseLong(reader.readLine());
            if (stack.isEmpty())
                stack.push(h);
            else {
                while (!stack.isEmpty() && stack.peek() <= h) {
                    stack.pop();
                    answer+=stack.size();
                }
                stack.push(h);
            }
        }
        while (!stack.isEmpty()) {
            stack.pop();
            answer+=stack.size();
        }
        System.out.println(answer);
    }
}
