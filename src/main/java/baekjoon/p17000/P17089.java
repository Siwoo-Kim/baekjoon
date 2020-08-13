package baekjoon.p17000;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class P17089 {
    private static final Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
    private static int N, M;
    private static boolean[][] friends;
    private static int[] counts;
    private static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) {
        N = scanner.nextInt();
        M = scanner.nextInt();
        friends = new boolean[N+1][N+1];
        counts = new int[N+1];
        for (int i=0; i<M; i++) {
            int v = scanner.nextInt(),
                    w = scanner.nextInt();
            friends[v][w] = true;
            friends[w][v] = true;
            counts[v]++;
            counts[w]++;
        }

        for (int i=1; i<=N; i++)
            answer = Integer.min(select(i), answer);
        System.out.println(answer == Integer.MAX_VALUE? -1: answer);
    }

    private static int select(int friend) {
        for (int i=friend+1; i<=N; i++)
            if (friends[friend][i])
                answer = Math.min(count(friend, i), answer);
        return answer;
    }

    private static int count(int a, int b) {
        int sum = counts[a] + counts[b];
        int answer = Integer.MAX_VALUE;
        for (int c=1; c<=N; c++) {
            if (c == a || c == b) continue;
            if (friends[a][c] && friends[b][c]) {
                answer = Math.min(sum + counts[c], answer);
            }
        }
        return answer != Integer.MAX_VALUE? answer - (2*3): Integer.MAX_VALUE;
    }

}
