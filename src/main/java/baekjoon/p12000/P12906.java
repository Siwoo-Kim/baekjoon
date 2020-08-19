package baekjoon.p12000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P12906 {
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Map<State, Integer> count = new HashMap<>();

    public static void main(String[] args) throws IOException {
        State state = new State();
        for (int i=0; i<3; i++) {
            StringTokenizer token = new StringTokenizer(reader.readLine());
            int N = Integer.parseInt(token.nextToken());
            Plate plate = new Plate();
            if (token.hasMoreTokens()) {
                String dishes = token.nextToken();
                for (int j = 0; j < N; j++) {
                    plate.add(dishes.charAt(j));
                }
            }
            state.plates.put((char) ('A' + i), plate);
        }

        Queue<State> q = new LinkedList<>();
        q.add(state);
        count.put(state, 0);
        while (!q.isEmpty()) {
            State s = q.poll();
            if (s.verify()) {
                System.out.println(count.get(s));
                return;
            }
            for (char from='A'; from<='C'; from++)
                for (char to='A'; to<='C'; to++) {
                    if (from == to) continue;
                    if (s.plates.get(from).stack.isEmpty())
                        continue;
                    State w = s.clone();
                    w.get(to).add(w.get(from).pop());
                    if (count.containsKey(w)) continue;
                    count.put(w, count.get(s) + 1);
                    q.add(w);
                }
        }

    }

    private static class State {
        private Map<Character, Plate> plates = new HashMap<>();

        public Plate get(char c) {
            return plates.get(c);
        }

        public State clone() {
            Map<Character, Plate> copy = new HashMap<>();
            for (Character k: plates.keySet())
                copy.put(k, plates.get(k).clone());
            State state = new State();
            state.plates = copy;
            return state;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            State state = (State) o;
            return Objects.equals(plates, state.plates);
        }

        @Override
        public int hashCode() {
            return Objects.hash(plates);
        }

        public boolean verify() {
            for (char key: plates.keySet()) {
                if (plates.get(key).stack.isEmpty()) continue;
                if (plates.get(key).stack.stream()
                        .anyMatch(c -> key != c))
                    return false;
            }
            return true;
        }
    }

    private static class Plate {
        LinkedList<Character> stack = new LinkedList<>();

        public void add(char c) {
            stack.push(c);
        }

        public char pop() {
            return stack.pop();
        }

        public Plate clone() {
            Plate p = new Plate();
            p.stack = new LinkedList<>(stack);
            return p;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Plate plate = (Plate) o;
            return Objects.equals(stack, plate.stack);
        }

        @Override
        public int hashCode() {
            return Objects.hash(stack);
        }
    }
}
