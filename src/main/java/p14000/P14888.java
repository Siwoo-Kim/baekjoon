package p14000;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class P14888 {

    private static final Scanner scanner = new Scanner(
        new BufferedReader(new InputStreamReader(System.in)));
    public static long min = Long.MAX_VALUE;
    public static long max = Long.MIN_VALUE;

    //O(N-1!)
    public static void main(String[] args) {
        int N = Integer.parseInt(scanner.nextLine());
        int[] S = new int[N];
        String[] line = scanner.nextLine().split(" ");
        for (int i = 0; i < N; i++)
            S[i] = Integer.parseInt(line[i]);
        int[] ops = new int[N - 1];
        int index = 0;
        for (int op = 0; op < 4; op++) {
            int cnt = scanner.nextInt();
            while (cnt > 0) {
                ops[index++] = op;
                cnt--;
            }
        }

        do {
            long val = S[0];
            for (int i = 1; i < N; i++) {
                if (ops[i - 1] == 0)
                    val += S[i];
                if (ops[i - 1] == 1)
                    val -= S[i];
                if (ops[i - 1] == 2)
                    val *= S[i];
                if (ops[i - 1] == 3)
                    val /= S[i];
            }
            min = Math.min(min, val);
            max = Math.max(max, val);
        } while (nextPermutation(ops));

        System.out.println(max);
        System.out.println(min);
    }

    private static boolean nextPermutation(int[] p) {
        int i = p.length - 1;
        while (i > 0 && p[i - 1] >= p[i])
            i--;
        if (i == 0)
            return false;
        int j = p.length - 1;
        while (i < j && p[i - 1] >= p[j])
            j--;
        swap(i - 1, j, p);
        j = p.length - 1;
        while (i < j) {
            swap(i++, j--, p);
        }
        return true;
    }

    private static void swap(int i, int j, int[] p) {
        int tmp = p[i];
        p[i] = p[j];
        p[j] = tmp;
    }
}
