package baekjoon.p16000;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class P16938 {
    private static int N, LOWER, UPPER, DIFF;
    private static int[] A;

    // O(2^N)
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        LOWER = scanner.nextInt();
        UPPER = scanner.nextInt();
        DIFF = scanner.nextInt();
        scanner.nextLine();
        A = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int answer = select(0, 0, new LinkedList<>());
        System.out.println(answer);
    }

    private static int select(int index, int sum, LinkedList<Integer> stack) {
        if (index == N) {
            if (stack.size() < 2)
                return 0;
            if (sum < LOWER || sum > UPPER)
                return 0;
            int max = stack.stream().mapToInt(i -> i).max().getAsInt();
            int min = stack.stream().mapToInt(i -> i).min().getAsInt();
            return (max - min) >= DIFF? 1: 0;
        }
        int result = 0;
        stack.push(A[index]);
        result += select(index + 1, sum + A[index], stack);
        stack.pop();
        result += select(index + 1, sum, stack);
        return result;
    }
}
