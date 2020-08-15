package baekjoon.p16000;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class P16987 {
    private static final Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
    private static int N;
    private static Egg[] E;

    //O(N^N)
    public static void main(String[] args) {
        N = scanner.nextInt();
        E = new Egg[N];
        for (int i=0; i<N; i++)
            E[i] = new Egg(scanner.nextInt(), scanner.nextInt());
        int answer = bruteForce(0);
        System.out.println(answer);
    }

    private static int bruteForce(int index) {
        if (index == N) {
            int cnt = 0;
            for (int i=0; i<N; i++)
                if (E[i].hp <= 0)
                    cnt++;
            return cnt;
        }
        int answer = 0;
        if (E[index].hp <= 0)
            return bruteForce(index+1);
        else {
            for (int i = 0; i < N; i++) {
                if (i == index) continue;
                if (E[i].hp >= 0) {
                    E[i].hp -= E[index].weight;
                    E[index].hp -= E[i].weight;
                    answer = Math.max(answer, bruteForce(index + 1));
                    E[i].hp += E[index].weight;
                    E[index].hp += E[i].weight;
                }
            }
        }
        return Math.max(answer, bruteForce(index+1));
    }

    private static class Egg {
        private int hp;
        private int weight;

        public Egg(int hp, int weight) {
            this.hp = hp;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Egg{" +
                    "hp=" + hp +
                    ", weight=" + weight +
                    '}';
        }
    }
}
