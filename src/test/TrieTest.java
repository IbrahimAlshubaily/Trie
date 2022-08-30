package test;

import main.Trie;
import main.LineParser;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class TrieTest {

    @org.junit.jupiter.api.Test
    void testAdd() {
        Trie trie = new Trie();
        assertFalse(trie.contains("hello"));
        trie.add("Hello");
        assertTrue(trie.contains("hello"));
        assertTrue(trie.contains("Hello"));
        assertTrue(trie.contains("HELLO"));
        assertFalse(trie.contains("he"));
        assertFalse(trie.contains("helloo"));
    }


    @org.junit.jupiter.api.Test
    void testLineParser() {
        LineParser parsedLine = LineParser.parseLine("1       HeLLo        (99)");
        assertEquals("hello", parsedLine.word);
        assertEquals(99, parsedLine.wordCount);
    }

    @org.junit.jupiter.api.Test
    void testBuild() throws IOException {
        Trie trie = new Trie();
        trie.build("data/word_frequency_simpsons.txt");
        assertEquals(trie.getVocabSize(), 5000);
    }

}