package baekjoon.p2000;

import java.io.*;
import java.util.*;

public class P2812 {
    private static final Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
    private static final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = scanner.nextInt();
        int M = scanner.nextInt();
        scanner.nextLine();
        int[] a = Arrays.stream(scanner.nextLine().split("")).mapToInt(Integer::parseInt).toArray();
        LinkedList<Integer> deque = new LinkedList<>();
        for (int i=0; i<a.length; i++) {
            while (M > 0 && !deque.isEmpty()
                    && deque.peek() < a[i]) {
                deque.pop();
                M--;
            }
            deque.push(a[i]);
        }
        int size = deque.size()-M;
        while (size != 0) {
            writer.write(deque.pollLast() + "");
            size--;
        }
        writer.flush();
    }
}
