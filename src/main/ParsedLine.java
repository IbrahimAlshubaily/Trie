package main;

class ParsedLine {
    private final String word;
    private final int wordCount;

    ParsedLine(String word, int wordCount) {
        this.word = word.toLowerCase();
        this.wordCount = wordCount;
    }

    String getWord() {
        return word;
    }

    int getWordCount() {
        return wordCount;
    }

    static ParsedLine parseLine(String line){
        String [] parsedLine = line.trim().replaceAll(" +", " ").split(" ");
        String  word = parsedLine[1];
        int wordCount = Integer.parseInt(parsedLine[2].substring(1,parsedLine[2].length()-1));
        return new ParsedLine(word, wordCount);
    }
}
