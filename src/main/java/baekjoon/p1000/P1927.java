package baekjoon.p1000;

import java.util.Scanner;

public class P1927 {
    public static Scanner scanner = new Scanner(System.in);
    public static final int MAX = 100000;
    public static int N;

    private static class PQ {
        long[] heap = new long[MAX+1];
        int N = 0;

        public long poll() {
            if (isEmpty())
                return 0L;
            long e = heap[1];
            swap(1, N--);
            sink(1);
            heap[N+1] = 0;
            return e;
        }

        private boolean isEmpty() {
            return N == 0;
        }

        public void add(long e) {
            heap[++N] = e;
            swim(N);
        }

        private void sink(int index) {
            if (index * 2 <= N) {
                int j = index * 2;
                if (j < N && heap[j + 1] < heap[j])
                    j++;
                if (heap[j] < heap[index]) {
                    swap(index, j);
                    sink(j);
                }
            }
        }

        private void swim(int index) {
            if (index == 1) return;
            else {
                int p = index/2;
                if (heap[p] > heap[index]) {
                    swap(index, p);
                    swim(p);
                }
            }
        }

        private void swap(int i, int j) {
            long t = heap[i];
            heap[i] = heap[j];
            heap[j] = t;
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
