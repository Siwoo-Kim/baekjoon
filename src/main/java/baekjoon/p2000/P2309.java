package baekjoon.p2000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class P2309 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int MAX = 9;
    private static int[] H = new int[MAX];
    private static boolean found = false;

    public static void main(String[] args) throws IOException {
        for (int i=0; i<MAX; i++)
            H[i] = Integer.parseInt(reader.readLine());
        subsets(0, new Stack<>());
    }

    private static void subsets(int index, Stack<Integer> stack) {
        if (index == H.length) {
            if (stack.size() == 7 && !found) {
                int sums = stack.stream().mapToInt(i -> H[i]).sum();
                if (sums == 100) {
                    stack.stream()
                            .mapToInt(i -> H[i])
                            .sorted()
                            .forEach(System.out::println);
                    found = true;
                }
            }
            return;
        }
        if (stack.size() > 7) return;
        stack.push(index);
        subsets(index + 1, stack);
        stack.pop();
        subsets(index + 1, stack);
    }
}
