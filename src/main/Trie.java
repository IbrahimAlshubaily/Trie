package main;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Trie {

    private final TrieNode root = new TrieNode();
    private static final int AUTO_CORRECT_SIZE = 3;
    private final static char[] ALPHABET = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    public void add(String word, int wordCount){
        this.root.add(word.toLowerCase(), 0, wordCount);
    }
    public void add(String word){
        add(word, 1);
    }
    public boolean contains(String word){
        return this.root.contains(word.toLowerCase());
    }
    public int getVocabSize(){
        return root.getVocabSize();
    }
    public void build(String filePath) throws IOException {
        File vocab = new File(filePath);
        FileReader fileReader = new FileReader(vocab);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        bufferedReader.readLine(); //discard header

        String line;
        LineParser parsedLine;
        while ((line = bufferedReader.readLine()) != null) {
            parsedLine = LineParser.parseLine(line);
            add(parsedLine.getWord(), parsedLine.getWordCount());
        }
        fileReader.close();
    }

    public Set<String> autoCorrect(String word){
        HashMap<TrieNode, String> corrections = getCorrections(word.toLowerCase());
        return selectTopCorrections(corrections);
    }


    private HashMap<TrieNode, String> getCorrections(String word) {
        HashMap<TrieNode, String> result = new HashMap<>();
        String currWord;
        for (int i = 0; i < word.length(); i++){
            for (char c : ALPHABET){
                //INSERT
                currWord = word.substring(0,i) + c + word.substring(i);
                addWord(currWord, result);
                //REPLACE
                currWord = word.substring(0,i) + c + word.substring(i+1);
                addWord(currWord, result);
            }
            //REMOVE
            currWord = word.substring(0,i) + word.substring(i+1);
            addWord(currWord, result);
        }
        return result;
    }

    private void addWord(String word, HashMap<TrieNode, String> result) {
        TrieNode node = root.find(word, 0);
        if (node != null){
            result.put(node, word);
        }
    }

    private Set<String> selectTopCorrections(HashMap<TrieNode, String> corrections) {
        return corrections.entrySet().stream().
                                        sorted(Map.Entry.comparingByKey()).
                                        limit(AUTO_CORRECT_SIZE).
                                        map(Map.Entry::getValue).
                                        collect(Collectors.toSet());
    }

}
