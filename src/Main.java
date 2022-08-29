public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Trie trie = new Trie();
        System.out.println(trie.contains("Hello"));
        trie.add("Hello");
        System.out.println(trie.contains("Hello"));
        System.out.println(trie.contains("hello"));
        System.out.println(trie.contains("he"));
    }
}