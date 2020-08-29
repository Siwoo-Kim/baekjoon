package baekjoon.p12000;

import util.Algorithm;
import util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

@Used({Algorithm.AHO_CORASICK, Algorithm.KMP})
public class P12104 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static String a, b;
    
    public static void main(String[] args) throws IOException {
        a = reader.readLine();
        b = reader.readLine();
        int N = a.length();
        b += b;
        b = b.substring(0, b.length()-1);
        AhoCorasick ahoCorasick = new AhoCorasick();
        ahoCorasick.add(a);
        System.out.println(ahoCorasick.query(b));
    }
    
    private static class AhoCorasick {
        private static final int MAX = 2;
        private static Node root = new Node();
        private static Queue<Node> fail;
        
        private static class Node {
            private Node[] children = new Node[MAX];
            private boolean exists;
            private Node link;
            private int cnt;
        }
        
        private void add(String s) {
            add(root, s, 0);
        }

        private void add(Node root, String s, int index) {
            if (s.length() == index) {
                root.exists = true;
                root.cnt++;
                return;
            }
            int child = s.charAt(index) - '0';
            if (root.children[child] == null)
                root.children[child] = new Node();
            add(root.children[child], s, index+1);
        }
        
        public void fail() {
            if (fail != null) return;
            fail = new LinkedList<>();
            root.link = root;
            fail.add(root);
            while (!fail.isEmpty()) {
                Node node = fail.poll();
                for (int i=0; i<2; i++) {
                    Node next = node.children[i];
                    if (next == null) continue;
                    if (node == root)
                        next.link = root;
                    else {
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
        
        public int query(String s) {
            fail();
            int cnt = 0;
            Node node = root;
            for (int i=0; i<s.length(); i++) {
                int c = s.charAt(i) - '0';
                while (node != root && node.children[c] == null)
                    node = node.link;
                if (node.children[c] != null)
                    node = node.children[c];
                if (node.exists)
                    cnt += node.cnt;
            }
            return cnt;
        }
    }
}
