package baekjoon.p16000;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class P16943 {

    private static final Scanner scanner = new Scanner(
            new BufferedReader(new InputStreamReader(System.in)));
    private static int A[], B[];
    private static String BS;

    //O(N!)
    public static void main(String[] args) {
        A = Arrays.stream(Long.toString(scanner.nextLong()).split(""))
                .mapToInt(Integer::parseInt)
                .boxed()
                .sorted(Collections.reverseOrder())
                .mapToInt(Integer::intValue)
                .toArray();
        B = Arrays.stream(Long.toString(scanner.nextInt()).split(""))
                .mapToInt(Integer::parseInt)
                .toArray();

        BS = "";
        for (int i=0; i<B.length; i++)
            BS += B[i];
        do {
            if (A[0] == 0)
                break;
            StringBuilder x1 = new StringBuilder();
            for (int i=0; i<A.length; i++)
                x1.append(A[i]);
            if (Long.parseLong(x1.toString()) <= Long.parseLong(BS)) {
                System.out.println(Integer.parseInt(x1.toString()));
                return;
            }
        } while (permutation(A));
        System.out.println(-1);
    }

    private static boolean permutation(int[] p) {
        int i = p.length-1;
        while (i>0 && p[i-1]<=p[i])
            i--;
        if (i == 0) return false;
        int j = p.length-1;
        while (j>i && p[i-1]<=p[j])
            j--;
        swap(i-1, j, p);
        j = p.length-1;
        while (i<j)
            swap(i++, j--, p);
        return true;
    }

    private static void swap(int i, int j, int[] P) {
        int t = P[i];
        P[i] = P[j];
        P[j] = t;
    }
}
