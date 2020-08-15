package baekjoon.p1000;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Scanner;

public class P1026 {
    private static final Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
    private static PriorityQueue<Integer> A = new PriorityQueue<>();
    private static PriorityQueue<Integer> B = new PriorityQueue<>((a, b) -> Integer.compare(b, a));

    //O(NlogN)
    public static void main(String[] args) {
        int N = scanner.nextInt();
        for (int i=0; i<N; i++)
            A.add(scanner.nextInt());
        for (int i=0; i<N; i++)
            B.add(scanner.nextInt());
        int sum = 0;
        for (int i=0; i<N; i++)
            sum += A.poll() * B.poll();
        System.out.println(sum);
    }
}
