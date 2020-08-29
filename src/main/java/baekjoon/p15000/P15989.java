package baekjoon.p15000;

import util.Algorithm;
import util.Tip;
import util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Used(Algorithm.DP)
@Tip("Permutation which contains same elements. (remove duplication)" +
    "Making an order first. 1 + ... + 2 ... + 3 .... " +
    "D[1] ~ D[N] by 1 + " + 
    "D[1] ~ D[N] by 2 + " + 
    "D[1] ~ D[N] by 3"
)
public class P15989 {
    private static final int MAX = 10001;
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int T;
    private static int[] D;
    
    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(reader.readLine());
        D = new int[MAX+1];
        int[] nums = {1, 2, 3};
        D[0] = 1;
        for (int n: nums) {
            for (int i = 1; i <= MAX; i++) {
                if (i - n >= 0)
                    D[i] += D[i - n];
            }
        }
        for (int i=0; i<T; i++)
            System.out.println(D[Integer.parseInt(reader.readLine())]);
    }
}
