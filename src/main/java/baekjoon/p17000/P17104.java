package baekjoon.p17000;
import util.Status;

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

import static util.Status.Result.FAIL;

//FFT?
@Status(result = FAIL)
public class P17104 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int MAX = 1000000, N;
    private static boolean[] isPrime = new boolean[MAX+1];
    private static List<Integer> primes = new ArrayList<>();

    static {
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        for (int i=2; i<=MAX; i++) {
            if (isPrime[i]) {
                for (int j=i*2; j<=MAX; j+=i)
                    isPrime[j] = false;
            }
        }
        IntStream.rangeClosed(2, MAX)
                .filter(p -> isPrime[p])
                .forEach(p -> primes.add(p));
    }

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        for (int i=0; i<N; i++) {
            int x = Integer.parseInt(reader.readLine());
            Set<Pair> pairs = new HashSet<>();
            for (int p1: primes) {
                if (p1 > x) break;
                if (isPrime[x - p1]) {
                    int p2 = x - p1;
                    pairs.add(new Pair(p1, p2));
                }
            }
            writer.write(pairs.size() + "");
            writer.write("\n");
        }
        writer.flush();
    }

    private static class Pair {
        Set<Integer> pair = new HashSet<>();

        public Pair(int a, int b) {
            pair.add(a);
            pair.add(b);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair1 = (Pair) o;
            return Objects.equals(pair, pair1.pair);
        }

        @Override
        public int hashCode() {
            return Objects.hash(pair);
        }
    }
}
