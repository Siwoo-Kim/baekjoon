package p2000;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class P2503 {

    private static final Scanner scanner = new Scanner(
            new BufferedReader(new InputStreamReader(System.in)));
    private static List<Question> questions = new ArrayList<>();
    private static boolean[] visit = new boolean[10];

    // O(9P3) = 504
    public static void main(String[] args) {
        int N = Integer.parseInt(scanner.nextLine());
        for (int i=0; i<N; i++) {
            String[] line = scanner.nextLine().split(" ");
            Question q = new Question(toInts(line[0]), Integer.parseInt(line[1]), Integer.parseInt(line[2]));
            questions.add(q);
        }
        int result = select(new Stack<>());
        System.out.println(result);
    }

    private static int select(Stack<Integer> stack) {
        if (stack.size() == 3)
            return check(stack);
        int result = 0;
        for (int i=1; i<10; i++) {
            if (!visit[i]) {
                visit[i] = true;
                stack.push(i);
                result += select(stack);
                stack.pop();
                visit[i] = false;
            }
        }
        return result;
    }

    private static int check(Stack<Integer> stack) {
        int[] answer = stack.stream().mapToInt(i -> i).toArray();
        for (Question q: questions) {
            int strike = 0, ball = 0;
            for (int i=0; i<3; i++) {
                int val = answer[i];
                if (val == q.nums[i])
                    strike++;
                else if (Arrays.stream(q.nums).anyMatch(pq -> val == pq))
                    ball++;
            }
            if (!(strike == q.strike) || !(ball == q.ball))
                return 0;
        }
        return 1;
    }

    private static int[] toInts(String s) {
        int[] ints = new int[3];
        for (int i=0; i<3; i++)
            ints[i] = Integer.parseInt(Character.toString(s.charAt(i)));
        return ints;
    }

    private static class Question {
        private int[] nums;
        private int strike, ball;

        public Question(int[] nums, int strike, int ball) {
            this.nums = nums;
            this.strike = strike;
            this.ball = ball;
        }
    }
}
