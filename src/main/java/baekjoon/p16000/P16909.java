package baekjoon.p16000;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class P16909 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N;

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(reader.readLine());
        int[] lg = new int[N+1];
        int[] rg = new int[N+1];
        int[] ls = new int[N+1];
        int[] rs = new int[N+1];
        int[] a = new int[N+1];
        String[] line = reader.readLine().split("\\s+");
        for (int i=1; i<=N; i++) {
            a[i] = Integer.parseInt(line[i-1]);
            lg[i] = ls[i] = 0;
            rg[i] = rs[i] = N+1;
        }

        Stack<Pair> sg = new Stack<>(), ss = new Stack<>();
        sg.push(new Pair(1, a[1]));
        ss.push(new Pair(1, a[1]));
        for (int i = 2; i <= N; i++) {
            while (!sg.isEmpty() && a[i] >= sg.peek().second) {
                rg[sg.peek().first] = i;
                sg.pop();
            }
            sg.push(new Pair(i, a[i]));
            while (!ss.isEmpty() && a[i] <= ss.peek().second) {
                rs[ss.peek().first] = i;
                ss.pop();
            }
            ss.push(new Pair(i, a[i]));
        }
        sg = new Stack<>();
        ss = new Stack<>();
        sg.push(new Pair(N, a[N]));
        ss.push(new Pair(N, a[N]));
        for (int i = N - 1; i >= 1; i--) {
            while (!sg.isEmpty() && a[i] > sg.peek().second) {
                lg[sg.peek().first] = i;
                sg.pop();
            }
            sg.push(new Pair(i, a[i]));
            while (!ss.isEmpty() && a[i] < ss.peek().second) {
                ls[ss.peek().first] = i;
                ss.pop();
            }
            ss.push(new Pair(i, a[i]));
        }

        long answer = 0;
        for (int i=1; i<=N; i++) {
            int l = Math.min(i, lg[i] + 1);
            int r = Math.max(i, rg[i] - 1);
            long len = r - l  +1;
            answer += (calc(len) - calc(r-i) - calc(i-l)) * a[i];
        }
        for (int i=1; i<=N; i++) {
            int l = Math.min(i, ls[i] + 1);
            int r = Math.max(i, rs[i] - 1);
            long len = r - l  +1;
            answer -= (calc(len) - calc(r-i) - calc(i-l)) * a[i];
        }
        System.out.println(answer);
    }

    private static long calc(long c) {
        return c * (c + 1) / 2;
    }

    private static class Pair {
        int first, second;

        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }
}
