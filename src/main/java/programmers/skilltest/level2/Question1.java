package programmers.skilltest.level2;

public class Question1 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        for (int i=1; i<=10; i++)
            System.out.println(solution.solution(i));
    }

    static class Solution {
        private static int[] v = {4, 1, 2};

        public String solution(int n) {
            StringBuilder s = new StringBuilder();
            while (n != 0) {
                int r = n % 3;
                s.insert(0, v[r]);
                n /= 3;
                if (r == 0)
                    n--;
            }
            return s.toString();
        }
    }
}
