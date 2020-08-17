package baekjoon.p17000;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class P17298 {
    private static final Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
    private static final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = scanner.nextInt();
        int[] answer = new int[N];
        Arrays.fill(answer, -1);
        scanner.nextLine();
        int[] S = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        Stack<Integer> stack = new Stack<>();
        for (int i=0; i<N; i++) {
            if (stack.isEmpty())
                stack.push(i);
            else {
                while (!stack.isEmpty() && S[i] > S[stack.peek()])
                    answer[stack.pop()] = S[i];
                stack.push(i);
            }
        }
        for (int i=0; i<answer.length; i++)
            writer.write(answer[i] + " ");
        writer.flush();
    }
}
