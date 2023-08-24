package gameLogic;

import databaseReader.FileReaderImpl;
import databaseReader.Reader;

import java.util.List;

public class GameCore {
    private List<String> cities;
    private Reader reader;

    //пока захоркоджена назва файлу, потрібно підтягувати в залежності від вибору мови
    private String fileName = "src/main/resources/UA_cities.txt";

    public GameCore() {
        reader = new FileReaderImpl();
        cities = reader.readCitiesToList(fileName);

        //виведення змісту списку для перевірки роботоздатності, згодом прибрати
        for (String city : cities) {
            System.out.println(city);
        }
    }
}
