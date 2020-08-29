package baekjoon.p5000;

import util.Algorithm;
import util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Used(Algorithm.AHO_CORASICK)
public class P5052 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int T, N;
    
    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(reader.readLine());
        for (int t=0; t<T; t++) {
            N = Integer.parseInt(reader.readLine());
            Trie trie = new Trie();
            String[] numbers = new String[N]; 
            for (int i=0; i<N; i++) {
                numbers[i] = reader.readLine();
                trie.add(numbers[i]);
            }
            if (trie.check())
                System.out.println("YES");
            else
                System.out.println("NO");
        }
    }
    
    private static class Trie {

        private static class Node {
            private Node[] children = new Node[10];
            private boolean exists;
        }
        
        private Node root = new Node();

        public boolean check() {
            return check(root);
        }

        private boolean check(Node root) {
            for (int i=0; i<10; i++) {
                if (root.children[i] != null) {
                    if (root.exists) return false;
                    if (!check(root.children[i]))
                        return false;
                }
            }
            return true;
        }
        
        public boolean add(String phone) {
            return add(root, phone, 0);
        }

        private boolean add(Node root, String phone, int index) {
            if (phone.length() == index) {
                root.exists = true;
                return true;
            }
            int id = phone.charAt(index) - '0';
            if (root.children[id] == null)
                root.children[id] = new Node();
            return add(root.children[id], phone, index+1);
        }
    }
}
