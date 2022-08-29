package test;

import main.Trie;
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
    }
}