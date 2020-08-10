package p16000;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

import static java.util.stream.Collectors.toList;

public class P16936 {
    private static final Scanner scanner = new Scanner(
            new BufferedReader(new InputStreamReader(System.in)));
    private static List<Long> A;

    /*
        나3: x를 3으로 나눈다. x는 3으로 나누어 떨어져야 한다.
        곱2: x에 2를 곱한다.

        invariant
        1. 3의 배수는 항상 앞에 온다.
        2. 약수로 3 개수가 많을 수록 앞에 온다.
        3. 3의 배수가 아니면서 2의 배수인 원소는 항상 3의 배수 뒤에 오면서 오름차순.

        //4 8 6 3 12 9
        //2*2 2*2*2 3*2 3 3*2*2 3*3
        //3*3 -> 3 -> 3*2 -> 3*2*2 -> 2*2 -> 2*2*2
     */
    // O(NlogN)
    public static void main(String[] args) {
        A = Arrays.stream(scanner.nextLine().split(" "))
                .map(Long::parseLong)
                .collect(toList());
        A.sort(new DivideThreeMultiplyTwo());
        for (long a: A)
            System.out.print(a + " ");
    }

    private static class DivideThreeMultiplyTwo implements Comparator<Long> {

        @Override
        public int compare(Long i, Long j) {
            if (i % 3 == 0 && j % 3 == 0) {
                int icnt = 0, jcnt = 0;
                while (i % 3 == 0) {
                    i /= 3;
                    icnt++;
                }
                while (j % 3 == 0) {
                    j /= 3;
                    jcnt++;
                }
                if (icnt == jcnt)
                    return Long.compare(i, j);
                else
                    return Long.compare(jcnt, icnt);
            }
            if (i % 3 == 0 || j % 3 == 0) {
                return i % 3 == 0? -1: 1;
            }
            return Long.compare(i, j);
        }
    }
}
