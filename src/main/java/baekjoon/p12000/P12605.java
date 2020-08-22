package baekjoon.p12000;

import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class P12605 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int N;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        Stack<String> stack = new Stack<>();
        for (int i=0; i<N; i++) {
            StringTokenizer token = new StringTokenizer(reader.readLine());
            while (token.hasMoreTokens())
                stack.push(token.nextToken());
            writer.write(String.format("Case #%d: ", i+1));
            while (!stack.isEmpty()) {
                writer.write(stack.pop());
                writer.write(" ");
            }
            writer.write("\n");
        }
        writer.flush();
    }

}
