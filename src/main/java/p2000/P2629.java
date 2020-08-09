package p2000;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;


public class P2629 {

    private static int MAX = 15000;
    private static int MAX_MARBLES = 30;
    private static int[] weights;
    private static int N;
    private static boolean[][] D = new boolean[MAX_MARBLES+1][MAX+1];

    private static final Scanner scanner = new Scanner(
            new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) {
        N = Integer.parseInt(scanner.nextLine());
        weights = Arrays.stream(scanner.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        weigh(0, 0);

        int T = Integer.parseInt(scanner.nextLine());
        while (T != 0) {
            int S = scanner.nextInt();
            boolean ok = false;
            if (S <= MAX) {
                for (int i=0; i<=N; i++)
                    if (D[i][S]) {
                        ok = true;
                        break;
                    }
            }
            System.out.print(ok? "Y ": "N ");
            T--;
        }
    }

    private static void weigh(int weight, int index) {
        if (index == N) {
            D[index][weight] = true;
            return;
        }
        if (D[index][weight])
            return;
        D[index][weight] = true;
        weigh(weight+weights[index], index+1);
        weigh(Math.abs(weight-weights[index]), index+1);
        weigh(weight, index+1);
    }

}
