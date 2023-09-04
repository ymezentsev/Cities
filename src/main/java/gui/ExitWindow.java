package gui;

import gui.design.GradientPanel;
import highscores.ScoreEntry;
import languages.LanguageSettingsDto;

import javax.swing.*;
import java.awt.*;
import java.io.File;

//This window shows on "end game" program state.
public class ExitWindow {
    private final LanguageSettingsDto languageSettingsDto;

    public ExitWindow(LanguageSettingsDto languageSettingsDto, JFrame parentFrame, boolean win, ScoreEntry newScoreEntry) {
        this.languageSettingsDto = languageSettingsDto;
        showModalDialog(parentFrame, win, newScoreEntry);
    }

    private void showModalDialog(JFrame parentFrame, boolean win, ScoreEntry newScoreEntry) {
        JDialog dialog = new JDialog(parentFrame, languageSettingsDto.getTitleResult(), true);
        dialog.setIconImage(Toolkit.getDefaultToolkit()
                .getImage(new File("src/main/resources/images/mainIcon.jpg").toString()));
        dialog.setSize(250, 150);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        String lblResult;
        if (win) {
            lblResult = languageSettingsDto.getLblResultWin();
        } else {
            lblResult = languageSettingsDto.getLblResultLoose();
        }

        JLabel resultMsg = new JLabel(lblResult);
        resultMsg.setHorizontalAlignment(JLabel.CENTER);

        JLabel scoreMsg = new JLabel(languageSettingsDto.getLblScore() + " " + newScoreEntry.getScore());
        scoreMsg.setHorizontalAlignment(JLabel.CENTER);

        JButton newGameButton = new JButton(languageSettingsDto.getBtnNewGame());
        newGameButton.addActionListener(e -> {
            dialog.dispose();
            parentFrame.dispose();
            WelcomeWindow welcomeWindow = new WelcomeWindow(languageSettingsDto, newScoreEntry.getPlayerName());
            welcomeWindow.showWindow();
        });

        JButton exitButton = new JButton(languageSettingsDto.getBtnExit());
        exitButton.addActionListener(e -> System.exit(0));

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(newGameButton);
        buttonPanel.add(exitButton);
        buttonPanel.setOpaque(false);

        GradientPanel contentPanel = new GradientPanel();
        contentPanel.setLayout(new GridLayout(3,1));
        contentPanel.setBackgroundColor();

        contentPanel.add(resultMsg);
        contentPanel.add(scoreMsg);
        contentPanel.add(buttonPanel);

        dialog.getContentPane().add(contentPanel);
        dialog.setLocationRelativeTo(parentFrame);
        dialog.setVisible(true);
    }
}
