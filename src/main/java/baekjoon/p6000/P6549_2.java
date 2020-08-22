package baekjoon.p6000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class P6549_2 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static long[] heights;

    public static void main(String[] args) throws IOException {
        while (true) {
            StringTokenizer token = new StringTokenizer(reader.readLine());
            N = Integer.parseInt(token.nextToken());
            if (N == 0) {
                return;
            }
            heights = new long[N+1];
            int i = 0;
            while (token.hasMoreTokens())
                heights[i++] = Long.parseLong(token.nextToken());
            long answer = 0;
            Stack<Pair> stack = new Stack<>();
            for (i=0; i<heights.length; i++) {
                long h = heights[i];
                if (stack.isEmpty())
                    stack.push(new Pair(i, h));
                else {
                    while (!stack.isEmpty() && stack.peek().height > h) {
                        Pair pair = stack.pop();
                        long width = (i - pair.index) * pair.height;
                        answer = Math.max(width, answer);
                    }
                    stack.push(new Pair(i, h));
                }
            }
            System.out.println(answer);
        }
    }

    private static class Pair {
        int index;
        long height;

        public Pair(int index, long height) {
            this.index = index;
            this.height = height;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "index=" + index +
                    ", height=" + height +
                    '}';
        }
    }
}
