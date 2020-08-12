package baekjoon.p16000;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Stack;

public class P16968 {
    private static final Scanner scanner = new Scanner(
            new BufferedReader(new InputStreamReader(System.in)));
    private static String plate;

    public static void main(String[] args) {
        plate = scanner.nextLine();
        int answer = select(0, new Stack<>());
        System.out.println(answer);
    }

    //O(N)
    private static int select(int non, Stack<Object> non2) {
        int answer = 1;
        for (int i=0; i<plate.length(); i++) {
            int c = plate.charAt(i) == 'd'? 10: 26;
            //ccc -> 26 * 25 * 25
            if (i > 0 && plate.charAt(i-1) == plate.charAt(i))
                c--;
            answer = answer * c;
        }
        return answer;
    }

    //O(26^4)
//    private static int select(int cnt, Stack<Character> stack) {
//        if (cnt == plate.length())
//            return 1;
//        char c = plate.charAt(cnt);
//        char last = stack.isEmpty()? 0: stack.peek();
//        int result = 0;
//        if (c == 'd') {
//            for (char i='0'; i<='9'; i++) {
//                if (last == i) continue;
//                stack.push(i);
//                result += select(cnt+1, stack);
//                stack.pop();
//            }
//        } else {
//            for (char i='A'; i<='Z'; i++) {
//                if (last == i) continue;
//                stack.push(i);
//                result += select(cnt+1, stack);
//                stack.pop();
//            }
//        }
//        return result;
//    }
}
