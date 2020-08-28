package baekjoon.p13000;

import util.Algorithm;
import util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

@Used(Algorithm.TRIE)
public class P13505 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        int[] a = new int[N];
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        Trie trie = new Trie();
        for (int i=0; i<N; i++) {
            a[i] = Integer.parseInt(tokenizer.nextToken());
            trie.add(a[i]);
        }
        int max = 0;
        for (int i=0; i<N; i++)
            max = Integer.max(a[i] ^ trie.xorQuery(a[i]), max);
        System.out.println(max);
    }
    
    private static class Trie {
        private static int BITS = 31;
        private static int BIT = 2;
        
        private static class Node {
            Node[] children = new Node[BIT];
            int current;
        }
        
        private Node root = new Node();
        
        public void add(int n) {
            add(root, n, BITS);
        }

        private void add(Node root, int n, int bits) {
            if (bits == -1) return;
            int i = (n >> bits) & 1;
            Node child = root.children[i];
            if (child == null) {
                Node node = new Node();
                node.current = i;
                child = root.children[i] = node;
            }
            add(child, n, bits-1);
        }
        
        public int xorQuery(int n) {
            return xorQuery(root, n, BITS);
        }

        private int xorQuery(Node root, int n, int bits) {
            if (bits == -1) return 0;
            int i = (n >> bits) & 1;
            i = 1-i;
            if (root.children[i] == null)
                i = 1-i;
            int next = 0;
            if (i == 1)
                next = 1 << bits;
            return next | xorQuery(root.children[i], n, bits-1);
        }
    }
}