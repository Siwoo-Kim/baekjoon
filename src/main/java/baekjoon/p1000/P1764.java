package baekjoon.p1000;

import java.io.*;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class P1764 {

    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int N, M;

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        TreeSet<String> neverHeard = new TreeSet<>();
        for (int i=0; i<N; i++)
            neverHeard.add(reader.readLine());
        TreeSet<String> neverSeen = new TreeSet<>();
        for (int i=0; i<M; i++)
            neverSeen.add(reader.readLine());
        neverHeard.retainAll(neverSeen);

        System.out.println(neverHeard.size());
        for (String s: neverHeard) {
            writer.write(s);
            writer.write("\n");
        }
        writer.flush();
    }
}
