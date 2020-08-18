package baekjoon.p2000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P2251 {

    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int A, B, C;
    private static PriorityQueue<Integer> pq = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        A = Integer.parseInt(token.nextToken());
        B = Integer.parseInt(token.nextToken());
        C = Integer.parseInt(token.nextToken());

        Node start = new Node(new Basket(A, 0), new Basket(B, 0), new Basket(C, C));

        Queue<Node> q = new LinkedList<>();
        Set<Node> visit = new HashSet<>();
        q.add(start);
        while (!q.isEmpty()) {
            Node v = q.poll();
            if (v.baskets[0].value == 0) {
                if (!pq.contains(v.baskets[2].value))
                    pq.add(v.baskets[2].value);
            }
            Node node;
            Basket first, second, third;
            if (v.baskets[0].value != 0) {
                first = v.baskets[0].clone();
                second = v.baskets[1].clone();
                third = v.baskets[2].clone();
                first.sendTo(second);
                node = new Node(first, second, third);
                if (!visit.contains(node)) {
                    visit.add(node);
                    q.add(node);
                }
                first = v.baskets[0].clone();
                second = v.baskets[1].clone();
                third = v.baskets[2].clone();
                first.sendTo(third);
                node = new Node(first, second, third);
                if (!visit.contains(node)) {
                    visit.add(node);
                    q.add(node);
                }
            }
            if (v.baskets[1].value != 0) {
                first = v.baskets[0].clone();
                second = v.baskets[1].clone();
                third = v.baskets[2].clone();
                second.sendTo(first);
                node = new Node(first, second, third);
                if (!visit.contains(node)) {
                    visit.add(node);
                    q.add(node);
                }
                first = v.baskets[0].clone();
                second = v.baskets[1].clone();
                third = v.baskets[2].clone();
                second.sendTo(third);
                node = new Node(first, second, third);
                if (!visit.contains(node)) {
                    visit.add(node);
                    q.add(node);
                }
            }
            if (v.baskets[2].value != 0) {
                first = v.baskets[0].clone();
                second = v.baskets[1].clone();
                third = v.baskets[2].clone();
                third.sendTo(first);
                node = new Node(first, second, third);
                if (!visit.contains(node)) {
                    visit.add(node);
                    q.add(node);
                }
                first = v.baskets[0].clone();
                second = v.baskets[1].clone();
                third = v.baskets[2].clone();
                third.sendTo(second);
                node = new Node(first, second, third);
                if (!visit.contains(node)) {
                    visit.add(node);
                    q.add(node);
                }
            }
        }
        while (!pq.isEmpty())
            System.out.print(pq.poll() + " ");
    }

    private static class Basket implements Cloneable {
        int capacity;
        int value;

        public Basket(int capacity, int x) {
            this.capacity = capacity;
            this.value = x;
        }

        @Override
        protected Basket clone() {
            return new Basket(capacity, value);
        }

        public void sendTo(Basket basket) {
            basket.value += this.value;
            this.value = 0;
            if (basket.value > basket.capacity) {
                this.value = basket.value - basket.capacity;
                basket.value = basket.capacity;
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Basket basket = (Basket) o;
            return capacity == basket.capacity &&
                    value == basket.value;
        }

        @Override
        public int hashCode() {
            return Objects.hash(capacity, value);
        }
    }

    private static class Node {
        Basket[] baskets;

        public Node(Basket... baskets) {
            this.baskets = baskets;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return Arrays.equals(baskets, node.baskets);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(baskets);
        }
    }

}
