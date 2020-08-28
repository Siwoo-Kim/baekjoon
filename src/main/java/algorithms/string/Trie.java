package algorithms.string;

/**
 * An ordered tree data structure used to store strings.
 * Node represents prefix of certain strings.
 * 
 */
public class Trie {
    private static int MAX = 26;
    
    private static class Node {
        Node[] children = new Node[MAX];
        private boolean valid;
    }
    
    private Node root = new Node();
    
    private void add(String s) {
        assert s != null;
        add(root, s, 0);
    }

    private void add(Node root, String s, int index) {
        if (s.length() == index) {
            root.valid = true;
            return;
        }
        int i = s.charAt(index) - 'a';
        Node child = root.children[i];
        if (child == null)
            child = root.children[i] = new Node();
        add(child, s, index+1);
    }

    public boolean search(String s) {
        assert s != null;
        return search(root, s, 0);
    }

    private boolean search(Node root, String s, int index) {
        if (root == null) return false;
        if (s.length() == index) return root.valid;
        int i = s.charAt(index) - 'a';
        Node child = root.children[i];
        return search(child, s, index+1);
    }

    /**
     * Test
     */
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.add("abc");
        assert trie.search("abc");
        assert !trie.search("abd");
        trie.add("abd");
        assert trie.search("abc");
        assert trie.search("abd");
        trie.add("ace");
        assert trie.search("abc");
        assert trie.search("abd");
        assert trie.search("ace");
        assert !trie.search("ac");
        assert !trie.search("");
        assert !trie.search("dddddddddd");
    }
}













