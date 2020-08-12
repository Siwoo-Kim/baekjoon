package baekjoon.p1000;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class P1038 {
    public static void main(String[] args) {
        int N = new Scanner(System.in).nextInt();
        if (N < 10)
            System.out.println(N);
        else if (N == 1022)
            System.out.println(9876543210L);
        else if (N > 1022)
            System.out.println(-1);
        else {
            int cnt = 0;
            Queue<Integer> q = new LinkedList<>();
            for (int i=1; i<10; i++) {
                q.add(i);
                cnt++;
            }
            while (cnt < N) {
                int x = q.poll();
                int y = x % 10;
                for (int j=0; j<y; j++) {
                    q.add(x * 10 + j);
                    cnt++;
                    if (cnt == N) {
                        System.out.println(x * 10 + j);
                        return;
                    }
                }
            }
        }
    }
}
