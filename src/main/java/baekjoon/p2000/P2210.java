package baekjoon.p2000;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class P2210 {
    private static final Scanner scanner = new Scanner(
            new BufferedReader(new InputStreamReader(System.in)));
    private static int N = 5;
    static int[][] board = new int[N][N];
    private static int[][] D = { {0, -1}, {0, 1}, {-1, 0}, {1, 0} };
    private static Set<Integer> set = new HashSet<>();

    public static void main(String[] args) {
        for (int i=0; i<N; i++) {
            board[i] = Arrays.stream(scanner.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }
        traverse(0);
        System.out.println(set.size());
    }

    private static void traverse(int index) {
        if (index == N*N) return;
        int row = index / N;
        int col = index % N;
        visit(row, col, 0, 0);
        traverse(index + 1);
    }

    private static void visit(int row, int col, int cnt, int r) {
        if (cnt == 6) {
            set.add(r);
            return;
        }
        r = (r * 10) + board[row][col];
        for (int[] d: D) {
            int dx = row+d[0];
            int dy = col+d[1];
            if (valid(dx, dy)) {
                visit(dx, dy, cnt+1, r);
            }
        }
    }

    private static boolean valid(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}
