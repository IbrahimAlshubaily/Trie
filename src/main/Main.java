package main;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello world!");
        Trie trie = new Trie();
        trie.build("data/word_frequency_simpsons.txt");
        System.out.println(trie.getVocabSize());
    }
}