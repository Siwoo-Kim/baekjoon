package baekjoon.p16000;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class P16922 {
    private static final Scanner scanner = new Scanner(
            new BufferedReader(new InputStreamReader(System.in)));
    private static boolean[] visit;
    private static int N;

    //O(N^3)
    public static void main(String[] args) {
        N = scanner.nextInt();
        visit = new boolean[20 * 50 + 1];
        int cnt = 0;
        for (int i=0; i<=N; i++)
            for (int v=0; i+v<=N; v++)
                for (int x=0; i+v+x<=N; x++) {
                    int l = N-i-v-x;
                    int sum = 1 * i + 5 * v + 10 * x + l * 50;
                    cnt++;
                    if (!visit[sum])
                        visit[sum] = true;
                }

        int answer = 0;
        for (int i=0; i<visit.length; i++)
            if (visit[i])
                answer++;
        System.out.println(answer);
        System.out.println(cnt);
    }

//    private static void select(int i, int x, int v, int cnt) {
//        if (cnt == N) {
//            int l = (N-i-x-v);
//            int sum = 1 * i + 5 * x + 10 * v + 50 * l;
//            if (!visit[sum])
//                visit[sum] = true;
//            return;
//        }
//        int l = (N-i-x-v);
//        int sum = 1 * i + 5 * x + 10 * v + 50 * l;
//        if (!visit[sum])
//            visit[sum] = true;
//        select(i+1, x, v, cnt+1);
//        select(i, x+1, v, cnt+1);
//        select(i, x, v+1, cnt+1);
//    }
}
