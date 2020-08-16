package baekjoon.p6000;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Stack;

public class P6549 {
    private static final Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
    private static int N;
    private static long answer;
    private static long[] heights;

    private static class Pair {
        int index;
        long height;

        public Pair(int index, long height) {
            this.index = index;
            this.height = height;
        }
    }

    public static void main(String[] args) {
        while (true) {
            N = scanner.nextInt();
            if (N == 0) return;
            heights = new long[N+1];
            answer = 0;
            for (int i=0; i<N; i++)
                heights[i] = scanner.nextLong();
            Stack<Pair> stack = new Stack<>();
            for (int i=0; i<=N; i++) {
                if (stack.isEmpty())
                    stack.push(new Pair(i, heights[i]));
                else {
                    Pair top = stack.peek();
                    if (top.height <= heights[i])
                        stack.push(new Pair(i, heights[i]));
                    else {
                        int minIndex = i;
                        while (!stack.isEmpty() &&
                                heights[i] < (top = stack.peek()).height) {
                            stack.pop();
                            long width = (i - top.index) * top.height;
                            answer = Math.max(width, answer);
                            minIndex = top.index;
                        }
                        stack.push(new Pair(minIndex, heights[i]));
                    }
                }
            }
            System.out.println(answer);
        }
    }
}
