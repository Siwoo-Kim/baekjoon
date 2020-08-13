package baekjoon.p15000;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Scanner;

// 순열 nPr
public class P15649 {
    private static final Scanner scanner = new Scanner(
            new BufferedReader(new InputStreamReader(System.in)));
    private static int N, M;
    private static boolean[] visit;

    //O(N!/N-M!)
    public static void main(String[] args) {
        N = scanner.nextInt();
        M = scanner.nextInt();
        visit = new boolean[N+1];
        permutation(N, M, new LinkedList<>());
    }

    private static void permutation(int N, int M, LinkedList<Integer> list) {
        if (list.size() == M) {
            for (int i=list.size()-1; i>=0; i--)
                System.out.print(list.get(i) + " ");
            System.out.println();
            return;
        }
        for (int i=1; i<=N; i++) {
            if (visit[i]) continue;
            visit[i] = true;
            list.push(i);
            permutation(N, M, list);
            list.poll();
            visit[i] = false;
        }
    }
}
