package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

//read highscores.txt file that contains previous games results, sorted by higher score
//add result of current game
//Limits HighScore entries by 10.
//Create and show High Scores table
//saves updated high scores in file highscores.txt
public class HighScoresWindow extends JFrame {
    private final DefaultTableModel tableModel;
    ScoreEntry newScoreEntry;

    public HighScoresWindow(ResourceBundle resourceBundle, ScoreEntry newScoreEntry) {

        String btnExit = resourceBundle.getString("btnExit");
        String columnPlayer = resourceBundle.getString("columnPlayer");
        String columnRank = resourceBundle.getString("columnRank");
        String columnScore = resourceBundle.getString("columnScore");
        String titleHighScores = resourceBundle.getString("titleHighScores");

        this.newScoreEntry = newScoreEntry;

        setTitle(titleHighScores);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        tableModel = new DefaultTableModel(new String[]{columnRank, columnPlayer, columnScore}, 0);
        JTable scoresTable = new JTable(tableModel);
        scoresTable.setFont(new Font("Arial", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(scoresTable);
        getContentPane().add(scrollPane);

        updateScoresTable(newScoreEntry);

        JButton exitButton = new JButton(btnExit);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(exitButton, BorderLayout.SOUTH);
        exitButton.addActionListener(e -> System.exit(0));
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        setVisible(true);
    }


    private void updateScoresTable(ScoreEntry newScoreEntry) {
        ArrayList<ScoreEntry> scores = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/highscores.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length == 2) {
                    String playerName = parts[0].trim();
                    int score = Integer.parseInt(parts[1].trim());
                    scores.add(new ScoreEntry(playerName, score));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        scores.add(new ScoreEntry(newScoreEntry.getPlayerName(), newScoreEntry.getScore()));

        Collections.sort(scores);
        if (scores.size() > 10){
            scores.remove(10);
        }


        tableModel.setRowCount(0);
        for (int i = 0; i < scores.size(); i++) {
            ScoreEntry entry = scores.get(i);
            tableModel.addRow(new Object[]{i+1, entry.getPlayerName(), entry.getScore()});
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/highscores.txt"))) {
            for (ScoreEntry score : scores) {
                String scoreLine = score.getPlayerName() + " " + score.getScore();
                writer.write(scoreLine);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}