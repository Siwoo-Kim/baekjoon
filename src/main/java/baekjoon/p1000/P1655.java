package baekjoon.p1000;

import java.util.PriorityQueue;
import java.util.Scanner;

public class P1655 {
    private static Scanner scanner = new Scanner(System.in);
    private static int N;

    public static void main(String[] args) {
        N = scanner.nextInt();
        PriorityQueue<Integer> left = new PriorityQueue<>((i, j) -> Integer.compare(j, i));
        PriorityQueue<Integer> right = new PriorityQueue<>();

        for (int i=0; i<N; i++) {
            int e = scanner.nextInt();
            if (left.isEmpty() || right.isEmpty())
                left.add(e);
            else {
                if (e <= left.peek())
                    left.add(e);
                else if (e >= right.peek())
                    right.add(e);
                else
                    left.add(e);
            }
            while (!(left.size() == right.size() || left.size() == right.size() + 1)) {
                if (left.size() > right.size()) {
                    right.add(left.poll());
                } else {
                    left.add(right.poll());
                }
            }
            System.out.println(left.peek());
        }
    }
}
