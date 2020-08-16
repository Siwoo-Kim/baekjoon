package baekjoon.p1000;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Stack;

public class P1725 {
    private static final Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
    private static int N;
    private static long answer;

    private static class Pair {
        int index;
        long height;

        public Pair(int index, long height) {
            this.index = index;
            this.height = height;
        }
    }

    public static void main(String[] args) {
        N = scanner.nextInt();
        long[] heights = new long[N+1];
        for (int i=0; i<N; i++)
            heights[i] = scanner.nextLong();
        Stack<Pair> stack = new Stack<>();
        for (int i=0; i<=N; i++) {
            if (stack.isEmpty())
                stack.push(new Pair(i, heights[i]));
            else {
                Pair top = stack.peek();
                if (heights[i] >= top.height) {
                    stack.push(new Pair(i, heights[i]));
                } else {
                    int minIndex = i;
                    while (!stack.isEmpty()
                            && stack.peek().height > heights[i]) {
                        top = stack.pop();
                        long width = (i - top.index) * top.height;
                        answer = Math.max(answer, width);
                        minIndex = top.index;
                    }
                    stack.push(new Pair(minIndex, heights[i]));
                }
            }
        }
        System.out.println(answer);
    }
}
