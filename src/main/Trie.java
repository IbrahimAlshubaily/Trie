package main;

public class Trie {
    private final TrieNode root = new TrieNode();
    public void add(String word, int wordCount){
        this.root.add(word.toLowerCase(), 0, wordCount);
    }
    public void add(String word){
        add(word, 1);
    }
    public boolean contains(String word){
        return this.root.contains(word.toLowerCase());
    }
}
