package gui;

import highscores.ScoreEntry;
import languages.LanguageSettingsDAO;

import javax.swing.*;
import java.awt.*;
import java.io.File;

//This window shows on "end game" program state.
public class ExitWindow {
    private final LanguageSettingsDAO languageSettingsDAO;

    public ExitWindow(LanguageSettingsDAO languageSettingsDAO, JFrame parentFrame, boolean win, ScoreEntry newScoreEntry) {
        this.languageSettingsDAO = languageSettingsDAO;
        showModalDialog(parentFrame, win, newScoreEntry);
    }

    private void showModalDialog(JFrame parentFrame, boolean win, ScoreEntry newScoreEntry) {
        JDialog dialog = new JDialog(parentFrame, languageSettingsDAO.getTitleResult(), true);
        dialog.setIconImage(Toolkit.getDefaultToolkit()
                .getImage(new File("src/main/resources/images/mainIcon.jpg").toString()));
        dialog.setSize(250, 150);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        String lblResult;
        if (win) {
            lblResult = languageSettingsDAO.getLblResultWin();
        } else {
            lblResult = languageSettingsDAO.getLblResultLoose();
        }

        JLabel resultMsg = new JLabel(lblResult);
        resultMsg.setHorizontalAlignment(JLabel.CENTER);

        JLabel scoreMsg = new JLabel(languageSettingsDAO.getLblScore() + " " + newScoreEntry.getScore());
        scoreMsg.setHorizontalAlignment(JLabel.CENTER);

        JButton newGameButton = new JButton(languageSettingsDAO.getBtnNewGame());
        newGameButton.addActionListener(e -> {
            dialog.dispose();
            parentFrame.dispose();
            WelcomeWindow welcomeWindow = new WelcomeWindow(languageSettingsDAO, newScoreEntry.getPlayerName());
            welcomeWindow.showWindow();
        });

        JButton exitButton = new JButton(languageSettingsDAO.getBtnExit());
        exitButton.addActionListener(e -> System.exit(0));

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(newGameButton);
        buttonPanel.add(exitButton);

        JPanel contentPanel = new JPanel(new GridLayout(3,1));

        contentPanel.add(resultMsg);
        contentPanel.add(scoreMsg);
        contentPanel.add(buttonPanel);

        dialog.getContentPane().add(contentPanel);
        dialog.setLocationRelativeTo(parentFrame);
        dialog.setVisible(true);
    }
}
