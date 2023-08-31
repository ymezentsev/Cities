package gui;

import highscores.ScoreEntry;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ResourceBundle;

//This window shows on "end game" program state.

public class ExitWindow {
    private final ResourceBundle resourceBundle;
    private final String btnExit;
    private final String btnNewGame;
    private final String lblResultWin;
    private final String lblResultLoose;
    private final String titleResult;
    private final String lblScore;

    public ExitWindow(ResourceBundle resourceBundle, JFrame parentFrame, boolean win, ScoreEntry newScoreEntry) {
        //current game winner
        this.resourceBundle = resourceBundle;
        this.btnExit = resourceBundle.getString("btnExit");
        this.btnNewGame = resourceBundle.getString("btnNewGame");
        this.lblResultWin = resourceBundle.getString("lblResultWin");
        this.lblResultLoose = resourceBundle.getString("lblResultLoose");
        this.titleResult = resourceBundle.getString("titleResult");
        this.lblScore = resourceBundle.getString("lblScore");
        showModalDialog(parentFrame, win, newScoreEntry);
    }

    private void showModalDialog(JFrame parentFrame, boolean win, ScoreEntry newScoreEntry) {
        JDialog dialog = new JDialog(parentFrame, titleResult, true);
        dialog.setIconImage(Toolkit.getDefaultToolkit()
                .getImage(new File("src/main/resources/images/mainIcon.jpg").toString()));
        dialog.setSize(250, 150);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        String lblResult;
        if (win) {
            lblResult = lblResultWin;
        } else {
            lblResult = lblResultLoose;
        }

        JLabel resultMsg = new JLabel(lblResult);
        resultMsg.setHorizontalAlignment(JLabel.CENTER);

        JLabel scoreMsg = new JLabel(lblScore + " " + newScoreEntry.getScore());
        scoreMsg.setHorizontalAlignment(JLabel.CENTER);

        JButton newGameButton = new JButton(btnNewGame);
        newGameButton.addActionListener(e -> {
            dialog.dispose();
            parentFrame.dispose();
            WelcomeWindow welcomeWindow = new WelcomeWindow(resourceBundle, newScoreEntry.getPlayerName());
            welcomeWindow.showWindow();
        });

        JButton exitButton = new JButton(btnExit);
        exitButton.addActionListener(e -> {
            System.exit(0);
        });


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
