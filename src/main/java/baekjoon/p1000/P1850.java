package baekjoon.p1000;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class P1850 {
    private static Scanner scanner = new Scanner(System.in);
    private static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public static long gcd(long a, long b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    public static void main(String[] args) throws IOException {
        long answer = gcd(scanner.nextLong(), scanner.nextLong());
        for (int i=0; i<answer; i++)
            writer.write(1 + "");
        writer.flush();
    }
}
