package baekjoon.p11000;

import util.Algorithm;
import util.Tip;
import util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Used(Algorithm.DP)
@Tip("Case. Button 1, 2, 3, 4" +
    "Button 2 - 3 - 4 go together." +
    "Ctrl-V 인 경우, 버퍼에 저장된 값이 화면에 출력. (a + number of clicking Button 4)" +
    "D[i] 가 i 번째 버튼을 눌렀을 때 최대 A 의 갯수, j 을 Button 2 - 3 - 4 을 누를시 Button 4 을 누른 갯수라면" +
    "D[i] = max(D[i-1] + 1, D[i-(j+2)]x(j+1)) 단 (1<=j<i-3)")
public class P11058 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static long[] D;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        D = new long[N+1];
        for (int i=1; i<=N; i++) {
            D[i] = D[i-1] +1;
            for (int b=1; b<=i-3; b++) {
                D[i] = Math.max(D[i-b-2] * (b + 1), D[i]);
            }
        }
        System.out.println(D[N]);
    }
}
