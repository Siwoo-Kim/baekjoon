package programmers;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class RepresentN {

    public static void main(String[] args) {
        int answer = new Solution().solution(5, 12);
        System.out.println(answer);
    }

    static class Solution {
        private Map<Pair, Integer> D = new HashMap<>();
        public static final int MAX = 8;
        private int N, NUMBER;
        private int[] values = new int[MAX];
        public int solution(int n, int number) {
            N = n;
            NUMBER = number;
            for (int i=0; i<8; i++) {
                values[i] = n;
                n *= 10;
                n += N;
            }
            int answer = dp(0, 0);
            return answer == Integer.MAX_VALUE? -1: answer;
        }

        private int dp(int n, int tried) {
            if (n == NUMBER)
                return tried;
            if (tried > MAX) return Integer.MAX_VALUE;
            Pair pair = new Pair(n, tried);
            if (D.containsKey(pair))
                return D.get(pair);
            int answer = Integer.MAX_VALUE;
            for (int i=0; i<values.length; i++) {
                int v = values[i];
                answer = Math.min(dp(n + v, tried  + (i+1)), answer);
                answer = Math.min(dp(n - v, tried  + (i+1)), answer);
                answer = Math.min(dp(n * v, tried  + (i+1)), answer);
                answer = Math.min(dp(n / v, tried  + (i+1)), answer);
            }
            D.put(pair, answer);
            return answer;
        }

        private static class Pair {
            int tried, value;

            public Pair(int tried, int value) {
                this.tried = tried;
                this.value = value;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Pair pair = (Pair) o;
                return tried == pair.tried &&
                        value == pair.value;
            }

            @Override
            public int hashCode() {
                return Objects.hash(tried, value);
            }
        }
    }
}
