package baekjoon.p2000;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class P2879 {
    private static final Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
    private static int answer;

    public static void main(String[] args) {
        int N = Integer.parseInt(scanner.nextLine());
        int[] tabs = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int[] correct = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int[] diff = new int[N];
        for (int i = 0; i < N; i++)
            diff[i] = correct[i] - tabs[i];

        int prev = diff[0];
        for (int i=1; i<N; i++) {
            if (signChanged(prev, diff[i])) {
                answer += Math.abs(prev);
                prev = diff[i];
            } else if (Math.abs(prev) <= Math.abs(diff[i]))
                prev = diff[i];
            else {
                answer += Math.abs(prev) - Math.abs(diff[i]);
                prev = diff[i];
            }
        }
        answer += Math.abs(prev);
        System.out.println(answer);

    }

    private static boolean signChanged(int a, int b) {
        return a > 0? b < 0: b > 0;
    }

}
