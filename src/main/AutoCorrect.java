package main;

import java.util.HashMap;
import java.util.Set;

public class AutoCorrect {
    private static final int AUTO_CORRECT_SIZE = 3;
    private static final char[] ALPHABET = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    static Set<String> autoCorrect(String word, Trie trie){
        HashMap<String, Integer> corrections = getCorrections(word.toLowerCase(), trie);
        return Utils.selectTopK(corrections, AUTO_CORRECT_SIZE).keySet();
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

}
