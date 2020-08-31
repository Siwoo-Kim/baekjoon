package forfun;

/**
 * Sequence - 일정한 "규칙"에 따라 차례대로 나열된 수.
 *  수열도 함수다.
 *  
 *  등차수열 (ArithmeticSequence) - 첫 항부터 차례대로 일정한 수를 더하여 만든 수열.
 *  공차: 등차수열에서 더해지는 일정한 수 N = N-1 + D
 *  일반항: An = A1 + (N-1)D
 *  등차수열의 합: N(A1 + An) / 2.
 *  
 */
public abstract class ArithmeticSequence {
    private final int start;
    
    public ArithmeticSequence(int start) {
        this.start = start;
    }

    //공차
    abstract int commonDifference();
    
    //일반항
    @Override
    public String toString() {
        return String.format("%d+(n-1)%d", start, commonDifference());
    }
    
    public int nth(int n) {
        if (n == 1) return start;
        return nth(n - 1) + commonDifference();
    }
    
    public int sum(int n) {
//        if (n == 0) return 0;
//        return sum(n-1) + nth(n);
        return n * (nth(1) + nth(n)) / 2;
    } 
    
    // n = n - 1 + d
    public static ArithmeticSequence newSequence(int start, int prev, int next) {
        int commonDifference = next - prev;
        return new ArithmeticSequence(start) {
            @Override
            int commonDifference() {
                return commonDifference;
            }
        };
    }

    public static void main(String[] args) {
        ArithmeticSequence sequence = new ArithmeticSequence(1) {
            @Override
            int commonDifference() {
                return 3;
            }
        };
        int sum = 0;
        for (int i=1; i<=5; i++) {
            sum += sequence.nth(i);
            System.out.println(sequence.nth(i));
        }
        System.out.println(sum);
        System.out.println(sequence.sum(5));
        sequence = ArithmeticSequence.newSequence(1, 7, 10);
        for (int i=1; i<=5; i++)
            System.out.println(sequence.nth(i));
        System.out.println(sequence);
    }
}
