package baekjoon.p9000;

import java.io.*;
import java.util.*;

public class P9935 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        String line = reader.readLine();
        String explosion = reader.readLine();
        Stack<Pair> stack = new Stack<>();
        boolean singleChar = explosion.length() == 1;
        boolean[] visit = new boolean[line.length()];
        for (int i=0; i<line.length(); i++) {
            char c = line.charAt(i);
            if (singleChar && explosion.charAt(0) == c) {
                visit[i] = true;
             } else {
                if (explosion.charAt(0) == c) {
                    stack.push(new Pair(i, 0));
                } else {
                    if (!stack.isEmpty()) {
                        Pair pair = stack.peek();
                        char nextChar = explosion.charAt(pair.expIndex+1);
                        if (nextChar == c) {
                            stack.push(new Pair(i, pair.expIndex+1));
                            if (nextChar == explosion.charAt(explosion.length()-1)) {
                                int size = explosion.length();
                                while (size != 0) {
                                    visit[stack.pop().index] = true;
                                    size--;
                                }
                            }
                        } else {
                            stack = new Stack<>();
                        }
                    }
                }
            }
        }
        boolean flura = true;
        for (int i=0; i<line.length(); i++) {
            if (!visit[i]) {
                flura = false;
                writer.write(line.charAt(i));
            }
        }
        if (flura)
            System.out.println("FRULA");
        else
            writer.flush();
    }

    private static class Pair {
        int index, expIndex;

        public Pair(int index, int expIndex) {
            this.index = index;
            this.expIndex = expIndex;
        }
    }
}
