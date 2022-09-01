package main;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Trie trie = new Trie();
        trie.build("data/word_frequency_simpsons.txt");
        //System.out.println(AutoCorrect.autoCorrect("wha", trie));
        for (String completion :  trie.autoComplete("t")){
            System.out.println(completion);
        }
    }
}