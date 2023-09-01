package gameLogic;

import exceptions.CityNameException;
import exceptions.CityNameValidator;
import gui.ExitWindow;
import gui.MainWindow;
import highscores.HighScoresProcessor;
import highscores.ScoreEntry;

import javax.swing.*;
import java.util.*;
import java.util.stream.Collectors;

public class GameLogic {
    private ResourceBundle resourceBundle;
    private String userName;
    private MainWindow mainWindow;
    private int countUserStep;
    private String lastComputerCity;
    // private String inputCity;
    private Set<String> cities;
    private List<String> usedCities;


    private final String exitWord;
    private final String errorFirstLetter;
    private final String errorCity;
    private final String errorTitle;
    private final String repeatCity;
    private final String lastLetterMessageTitle;
    private final String lastLetterException;

    public GameLogic(ResourceBundle resourceBundle, String userName) {
        this.resourceBundle = resourceBundle;
        this.userName = userName;
        countUserStep = 0;
        lastComputerCity = null;
        usedCities = new ArrayList<>();

        exitWord = resourceBundle.getString("exitWord");
        errorTitle = resourceBundle.getString("errorTitle");
        errorFirstLetter = resourceBundle.getString("errorFirstLetter");
        errorCity = resourceBundle.getString("errorCity");
        repeatCity = resourceBundle.getString("repeatCity");
        lastLetterMessageTitle = resourceBundle.getString("lastLetterMessageTitle");
        lastLetterException = resourceBundle.getString("lastLetterException");

    }

    public int getCountUserStep() {
        return countUserStep;
    }

    public void setMainWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    public void setCities(Set<String> cities) {
        this.cities = cities;
    }

    public void endGame(int countUserStep, boolean isUserWin) {
        //record the result of the game in the table of the best results
        ScoreEntry newScoreEntry = new ScoreEntry(userName, countUserStep);
        HighScoresProcessor highScoresProcessor = new HighScoresProcessor();
        highScoresProcessor.processNewEntry(newScoreEntry);
        //open exit window
        ExitWindow exitWindow = new ExitWindow(resourceBundle, mainWindow.getFrame(), isUserWin,
                new ScoreEntry(userName, countUserStep));
    }

    public boolean checks(String inputCity, JLabel computerLabel) {
        //check the exit word
        if (inputCity.equalsIgnoreCase(exitWord)) {
            endGame(countUserStep, false);
            return false;
        }

        //check - repeat city
        if (usedCities.contains(inputCity)) {
            JOptionPane.showMessageDialog(mainWindow, repeatCity, errorTitle, JOptionPane.WARNING_MESSAGE);
            return false;
        }

        //check - correct last letter
        if (lastComputerCity != null) {
            if (!new CityNameValidator(cities).isFirstLetterCorrect(lastComputerCity, inputCity)) {
                JOptionPane.showMessageDialog(mainWindow, errorFirstLetter, errorTitle, JOptionPane.WARNING_MESSAGE);
                return false;
            }
        }

        //check - if city in database
        if (!new CityNameValidator(cities).isCityInDatabase(inputCity)) {
            JOptionPane.showMessageDialog(mainWindow, errorCity, errorTitle, JOptionPane.WARNING_MESSAGE);
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
        inputCity = processCityNameException(mainWindow, inputCity);


        char lastChar = inputCity.charAt(inputCity.length() - 1);
        if ((lastComputerCity = getRandomCity(lastChar, mainWindow)).equals("computerLostOut")) {
            endGame(countUserStep, true);
        }

        //замінити на вивід міста в основне вікно
        printComputerAnswer(computerLabel);

        usedCities.add(lastComputerCity);
        cities.remove(lastComputerCity);
        //check city name exception
        lastComputerCity = processCityNameException(mainWindow, lastComputerCity);
    }

    private void printComputerAnswer(JLabel computerLabel) {
        String oldAnswer = computerLabel.getText();
        String[] parts = oldAnswer.split(" ");

        String newAnswer = parts[0].trim() + " " + Character.toUpperCase(lastComputerCity.charAt(0))
                + lastComputerCity.substring(1).toLowerCase();
        computerLabel.setText(newAnswer);
    }

    private String processCityNameException(JFrame mainWindow, String cityName) {
        try {
            new CityNameValidator(cities).checkLastLetterException(cityName);
        } catch (CityNameException e) {
            if (cityName.length() >= 2) {
                cityName = cityName.substring(0, cityName.length() - 1);
                JOptionPane.showMessageDialog(mainWindow, lastLetterException, lastLetterMessageTitle, JOptionPane.INFORMATION_MESSAGE);
            }
        }
        return cityName;
    }

    private String getRandomCity(char firstChar, JFrame mainWindow) {
        List<String> suitableCities = new ArrayList<>();
        suitableCities = cities.stream().filter((city) -> city.charAt(0) == firstChar).collect(Collectors.toList());

        if (suitableCities.size() == 0) {
            return "computerLostOut";
        }

        Random random = new Random();
        int index = random.nextInt(suitableCities.size());
        return suitableCities.get(index);
    }
}
