package main;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class AutoCorrect {
    private static final int AUTO_CORRECT_SIZE = 3;
    private static final char[] ALPHABET = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    static Set<String> autoCorrect(String word, Trie trie){
        HashMap<String, Integer> corrections = getCorrections(word.toLowerCase(), trie);
        return selectTopCorrections(corrections);
    }
    private static HashMap<String, Integer> getCorrections(String word, Trie trie) {
        HashMap<String, Integer> result = new HashMap<>();
        String currWord;
        for (int i = 0; i < word.length(); i++){
            for (char c : ALPHABET){
                //INSERT
                currWord = word.substring(0,i) + c + word.substring(i);
                addWord(currWord, trie, result);
                //REPLACE
                currWord = word.substring(0,i) + c + word.substring(i+1);
                addWord(currWord, trie, result);
            }
            //REMOVE
            currWord = word.substring(0,i) + word.substring(i+1);
            addWord(currWord, trie, result);
        }
        //INSERT last
        for (char c : ALPHABET){
            addWord(word + c, trie, result);
        }
        return result;
    }

    private static void addWord(String word, Trie trie, HashMap<String, Integer> result) {
        if (trie.contains(word)){
            result.put(word, trie.getWordCount(word));
        }
    }
    private static Set<String> selectTopCorrections(HashMap<String, Integer> corrections) {
        return corrections.entrySet().stream().
                //Sort by wordCount in descending order
                sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).
                limit(AUTO_CORRECT_SIZE).
                map(Map.Entry::getKey).
                collect(Collectors.toSet());
    }
}
