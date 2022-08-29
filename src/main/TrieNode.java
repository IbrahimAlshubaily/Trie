package main;

import java.util.HashMap;

class TrieNode {
    private int wordCount = 0;
    private final HashMap<Character, TrieNode> children = new HashMap<>();
    void add(String word, int index, int wordCount){
        if (index == word.length()){
            this.wordCount += wordCount;
            return;
        }
        char currChar = word.charAt(index);
        if (!children.containsKey(currChar)){
            children.put(currChar, new TrieNode());
        }
        children.get(currChar).add(word, index+1, wordCount);
    }

    TrieNode find(String word, int index){
        if (index == word.length()){
            return this;
        }
        char currChar = word.charAt(index);
        if (children.containsKey(currChar)){
            return children.get(currChar).find(word, index+1);
        }
        return null;
    }
    boolean contains(String word){
        TrieNode node = find(word, 0);
        return node != null && node.wordCount > 0;
    }
}
