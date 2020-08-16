package baekjoon.p2000;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class P2493 {
    private static final BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int N;

    private static class Pair {
        long height;
        int index;

        public Pair(int index, long height) {
            this.height = height;
            this.index = index;
        }
    }

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(scanner.readLine());
        int[] heights = Arrays.stream(scanner.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        Stack<Pair> stack = new Stack<>();
        for (int i=1; i<=N; i++) {
            if (stack.isEmpty()) {
                writer.write(0 + " ");
                stack.push(new Pair(i, heights[i-1]));
            } else {
                long height = heights[i-1];
                while (!stack.isEmpty() &&
                        stack.peek().height < height)
                    stack.pop();
                if (stack.isEmpty())
                    writer.write(0 + " ");
                else
                    writer.write(stack.peek().index + " ");
                stack.push(new Pair(i, height));
            }
        }
        writer.flush();
    }
}
