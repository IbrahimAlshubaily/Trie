package main;

import java.util.*;
class TrieNode{
    private static final int AUTO_COMPLETE_SIZE = 5;
    private int wordCount = 0;
    private final HashMap<Character, TrieNode> children = new HashMap<>();
    private HashMap<String, Integer> topCompletions = new HashMap<>();

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
        int prefixWordCount = wordCount;
        for (TrieNode childNode : children.values()) {
            prefixWordCount = Math.max(prefixWordCount, childNode.initPrefixCount());
        }
        return prefixWordCount;
    }
    int getWordCount() {
        return wordCount;
    }

    HashMap<String, Integer> initCompletions(String prefix){
        if (wordCount > 0){
            topCompletions.put(prefix, wordCount);
        }
        for (Map.Entry<Character, TrieNode> child: children.entrySet()){
            topCompletions.putAll(child.getValue().initCompletions(prefix+child.getKey()));
        }
        topCompletions = Utils.selectTopK(topCompletions, AUTO_COMPLETE_SIZE);
        return topCompletions;
    }
    public Set<String> autoComplete() {
        return topCompletions.keySet();
    }
}
