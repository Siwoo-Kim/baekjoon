package programmers.kakao2019;

import java.util.*;

public class FunctionFinish {

    static class Solution {
        public int[] solution(int[] P, int[] S) {
            Deque<Integer> dq = new LinkedList<>();
            int size = 1;
            dq.push(deployment(0, P, S));
            for (int i=1; i<P.length; i++) {
                int pd = dq.peek();
                int d = deployment(i, P, S);
                if (pd >= d) {
                    dq.push(pd);
                } else {
                    size++;
                    dq.push(d);
                }
            }
            int[] answers = new int[size];
            int index = 0;
            while (!dq.isEmpty()) {
                answers[index] = 1;
                int d = dq.pollLast();
                while (!dq.isEmpty() && dq.peekLast() == d) {
                    dq.pollLast();
                    answers[index]++;
                }
                index++;
            }
            return answers;
        }

        private int deployment(Integer index, int[] P, int[] S) {
            return (int) Math.ceil((100.0 - P[index]) / S[index]);
        }
    }
}
