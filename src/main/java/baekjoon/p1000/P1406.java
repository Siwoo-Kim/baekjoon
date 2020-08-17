package baekjoon.p1000;

import java.io.*;
import java.util.Stack;

public class P1406 {
    private static final BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        Stack<Character> left = new Stack<>();
        Stack<Character> right = new Stack<>();
        String line = scanner.readLine();
        for (int i=0; i<line.length(); i++)
            left.push(line.charAt(i));
        int N = Integer.parseInt(scanner.readLine());
        for (int i=0; i<N; i++) {
            String[] commands = scanner.readLine().split("\\s+");
            String command = commands[0];
            if ("P".equals(command)) {
                left.push(commands[1].charAt(0));
            } else if ("L".equals(command)) {
                if (!left.isEmpty())
                    right.push(left.pop());
            } else if ("D".equals(command)) {
                if (!right.isEmpty())
                    left.push(right.pop());
            } else if ("B".equals(command)) {
                if (!left.isEmpty())
                    left.pop();
            }
        }
        while (!left.isEmpty())
            right.push(left.pop());
        while (!right.isEmpty())
            writer.write(right.pop());
        writer.flush();
    }
}
