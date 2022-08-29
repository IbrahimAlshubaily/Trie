public class Trie {
    private final TrieNode root = new TrieNode();
    void add(String word, int wordCount){
        this.root.add(word.toLowerCase(), 0, wordCount);
    }
    void add(String word){
        add(word, 1);
    }
    boolean contains(String word){
        return this.root.contains(word.toLowerCase());
    }
}
