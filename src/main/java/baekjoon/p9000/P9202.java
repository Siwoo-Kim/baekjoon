package baekjoon.p9000;

import util.Algorithm;
import util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

@Used(Algorithm.TRIE)
public class P9202 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N = 4, B;
    private static String[] dictionay;
    private static char[][] board;
    private static boolean[][] visit;
    
    public static void main(String[] args) throws IOException {
        int S = Integer.parseInt(reader.readLine());
        dictionay = new String[S];
        for (int i=0; i<S; i++) 
            dictionay[i] = reader.readLine();
        reader.readLine();
        B = Integer.parseInt(reader.readLine());
        for (int b=0; b<B; b++) {
            board = new char[N][N];
            visit = new boolean[N][N];
            for (int i = 0; i < N; i++)
                board[i] = reader.readLine().toCharArray();

            Set<String> words = new HashSet<>();
            for (int i = 0; i < N; i++)
                for (int j = 0; j < N; j++) {
                    visit[i][j] = true;
                    dfs(i, j,  Character.toString(board[i][j]), words);
                    visit[i][j] = false;
                }
            Trie trie = new Trie();
            for (String word : words)
                trie.add(word);
            int cnt = 0, score = 0;
            String found = "";
            for (String d : dictionay) {
                if (trie.search(d)) {
                    score += score(d);
                    cnt++;
                    if (found.length() <= d.length()) {
                        if (found.length() == d.length()) {
                            int cmp = d.compareTo(found);
                            if (cmp < 0) {
                                found = d;
                            }
                        } else {
                            found = d;
                        }
                    }
                }
            }
            System.out.printf("%d %s %d%n", score, found, cnt);
            reader.readLine();
        }
    }

    private static int score(String s) {
        int n = s.length();
        if (n <= 2) return 0;
        if (n <= 4) return 1;
        if (n == 5) return 2;
        if (n == 6) return 3;
        return n == 7? 5: 11; 
    }

    private static void dfs(int x, int y, String word, Set<String> result) {
        result.add(word);
        if (word.length() == 8) return;
        for (int[] d: D) {
            int dx = x + d[0];
            int dy = y + d[1];
            if (valid(dx, dy) && !visit[dx][dy]) {
                visit[x][y] = true;
                dfs(dx, dy, word + board[dx][dy], result);
                visit[x][y] = false;
            }
        }
    }

    private static boolean valid(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
    
    private static int[][] D = {
            {-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}
    };

    private static class Trie {
        private static int MAX = 26;

        public boolean search(String s) {
            return search(root, s, 0);
        }

        private boolean search(Node root, String s, int index) {
            if (root == null) return false;
            if (s.length() == index) return true;
            int id = s.charAt(index) - 'A';
            return search(root.children[id], s, index+1);
        }

        private static class Node {
            private Node[] children = new Node[MAX];    
        }
        
        private Node root = new Node();
        
        public void add(String s) {
            add(root, s, 0);
        }

        private void add(Node root, String s, int index) {
            if (s.length() == index) return;
            int id = s.charAt(index) - 'A';
            if (root.children[id] == null)
                root.children[id] = new Node();
            add(root.children[id], s, index + 1);
        }
    }
}