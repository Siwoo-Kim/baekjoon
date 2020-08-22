package programmers;

import java.util.Arrays;
import java.util.Stack;

public class StockPrice {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] answer =  solution.solution(new int[]{1, 2, 3, 2, 3});
        System.out.println(Arrays.toString(answer));
    }

    static class Solution {
        public int[] solution(int[] prices) {
            int[] answers = new int[prices.length];
            Stack<Pair> stack = new Stack<>();
            for (int i=0; i<prices.length; i++) {
                if (stack.isEmpty()) {
                    stack.push(new Pair(i, prices[i]));
                } else {
                    while (!stack.isEmpty() &&
                            stack.peek().price > prices[i]) {
                        Pair top = stack.pop();
                        answers[top.index] = i - top.index;
                    }
                    stack.push(new Pair(i, prices[i]));
                }
            }
            while (!stack.isEmpty()) {
                Pair pair = stack.pop();
                answers[pair.index] = prices.length - 1 - pair.index;
            }
            return answers;
        }

        private static class Pair {
            final int index, price;

            public Pair(int index, int price) {
                this.index = index;
                this.price = price;
            }
        }
    }

}
