package baekjoon.p3000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class P3015 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        Stack<Pair> stack = new Stack<>();
        long answer = 0;
        for (int i=0; i<N; i++) {
            Pair pair = new Pair(Long.parseLong(reader.readLine()), 1);
            while (!stack.isEmpty()) {
                if (stack.peek().height <= pair.height) {
                    Pair top = stack.pop();
                    answer += top.count;
                    if (pair.height == top.height) {
                        pair.count += top.count;
                    }
                } else {
                    break;
                }
            }
            if (!stack.isEmpty())
                answer++;
            stack.push(pair);
        }
        System.out.println(answer);
    }

    private static class Pair {
        long height, count;

        public Pair(long height, long count) {
            this.height = height;
            this.count = count;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "height=" + height +
                    ", count=" + count +
                    '}';
        }
    }
}
