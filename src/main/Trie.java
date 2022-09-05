package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Trie {
    private final TrieNode root = new TrieNode();
    public void add(String word, int wordCount){
        root.add(word.toLowerCase(), 0, wordCount);
    }
    public void add(String word){
        add(word, 1);
    }
    public int getWordCount(String word){
        if (root.contains(word)){
            return root.find(word, 0).getWordCount();
        }
        return 0;
    }
    public boolean contains(String word){
        return root.contains(word.toLowerCase());
    }

    public void build(String filePath) throws IOException {
        File vocab = new File(filePath);
        FileReader fileReader = new FileReader(vocab);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        bufferedReader.readLine(); //discard header

        String line;
        ParsedLine parsedLine;
        while ((line = bufferedReader.readLine()) != null) {
            parsedLine = ParsedLine.parseLine(line);
            add(parsedLine.getWord(), parsedLine.getWordCount());
        }
        fileReader.close();
        root.initPrefixCount();
        root.initCompletions("");
    }
    public Set<String> autoComplete(String prefix){
        if (root.find(prefix, 0) != null){
            return root.find(prefix.toLowerCase(), 0).autoComplete();
        }
        return new HashSet<>(){};
    }

}

