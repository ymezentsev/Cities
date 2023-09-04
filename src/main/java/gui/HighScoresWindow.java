package gui;

import gui.design.GradientPanel;
import highscores.HighScoresProcessor;
import highscores.ScoreEntry;
import languages.LanguageSettingsDto;
import lombok.RequiredArgsConstructor;

import java.awt.Color;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

import static gui.design.GradientPanel.*;

//read highscores.txt file that contains previous games results, sorted by higher score
//Limits HighScore entries by 10.
@RequiredArgsConstructor
public class HighScoresWindow extends JFrame {
    private DefaultTableModel tableModel;
    private final LanguageSettingsDto languageSettingsDto;

    public void showWindow() {
        setTitle(languageSettingsDto.getTitleHighScores());
        setIconImage(Toolkit.getDefaultToolkit()
                .getImage(new File("src/main/resources/images/mainIcon.jpg").toString()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        createGUI();
    }

    private void createGUI() {
        GradientPanel mainPanel = new GradientPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackgroundColor();

        tableModel = new DefaultTableModel(new String[]{languageSettingsDto.getColumnRank(),
                languageSettingsDto.getColumnPlayer(), languageSettingsDto.getColumnScore()}, 0);
        JTable scoresTable = new JTable(tableModel);

        scoresTable.setDefaultEditor(Object.class, null); // Встановлюємо тільки читання таблиці

        scoresTable.setBackground(new Color(0, 0, 0, 0));
        scoresTable.setOpaque(false);
        scoresTable.getTableHeader().setOpaque(false);

        scoresTable.setFont(new Font("Arial", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(scoresTable);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        mainPanel.add(scrollPane, BorderLayout.CENTER);

        HighScoresProcessor processor = new HighScoresProcessor();
        createTable(processor.readScoresFile());

        JButton exitButton = new JButton(languageSettingsDto.getBtnExit());
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(MAIN_COLOR_VALUE_OF_RED, MAIN_COLOR_VALUE_OF_GREEN, MAIN_COLOR_VALUE_OF_BLUE));
        buttonPanel.add(exitButton, BorderLayout.SOUTH);
        exitButton.addActionListener(e -> this.dispose());

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        getContentPane().add(mainPanel);

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