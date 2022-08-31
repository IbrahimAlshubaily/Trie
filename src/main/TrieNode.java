package main;

import java.util.HashMap;
import java.util.Map;

class TrieNode{
    private int wordCount = 0;
    private int prefixWordCount = 0;
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
    int initPrefixCount() {
        prefixWordCount = wordCount;
        for (TrieNode childNode : children.values()) {
            prefixWordCount = Math.max(prefixWordCount, childNode.initPrefixCount());
        }
        return prefixWordCount;
    }
    int getWordCount() {
        return wordCount;
    }

    public String getCompletions(String prefix) {
        
        char nextChar = 0;
        TrieNode nextNode = null;
        int maxPrefixCount = 0;
        for (Map.Entry<Character, TrieNode> child: children.entrySet()){
            if (child.getValue().prefixWordCount > maxPrefixCount){
                nextChar = child.getKey();
                nextNode = child.getValue();
                maxPrefixCount = nextNode.prefixWordCount;
            }
        }

        if (nextNode != null && maxPrefixCount > this.wordCount){
            return nextNode.getCompletions(prefix+nextChar);
        }
        return prefix;
    }
}
