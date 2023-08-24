package databaseReader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReaderImpl implements Reader {

    @Override
    public List<String> readCitiesToList(String source) {
        List<String> cities = new ArrayList<>();

        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(source)))) {
            while (scanner.hasNext()) {
                cities.add(scanner.next());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return cities;
    }
}
