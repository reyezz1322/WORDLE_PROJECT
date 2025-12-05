package org.example.wordle;

import javax.swing.*;
import java.awt.*;

public class WordleGUI {
    private WordleGame game;
    private JFrame frame;
    private JTextField inputField;
    private JPanel boardPanel;

    public WordleGUI(WordleGame game) {
        this.game = game;
        createGUI();
    }

    private void createGUI() {
        frame = new JFrame("Wordle Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 600);
        frame.setLayout(new BorderLayout());

        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(game.getMaxAttempts(), 5, 5, 5));
        frame.add(boardPanel, BorderLayout.CENTER);

        inputField = new JTextField();
        inputField.setFont(new Font("Monospaced", Font.BOLD, 24));
        frame.add(inputField, BorderLayout.SOUTH);

        inputField.addActionListener(e -> {
            String guess = inputField.getText().trim().toUpperCase();
            if (guess.length() != 5) {
                JOptionPane.showMessageDialog(frame, "Please enter a 5-letter word.");
                return;
            }

            String[] colors = game.checkGuess(guess);
            for (int i = 0; i < 5; i++) {
                JLabel lbl = new JLabel(String.valueOf(guess.charAt(i)), SwingConstants.CENTER);
                lbl.setOpaque(true);
                switch (colors[i]) {
                    case "GREEN" -> lbl.setBackground(Color.GREEN);
                    case "YELLOW" -> lbl.setBackground(Color.YELLOW);
                    default -> lbl.setBackground(Color.LIGHT_GRAY);
                }
                lbl.setFont(new Font("Monospaced", Font.BOLD, 24));
                boardPanel.add(lbl);
            }

            boardPanel.revalidate();
            boardPanel.repaint();
            inputField.setText("");

            if (game.isWin()) {
                JOptionPane.showMessageDialog(frame, "Congratulations! You guessed it!");
                game.startNewGame();
                boardPanel.removeAll();
            } else if (game.isGameOver()) {
                JOptionPane.showMessageDialog(frame, "Game over! The word was: " + game.getGuesses().get(game.getGuesses().size() - 1));
                game.startNewGame();
                boardPanel.removeAll();
            }
        });

        frame.setVisible(true);
    }
}
