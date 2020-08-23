package baekjoon.p16000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P16987 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static Egg[] eggs;
    private static int answer = 0;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        eggs = new Egg[N];
        for (int i=0; i<N; i++) {
            StringTokenizer token = new StringTokenizer(reader.readLine());
            eggs[i] = new Egg(Integer.parseInt(token.nextToken()), Integer.parseInt(token.nextToken()));
        }
        select(0);
        System.out.println(answer);
    }

    private static void select(int index) {
        if (index == N) { //base case
            int cnt = 0;
            for (int i=0; i < eggs.length; i++) {
                if (eggs[i].hp <= 0)
                    cnt++;
            }
            answer = Math.max(answer, cnt);
            return;
        }
        if (eggs[index].hp <= 0)
            select(index+1);
        else {
            boolean ok = false;
            for (int i=0; i<eggs.length; i++) {
                if (index == i) continue;
                if (eggs[i].hp <= 0) continue;
                ok = true;
                Egg e1 = eggs[index];
                Egg e2 = eggs[i];
                e1.hp -= e2.weight;
                e2.hp -= e1.weight;
                select(index+1);
                e1.hp += e2.weight;
                e2.hp += e1.weight;
            }
            if (!ok)
                select(index+1);
        }
    }

    private static class Egg {
        int weight;
        int hp;

        public Egg(int hp, int weight) {
            this.hp = hp;
            this.weight = weight;
        }
    }
}
