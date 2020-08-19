package baekjoon.p10000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P11720 {
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        reader.readLine();
        String line = reader.readLine();
        int sum = 0;
        for (int i=0; i<line.length(); i++)
            sum += line.charAt(i) - '0';
        System.out.println(sum);
    }
}
