package org.example.wordle;

import java.util.ArrayList;
import java.util.List;

public class WordleGame {
    private String hiddenWord;       // The word player needs to guess
    private int maxAttempts = 6;     // Maximum guesses allowed
    private List<String> guesses;    // Store all guesses
    private WordList wordList;

    public WordleGame(WordList wordList) {
        this.wordList = wordList;
        startNewGame();
    }

    public void startNewGame() {
        hiddenWord = wordList.getRandomWord();
        guesses = new ArrayList<>();
        System.out.println("Debug: Hidden word is " + hiddenWord); // Remove in final
    }

    public int getMaxAttempts() {
        return maxAttempts;
    }

    public List<String> getGuesses() {
        return guesses;
    }

    public boolean isGameOver() {
        return guesses.size() >= maxAttempts || isWin();
    }

    public boolean isWin() {
        return !guesses.isEmpty() && guesses.get(guesses.size() - 1).equals(hiddenWord);
    }

    // Check guess and return color feedback
    public String[] checkGuess(String guess) {
        guess = guess.toUpperCase();
        guesses.add(guess);
        String[] result = new String[5]; // Store color for each letter

        boolean[] used = new boolean[5]; // Track letters already matched for yellow

        // First pass: Green letters
        for (int i = 0; i < 5; i++) {
            if (guess.charAt(i) == hiddenWord.charAt(i)) {
                result[i] = "GREEN";
                used[i] = true;
            }
        }

        // Second pass: Yellow / Gray
        for (int i = 0; i < 5; i++) {
            if (result[i] != null) continue; // Already green
            boolean found = false;
            for (int j = 0; j < 5; j++) {
                if (!used[j] && guess.charAt(i) == hiddenWord.charAt(j)) {
                    found = true;
                    used[j] = true;
                    break;
                }
            }
            result[i] = found ? "YELLOW" : "GRAY";
        }

        return result;
    }
}
