package baekjoon.p1000;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class P1208 {
    private static final Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) {
        int N = scanner.nextInt();
        int S = scanner.nextInt();
        scanner.nextLine();
        int[] A = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        int M = N/2;
        N = N - M;
        int[] first = new int[1<<N];
        for (int i=0; i<(1<<N); i++) {  //bitset
            for (int k=0; k<N; k++) {
                if ((i&(1<<k)) > 0)
                    first[i] += A[k];
            }
        }
        int[] second = new int[1<<M];
        for (int i=0; i<(1<<M); i++) {
            for (int k=0; k<M; k++) {
                if ((i&(1<<k)) > 0)
                    second[i] += A[k+N];
            }
        }
        Arrays.sort(first);
        Arrays.sort(second);
        for (int i=0; i<second.length/2; i++)
            swap(i, second.length-i-1, second);
        N = (1<<N);
        M = (1<<M);
        int i = 0, j = 0;
        long answer = 0;
        while (i < N && j < M) {
            if (first[i] + second[j] == S) {
                long cnt1 = 1;
                long cnt2 = 1;
                i++;
                j++;
                while (i < N && first[i] == first[i-1]) {
                    cnt1++;
                    i++;
                }
                while (j < M && second[j] == second[j-1]) {
                    cnt2++;
                    j++;
                }
                answer += cnt1 * cnt2;
            } else if (first[i] + second[i] < S) {
                i++;
            } else {
                j++;
            }
        }
        System.out.println(answer);
    }

    private static void swap(int i, int j, int[] a) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

}
