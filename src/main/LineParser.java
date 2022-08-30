package main;

public class LineParser{
    private final String word;
    private final int wordCount;
    public LineParser(String word, int wordCount){
        this.word = word.toLowerCase();
        this.wordCount = wordCount;
    }

    public String getWord(){
        return word;
    }
    public int getWordCount(){
        return wordCount;
    }
    public static LineParser parseLine(String line){
        String [] parsedLine = line.trim().replaceAll(" +", " ").split(" ");
        String  word = parsedLine[1];
        int wordCount = Integer.parseInt(parsedLine[2].substring(1,parsedLine[2].length()-1));
        return new LineParser(word, wordCount);
    }
}