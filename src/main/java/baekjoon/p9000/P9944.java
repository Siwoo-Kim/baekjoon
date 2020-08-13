package baekjoon.p9000;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class P9944 {
    private static final Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
    private static int[][] D = {
            {0, -1}, {0, 1}, {-1, 0}, {1, 0}
    };
    private static char[][] board;
    private static int N, M;

    public static void main(String[] args) {
        int cnt = 1;
        while (scanner.hasNext()) {
            N = scanner.nextInt();
            M = scanner.nextInt();
            int vacant = 0;
            board = new char[N][M];
            for (int i = 0; i < N; i++) {
                String line = scanner.next();
                for (int j = 0; j < M; j++) {
                    board[i][j] = line.charAt(j);
                    if (board[i][j] == '.')
                        vacant++;
                }
            }
            int answer = bruteForce(0, vacant);
            System.out.printf("Case %d: %d%n", cnt++, answer == Integer.MAX_VALUE ? -1 : answer);
        }
    }

    private static int bruteForce(int index, int vacant) {
        if (index == N * M) return Integer.MAX_VALUE;
        int answer = Integer.MAX_VALUE;
        int row = index / M;
        int col = index % M;
        if (board[row][col] != '.') {
            answer = Math.min(bruteForce(index + 1, vacant), answer);
        } else {
            board[row][col] = '#';
            answer = Math.min(play(row, col, vacant-1), answer);
            board[row][col] = '.';
            answer = Math.min(bruteForce(index+1, vacant), answer);
        }
        return answer;
    }

    private static int play(int row, int col, int vacant) {
        if (vacant == 0) return 0;
        int answer = -1;
        for (int[] d: D) {
            int dx = row + d[0];
            int dy = col + d[1];
            while (valid(dx, dy) && board[dx][dy] == '.') {
                board[dx][dy] = '*';
                dx += d[0];
                dy += d[1];
                vacant--;
            }
            dx -= d[0];
            dy -= d[1];
            if (!(row == dx && col == dy)) {
                int tmp = play(dx, dy, vacant);
                if (tmp != Integer.MAX_VALUE) {
                    if (answer == -1 || answer > tmp + 1)
                        answer = tmp + 1;
                }
            }
            while (!(row == dx && col == dy)) {
                board[dx][dy] = '.';
                vacant++;
                dx -= d[0];
                dy -= d[1];
            }
        }
        return answer == -1? Integer.MAX_VALUE: answer;
    }

    private static boolean valid(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }
}
