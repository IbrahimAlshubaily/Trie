package main;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

class TrieTest {

    private Trie trie;
    @BeforeEach
    void initTrie(){
        trie = new Trie();
    }

    @Test
    void testAdd() {
        assertFalse(trie.contains("hello"));
        trie.add("Hello");
        assertTrue(trie.contains("hello"));
        assertTrue(trie.contains("Hello"));
        assertTrue(trie.contains("HELLO"));
        assertFalse(trie.contains("he"));
        assertFalse(trie.contains("helloo"));
    }

    @Test
    void testLineParser() {
        ParsedLine parsedLine = ParsedLine.parseLine("1       HeLLo        (99)");
        assertEquals("hello", parsedLine.getWord());
        assertEquals(99, parsedLine.getWordCount());
    }

    @Test
    void testAutoComplete() throws IOException {
        trie.build("data/word_frequency_simpsons.txt");
        assertEquals("the", trie.autoComplete("t"));
        assertEquals("you", trie.autoComplete("y"));
        assertEquals("and", trie.autoComplete("an"));
        assertEquals("we", trie.autoComplete("w"));
        assertEquals("what", trie.autoComplete("wh"));
        assertEquals("be", trie.autoComplete("b"));
        assertEquals("but", trie.autoComplete("bu"));
    }
    @Test
    void testAutoCorrect() throws IOException {
        trie.build("data/word_frequency_simpsons.txt");
        assertTrue(AutoCorrect.autoCorrect("Condtion", trie).contains("condition"));
        assertTrue(AutoCorrect.autoCorrect("Condetion", trie).contains("condition"));
        assertTrue(AutoCorrect.autoCorrect("wha", trie).contains("what"));
        assertTrue(AutoCorrect.autoCorrect("hat", trie).contains("what"));
    }
}