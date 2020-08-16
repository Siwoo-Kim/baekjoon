package baekjoon.p1000;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

//two pointer
public class P1806 {
    private static final Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) {
        int N = scanner.nextInt();
        long M = scanner.nextLong();
        scanner.nextLine();
        long[] S = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToLong(Long::parseLong)
                .toArray();
        long sum = S[0];
        int start = 0, end = 0, length = 1, answer = Integer.MAX_VALUE;
        while (true) {
            if (sum >= M) {
                answer = Math.min(length, answer);
                if (answer == 1) {
                    System.out.println(answer);
                    return;
                }
            }
            if (sum <= M || start == end) {
                end++;
                if (end == N) {
                    System.out.println(answer == Integer.MAX_VALUE? 0: answer);
                    return;
                }
                sum += S[end];
                length++;
            } else {
                sum -= S[start];
                length--;
                start++;
            }
        }
    }
}
