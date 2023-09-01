package databaseReader;

import java.util.Set;

//interface for reading data
public interface Reader {
    Set<String> readCitiesToList(String source);
}
