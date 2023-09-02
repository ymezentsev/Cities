package gui;

import highscores.HighScoresProcessor;
import highscores.ScoreEntry;
import languages.LanguageSettingsDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

//read highscores.txt file that contains previous games results, sorted by higher score
//Limits HighScore entries by 10.
public class HighScoresWindow extends JFrame {
    private DefaultTableModel tableModel;
    private final LanguageSettingsDAO languageSettingsDAO;

    public HighScoresWindow(LanguageSettingsDAO languageSettingsDAO) {
        this.languageSettingsDAO = languageSettingsDAO;
    }

    public void showWindow() {
        setTitle(languageSettingsDAO.getTitleHighScores());
        setIconImage(Toolkit.getDefaultToolkit()
                .getImage(new File("src/main/resources/images/mainIcon.jpg").toString()));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        createGUI();
    }
    private void createGUI() {
        tableModel = new DefaultTableModel(new String[]{languageSettingsDAO.getColumnRank(),
                languageSettingsDAO.getColumnPlayer(), languageSettingsDAO.getColumnScore()}, 0);
        JTable scoresTable = new JTable(tableModel);
        scoresTable.setFont(new Font("Arial", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(scoresTable);
        getContentPane().add(scrollPane);

        HighScoresProcessor processor = new HighScoresProcessor();
        createTable(processor.readScoresFile());

        JButton exitButton = new JButton(languageSettingsDAO.getBtnExit());
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(exitButton, BorderLayout.SOUTH);
        exitButton.addActionListener(e -> this.dispose());
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