package baekjoon.p4000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class P4949 {
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Map<Character, Character> pairs = new HashMap<>();

    static  {
        pairs.put('[', ']');
        pairs.put('(', ')');
    }

    public static void main(String[] args) throws IOException {
        while (true) {
            String line = reader.readLine();
            if (line.equals("."))
                return;
            Stack<Character> stack = new Stack<>();
            boolean ok = true;
            for (int i=0; i<line.length(); i++) {
                char c = line.charAt(i);
                if (c == '(' || c == '[')
                    stack.push(c);
                else if (c == ')' || c == ']') {
                    if (stack.isEmpty()) {
                        ok = false;
                        break;
                    }
                    char openBracket = stack.pop();
                    if (pairs.get(openBracket) != c) {
                        ok = false;
                        break;
                    }
                }
            }
            if (ok && stack.isEmpty())
                System.out.println("yes");
            else
                System.out.println("no");
        }
    }
}
