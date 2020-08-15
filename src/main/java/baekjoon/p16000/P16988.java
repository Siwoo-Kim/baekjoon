package baekjoon.p16000;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class P16988 {
    private static final Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
    private static int[][] D = { {0, -1}, {0, 1}, {-1, 0}, {1, 0} };
    private static int N, M;
    private static int[][] board;

    //NC2*NM = O(NC2)
    public static void main(String[] args) {
        N = scanner.nextInt();
        M = scanner.nextInt();
        scanner.nextLine();
        board = new int[N][M];
        for (int i=0; i<N; i++)
            board[i] = Arrays.stream(scanner.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        int answer = combination(N, M, 0, new Stack<>());
        System.out.println(answer);
    }

    private static int combination(int N, int M, int index, Stack<Integer> stack) {
        if (stack.size() == 2) {
            int[] blacks = stack.stream().mapToInt(Integer::intValue).toArray();
            Set<Integer> candidates = getCandidates(blacks[0]);
            candidates.addAll(getCandidates(blacks[1]));
            board[blacks[0] / M][blacks[0] % M] = 1;
            board[blacks[1] / M][blacks[1] % M] = 1;
            int cnt = 0;
            boolean[] visit = new boolean[N * M];
            for (int v: candidates) {
                if (!visit[v]) {
                    Result result = dfs(v, visit, new Result(0, true));
                    if (result.ok)
                        cnt += result.cnt;
                }
            }
            board[blacks[0] / M][blacks[0] % M] = 0;
            board[blacks[1] / M][blacks[1] % M] = 0;
            return cnt;
        }
        int answer = 0;
        for (int i=index; i<N*M; i++) {
            int row = i / M;
            int col = i % M;
            if (board[row][col] == 0) {
                stack.add(i);
                answer = Math.max(answer, combination(N, M, i + 1, stack));
                stack.pop();
            }
        }
        return answer;
    }

    private static class Result {
        int cnt;
        boolean ok;

        public Result(int cnt, boolean ok) {
            this.cnt = cnt;
            this.ok = ok;
        }
    }

    private static Result dfs(int v, boolean[] visit, Result result) {
        visit[v] = true;
        int row = v / M;
        int col = v % M;
        result.cnt++;
        for (int[] d: D) {
            int dx = row + d[0];
            int dy = col + d[1];
            int w = dx * M + dy;
            if (!valid(dx, dy)) continue;
            if (board[dx][dy] == 0) {
                result.ok = false;
            } else if (!visit[w] && board[dx][dy] == 2) {
                dfs(w, visit, result);
            }
        }
        return result;
    }

    private static Set<Integer> getCandidates(int index) {
        Set<Integer> candidates = new HashSet<>();
        int row = index / M;
        int col = index % M;
        for (int[] d: D) {
            int dx = row + d[0];
            int dy = col + d[1];
            if (valid(dx, dy) && board[dx][dy] == 2)
                candidates.add(dx * M + dy);
        }
        return candidates;
    }

    private static boolean valid(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }
}
