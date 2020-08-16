package baekjoon.p2000;

import java.io.*;
import java.util.Scanner;

public class P2438 {
    private static final Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
    private static final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = scanner.nextInt();
        for (int i=0; i<N; i++) {
            for (int j = 0; j <= i; j++)
                writer.write("*");
            writer.write("\n");
        }
        writer.flush();
    }
}
