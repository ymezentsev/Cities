package gameLogic;

import databaseReader.FileReaderImpl;
import databaseReader.Reader;
import difficultyLevels.DifficultyLevel;
import difficultyLevels.DifficultyLevelTimer;
import gui.MainWindow;
import languages.LanguageSettingsDAO;

import java.util.Set;

public class GameCore {
    private final LanguageSettingsDAO languageSettingsDAO;
    private final String userName;
    private final DifficultyLevel difficultyLevel;
    private DifficultyLevelTimer difficultyLevelTimer;
    private MainWindow mainWindow;

    public GameCore(LanguageSettingsDAO languageSettingsDAO, String userName, DifficultyLevel difficultyLevel) {
        this.languageSettingsDAO = languageSettingsDAO;
        this.userName = userName;
        this.difficultyLevel = difficultyLevel;
    }

    public void startGame() {
        //set values to gameLogic and mainWindow classes
        GameLogic gameLogic = new GameLogic(languageSettingsDAO, userName);
        difficultyLevelTimer = new DifficultyLevelTimer(difficultyLevel, gameLogic);

        mainWindow = new MainWindow(languageSettingsDAO, difficultyLevelTimer, gameLogic);
        gameLogic.setMainWindow(mainWindow);
        gameLogic.setDifficultyLevelTimer(difficultyLevelTimer);

        //fill cities database
        Reader reader = new FileReaderImpl();
        Set<String> cities = reader.readCitiesToList(languageSettingsDAO.getFileName());
        gameLogic.setCities(cities);

        //start thread with difficulty level timer
        startThreadWithTimer();
  }

    private void startThreadWithTimer() {
        Thread thread1 = new Thread(() -> difficultyLevelTimer.timer(mainWindow.getTimerLabel()));
        thread1.start();
    }
}
