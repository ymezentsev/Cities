package gameLogic;

import databaseReader.FileReaderImpl;
import databaseReader.Reader;

import java.util.*;

public class GameCore {

    //значення залежні від локалі
    private String fileName = "src/main/resources/UA_cities.txt";
    private String exitWord = "здаюсь";
    private String errorFirstLetter = "Ви ввели місто з неправильною першою буквою";
    private String errorCity = "Такого міста не існує. Введіть інше місто";
    private String repeatCity = "Таке місто вже було. Введіть інше місто";
    //


    static List<String> cities;
    private Reader reader;
    private Validator validator;
    private int countUserStep;
    private int countComputerStep;


    public GameCore() {
        reader = new FileReaderImpl();
        cities = reader.readCitiesToList(fileName);
        validator = new Validator();
        countUserStep = 0;
        countComputerStep = 0;

        gameLoop();
    }

    private void gameLoop() {
        String lastComputerCity = null;
        List<String> usedCities = new ArrayList<>();


        //Scanner для перевірки, згодом замінити на результат роботи методу обробки кнопки "Зробити хід"
        Scanner scanner = new Scanner(System.in);

        while (true) {
            //Scanner для перевірки, згодом замінити на результат роботи методу обробки кнопки "Зробити хід"
            String inputCity = scanner.nextLine().toLowerCase().trim();

            if (inputCity.equalsIgnoreCase(exitWord)) {
                //замінити на вивід вікна закінчення гри, програш юзера
                System.exit(0);
            }

            //перевірки
            if(usedCities.contains(inputCity)){
                //замінити на вивід модального вікна з помилкою, текст підтягувати в залежності від мови
                System.out.println(repeatCity);
                continue;
            }

            if (lastComputerCity != null) {
                if (!validator.isFirstLetterCorrect(lastComputerCity, inputCity)) {
                    //замінити на вивід модального вікна з помилкою, текст підтягувати в залежності від мови
                    System.out.println(errorFirstLetter);
                    continue;
                }
            }

            if (!validator.isCityCorrect(inputCity)) {
                //замінити на вивід модального вікна з помилкою, текст підтягувати в залежності від мови
                System.out.println(errorCity);
                continue;
            }

            //додати перевірки міста, введеного користувачем
            //у разі неправильного ввода виводити модальне вікно з помилкою, текст підтягувати в залежності від мови

            countUserStep++;
            usedCities.add(inputCity);
            cities.remove(inputCity);


            char lastChar = inputCity.charAt(inputCity.length() - 1);
            lastComputerCity = getRandomCity(lastChar);

            //замінити на вивід міста в основне вікно
            System.out.println(Character.toUpperCase(lastComputerCity.charAt(0))
                    + lastComputerCity.substring(1).toLowerCase());

            countComputerStep++;
            usedCities.add(lastComputerCity);
            cities.remove(lastComputerCity);
        }
    }

    private String getRandomCity(char firstChar) {
        List<String> suitableCities = new ArrayList<>();
        for (String city : cities) {
            if (city.charAt(0) == firstChar){
                suitableCities.add(city);
            }
        }

        if (suitableCities.size() == 0) {
            //замінити на вивід вікна закінчення гри, програш ПК
            System.out.println("You win");
            System.exit(0);
        }

        Random random = new Random();
        int index = random.nextInt(suitableCities.size());
        return suitableCities.get(index);
    }
}
