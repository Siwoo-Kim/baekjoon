package baekjoon.p10000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P10256 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int T, N, M;
    private static String dna, marker;
    
    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        T = Integer.parseInt(tokenizer.nextToken());
        for (int t=0; t<T; t++) {
            tokenizer = new StringTokenizer(reader.readLine());
            N = Integer.parseInt(tokenizer.nextToken());
            M = Integer.parseInt(tokenizer.nextToken());
            dna = reader.readLine();
            marker = reader.readLine();
            Set<String> markers = new HashSet<>();
            for (int i = 0; i < M; i++)
                for (int j = i; j < M; j++) {
                    markers.add(
                            marker.substring(0, i) +
                                    new StringBuilder(marker.substring(i, j+1)).reverse() +
                                    marker.substring(j+1));
                }
            AhoCorasick ahoCorasick = new AhoCorasick();
            for (String s: markers)
                ahoCorasick.add(s);
            System.out.println(ahoCorasick.query(dna));
        }
    }
    
    private static class AhoCorasick {
        private static final int MAX = 4;
        private Queue<Node> fail;
        private Node root = new Node();
        
        private static class Node {
            private Node[] children = new Node[MAX];
            private Node link;
            private boolean exists;
        }
        
        public void add(String pattern) {
            add(root, pattern, 0);
        }

        private void add(Node root, String s, int index) {
            if (s.length() == index) {
                root.exists = true;
                return;
            }
            int id = getID(s.charAt(index));
            if (root.children[id] == null)
                root.children[id] = new Node();
            add(root.children[id], s, index+1);
        }

        private int getID(char c) {
            if (c == 'A') return 0;
            if (c == 'G') return 1;
            if (c == 'T') return 2;
            if (c == 'C') return 3;
            throw new AssertionError();
        }
        
        private void fail() {
            if (fail != null) return;
            fail = new LinkedList<>();
            root.link = root;
            fail.add(root);
            while (!fail.isEmpty()) {
                Node node = fail.poll();
                for (int i=0; i<MAX; i++) {
                    Node next = node.children[i];
                    if (next == null) continue;
                    if (node == root) {
                        next.link = root;
                    } else {
                        Node pi = node.link;
                        while (pi != root && pi.children[i] == null)
                            pi = pi.link;
                        if (pi.children[i] != null)
                            pi = pi.children[i];
                        next.link = pi;
                    }
                    Node pi = next.link;
                    next.exists |= pi.exists;
                    fail.add(next);
                }
            }
        }
        
        public int query(String dna) {
            fail();
            int cnt = 0;
            Node node = root;
            for (int i=0; i<dna.length(); i++) {
                int id = getID(dna.charAt(i));
                while (node != root && node.children[id] == null)
                    node = node.link;
                if (node.children[id] != null)
                    node = node.children[id];
                if (node.exists)
                    cnt++;
            }
            return cnt;
        }
    }
}
