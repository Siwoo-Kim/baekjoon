package baekjoon.p2000;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class P2800 {
    private static final Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
    private static String line;
    private static List<Pair> pairs = new ArrayList<>();
    private static List<String> result = new ArrayList<>();

    public static void main(String[] args) {
        line = scanner.nextLine();
        Stack<Pair> stack = new Stack<>();
        for (int i=0; i<line.length(); i++) {
            char c = line.charAt(i);
            if (c == '(')
                stack.push(new Pair(i, -1));
            else if (c == ')') {
                Pair p = stack.pop();
                p.y = i;
                pairs.add(p);
            }
        }
        select(0, new Stack<>());
        Collections.sort(result);
        for (String s: result)
            System.out.println(s);
    }

    private static void select(int index, Stack<Integer> stack) {
        if (index == pairs.size()) {
            if (!stack.isEmpty()) {
                Set<Integer> set = new HashSet<>();
                for (int i: stack) {
                    set.add(pairs.get(i).x);
                    set.add(pairs.get(i).y);
                }
                StringBuilder sb = new StringBuilder();
                for (int i=0; i<line.length(); i++) {
                    if (set.contains(i)) continue;
                    sb.append(line.charAt(i));
                }
                if (!result.contains(sb.toString()))
                    result.add(sb.toString());
            }
            return;
        }
        stack.push(index);
        select(index+1, stack);
        stack.pop();
        select(index+1, stack);
    }

    private static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
