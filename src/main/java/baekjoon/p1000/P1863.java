package baekjoon.p1000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class P1863 {
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(reader.readLine());
        int answer = 0;
        Stack<Integer> stack = new Stack<>();

        for (int i=0; i<N; i++) {
            int height = Integer.parseInt(reader.readLine().split("\\s+")[1]);
            if (stack.isEmpty())
                stack.push(height);
            else if (height > stack.peek())
                stack.push(height);
            else {
                while (!stack.isEmpty() && stack.peek() > height) {
                    stack.pop();
                    answer++;
                }
                if (!stack.isEmpty() && stack.peek() == height)
                    continue;
                stack.push(height);
            }
        }
        while (!stack.isEmpty()) {
            int height = stack.pop();
            if (height != 0)
                answer++;
        }
        System.out.println(answer);
    }
}
