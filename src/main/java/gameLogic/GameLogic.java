package gameLogic;

import gui.ExitWindow;
import highscores.HighScoresProcessor;
import highscores.ScoreEntry;

import javax.swing.*;
import java.util.ResourceBundle;

public class GameLogic {
    private ResourceBundle resourceBundle;
    private String userName;
    private JFrame mainWindow;
    private int countUserStep;

    public GameLogic(ResourceBundle resourceBundle, String userName) {
        this.resourceBundle = resourceBundle;
        this.userName = userName;
        countUserStep = 0;
    }

    public int getCountUserStep() {
        return countUserStep;
    }

    public void setMainWindow(JFrame mainWindow) {
        this.mainWindow = mainWindow;
    }

    public void endGame(int countUserStep, boolean isUserWin) {
        //record the result of the game in the table of the best results
        ScoreEntry newScoreEntry = new ScoreEntry(userName, countUserStep);
        HighScoresProcessor highScoresProcessor = new HighScoresProcessor();
        highScoresProcessor.processNewEntry(newScoreEntry);
        //open exit window
        ExitWindow exitWindow = new ExitWindow(resourceBundle, mainWindow, isUserWin,
                new ScoreEntry(userName, countUserStep));
    }
}
