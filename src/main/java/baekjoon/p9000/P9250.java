package baekjoon.p9000;

import util.Algorithm;
import util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

@Used(Algorithm.AHO_CORASICK)
public class P9250 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M;
    
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        AhoCorasick ahoCorasick = new AhoCorasick();
        for (int i=0; i<N; i++)
            ahoCorasick.add(reader.readLine());
        M = Integer.parseInt(reader.readLine());
        for (int i=0; i<M; i++)
            if (ahoCorasick.query(reader.readLine()))
                System.out.println("YES");
            else
                System.out.println("NO");
    }
    
    private static class AhoCorasick {
        private static final int MAX = 26;
        private final Node root = new Node();
        private Queue<Node> queue;

        private static class Node {
            private Node[] children = new Node[MAX];
            private Node link;
            private boolean exist;
        }
        
        public void add(String pattern) {
            add(root, pattern, 0);
        }

        private void add(Node root, String s, int index) {
            if (s.length() == index) {
                root.exist = true;
                return;
            }
            int c = s.charAt(index) - 'a';
            if (root.children[c] == null)
                root.children[c] = new Node();
            add(root.children[c], s, index+1);
        }
        
        private void fail() {
            Queue<Node> queue = new LinkedList<>();
            root.link = root;
            queue.add(root);
            while (!queue.isEmpty()) {
                Node node = queue.poll();
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
                    next.exist |= pi.exist;
                    queue.add(next);
                }
            }
            this.queue = queue;
        }
        
        public boolean query(String s) {
            if (queue == null)
                fail();
            Node node = root;
            for (int i=0; i<s.length(); i++) {
                int c = s.charAt(i) - 'a';
                while (node != root && node.children[c] == null)
                    node = node.link;
                if (node.children[c] != null)
                    node = node.children[c];
                if (node.exist)
                    return true;
            }
            return false;
        }
    }
}
