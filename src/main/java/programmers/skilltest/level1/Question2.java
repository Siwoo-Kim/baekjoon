package programmers.skilltest.level1;

import java.util.Arrays;

public class Question2 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().solution(new int[]{4, 3, 2, 1})));
        System.out.println(Arrays.toString(new Solution().solution(new int[]{10})));
        System.out.println(Arrays.toString(new Solution().solution(new int[]{3, 4, 9, -1})));
    }

    static class Solution {
        public int[] solution(int[] arr) {
            if (arr.length == 1) return new int[]{-1};
            int[] answer = new int[arr.length - 1];
            int min = Arrays.stream(arr).min().getAsInt();
            for (int i = 0, index = 0; i < arr.length; i++) {
                if (min == arr[i]) continue;
                answer[index++] = arr[i];
            }
            return answer;
        }
    }
}
