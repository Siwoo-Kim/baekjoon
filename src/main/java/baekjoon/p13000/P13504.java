package baekjoon.p13000;

import util.Algorithm;
import util.Tip;
import util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

@Used(Algorithm.TRIE)
@Tip("S[i..j]" +
    "if S[i] = xor(A[1] .. A[i])" +
    "then S[i..j] = S[i-1] xor S[j]")
public class P13504 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int T, N;

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(reader.readLine());
        for (int t=0; t<T; t++) {
            N = Integer.parseInt(reader.readLine());
            int[] ints = Arrays.stream(reader.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            Trie trie = new Trie();
            int sumBits = 0, answer = 0;
            trie.add(sumBits);
            for (int i=0; i<N; i++) {
                sumBits ^= ints[i];
                trie.add(sumBits);
                int max = trie.query(sumBits) ^ sumBits;
                answer = Math.max(max, answer);
            }
            System.out.println(answer);
        }
    }
    
    private static class Trie {
        private static final int BIT = 2, BITS = 32;
        
        private static class Node {
            Node[] children = new Node[BIT];
            boolean exist;
        }
        
        private Node root = new Node();
        
        public void add(int x) {
            add(root, x, BITS-1);
        }

        private void add(Node root, int x, int bits) {
            if (bits == -1) {
                root.exist = true;
                return;
            }
            int bit = (x >> bits) & 1;
            if (root.children[bit] == null)
                root.children[bit] = new Node();
            add(root.children[bit], x, bits-1);
        }
        
        public int query(int x) {
            return query(root, x, BITS-1);
        }

        private int query(Node root, int x, int bits) {
            if (bits == -1) return 0;
            int bit = (x >> bits) & 1;
            bit = 1 - bit;
            if (root.children[bit] == null)
                bit = 1 - bit;
            int current = bit << bits;
            return current | query(root.children[bit], x, bits-1);
        }
    }
}