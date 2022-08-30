package main;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Trie trie = new Trie();
        trie.build("data/word_frequency_simpsons.txt");
        System.out.println(trie.autoCorrect("condetion"));
        System.out.println(trie.autoCorrect("Condetion").contains("condition"));
    }
}