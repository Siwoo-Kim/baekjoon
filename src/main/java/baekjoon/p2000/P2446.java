package baekjoon.p2000;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class P2446 {
    private static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    private static Scanner scanner = new Scanner(System.in);
    private static int N;

    public static void main(String[] args) throws IOException {
        N = scanner.nextInt() * 2 - 1;
        if (N == 1) {
            System.out.print('*');
            return;
        }
        print(N);
        writer.flush();
    }

    private static void print(int star) throws IOException {
        if (star < 0) return;
        int blank = (N - star) / 2;
        for (int i=0; i<blank; i++)
            writer.write(' ');
        for (int i=0; i<star; i++)
            writer.write('*');
        writer.write('\n');
        print(star-2);
        if (star != 1) {
            for (int i = 0; i < blank; i++)
                writer.write(' ');
            for (int i = 0; i < star; i++)
                writer.write('*');
            if (star != N)
                writer.write('\n');
        }
    }
}
