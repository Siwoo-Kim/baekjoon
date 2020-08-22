package baekjoon.p1000;

import util.Tip;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Tip("2 진수를 3 자리씩 끊으면, 8 진수를 만들 수 있다.")
public class P1212 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        String octal = reader.readLine();
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<octal.length(); i++) {
            int o = octal.charAt(i) - '0';
            StringBuilder s = new StringBuilder(Integer.toBinaryString(o));
            if (i != 0) {
                while (s.length() != 3) {
                    s.insert(0, "0");
                }
            }
            sb.append(s);
        }
        System.out.println(sb.toString());
    }

}
