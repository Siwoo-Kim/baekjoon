package baekjoon.p17000;

import java.io.*;
import java.util.*;

public class P7785 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    private static TreeSet<String> sets = new TreeSet<>(Comparator.reverseOrder());
    private static int N;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        for (int i=0; i<N; i++) {
            StringTokenizer token = new StringTokenizer(reader.readLine());
            String name = token.nextToken(),
                    status = token.nextToken();
            if ("leave".equals(status))
                sets.remove(name);
            else
                sets.add(name);
        }
        for (String s: sets) {
            writer.write(s);
            writer.write("\n");
        }
        writer.flush();
    }
}
