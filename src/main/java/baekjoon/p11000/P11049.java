package baekjoon.p11000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P11049 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static Matrix[] matrix;
    private static int[][] D; 

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        D = new int[N][N];
        matrix = new Matrix[N];
        for (int i=0; i<N; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            matrix[i] = new Matrix(
                    Integer.parseInt(tokenizer.nextToken()), 
                    Integer.parseInt(tokenizer.nextToken()));
            Arrays.fill(D[i], -1);
        }
        int answer = merge(0, N-1);
        System.out.println(answer);
    }

    private static int merge(int from, int to) {
        if (from == to) return 0;
        if (from+1 == to) return matrix[from].multiply(matrix[to]);  
        if (D[from][to] != -1) return D[from][to];
        int sum = Integer.MAX_VALUE;
        for (int k=from; k<=to-1; k++) {
            int t1 = merge(from, k);
            int t2 = merge(k+1, to);
            int value = matrix[from].n * matrix[k].m * matrix[to].m;
            sum = Math.min(t1 + t2 + value, sum);
        }
        return D[from][to] = sum;
    }

    private static class Matrix {
        int n, m;

        public Matrix(int n, int m) {
            this.n = n;
            this.m = m;
        }
        
        public int multiply(Matrix matrix) {
            return n * m * matrix.m;
        }
    }
}
