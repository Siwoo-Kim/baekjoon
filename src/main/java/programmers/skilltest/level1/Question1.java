package programmers.skilltest.level1;

public class Question1 {

    public static void main(String[] args) {
        System.out.println(new Solution().solution(987));
    }

    public static class Solution {
        public int solution(int n) {
            int answer = 0;
            while (n != 0) {
                answer += n % 10;
                n /= 10;
            }
            return answer;
        }
    }
}
