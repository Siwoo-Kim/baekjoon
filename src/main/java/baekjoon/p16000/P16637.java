package baekjoon.p16000;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class P16637 {
    private static final Scanner scanner = new Scanner(
            new BufferedReader(new InputStreamReader(System.in)));
    private static long[] numbers;
    private static char[] ops;
    private static boolean[] visit;

    public static void main(String[] args) {
        int N = Integer.parseInt(scanner.nextLine());
        String line = scanner.nextLine();
        numbers = new long[N/2+1];
        ops = new char[N/2];
        visit = new boolean[N/2];
        int numIndex = 0, opIndex = 0;
        for (int i=0; i<N; i++) {
            char c = line.charAt(i);
            if (Character.isDigit(c))
                numbers[numIndex++] = Integer.parseInt(Character.toString(c));
            else
                ops[opIndex++] = c;
        }
        long answer = select(0);
        System.out.println(answer);
    }

    private static long select(int index) {
        if (index >= ops.length) {
            long[] operands = Arrays.copyOf(numbers, numbers.length);
            for (int i=1; i<operands.length; i++) {
                if (visit[i-1]) {
                    operands[i-1] = calc(operands[i-1], operands[i], ops[i-1]);
                    operands[i] = 0;
                }
            }
            long sum = operands[0];
            for (int i=1; i<operands.length; i++)
                if (visit[i-1])
                    sum = calc(sum, operands[i], '+');
                else
                    sum = calc(sum, operands[i], ops[i-1]);
            return sum;
        }
        long r1, r2;
        visit[index] = true;
        r1 = select(index + 2);
        visit[index] = false;
        r2 = select(index + 1);
        return Math.max(r1, r2);
    }

    private static long calc(long i, long j, char op) {
        if (op == '+')
            return i + j;
        if (op == '-')
            return i - j;
        if (op == '*')
            return i * j;
        if (op == '/')
            return i / j;
        throw new UnsupportedOperationException();
    }
}
