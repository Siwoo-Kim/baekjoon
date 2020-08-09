package p16000;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class P16198 {

    private static final Scanner scanner = new Scanner(
            new BufferedReader(new InputStreamReader(System.in)));
    private static int N;
    private static int[] W;

    //O(N-2!)
    public static void main(String[] args) {
        N = Integer.parseInt(scanner.nextLine());
        W = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        Set<Integer> visit = new HashSet<>();
        long answer = select(N, 0, visit);
        System.out.println(answer);
    }

    private static long select(int cnt, long sum, Set<Integer> visit) {
        if (cnt == 2) return sum;
        long max = 0;
        for (int i=1; i<N-1; i++) {
            if (!visit.contains(i)) {
                visit.add(i);
                int left = i-1;
                int right = i+1;
                while (visit.contains(left))
                    left--;
                while (visit.contains(right))
                    right++;
                max = Math.max(select(cnt-1, sum, visit) + W[left] * W[right], max);
                visit.remove(i);
            }
        }
        return max;
    }
}
