package baekjoon.p1000;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class P1037 {
    private static final Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) {
        int N = scanner.nextInt();
        int[] a = new int[N];
        for (int i=0; i<N; i++)
            a[i] = scanner.nextInt();
        Arrays.sort(a);
        System.out.println(a[0] * a[N-1]);
    }
}
