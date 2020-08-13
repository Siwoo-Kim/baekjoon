package baekjoon.p15000;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

import static java.util.stream.Collectors.toList;

public class P15686 {
    private static final Scanner scanner = new Scanner(
            new BufferedReader(new InputStreamReader(System.in)));
    private static int N, M;
    private static List<Point> chickens = new ArrayList<>();
    private static List<Point> houses = new ArrayList<>();

    public static void main(String[] args) {
        N = scanner.nextInt();
        M = scanner.nextInt();
        scanner.nextLine();
        for (int i=0; i<N; i++) {
            int[] points = Arrays.stream(scanner.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            for (int j=0; j<N; j++) {
                if (points[j] == 1)
                    houses.add(new Point(i, j));
                else if (points[j] == 2)
                    chickens.add(new Point(i, j));
            }
        }
        int answer = traverse(0, new Stack<>());
        System.out.println(answer);
    }

    private static int traverse(int index, Stack<Integer> stack) {
        if (stack.size() == M) {
            List<Point> chicks = stack.stream().map(i -> chickens.get(i)).collect(toList());
            int sum = 0;
            for (Point h: houses) {
                int distance = Integer.MAX_VALUE;
                for (Point c: chicks)
                    distance = Math.min(distance, Math.abs(c.x - h.x) + Math.abs(c.y - h.y));
                sum += distance;
            }
            return sum;
        }
        if (index == chickens.size()) return Integer.MAX_VALUE;
        int result;
        stack.add(index);
        result = traverse(index+1, stack);
        stack.pop();
        return Math.min(result, traverse(index+1, stack));
    }

    private static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
