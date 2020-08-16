package baekjoon.p2000;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

//two pointer
public class P2003 {
    private static final Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
    private static int N, M;
    private static long answer;
    private static int[] A;

    public static void main(String[] args) {
        N = scanner.nextInt();
        M = scanner.nextInt();
        scanner.nextLine();
        A = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt).toArray();

        int start = 0, end = 0, sum = A[0];
        while (true) {
            if (M == sum)
                answer++;
            if (M >= sum || end == start) {
                end++;
                if (end == N)
                    break;
                sum += A[end];
            }  else {
                sum -= A[start];
                start++;
            }
        }
        System.out.println(answer);
    }
}
