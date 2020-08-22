package util;

public class Mathematics {

    public static long nCr(int n, int r) {
        return factorial(n) / (factorial(n-r) * factorial(r));
    }

    private static long factorial(int n) {
        if (n == 0) return 1;
        return factorial(n-1) * n;
    }

    public static void main(String[] args) {
        System.out.println(nCr(5, 3));
    }
}
