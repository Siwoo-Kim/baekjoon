package baekjoon.p4000;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class P4902 {
    private static final Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
    private static int[][] triangle;
    private static int[][] sums;
    private static int N;
    private static int answer;

    public static void main(String[] args) {
        int t = 1;
        while (true) {
            N = scanner.nextInt();
            answer = Integer.MIN_VALUE;
            if (N == 0)
                return;
            triangle = new int[N][(N-1)*2+1];
            sums = new int[N][(N-1)*2+1];
            for (int row=0; row<N; row++)
                for (int col=0; col<row*2+1; col++) {
                    triangle[row][col] = scanner.nextInt();
                    sums[row][col] = triangle[row][col];
                    if (col != 0)
                        sums[row][col] += sums[row][col-1];
                }
            for (int row=0; row<N; row++)
                for (int col=0; col<row*N+1; col++) {
                    calculate(row, col, col, 0);
                }
            System.out.printf("%d. %d%n", t++, answer);
        }
    }

    private static void calculate(int row, int left, int right, int sum) {
        if (row < 0 || row >= N) return;
        if (left < 0 || right >= row*2+1) return;
        sum += sums[row][right] - (left == 0? 0: sums[row][left-1]);
        if (answer < sum)
            answer = sum;
        if (left % 2 == 0)
            calculate(row+1, left, right+2, sum);
        else
            calculate(row-1, left-2, right, sum);
    }
}
