package baekjoon.p15000;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Scanner;

// 조합 nCr
public class P15650 {
    private static final Scanner scanner = new Scanner(
            new BufferedReader(new InputStreamReader(System.in)));
    public static void main(String[] args) {
        int N = scanner.nextInt(),
            M = scanner.nextInt();
        combination(N, M, 1, new LinkedList<>());
    }

    private static void combination(int N, int M, int start, LinkedList<Integer> list) {
        if (list.size() == M) {
            for (int i=list.size()-1; i>=0; i--)
                System.out.print(list.get(i) + " ");
            System.out.println();
            return;
        }
        for (int i=start; i<=N; i++) {
            list.push(i);
            combination(N, M, i+1, list);
            list.pop();
        }
    }
}
