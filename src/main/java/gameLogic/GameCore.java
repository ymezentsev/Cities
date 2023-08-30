package gameLogic;

import difficultyLevels.DifficultyLevel;
import exceptions.CityNameException;
import exceptions.CityNameValidator;
import databaseReader.FileReaderImpl;
import databaseReader.Reader;
import gui.ExitWindow;
import highscores.HighScoresProcessor;
import highscores.ScoreEntry;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class GameCore {
    private ResourceBundle resourceBundle;
    private String userName;
    private String fileName;
    private DifficultyLevel difficultyLevel;
    private final String exitWord;
    private final String errorFirstLetter;
    private final String errorCity;
    private final String errorTitle;
    private final String repeatCity;
    private final String lastLetterMessageTitle;
    private final String lastLetterException;
    private Set<String> cities;
    private final Reader reader;
    private int countUserStep;

    public GameCore(ResourceBundle resourceBundle, String userName, DifficultyLevel difficultyLevel) {
        this.resourceBundle = resourceBundle;
        this.userName = userName;
        this.difficultyLevel = difficultyLevel;
        fileName = resourceBundle.getString("fileName");
        exitWord = resourceBundle.getString("exitWord");
        errorTitle = resourceBundle.getString("errorTitle");
        errorFirstLetter = resourceBundle.getString("errorFirstLetter");
        errorCity = resourceBundle.getString("errorCity");
        repeatCity = resourceBundle.getString("repeatCity");
        lastLetterMessageTitle = resourceBundle.getString("lastLetterMessageTitle");
        lastLetterException = resourceBundle.getString("lastLetterException");

        reader = new FileReaderImpl();
        cities = reader.readCitiesToList(fileName);
        countUserStep = 0;

        //замінити на створення головного вікна
        JFrame mainWindow = new JFrame("Main Window");
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setPreferredSize(new Dimension(450, 200));
        mainWindow.pack();
        mainWindow.setLocationRelativeTo(null);
        mainWindow.setVisible(true);

        gameLoop(mainWindow);
    }

    private void gameLoop(JFrame mainWindow) {
        String lastComputerCity = null;
        List<String> usedCities = new ArrayList<>();

        //Scanner для перевірки, згодом замінити на результат роботи методу обробки кнопки "Зробити хід"
        Scanner scanner = new Scanner(System.in);

        while (true) {
            //Scanner для перевірки, згодом замінити на результат роботи методу обробки кнопки "Зробити хід"
            String inputCity = scanner.nextLine().toLowerCase().trim();

            //check the exit word
            if (inputCity.equalsIgnoreCase(exitWord)) {
                //record the result of the game in the table of the best results
                ScoreEntry newScoreEntry = new ScoreEntry(userName, countUserStep);
                HighScoresProcessor highScoresProcessor = new HighScoresProcessor();
                highScoresProcessor.processNewEntry(newScoreEntry);
                //open exit window
                ExitWindow exitWindow = new ExitWindow(resourceBundle, mainWindow, false,
                        new ScoreEntry(userName, countUserStep));
                break;
            }

            //check - repeat city
            if (usedCities.contains(inputCity)) {
                JOptionPane.showMessageDialog(mainWindow, repeatCity, errorTitle, JOptionPane.WARNING_MESSAGE);
                //для тесту потім прибрати
                System.out.println(repeatCity);
                continue;
            }

            //check - correct last letter
            if (lastComputerCity != null) {
                if (!new CityNameValidator(cities).isFirstLetterCorrect(lastComputerCity, inputCity)) {
                    JOptionPane.showMessageDialog(mainWindow, errorFirstLetter, errorTitle, JOptionPane.WARNING_MESSAGE);
                    //для тесту потім прибрати
                    System.out.println(errorFirstLetter);
                    continue;
                }
            }

            //check - if city in database
            if (!new CityNameValidator(cities).isCityInDatabase(inputCity)) {
                JOptionPane.showMessageDialog(mainWindow, errorCity, errorTitle, JOptionPane.WARNING_MESSAGE);
                //для тесту потім прибрати
                System.out.println(errorCity);
                continue;
            }

            countUserStep++;
            usedCities.add(inputCity);
            cities.remove(inputCity);
            //check city name exception
            inputCity = processCityNameException(mainWindow, inputCity);


            char lastChar = inputCity.charAt(inputCity.length() - 1);
            if((lastComputerCity = getRandomCity(lastChar, mainWindow)).equals("computerLostOut")){
                //record the result of the game in the table of the best results
                ScoreEntry newScoreEntry = new ScoreEntry(userName, countUserStep);
                HighScoresProcessor highScoresProcessor = new HighScoresProcessor();
                highScoresProcessor.processNewEntry(newScoreEntry);
                //open exit window
                ExitWindow exitWindow = new ExitWindow(resourceBundle, mainWindow, true,
                        new ScoreEntry(userName, countUserStep));
                //для тесту потім прибрати
                System.out.println("You win");
                break;
            }

            //замінити на вивід міста в основне вікно
            System.out.println(Character.toUpperCase(lastComputerCity.charAt(0))
                    + lastComputerCity.substring(1).toLowerCase());

            usedCities.add(lastComputerCity);
            cities.remove(lastComputerCity);
            //check city name exception
            lastComputerCity = processCityNameException(mainWindow, lastComputerCity);
        }
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
        for (String city : cities) {
            if (city.charAt(0) == firstChar) {
                suitableCities.add(city);
            }
        }

        if (suitableCities.size() == 0) {
            return "computerLostOut";
        }

        Random random = new Random();
        int index = random.nextInt(suitableCities.size());
        return suitableCities.get(index);
    }
}
