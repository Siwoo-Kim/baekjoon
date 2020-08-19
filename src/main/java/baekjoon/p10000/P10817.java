package baekjoon.p10000;

import java.util.PriorityQueue;
import java.util.Scanner;

public class P10817 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PriorityQueue<Integer> q = new PriorityQueue<>();
        q.add(scanner.nextInt());
        q.add(scanner.nextInt());
        q.add(scanner.nextInt());
        q.poll();
        System.out.println(q.poll());
    }
}
