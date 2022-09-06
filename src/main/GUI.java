package main;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class GUI {
    private static void createAndShowGUI() throws IOException {

        Trie trie = new Trie();
        trie.build("data/word_frequency_simpsons.txt");

        JFrame frame = new JFrame("TextEditorSwing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel("Text Editor");
        frame.getContentPane().add(label);

        TextArea textArea = new TextArea();
        frame.getContentPane().add(textArea);
        textArea.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyChar() == KeyEvent.VK_TAB){
                    String currWord = getLastWord();
                    autoComplete(currWord);
                    e.consume();
                } else if (e.getKeyCode() == 0) {//Fn
                    String currWord = getLastWord();
                    autoCorrect(currWord);
                    e.consume();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
            private String getLastWord(){
                int startInd = textArea.getText().lastIndexOf(' ')+1;
                return textArea.getText().substring(startInd);
            }
            private void autoComplete(String prefix){
                String completeWord = trie.autoComplete(prefix).iterator().next();
                textArea.append(completeWord.substring(prefix.length()) + " ");
            }
            private void autoCorrect(String currWord){
                if (trie.contains(currWord)) return;
                String correctWord = AutoCorrect.autoCorrect(currWord, trie).iterator().next();
                int startInd = textArea.getText().lastIndexOf(' ')+1;
                textArea.replaceRange(correctWord, startInd, startInd+currWord.length());
            }
        });
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) throws IOException{
        javax.swing.SwingUtilities.invokeLater(() -> {
            try {
                createAndShowGUI();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}