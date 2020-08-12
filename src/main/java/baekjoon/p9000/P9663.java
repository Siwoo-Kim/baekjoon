package baekjoon.p9000;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

    //O(N!)
public class P9663 {
    private static final Scanner scanner = new Scanner(
            new BufferedReader(new InputStreamReader(System.in)));
    private static boolean[][] chess;
    private static int N;

    public static void main(String[] args) {
        N = scanner.nextInt();
        chess = new boolean[N][N];
        int answer = queen(0);
        System.out.println(answer);
    }

    private static int queen(int row) {
        if (row == N) return 1;
        int sum = 0;
        for (int col=0; col<N; col++) {
            if (check(col, row)) {
                chess[row][col] = true;
                sum += queen(row+1);
                chess[row][col] = false;
            }
        }
        return sum;
    }

    private static boolean check(int col, int row) {
        for (int i=0; i<N; i++) {
            if (chess[row][i])
                return false;
            if (chess[i][col])
                return false;
            if (valid(row-i, col-i)
                    && chess[row-i][col-i])
                return false;
            if (valid(row+i, col+i)
                    && chess[row+i][col+i])
                return false;
            if (valid(row+i, col-i)
                    && chess[row+i][col-i])
                return false;
            if (valid(row-i, col+i)
                    && chess[row-i][col+i])
                return false;
        }
        return true;
    }

    private static boolean valid(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}
