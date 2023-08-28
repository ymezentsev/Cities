package databaseReader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;


public class FileReaderImpl implements Reader {

    @Override
    public Set<String> readCitiesToList(String source) {
        Set<String> cities = new TreeSet<>();

        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(source)))) {
            while (scanner.hasNext()) {
                cities.add(scanner.next().trim().toLowerCase());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return cities;
    }
}
