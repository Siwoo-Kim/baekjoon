package baekjoon.p16000;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.stream.IntStream;

public class P16945 {
    private static final Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
    private static int[][] MAGIC_SQUARE = {
            {2, 7, 6, 9, 5, 1, 4, 3, 8},
            {2, 9, 4, 7, 5, 3, 6, 1, 8},
            {4, 3, 8, 9, 5, 1, 2, 7, 6},
            {4, 9, 2, 3, 5, 7, 8, 1, 6},
            {6, 1, 8, 7, 5, 3, 2, 9, 4},
            {6, 7, 2, 1, 5, 9, 8, 3, 4},
            {8, 1, 6, 3, 5, 7, 4, 9, 2},
            {8, 3, 4, 1, 5, 9, 6, 7, 2}
    };

    public static void main(String[] args) {
        int[] magicSquare = IntStream.rangeClosed(1, 9).toArray();
        int[] a = new int[9];
        for (int i=0; i<9; i++)
            a[i] = scanner.nextInt();
        int answer = Integer.MAX_VALUE;

        for (int i=0; i<MAGIC_SQUARE.length; i++) {
            int diff = 0;
            for (int j=0; j<MAGIC_SQUARE[i].length; j++) {
                diff += Math.abs(a[j] - MAGIC_SQUARE[i][j]);
            }
            answer = Math.min(answer, diff);
        }
        System.out.println(answer);
//        do {
//            if (isMagicSquare(magicSquare)) {
//                System.out.println(Arrays.toString(magicSquare));
//                int diff = 0;
//                for (int i=0; i<9; i++)
//                    diff += Math.abs(magicSquare[i] - a[i]);
//                answer = Math.min(answer, diff);
//            }
//        } while (nextPermutation(magicSquare));
//        System.out.println(answer);
    }

    private static boolean isMagicSquare(int[] magicSquare) {
        int[] sums = new int[6];
        for (int i=0; i<9; i++)
            sums[i/3] += magicSquare[i];
        for (int i=0; i<9; i++)
            sums[i%3+3] += magicSquare[i];
        for (int i=1; i<6; i++)
            if (sums[i] != sums[i-1])
                return false;
        int diag1 = magicSquare[0*3+0] + magicSquare[1*3+1] + magicSquare[2*3+2];
        int diag2 = magicSquare[0*3+2] + magicSquare[1*3+1] + magicSquare[2*3+0];
        return diag1 == diag2;
    }

    private static boolean nextPermutation(int[] p) {
        int i = p.length - 1;
        while (i > 0 && p[i-1] >= p[i])
            i--;
        if (i == 0) return false;
        int j = p.length - 1;
        while (j > i && p[i-1] >= p[j])
            j--;
        swap(i-1, j, p);
        j = p.length - 1;
        while (i < j)
            swap(i++, j--, p);
        return true;
    }

    private static void swap(int i, int j, int[] p) {
        int t = p[i];
        p[i] = p[j];
        p[j] = t;
    }
}
