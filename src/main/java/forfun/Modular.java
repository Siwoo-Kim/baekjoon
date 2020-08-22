package forfun;

public class Modular {

    public static void main(String[] args) {
        int M = 11, B=12;
        for (int A=2; A<100; A++) {
            System.out.printf("(%d + %d) %% %d = %d%n", A, B, M, (A+B)%M);
            System.out.printf("((%d %% %d) + (%d %% %d)) %% %d = %d%n",
                    A, M, B, M, M, (((A%M) + (B%M))%M));
        }
    }
}
