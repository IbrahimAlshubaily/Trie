package main;

import java.util.*;
import java.util.stream.Collectors;

class TrieNode{
    private static final int AUTO_COMPLETE_SIZE = 3;
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
    public Set<String> autoComplete(String word) {
        return selectTopCandidates(getCompletions(word));
    }
    public HashMap<String, Integer> getCompletions(String prefix) {
        HashMap<String, Integer> candidates = new HashMap<>();
        if (wordCount > 0){
            candidates.put(prefix, wordCount);
        }
        for (Map.Entry<Character, TrieNode> child: children.entrySet()){
            candidates.putAll(child.getValue().getCompletions(prefix+child.getKey()));
        }
        return candidates;
    }
    private static Set<String> selectTopCandidates(HashMap<String, Integer> candidates) {
        return candidates.entrySet().stream().
                //Sort by wordCount in descending order
                sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).
                limit(AUTO_COMPLETE_SIZE).
                map(Map.Entry::getKey).
                collect(Collectors.toSet());
    }
}
