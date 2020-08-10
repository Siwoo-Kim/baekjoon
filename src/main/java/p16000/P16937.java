package p16000;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class P16937 {
    private static final Scanner scanner = new Scanner(
            new BufferedReader(new InputStreamReader(System.in)));
    private static List<Sticker> stickers;
    private static boolean[][] visit;
    private static int N, M;
    private static int answer = 0;

    //nC2 * (2^2 * 2)
    //O(nC2)
    public static void main(String[] args) {
        N = scanner.nextInt();
        M = scanner.nextInt();
        int numOfStickers = scanner.nextInt();
        visit = new boolean[numOfStickers][numOfStickers];
        stickers = new ArrayList<>();
        for (int i=0; i<numOfStickers; i++)
            stickers.add(new Sticker(scanner.nextInt(), scanner.nextInt()));
        Collections.shuffle(stickers);
        Collections.sort(stickers);

        select(0);
        System.out.println(answer);
    }

    private static void select(int index) {
        if (index == stickers.size()) return;
        for (int i=0; i<stickers.size(); i++) {
            if (i == index) continue;
            if (!visit[index][i]) {
                visit[index][i] = true;
                Sticker s1 = stickers.get(index);
                Sticker s2 = stickers.get(i);
                int area = s1.area() + s2.area();
                // [H,W] && [H,W]
                if (check(s1, s2))
                    answer = Math.max(area, answer);
                // [H,W] && [W,H]
                if (check(s1, s2.swap()))
                    answer = Math.max(area, answer);
                // [W,H] && [H,W]
                if (check(s1.swap(), s2))
                    answer = Math.max(area, answer);
                // [W,H] && [W,H]
                if (check(s1.swap(), s2.swap()))
                    answer = Math.max(area, answer);
            }
        }
        select(index+1);
    }

    private static boolean check(Sticker s1, Sticker s2) {
        // [][]
        if (Math.max(s1.height, s2.height) <= N &&  s1.width+s2.width <= M)
            return true;
        // []
        // []
        if (s1.height + s2.height <= N && Math.max(s1.width, s2.width) <= M)
            return true;
        return false;
    }

    private static class Sticker implements Comparable<Sticker> {
        private int height, width;

        public Sticker(int height, int weight) {
            this.height = height;
            this.width = weight;
        }

        @Override
        public int compareTo(Sticker o) {
            return Integer.compare(o.height * o.width, height * width);
        }

        public int area() {
            return height * width;
        }

        public Sticker swap() {
            return new Sticker(width, height);
        }
    }
}
