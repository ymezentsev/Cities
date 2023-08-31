package gameLogic;

import databaseReader.FileReaderImpl;
import databaseReader.Reader;
import difficultyLevels.DifficultyLevel;
import difficultyLevels.DifficultyLevelTimer;
import exceptions.CityNameException;
import exceptions.CityNameValidator;
import gui.ExitWindow;
import gui.HighScoresWindow;
import gui.MainWindow;
import gui.WelcomeWindow;
import highscores.HighScoresProcessor;
import highscores.ScoreEntry;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;

public class GameCore {
    private ResourceBundle resourceBundle;
    private String userName;
    private String fileName;
    private DifficultyLevel difficultyLevel;
    private DifficultyLevelTimer difficultyLevelTimer;
    private boolean timeOut;
    private GameLogic gameLogic;
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

        //замінити на створення головного вікна
       // MainWindow mainWindow = new MainWindow(resourceBundle);

        this.gameLogic = new GameLogic(resourceBundle, userName);
       //this.difficultyLevelTimer = new DifficultyLevelTimer(difficultyLevel);

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

       // Thread thread1 = new Thread(() -> difficultyLevelTimer.isTimeOut(mainWindow.getTimerLabel()));
       // thread1.start();


       // gameLoop(mainWindow);
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
            if (inputCity.equalsIgnoreCase(exitWord) || timeOut) {
              //  gameLogic.endGame(countUserStep, false);
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
            if ((lastComputerCity = getRandomCity(lastChar, mainWindow)).equals("computerLostOut")) {
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
        suitableCities = cities.stream().filter((city) -> city.charAt(0) == firstChar).collect(Collectors.toList());
      /*  for (String city : cities) {
            if (city.charAt(0) == firstChar) {
                suitableCities.add(city);
            }
        }*/

        if (suitableCities.size() == 0) {
            return "computerLostOut";
        }

        Random random = new Random();
        int index = random.nextInt(suitableCities.size());
        return suitableCities.get(index);
    }
}