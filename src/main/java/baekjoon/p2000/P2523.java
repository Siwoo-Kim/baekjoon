package baekjoon.p2000;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class P2523 {

    public static void main(String[] args) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = new Scanner(System.in).nextInt();
        for (int i=0; i<N; i++) {
            for (int j=0; j<=i; j++)
                writer.write('*');
            writer.write('\n');
        }
        for (int i=N-1; i>0; i--) {
            for (int j=0; j<i; j++)
                writer.write('*');
            writer.write('\n');
        }
        writer.flush();
    }
}
