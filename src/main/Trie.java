package main;

import java.io.*;

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
            add(parsedLine.word, parsedLine.wordCount);
        }
        fileReader.close();
    }
}
