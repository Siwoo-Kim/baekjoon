package baekjoon.p2000;

import util.Algorithm;
import util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Used(Algorithm.KMP)
public class P2902 {
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    
    public static void main(String[] args) throws IOException {
        String algorithm = reader.readLine();
        KMP kmp = new KMP(algorithm, "-");
        Queue<Integer> dashes = new LinkedList<>(kmp.search());
        StringBuilder shorten = new StringBuilder();
        shorten.append(algorithm.charAt(0));
        while (!dashes.isEmpty())
            shorten.append(algorithm.charAt(dashes.poll()+1));
        System.out.println(shorten.toString());
    }
    
    private static class KMP {
        private String s;
        private String p;
        private int[] fail;

        public KMP(String s, String p) {
            this.s = s;
            this.p = p;
            fail();
        }

        private void fail() {
            fail = new int[p.length()];
            int j = 1;
            for (int i=1; i<p.length(); i++) {
                while (j > 0 && p.charAt(i) != p.charAt(j))
                    j = fail[j-1];
                if (p.charAt(i) == p.charAt(j))
                    fail[i] = ++j;
            }
        }
        
        public List<Integer> search() {
            List<Integer> result = new ArrayList<>();
            int j = 0;
            for (int i=0; i<s.length(); i++) {
                while (j > 0 && s.charAt(i) != p.charAt(j))
                    j = fail[j-1];
                if (s.charAt(i) == p.charAt(j)) {
                    if (j == p.length()-1) {
                        result.add(i-p.length()+1);
                        j = fail[j];
                    } else {
                        j++;
                    }
                }
            }
            return result;
        }
    }
}
