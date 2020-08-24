package forfun;

/**
 * nPr = N 개에서 R개 (0<R<=N) 을 택하여 순서대로 나열.
 *      n * (n-1) * (n-2) ... (n-r+1)
 *      n! / (n-r)!
 * n! = n의 계승
 *   1*2*..(n-2)(n-1) = n!
 *   0! = 1
 *   nPn = n!
 *   nPr = n!/(n-r)!
 *      n(n-1)(n-2)...n(n-r+1)*(n-r)..1 / n(n-r)*(n-r-1)...1
 *
 */
public class Permutation {

    public static long nPr(int n, int r) {
        return factorial(n) / (factorial(n - r));
    }

    private static long factorial(int n) {
        if (n == 0) return 1;
        return factorial(n-1) * n;
    }

    public static void main(String[] args) {
        // 서로 다른 5장의 카드 중 3장을 택하여 나열 5P3
        System.out.println(nPr(5, 3));
        // 학생 20명인 학급에서 회장, 부회장 20P2
        System.out.println(nPr(20, 2));
    }
}
