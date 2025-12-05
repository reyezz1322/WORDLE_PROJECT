package org.example.wordle;

import java.io.*;
import java.util.*;

public class WordList {
    private List<String> words;

    public WordList(String filename) {
        words = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim().toUpperCase();
                if (line.length() == 5) { // Only 5-letter words
                    words.add(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading words file: " + e.getMessage());
        }
    }

    // Pick a random word from the list
    public String getRandomWord() {
        Random rand = new Random();
        return words.get(rand.nextInt(words.size()));
    }

    // Check if a word is in the list
    public boolean contains(String word) {
        return words.contains(word.toUpperCase());
    }
}

