package gameLogic;

import databaseReader.FileReaderImpl;
import databaseReader.Reader;
import difficultyLevels.DifficultyLevel;
import difficultyLevels.DifficultyLevelTimer;
import gui.MainWindow;

import javax.swing.*;
import java.util.ResourceBundle;
import java.util.Set;

public class GameCore {
    private ResourceBundle resourceBundle;
    private String userName;
    private DifficultyLevel difficultyLevel;
    private DifficultyLevelTimer difficultyLevelTimer;
    private GameLogic gameLogic;
    private MainWindow mainWindow;
    private Reader reader;
    private Set<String> cities;
    private String fileName;

    public GameCore(ResourceBundle resourceBundle, String userName, DifficultyLevel difficultyLevel) {
        this.resourceBundle = resourceBundle;
        this.userName = userName;
        this.difficultyLevel = difficultyLevel;
        this.fileName = resourceBundle.getString("fileName");
   }

    public void startGame() {
        gameLogic = new GameLogic(resourceBundle, userName);
        this.difficultyLevelTimer = new DifficultyLevelTimer(difficultyLevel, gameLogic);

        mainWindow = new MainWindow(resourceBundle, difficultyLevelTimer, gameLogic);
        gameLogic.setMainWindow(mainWindow);

        //fill cities database
        reader = new FileReaderImpl();
        cities = reader.readCitiesToList(fileName);
        gameLogic.setCities(cities);

        startThreadWithTimer(mainWindow.getTimerLabel());
  }

    private void startThreadWithTimer(JLabel timerLabel) {
        Thread thread1 = new Thread(() -> difficultyLevelTimer.timer(mainWindow.getTimerLabel()));
        thread1.start();
    }
}
