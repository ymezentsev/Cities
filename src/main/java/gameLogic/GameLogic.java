package gameLogic;

import difficultyLevels.DifficultyLevelTimer;
import exceptions.CityNameException;
import exceptions.CityNameValidator;
import gui.ExitWindow;
import gui.MainWindow;
import highscores.HighScoresProcessor;
import highscores.ScoreEntry;
import languages.LanguageSettingsDAO;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.util.*;
import java.util.stream.Collectors;

//logic of the game
public class GameLogic {
    private final LanguageSettingsDAO languageSettingsDAO;
    private final String userName;
    @Setter
    private DifficultyLevelTimer difficultyLevelTimer;
    @Setter
    private Set<String> cities;
    @Setter
    private MainWindow mainWindow;
    @Getter
    private int countUserStep;
    private String lastComputerCity;
    private final List<String> usedCities;

    public GameLogic(LanguageSettingsDAO languageSettingsDAO, String userName) {
        this.languageSettingsDAO = languageSettingsDAO;
        this.userName = userName;
        countUserStep = 0;
        lastComputerCity = null;
        usedCities = new ArrayList<>();
    }

    public void endGame(int countUserStep, boolean isUserWin) {
        //stop difficult level timer
        difficultyLevelTimer.setGameOver(true);
        //record the result of the game in the table of the best results
        ScoreEntry newScoreEntry = new ScoreEntry(userName, countUserStep);
        HighScoresProcessor highScoresProcessor = new HighScoresProcessor();
        highScoresProcessor.processNewEntry(newScoreEntry);
        //open exit window
        new ExitWindow(languageSettingsDAO, mainWindow.getFrame(), isUserWin,
                new ScoreEntry(userName, countUserStep));
    }

    public boolean checks(String inputCity, JLabel computerLabel) {
        //check the exit word
        if (inputCity.equalsIgnoreCase(languageSettingsDAO.getExitWord())) {
            endGame(countUserStep, false);
            return false;
        }

        //check - repeat city
        if (usedCities.contains(inputCity)) {
            JOptionPane.showMessageDialog(mainWindow.getFrame(), languageSettingsDAO.getRepeatCity(),
                    languageSettingsDAO.getErrorTitle(), JOptionPane.WARNING_MESSAGE);
            return false;
        }

        //check - correct first letter
        if (lastComputerCity != null) {
            if (!new CityNameValidator(cities).isFirstLetterCorrect(lastComputerCity, inputCity)) {
                JOptionPane.showMessageDialog(mainWindow.getFrame(), languageSettingsDAO.getErrorFirstLetter(),
                        languageSettingsDAO.getErrorTitle(), JOptionPane.WARNING_MESSAGE);
                return false;
            }
        }

        //check - if city in database
        if (!new CityNameValidator(cities).isCityInDatabase(inputCity)) {
            JOptionPane.showMessageDialog(mainWindow.getFrame(), languageSettingsDAO.getErrorCity(),
                    languageSettingsDAO.getErrorTitle(), JOptionPane.WARNING_MESSAGE);
            return false;
        }

        makeMove(inputCity, computerLabel);
        return true;
    }

    private void makeMove(String inputCity, JLabel computerLabel) {
        countUserStep++;
        usedCities.add(inputCity);
        cities.remove(inputCity);
        //check city name exception
        inputCity = processCityNameException(mainWindow.getFrame(), inputCity);

        char lastChar = inputCity.charAt(inputCity.length() - 1);
        if ((lastComputerCity = getRandomCity(lastChar)).equals("computerLostOut")) {
            endGame(countUserStep, true);
        }

        printComputerAnswer(computerLabel);

        usedCities.add(lastComputerCity);
        cities.remove(lastComputerCity);
        //check city name exception
        lastComputerCity = processCityNameException(mainWindow.getFrame(), lastComputerCity);
    }

    private void printComputerAnswer(JLabel computerLabel) {
        String oldAnswer = computerLabel.getText();
        String[] parts = oldAnswer.split(" ");

        String newAnswer = parts[0].trim() + " " + Character.toUpperCase(lastComputerCity.charAt(0))
                + lastComputerCity.substring(1).toLowerCase();
        computerLabel.setText(newAnswer);
    }

    private String processCityNameException(JFrame frame, String cityName) {
        try {
            new CityNameValidator(cities).checkLastLetterException(cityName);
        } catch (CityNameException e) {
            if (cityName.length() >= 2) {
                cityName = cityName.substring(0, cityName.length() - 1);
                JOptionPane.showMessageDialog(frame, languageSettingsDAO.getLastLetterException(),
                        languageSettingsDAO.getLastLetterMessageTitle(), JOptionPane.INFORMATION_MESSAGE);
            }
        }
        return cityName;
    }

    private String getRandomCity(char firstChar) {
        List<String> suitableCities;
        suitableCities = cities.stream().filter((city) -> city.charAt(0) == firstChar).collect(Collectors.toList());

        if (suitableCities.size() == 0) {
            return "computerLostOut";
        }

        Random random = new Random();
        int index = random.nextInt(suitableCities.size());
        return suitableCities.get(index);
    }
}
