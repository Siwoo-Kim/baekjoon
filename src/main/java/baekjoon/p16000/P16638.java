package baekjoon.p16000;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class P16638 {
    private static final Scanner scanner = new Scanner(
            new BufferedReader(new InputStreamReader(System.in)));
    private static int M, N;
    private static List<Element> elements = new ArrayList<>();

    private static class Element {
        int type;
        int value;

        public Element(int type, int value) {
            this.type = type;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        N = Integer.parseInt(scanner.nextLine());
        M = N/2;
        String line = scanner.nextLine();
        for (int i=0; i<N; i++) {
            char c = line.charAt(i);
            if (Character.isDigit(c))
                elements.add(new Element(0, c - '0'));
            else {
                int op = 1;
                if (c == '-')
                    op = 2;
                if (c == '*')
                    op = 3;
                elements.add(new Element(op, -1));
            }
        }
        int answer = select(0, new Stack<>());
        System.out.println(answer);
    }

    private static int select(int index, Stack<Integer> indexes) {
        if (index >= M) {
            Set<Integer> visit = new HashSet<>(indexes);
            List<Element> a = deepCopy(elements);
            for (int i=0; i<M; i++) {
                if (visit.contains(i)) {
                    int k = 2 * i + 1;
                    if (a.get(k).type == 1) {
                        a.get(k - 1).value += a.get(k + 1).value;
                        a.get(k).type = -1;
                        a.get(k + 1).value = 0;
                    }
                    if (a.get(k).type == 2) {
                        a.get(k - 1).value -= a.get(k + 1).value;
                        a.get(k).type = -1;
                        a.get(k + 1).value = 0;
                    }
                    if (a.get(k).type == 3) {
                        a.get(k - 1).value *= a.get(k + 1).value;
                        a.get(k).type = -1;
                        a.get(k + 1).value = 0;
                    }
                }
            }
            List<Element> b = new ArrayList<>();
            for (int i=0; i<N; i++) {
                if (a.get(i).type == 0) {
                    b.add(a.get(i));
                } else {
                    if (a.get(i).type == -1) {
                        i++;
                    } else {
                        if (a.get(i).type == 3) {
                            int value = b.get(b.size()-1).value * a.get(i+1).value;
                            b.remove(b.size()-1);
                            b.add(new Element(0, value));
                            i++;
                        } else {
                            b.add(a.get(i));
                        }
                    }
                }
            }
            Element[] elements = b.toArray(new Element[b.size()]);
            int m = (elements.length-1)/2;
            int sum = elements[0].value;
            for (int i=0; i<m; i++) {
                int k = 2*i+1;
                sum = exp(sum, elements[k+1].value, elements[k].type);
            }
            return sum;
        }
        indexes.add(index);
        int r1 = select(index+2, indexes);
        indexes.pop();
        int r2 = select(index+1, indexes);
        return Math.max(r1, r2);
    }

    private static List<Element> deepCopy(List<Element> elements) {
        List<Element> copy = new ArrayList<>();
        for (int i=0; i<elements.size(); i++) {
            Element element = elements.get(i);
            copy.add(new Element(element.type, element.value));
        }
        return copy;
    }

    private static int exp(int v1, int v2, int op) {
        if (op == 1)
            return v1 + v2;
        if (op == 2)
            return v1 - v2;
        if (op == 3)
            return v1 * v2;
        throw new RuntimeException();
    }
}
