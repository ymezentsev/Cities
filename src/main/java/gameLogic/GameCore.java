package gameLogic;

import databaseReader.FileReaderImpl;
import databaseReader.Reader;
import difficultyLevels.DifficultyLevel;
import difficultyLevels.DifficultyLevelTimer;
import gui.MainWindow;
import languages.LanguageSettingsDto;

import java.util.Set;

public class GameCore {
    private final LanguageSettingsDto languageSettingsDto;
    private final String userName;
    private final DifficultyLevel difficultyLevel;
    private DifficultyLevelTimer difficultyLevelTimer;
    private MainWindow mainWindow;

    public GameCore(LanguageSettingsDto languageSettingsDto, String userName, DifficultyLevel difficultyLevel) {
        this.languageSettingsDto = languageSettingsDto;
        this.userName = userName;
        this.difficultyLevel = difficultyLevel;
    }

    public void startGame() {
        //fill cities database
        Reader reader = new FileReaderImpl();
        Set<String> cities = reader.readCitiesToList(languageSettingsDto.getFileName());

        //set values to gameLogic and mainWindow classes
        GameLogic gameLogic = new GameLogic(languageSettingsDto, userName);
        difficultyLevelTimer = new DifficultyLevelTimer(difficultyLevel, gameLogic);

        mainWindow = new MainWindow(languageSettingsDto, difficultyLevelTimer, gameLogic);
        gameLogic.setMainWindow(mainWindow);
        gameLogic.setDifficultyLevelTimer(difficultyLevelTimer);
        gameLogic.setCities(cities);

        //start thread with difficulty level timer
        startThreadWithTimer();
  }

    private void startThreadWithTimer() {
        Thread thread1 = new Thread(() -> difficultyLevelTimer.timer(mainWindow.getTimerLabel()));
        thread1.start();
    }
}
