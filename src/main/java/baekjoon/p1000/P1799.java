package baekjoon.p1000;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class P1799 {
    private static final Scanner scanner = new Scanner(
            new BufferedReader(new InputStreamReader(System.in)));
    private static int[][] chess;
    private static boolean[] diag;
    private static boolean[] diag2;
    private static boolean[][] colors;
    private static boolean[][] visit;
    private static int N;
    private static int COUNT =0 ;

    // O(2^(N*N/4))
    public static void main(String[] args) {
        N = Integer.parseInt(scanner.nextLine());
        chess = new int[N][N];
        visit = new boolean[N][N];
        diag = new boolean[N*2];
        diag2 = new boolean[N*2];
        colors = new boolean[N][N];
        for (int i=0; i<N; i++) {
            chess[i] = Arrays.stream(scanner.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }
        for (int i=0; i<N; i++)
            for (int j=0; j<N; j++) {
                if (i % 2 == 0) {
                    if (j % 2 == 0)
                        colors[i][j] = true;
                } else {
                    if (j % 2 != 0)
                        colors[i][j] = true;
                }
            }
        bishop(-1, 0, true);
        bishop(-1, 0, false);
        System.out.println(answer1 + answer2);
        System.out.println(COUNT);
    }

    private static int answer1 = 0;
    private static int answer2 = 0;

    private static void bishop(int index, int cnt, boolean color) {
        if (index == N*N) return;
        if (color)
            answer1 = Math.max(cnt, answer1);
        else
            answer2 = Math.max(cnt, answer2);
        for (int i=index+1; i<N*N; i++) {
            int r = i / N;
            int c = i % N;
            COUNT++;
            if (colors[r][c] != color)
                continue;
            if (check(r, c)) {
                visit[r][c] = true;
                diag[r + c] = true;
                diag2[r - c + N] = true;
                bishop(i, cnt+1, color);
                visit[r][c] = false;
                diag[r + c] = false;
                diag2[r - c + N] = false;
            }
        }
    }

    private static boolean check(int row, int col) {
        if (chess[row][col] == 0) return false;
        if (visit[row][col]) return false;
        /*  / way   */
        if (diag[row+col])
            return false;
        /*  \ way   */
        if (diag2[row-col+N])
            return false;
        return true;
    }
}
