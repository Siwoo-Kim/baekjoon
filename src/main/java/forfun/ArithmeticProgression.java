package forfun;

public abstract class ArithmeticProgression {
    
    abstract int start();
    
    abstract int commonDifference();
    
    public int nth(int x) {
        return start() + ((x - 1) * commonDifference());
    }
    
    public int sum(int n) {
        //return n * (nth(1) + nth(n)) / 2;
        return n * (2 * nth(1) + (n - 1) * commonDifference()) / 2;
    }

    public static void main(String[] args) {
        ArithmeticProgression sequence = new ArithmeticProgression() {
            @Override
            int start() {
                return 1;
            }
            @Override
            int commonDifference() {
                return 2;
            }
        };
        int sum = 0;
        for (int i=1; i<=150; i++) {
            System.out.println(sequence.nth(i));
            sum += sequence.nth(i);
        }
        int sum2 = sequence.sum(150);
        System.out.println(sum);
        System.out.println(sum2);
    }
}
