package programmers.kakao2019;

import java.util.*;
import java.util.stream.IntStream;

public class CraneCraw {

    public static class Solution {
        private int N, M, answer;
        private List<Stack<Integer>> dolls = new ArrayList<>();
        private Stack<Integer> basket = new Stack<>();

        public int solution(int[][] board, int[] moves) {
            N = board.length;
            M = board[0].length;
            create(board);
            for (int i=0; i<moves.length; i++) {
                Stack<Integer> stack = dolls.get(moves[i]-1);
                if (stack.isEmpty())
                    continue;
                int doll = stack.pop();
                if (!basket.isEmpty()
                        && basket.peek() == doll) {
                    answer+=2;
                    basket.pop();
                } else
                    basket.add(doll);
            }
            return answer;
        }

        private void create(int[][] board) {
            IntStream.range(0, M).forEach(i -> dolls.add(new Stack<>()));
            for (int i=N-1; i>=0; i--) {
                for (int j = 0; j < M; j++) {
                    if (board[i][j] != 0)
                        dolls.get(j).add(board[i][j]);
                }
            }
        }
    }
}
