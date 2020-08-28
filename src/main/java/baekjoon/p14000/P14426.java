package baekjoon.p14000;

import util.Algorithm;
import util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

@Used(Algorithm.TRIE)
public class P14426 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M;
    
    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        Trie trie = new Trie();
        for (int i=0; i<N; i++)
            trie.add(reader.readLine());
        int cnt = 0;
        for (int i=0; i<M; i++)
            if (trie.search(reader.readLine()))
                cnt++;
        System.out.println(cnt);
    }
    
    private static class Trie {
        
        private static class Node {
            private Node[] children = new Node[26];
        }
        
        private Node root = new Node();
        
        public void add(String s) {
            add(root, s, 0);
        }

        private void add(Node root, String s, int index) {
            if (s.length() == index) return;
            int child = s.charAt(index) - 'a';
            if (root.children[child] == null)
                root.children[child] = new Node();
            add(root.children[child], s, index + 1);
        }
        
        public boolean search(String s) {
            return search(root, s, 0);
        }

        private boolean search(Node root, String s, int index) {
            if (root == null) return false;
            if (s.length() == index) return true;
            return search(root.children[s.charAt(index) - 'a'], s, index+1);
        }
    }
}
