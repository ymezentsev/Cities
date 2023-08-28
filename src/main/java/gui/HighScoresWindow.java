package gui;

import highscores.HighScoresProcessor;
import highscores.ScoreEntry;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

//read highscores.txt file that contains previous games results, sorted by higher score
//add result of current game
//Limits HighScore entries by 10.
//Create and show High Scores table
//saves updated high scores in file highscores.txt
public class HighScoresWindow extends JFrame {
    private DefaultTableModel tableModel;
    private final String btnExit;
    private final String columnPlayer;
    private final String columnRank;
    private final String columnScore;
    private final String titleHighScores;

    ScoreEntry newScoreEntry;

    public HighScoresWindow(ResourceBundle resourceBundle, ScoreEntry newScoreEntry) {

        this.btnExit = resourceBundle.getString("btnExit");
        this.columnPlayer = resourceBundle.getString("columnPlayer");
        this.columnRank = resourceBundle.getString("columnRank");
        this.columnScore = resourceBundle.getString("columnScore");
        this.titleHighScores = resourceBundle.getString("titleHighScores");

        this.newScoreEntry = newScoreEntry;
    }
    public void showWindow() {
        setTitle(titleHighScores);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        createGUI();
    }
    private void createGUI() {
        tableModel = new DefaultTableModel(new String[]{columnRank, columnPlayer, columnScore}, 0);
        JTable scoresTable = new JTable(tableModel);
        scoresTable.setFont(new Font("Arial", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(scoresTable);
        getContentPane().add(scrollPane);

        HighScoresProcessor processor = new HighScoresProcessor();
        createTable(processor.processNewEntry(newScoreEntry));

        JButton exitButton = new JButton(btnExit);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(exitButton, BorderLayout.SOUTH);
        exitButton.addActionListener(e -> System.exit(0));
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        setVisible(true);
    }
    private void createTable(ArrayList<ScoreEntry> scores) {
        tableModel.setRowCount(0);
        for (int i = 0; i < scores.size(); i++) {
            ScoreEntry entry = scores.get(i);
            tableModel.addRow(new Object[]{i + 1, entry.getPlayerName(), entry.getScore()});
        }
    }
}