package baekjoon.p17000;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class P17088 {
    private static final Scanner scanner = new Scanner(
            new BufferedReader(new InputStreamReader(System.in)));
    private static int[] A;
    private static int N;
    private static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) {
        N = Integer.parseInt(scanner.nextLine());
        A = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        if (N == 1) {
            System.out.println(0);
            return;
        }
        for (int op1 = -1; op1 <= 1; op1++) {
            for (int op2 = -1; op2 <= 1; op2++) {
                int cnt = 0;
                if (op1 != 0) cnt++;
                if (op2 != 0) cnt++;
                int a1 = A[0] + op1;
                int a2 = A[1] + op2;
                int d = a2 - a1;
                int index = 2;
                boolean ok = true;
                int an = a2;
                while (index < N) {
                    an += d;
                    int i1 = A[index];
                    if (i1 == an) {
                        //ignore
                    } else if ((i1 - 1) == an)
                        cnt++;
                    else if ((i1 + 1) == an)
                        cnt++;
                    else {
                        ok = false;
                        break;
                    }
                    index++;
                }
                if (ok)
                    answer = Math.min(answer, cnt);
            }

        }
        if (answer == Integer.MAX_VALUE)
            System.out.println(-1);
        else
            System.out.println(answer);
    }
}
