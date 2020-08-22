package baekjoon.p11000;

import java.util.Scanner;

public class P11279 {
    private static Scanner scanner = new Scanner(System.in);
    private static int MAX = 100000;
    private static int N;

    private static class PQ {
        long[] heap = new long[MAX+1];
        int N = 0;

        public void add(long e) {
            heap[++N] = e;
            swim(N);
        }

        private void swim(int s) {
            while (s > 1 && heap[s/2] < heap[s]) {
                swap(s/2, s, heap);
                s = s/2;
            }
        }

        private void swap(int x, int y, long[] heap) {
            long t = heap[x];
            heap[x] = heap[y];
            heap[y] = t;
        }

        public long poll() {
            if (isEmpty()) return 0;
            long e = heap[1];
            swap(1, N--, heap);
            sink(1);
            heap[N+1] = 0;
            return e;
        }

        private boolean isEmpty() {
            return N == 0;
        }

        private void sink(int i) {
            while (2*i <= N) {
                int j = i * 2;
                if (j < N && heap[j] < heap[j+1]) {
                    j++;
                }
                if (heap[j] <= heap[i])
                    break;
                swap(j, i, heap);
                i = j;
            }
        }
    }

    public static void main(String[] args) {
        N = scanner.nextInt();
        PQ pq = new PQ();
        for (int i=0; i<N; i++) {
            long command = scanner.nextLong();
            if (command == 0)
                System.out.println(pq.poll());
            else
                pq.add(command);
        }
    }
}
