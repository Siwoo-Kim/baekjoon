package baekjoon.p3000;

import java.io.*;
import java.util.LinkedList;

public class P3111 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    private static String M1, M2, S;

    public static void main(String[] args) throws IOException {
        M1 = reader.readLine();
        M2 = new StringBuilder(M1).reverse().toString();
        S = reader.readLine();

        int left = 0, right = S.length()-1;
        LinkedList<Character> leftStack = new LinkedList<>(),
                rightStack = new LinkedList<>();
        boolean turn = true;
        while (left <= right) {
            if (turn)
                leftStack.push(S.charAt(left++));
            else
                rightStack.push(S.charAt(right--));
            if (verify(turn? leftStack: rightStack, turn? M1: M2)) {
                turn = !turn;
            }
        }
        while (!leftStack.isEmpty()) {
            rightStack.push(leftStack.pop());
            verify(rightStack, M2);
        }

        while (!rightStack.isEmpty())
            writer.write(rightStack.pop());
        writer.flush();
    }

    private static boolean verify(LinkedList<Character> stack, String M) {
        if (stack.size() < M.length()) return false;
        for (int i=0; i<M.length(); i++) {
            char c = stack.get(M.length()-1-i);
            if (c != M.charAt(i))
                return false;
        }
        int size = M.length();
        while (size != 0) {
            stack.pop();
            size--;
        }
        return true;
    }
}
