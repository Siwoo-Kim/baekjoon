package algorithms.string;

import java.util.*;

/**
 * Aho-corasick
 *  Search patterns p[] from String s
 *  
 */
public class AhoCorasick {
    private static int MAX = 26;
    private Queue<Node> queue;
    
    private static class Node {
        private Node[] children = new Node[MAX];
        private String prefix;
        private Node pi = null;
        private boolean valid;

        @Override
        public String toString() {
            return "Node{" +
                    prefix +
                    '}';
        }
    }
    
    private Node root = new Node();
    
    public void add(String s) {
        add(root, s, 0);
    }

    private void add(Node root, String s, int index) {
        if (s.length() == index) {
            root.valid = true;
            return;
        }
        int c = s.charAt(index) - 'a';
        if (root.children[c] == null) {
            root.children[c] = new Node();
            root.children[c].prefix = s.substring(0, index+1);
        }
        add(root.children[c], s, index+1);
    }
    
    private void fail() {
        Queue<Node> queue = new LinkedList<>();
        root.pi = root;
        queue.add(root);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            for (int i=0; i<MAX; i++) {
                Node next = node.children[i];
                if (next == null) continue;
                if (node == root) {
                    next.pi = root;
                } else {
                    Node x = node.pi;
                    while (x != root && x.children[i] == null)
                        x = x.pi;
                    if (x.children[i] != null)
                        x = x.children[i];
                    next.pi = x;
                }
                Node pi = next.pi;
                next.valid |= pi.valid;
                queue.add(next);
            }
        }
        this.queue = queue;
    }
    
    public boolean query(String s) {
        if (this.queue == null)
            fail();
        Node node = root;
        for (int i=0; i<s.length(); i++) {
            int c = s.charAt(i) - 'a';
            while (node != root && node.children[c] == null)
                node = node.pi;
            if (node.children[c] != null)
                node = node.children[c];
            if (node.valid)
                return true;
        }
        return false;
    }

    public static void main(String[] args) {
        AhoCorasick ahoCorasick = new AhoCorasick();
        ahoCorasick.add("ad");
        ahoCorasick.add("abc");
        ahoCorasick.add("abab");
        ahoCorasick.add("ababc");
        ahoCorasick.add("bcd");
        ahoCorasick.query("abcdad");
    }
}