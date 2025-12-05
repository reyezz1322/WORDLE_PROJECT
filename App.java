package org.example.wordle;

import javax.swing.SwingUtilities;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            WordList wordList = new WordList("Words.txt"); // Load words from file
            WordleGame game = new WordleGame(wordList);    // Create a new game
            new WordleGUI(game);                           // Launch GUI
        });
    }
}
