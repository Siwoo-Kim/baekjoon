package baekjoon.p6000;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class P6603 {
    private static final Scanner scanner = new Scanner(
            new BufferedReader(new InputStreamReader(System.in)));
    private static final PrintWriter writer = new PrintWriter(
            new BufferedWriter(new OutputStreamWriter(System.out)));
    private static final int LOTTO = 6;

    // O(2^N)
    public static void main(String[] args) {
        while (true) {
            int K = scanner.nextInt();
            if (K == 0) {
                writer.flush();
                return;
            }
            int[] S = Arrays.stream(scanner.nextLine().split(" "))
                    .filter(i -> !i.isEmpty())
                    .mapToInt(Integer::parseInt)
                    .toArray();
            select(S, 0, new Stack<>());
            writer.write("\n");
        }
    }

    private static void select(int[] S,
                               int select,
                               Stack<Integer> stack) {
        if (stack.size() == LOTTO) {
            stack.forEach(i -> writer.write(i + " "));
            writer.write("\n");
            return;
        }
        if (select == S.length) return;
        if (LOTTO > (S.length-select) + stack.size())
            return;
        stack.push(S[select]);
        select(S, select+1, stack);
        stack.pop();
        select(S, select+1, stack);
    }
}
